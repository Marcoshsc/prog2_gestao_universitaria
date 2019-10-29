package ensino.secaodisciplina;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;

public class GerenciadorDisciplinas {

    private static String PATH_DISC_BASE = "src/arquivos/registrosDisciplinas.txt";
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
					Disciplina a = Disciplina.fromStorageString(i);
					GerenciadorDisciplinas.disciplinasCadastradas.add(a);
					GerenciadorCursos.pesquisaCursoCodigo(a.getCodigoCurso()).adicionaDisciplina(a);;
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

    public ArrayList<Disciplina> pesquisarDisciplinaCurso(Curso curso) {
        ArrayList<Disciplina> procurados = new ArrayList<Disciplina>();
        // RESOLVER ISSO AQUI PQ TEM QUE PEGAR DO CURSO
//        if(curso.getDisciplinasRelacionadas().size() == 0)
//            return null;
//        for(Disciplina i: curso.getDisciplinasRelacionadas()) {
//            procurados.add(i);
//        }
        return (curso.getDisciplinasRelacionadas().size() == 0) ? null : curso.getDisciplinasRelacionadas();
    }

    public TableModel getDisciplinasTable() {
		String[] header = {
			"Codigo", "Nome", "Carga Horária", "Maximo Faltas"
		};
		String[][] data;
		if(GerenciadorDisciplinas.disciplinasCadastradas.size() > 0) {
			data = new String[GerenciadorDisciplinas.disciplinasCadastradas.size()][7];
			for(int i = 0; i < GerenciadorDisciplinas.disciplinasCadastradas.size(); i++) {
				data[i] = GerenciadorDisciplinas.disciplinasCadastradas.get(i).getInfoBasicasArray();
			}
		}
		else {
			data = null;
		}
		return new DefaultTableModel(data, header) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
    }
    
    public TableModel getDisciplinasTable(Curso curso) {
		String[] header = {
			"Codigo", "Nome", "Carga Horária", "Maximo Faltas"
		};
		boolean existe;
		ArrayList<Disciplina> disciplinasProcuradas = this.pesquisarDisciplinaCurso(curso);
		existe = (disciplinasProcuradas != null) ? true : false;
		String[][] data;
		if(existe) {
            data = new String[disciplinasProcuradas.size()][7];
            for(int i = 0; i < disciplinasProcuradas.size(); i++) {
                data[i] = disciplinasProcuradas.get(i).getInfoBasicasArray();
            }
		}
		else 
			data = null;
		return new DefaultTableModel(data, header) {

			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}

		};
    }

}