package pessoas.classeprofessor;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import contratos.OperacoesBasicas;

public class GerenciadorProfessor implements OperacoesBasicas {

    private static ArrayList<Professor> professoresCadastrados = new ArrayList<Professor>();
    private static final String PATH = "src\\arquivos\\registrosProfessores.txt";

    @Override
    public void adiciona(Object objeto) {
        GerenciadorProfessor.professoresCadastrados.add((Professor)objeto);
        try {
			GerenciadorProfessor.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Professores");
		}
    }

    public static void atualizaBanco() throws Exception {
        Utilitario.atualizaBanco(GerenciadorProfessor.professoresCadastrados.toArray(), GerenciadorProfessor.PATH);
	}
	
	public static void excluir(Professor professor) {
		GerenciadorProfessor.professoresCadastrados.remove(professor);
		try {
			GerenciadorProfessor.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Professores");
		}
	}

    @Override
    public void imprimeTodos() {
        for(Professor i: GerenciadorProfessor.professoresCadastrados) {
			System.out.println(i);
		}

    }

    @Override
    public void inicializa() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorProfessor.PATH);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					String[] campos = i.split("-");
					GerenciadorProfessor.professoresCadastrados.add(Professor.fromStorageString(campos));
				}
			}
        }
    }
    
    public Professor pesquisarProfessorCPF(String cpf) {
		if(GerenciadorProfessor.professoresCadastrados.size() == 0) {
			return null;
		}
		else {
			for(int i = 0; i < GerenciadorProfessor.professoresCadastrados.size(); i++) {
				if(GerenciadorProfessor.professoresCadastrados.get(i).getCpf().equals(cpf)) {
					return GerenciadorProfessor.professoresCadastrados.get(i);
				}
			}
			return null;
		}
	}
	
	public TableModel getProfessoresTable() {
		String[] header = {
			"Nome", "CPF", "Salário", "RG", "Data Nascimento", "Data Ingresso"
		};
		String[][] data;
		if(GerenciadorProfessor.professoresCadastrados.size() > 0) {
			data = new String[GerenciadorProfessor.professoresCadastrados.size()][6];
			for(int i = 0; i < GerenciadorProfessor.professoresCadastrados.size(); i++) {
				data[i] = GerenciadorProfessor.professoresCadastrados.get(i).getInfoBasicasArray();
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

	public TableModel getProfessoresTable(String cpf) {
		String[] header = {
			"Nome", "CPF", "Salário", "RG", "Data Nascimento", "Data Ingresso"
		};
		boolean existe;
		Professor professorProcurado = this.pesquisarProfessorCPF(cpf);
		existe = (professorProcurado != null) ? true : false;
		String[][] data;
		if(existe) {
			data = new String[1][6];
			data[0] = professorProcurado.getInfoBasicasArray();
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