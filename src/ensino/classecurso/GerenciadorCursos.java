package ensino.classecurso;

import java.util.ArrayList;

import complementares.Utilitario;
import contratos.OperacoesBasicas;

public class GerenciadorCursos implements OperacoesBasicas {

    private static ArrayList<Curso> cursosCadastrados = new ArrayList<Curso>();
    private static final String PATH = "src\\arquivos\\registrosCursos.txt";

    @Override
    public void adiciona(Object objeto) {
        GerenciadorCursos.cursosCadastrados.add((Curso)objeto);
		try {
			this.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Cursos");
        }
        this.atualizaBanco();
    }

    private void atualizaBanco() {
        Utilitario.atualizaBanco(GerenciadorCursos.cursosCadastrados.toArray(), GerenciadorCursos.PATH);

    }

    @Override
    public void imprimeTodos() {
        // TODO Auto-generated method stub

    }

    @Override
    public void inicializa() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorCursos.PATH);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					GerenciadorCursos.cursosCadastrados.add(Curso.fromStorageString(i));
				}
			}
		}
    }

    public static Curso pesquisaCursoCodigo(String codigo) {
		if(GerenciadorCursos.cursosCadastrados.size() >= 0) {
			for(Curso i: GerenciadorCursos.cursosCadastrados) {
				if(i.getCodigo().equals(codigo)) {
					return i;
				}
			}
			return null;
		}
		else
			return null;
    }
    
    public Curso pesquisaCursoNome(String nome) {
		if(GerenciadorCursos.cursosCadastrados.size() >= 0) {
			for(Curso i: GerenciadorCursos.cursosCadastrados) {
				if(i.getNome().equals(nome)) {
					return i;
				}
			}
			return null;
		}
		else
			return null;
    }
    
    public String[] getNomeCursos() {
		if(GerenciadorCursos.cursosCadastrados.size() == 0) {
			String[] vazia = {""};
			return vazia;
		}
		else {
			String[] nomes = new String[GerenciadorCursos.cursosCadastrados.size()];
			for(int i = 0; i < nomes.length; i++) {
				nomes[i] = GerenciadorCursos.cursosCadastrados.get(i).getNome();
			}
			return nomes;
		}
	}
    
}