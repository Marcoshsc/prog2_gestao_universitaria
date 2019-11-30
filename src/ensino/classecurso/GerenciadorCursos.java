package ensino.classecurso;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import contratos.OperacoesBasicas;
import ensino.secaodisciplina.Disciplina;

public class GerenciadorCursos implements OperacoesBasicas {

    private static ArrayList<Curso> cursosCadastrados = new ArrayList<Curso>();
    private static final String PATH = "src\\arquivos\\registrosCursos.txt";

	/**
	 *
	 * @param objeto: Curso a ser adicionado
	 */
	@Override
    public void adiciona(Object objeto) {
        GerenciadorCursos.cursosCadastrados.add((Curso)objeto);
		try {
			GerenciadorCursos.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Cursos");
        }
        GerenciadorCursos.atualizaBanco();
    }

	/**
	 *
	 */
	public static void atualizaBanco() {
        Utilitario.atualizaBanco(GerenciadorCursos.cursosCadastrados.toArray(), GerenciadorCursos.PATH);
	}

	/**
	 *
	 * @param curso: curso a ser excluído
	 */
	public static void excluir(Curso curso) {
		GerenciadorCursos.cursosCadastrados.remove(curso);
		try {
			GerenciadorCursos.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Cursos");
		}
	}

	/**
	 *
	 */
    @Override
    public void imprimeTodos() {
        // TODO Auto-generated method stub

    }

	/**
	 *
	 * @throws Exception caso não seja possível ler o arquivo.
	 */
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

	/**
	 *
	 * @param codigo: codigo de um curso
	 * @return null caso não encontrado, o curso referente ao código caso encontrado
	 */
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

	/**
	 *
	 * @param nome: nome do curso
	 * @return curso relativo ao nome, null caso não encontrado
	 */
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

	/**
	 *
	 * @return vetor com o nome de todos os cursos cadastrados
	 */
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

	/**
	 *
	 * @return TableModel com informações de todos os cursos cadastrados.
	 */
	public TableModel getCursosTable() {
		String[] header = {
			"Código", "Nome", "Carga Horária", "Tempo de Conclusão"
		};
		String[][] data;
		if(GerenciadorCursos.cursosCadastrados.size() > 0) {
			data = new String[GerenciadorCursos.cursosCadastrados.size()][7];
			for(int i = 0; i < GerenciadorCursos.cursosCadastrados.size(); i++) {
				data[i] = GerenciadorCursos.cursosCadastrados.get(i).getInfoBasicasArray();
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
	 * @param codigo: codigo de um curso
	 * @return TableModel com informações do curso referente.
	 */
	public TableModel getCursosTable(String codigo) {
		String[] header = {
			"Código", "Nome", "Carga Horária", "Tempo de Conclusão"
		};
		boolean existe;
		Curso cursoProcurado = GerenciadorCursos.pesquisaCursoCodigo(codigo);
		existe = (cursoProcurado != null) ? true : false;
		String[][] data;
		if(existe) {
			data = new String[1][7];
			data[0] = cursoProcurado.getInfoBasicasArray();
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