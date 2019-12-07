package ensino.secaodisciplina;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import pessoas.classeprofessor.GerenciadorProfessor;
import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

public class GerenciadorDisciplinas {

    private static final String PATH_DISC_BASE = "src\\arquivos\\registrosDisciplinas.txt";
    private static final String PATH_DISC_APLICADA = "src\\arquivos\\registrosDisciplinasVigentes.txt";
    private static final String PATH_DISC_CONCLUIDA = "src\\arquivos\\registrosDisciplinasConcluidas.txt";
    private static ArrayList<Disciplina> disciplinasCadastradas = new ArrayList<Disciplina>();
    private static ArrayList<DisciplinaAplicada> disciplinasVigentes = new ArrayList<DisciplinaAplicada>();

    /**
     *
     * @param disciplina: disciplina a ser adicionada
     */
    public void adicionaDisciplina(Disciplina disciplina) {
        GerenciadorDisciplinas.disciplinasCadastradas.add(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplina();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     *
     * @param disciplina: turma a ser adicionado
     */
    public void adicionaDisciplinaVigente(DisciplinaAplicada disciplina) {
        GerenciadorDisciplinas.disciplinasVigentes.add(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplinaVigente();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     *
     * @param disciplina: disciplina a ser excluída
     */
    public void excluirDisciplina(Disciplina disciplina) {
        GerenciadorDisciplinas.disciplinasCadastradas.remove(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplina();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     *
     * @param disciplina: turma a ser excluída
     */
    public void excluirDisciplinaVigente(DisciplinaAplicada disciplina) {
        for(Aluno i: disciplina.getArrayListAlunosMatriculados()) {
            i.getCursando().remove(disciplina.getCodigoVigente());
        }
        Professor prof = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(disciplina.getProfessor());
        if(prof != null) {
            prof.getDisciplinasMinistradas().remove(disciplina.getCodigoVigente());
        }
        else
            System.out.println("Não encontrei o professor.");
        GerenciadorDisciplinas.disciplinasVigentes.remove(disciplina);
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplinaVigente();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     *
     * @throws Exception caso não seja possível atualizar o banco
     */
    public static void atualizaBancoDisciplina() throws Exception {
        Utilitario.atualizaBanco(GerenciadorDisciplinas.disciplinasCadastradas.toArray(), GerenciadorDisciplinas.PATH_DISC_BASE);
    }

    /**
     *
     * @throws Exception caso não seja possível atualizar o banco
     */
    public static void atualizaBancoDisciplinaVigente() throws Exception {
        Utilitario.atualizaBanco(GerenciadorDisciplinas.disciplinasVigentes.toArray(), GerenciadorDisciplinas.PATH_DISC_APLICADA);
    }

    /**
     *
     * @throws Exception caso não seja possível atualizar o banco
     */
    public static void atualizaBancoDisciplinaConcluida() throws Exception {
        ArrayList<DisciplinaConcluida> discs = new ArrayList<>();
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            discs.addAll(i.getDisciplinasConcluidas());
        }
        Utilitario.atualizaBanco(discs.toArray(), GerenciadorDisciplinas.PATH_DISC_CONCLUIDA);
    }

    /**
     *
     * @param codigo: código da disciplina desejada
     * @return Disciplina caso seja encontrada, null caso contrário.
     */
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

    /**
     *
     * @param codigo: código da turma a ser pesquisada
     * @return turma referente ao código caso seja encontrada, null caso contrário.
     */
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

    /**
     *
     * @throws Exception caso não seja possível inicializar as classes do banco.
     */
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

    /**
     *
     * @throws Exception caso não seja possível inicializar as classes do banco.
     */
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

    /**
     *
     * @throws Exception caso não seja possível inicializar as classes do banco.
     */
    private void inicializaDisciplinaConcluida() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorDisciplinas.PATH_DISC_CONCLUIDA);
        if(objetos == null)
            return;
        if(objetos.length > 0) {
            if(objetos[0] != "") {
                for(String i: objetos) {
                    DisciplinaConcluida a = DisciplinaConcluida.fromStorageString(i);
                    Aluno aluno = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(a.getCpfAluno());
                    aluno.adicionaDisciplinaConcluida(a);
                }
            }
        }
    }

    /**
     *
     */
    public void inicializa() {
        try {
            inicializaDisciplina();
            inicializaDisciplinaVigente();
            inicializaDisciplinaConcluida();
        } catch(Exception exc) {
            System.out.println(exc.getMessage());
            exc.printStackTrace();
        }
    }

    /**
     *
     * @param disc: disciplina que se quer verificar o vínculo
     * @return true caso tenha vínculo, false caso contrário
     */
    private static boolean verificaVinculoAluno(Disciplina disc) {
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            for(DisciplinaConcluida j: i.getDisciplinasConcluidas()) {
                if(j.getDisciplina().getCodigo().equals(disc.getCodigo()))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param aluno: aluno que se quer verificar o vínculo
     * @return true caso tenha vínculo, false caso contrário
     */
    public static boolean verificaVinculoAluno(Aluno aluno) {
        for(DisciplinaAplicada i: disciplinasVigentes) {
            for(Aluno j: i.getArrayListAlunosMatriculados()) {
                if(j.getCpf().equals(aluno.getCpf()))
                    return true;
            }
        }
        return false;
    }

    /**
     *
     * @param disc: disciplina que se quer verificar o vínculo
     * @return true caso tenha vínculo, false caso contrário
     */
    public static boolean verificaVinculoTurma(Disciplina disc) {
        if(verificaVinculoAluno(disc))
            return true;
        for (Disciplina i : GerenciadorDisciplinas.disciplinasVigentes) {
            if(disc.getCodigo().equals(i.getCodigo()))
                return true;
        }
        return false;
    }

    /**
     *
     * @param curso: curso desejado
     * @return arrayList disciplinas relacionadas ao curso.
     */
    private ArrayList<Disciplina> pesquisarDisciplinaCurso(Curso curso) {
        return (curso.getDisciplinasRelacionadas().size() == 0) ? null : curso.getDisciplinasRelacionadas();
    }

    /**
     *
     * @return vetor de strings com todos os códigos das disciplinas
     */
    public String[] getCodigoDisciplinas() {
        if(disciplinasCadastradas.size() == 0) {
            String[] vazia = {""};
            return vazia;
        }
        else {
            String[] disc = new String[disciplinasCadastradas.size()];
            for(int i = 0; i < disciplinasCadastradas.size(); i++) {
                disc[i] = disciplinasCadastradas.get(i).getCodigo();
            }
            return disc;
        }
    }

    /**
     *
     * @param codigo: código da disciplina
     * @return arraylist de turmas que sejam de tal disciplina
     */
    private ArrayList<DisciplinaAplicada> pesquisaDisciplinaVigenteCodigoArrayList(String codigo) {
        ArrayList<DisciplinaAplicada> achados = new ArrayList<>();
        for(DisciplinaAplicada i: GerenciadorDisciplinas.disciplinasVigentes) {
            if(i.getCodigo().equals(codigo)) {
                achados.add(i);
            }
        }
        return achados;
    }

    /**
     *
     * @param arr: arraylist de alunos
     * @param disc: turma a ser usada
     * @return TableModel com os alunos e notas de uma turma.
     */
    public TableModel getTableFromArray(ArrayList<Aluno> arr, DisciplinaAplicada disc) {
        String[] header = {
                "Nome", "Curso", "CPF", "Matrícula", "Notas", "Faltas"
        };
        String[][] data;
        if(arr.size() == 0 && disc == null) {
            data = null;
        }
        else {
            data = new String[arr.size()][6];
            for(int i = 0; i < arr.size(); i++) {
                data[i] = arr.get(i).getInfoBasicasArray();
                data[i][4] = (disc != null && disc.getArrayListAlunosMatriculados().contains(arr.get(i)))
                        ? Float.toString(disc.pesquisaAluno(arr.get(i).getCpf()).getNota()) : "0";
                data[i][5] = (disc != null && disc.getArrayListAlunosMatriculados().contains(arr.get(i)))
                        ? Integer.toString(disc.pesquisaAluno(arr.get(i).getCpf()).getFaltas()) : "0";
            }
        }
        return new DefaultTableModel(data, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return column == 4 || column == 5;
            }

        };

    }

    /**
     *
     * @return TableModel com todas as disciplinas cadastradas.
     */
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

    /**
     *
     * @return TableModel com todas as turmas cadastradas.
     */
    public TableModel getDisciplinasVigentesTable() {
        String[] header = {
                "Codigo", "Semestre", "Professor", "Data Inicio", "Data Fim"
        };
        String[][] data;
        if(GerenciadorDisciplinas.disciplinasVigentes.size() > 0) {
            data = new String[GerenciadorDisciplinas.disciplinasVigentes.size()][5];
            for(int i = 0; i < GerenciadorDisciplinas.disciplinasVigentes.size(); i++) {
                data[i] = GerenciadorDisciplinas.disciplinasVigentes.get(i).getInfoBasicasArray();
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

    /**
     *
     * @param curso: curso desejado
     * @return TableModel com as disciplinas que pertencem a esse curso.
     */
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

    /**
     *
     * @param disc: código de uma disciplina
     * @return TableModel com todas as turmas de tal disciplina
     */
    public TableModel getDisciplinasVigentesTable(String disc) {
        String[] header = {
                "Codigo", "Semestre", "Professor", "Data Inicio", "Data Fim"
        };
        boolean existe;
        ArrayList<DisciplinaAplicada> disciplinasProcuradas = this.pesquisaDisciplinaVigenteCodigoArrayList(disc);
        existe = disciplinasProcuradas.size() != 0;
        String[][] data;
        if(existe) {
            data = new String[disciplinasProcuradas.size()][5];
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