package ensino.classecurso;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import contratos.OperacoesBasicas;
import ensino.secaodisciplina.Disciplina;

public class GerenciadorCursos implements OperacoesBasicas {

    private static ArrayList<Curso> cursosCadastrados = new ArrayList<Curso>();
    private static final String PATH = "src/arquivos/registrosCursos.txt";

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

    public static void atualizaBanco() {
        Utilitario.atualizaBanco(GerenciadorCursos.cursosCadastrados.toArray(), GerenciadorCursos.PATH);
	}

	public static void excluir(Curso curso) {
		GerenciadorCursos.cursosCadastrados.remove(curso);
		try {
			GerenciadorCursos.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Cursos");
		}
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