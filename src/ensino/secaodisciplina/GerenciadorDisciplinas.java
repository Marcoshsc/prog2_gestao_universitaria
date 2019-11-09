package ensino.secaodisciplina;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import pessoas.classealuno.Aluno;

public class GerenciadorDisciplinas {

    private static final String PATH_DISC_BASE = "src\\arquivos\\registrosDisciplinas.txt";
    private static final String PATH_DISC_APLICADA = "src\\arquivos\\registrosDisciplinasVigentes.txt";
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

    public void adicionaDisciplinaVigente(DisciplinaAplicada disciplina) {
        GerenciadorDisciplinas.disciplinasVigentes.add(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplinaVigente();
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

    public void excluirDisciplinaVigente(DisciplinaAplicada disciplina) {
        GerenciadorDisciplinas.disciplinasVigentes.remove(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplinaVigente();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    public static void atualizaBancoDisciplina() throws Exception {
        Utilitario.atualizaBanco(GerenciadorDisciplinas.disciplinasCadastradas.toArray(), GerenciadorDisciplinas.PATH_DISC_BASE);
    }

    public static void atualizaBancoDisciplinaVigente() throws Exception {
        Utilitario.atualizaBanco(GerenciadorDisciplinas.disciplinasVigentes.toArray(), GerenciadorDisciplinas.PATH_DISC_APLICADA);
    }

    public static Disciplina pesquisaDisciplinaCodigo(String codigo) {
		if(GerenciadorDisciplinas.disciplinasCadastradas.size() > 0) {
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

    public static DisciplinaAplicada pesquisaDisciplinaVigenteCodigo(String codigo) {
        if(GerenciadorDisciplinas.disciplinasVigentes.size() > 0) {
            for(DisciplinaAplicada i: GerenciadorDisciplinas.disciplinasVigentes) {
                if(i.getCodigoVigente().equals(codigo)) {
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

    private void inicializaDisciplinaVigente() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorDisciplinas.PATH_DISC_APLICADA);
        if(objetos == null)
            return;
        if(objetos.length > 0) {
            if(objetos[0] != "") {
                for(String i: objetos) {
                    DisciplinaAplicada a = DisciplinaAplicada.fromStorageString(i);
                    GerenciadorDisciplinas.disciplinasVigentes.add(a);
                }
            }
        }
    }

    public void inicializa() {
        try {
            inicializaDisciplina();
            inicializaDisciplinaVigente();
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

    public String[] getCodigoDisciplinas() {
        if(disciplinasCadastradas.size() == 0)
            return null;
        else {
            String[] disc = new String[disciplinasCadastradas.size()];
            for(int i = 0; i < disciplinasCadastradas.size(); i++) {
                disc[i] = disciplinasCadastradas.get(i).getCodigo();
            }
            return disc;
        }
    }

    public TableModel getTableFromArray(ArrayList<Aluno> arr, DisciplinaAplicada disc) {
        String[] header = {
                "Nome", "Curso", "CPF", "Matrícula", "Notas"
        };
        String[][] data;
        if(arr.size() == 0 && disc == null) {
            data = null;
        }
        else {
            data = new String[arr.size()][5];
            for(int i = 0; i < arr.size(); i++) {
                data[i] = arr.get(i).getInfoBasicasArray();
                data[i][4] = (disc != null && disc.getArrayListAlunosMatriculados().contains(arr.get(i)))
                        ? Float.toString(disc.pesquisaAluno(arr.get(i).getCpf()).getNota()) : "0";
            }
        }
        return new DefaultTableModel(data, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4;
            }

        };

    }

    public TableModel getDisciplinasTable() {
		String[] header = {
			"Codigo", "Nome", "Carga Horária", "Maximo Faltas"
		};
		String[][] data;
		if(GerenciadorDisciplinas.disciplinasCadastradas.size() > 0) {
			data = new String[GerenciadorDisciplinas.disciplinasCadastradas.size()][4];
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
		existe = disciplinasProcuradas != null;
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