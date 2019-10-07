package ensino.secaodisciplina;

import java.util.ArrayList;

import complementares.Utilitario;

public class GerenciadorDisciplinas {

    private static String PATH_DISC_BASE = "src\\arquivos\\registrosDisciplinas.txt";
    private static ArrayList<Disciplina> disciplinasCadastradas = new ArrayList<Disciplina>();
    private static ArrayList<DisciplinaAplicada> disciplinasVigentes = new ArrayList<DisciplinaAplicada>();

    public void adicionaDisciplina(Disciplina disciplina) {
        GerenciadorDisciplinas.disciplinasCadastradas.add(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplina();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public void excluirDisciplina(Disciplina disciplina) {
        GerenciadorDisciplinas.disciplinasCadastradas.remove(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplina();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public static void atualizaBancoDisciplina() throws Exception {
        Utilitario.atualizaBanco(GerenciadorDisciplinas.disciplinasCadastradas.toArray(), GerenciadorDisciplinas.PATH_DISC_BASE);
    }

    public static Disciplina pesquisaDisciplinaCodigo(String codigo) {
		if(GerenciadorDisciplinas.disciplinasCadastradas.size() >= 0) {
			for(Disciplina i: GerenciadorDisciplinas.disciplinasCadastradas) {
				if(i.getCodigo().equals(codigo)) {
					return i;
				}
			}
			return null;
		}
		else
			return null;
    }

    private void inicializaDisciplina() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorDisciplinas.PATH_DISC_BASE);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					GerenciadorDisciplinas.disciplinasCadastradas.add(Disciplina.fromStorageString(i));
				}
			}
		}
	}

    public void inicializa() {
        try {
            inicializaDisciplina();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

}