package pessoas.classealuno;

import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import contratos.OperacoesBasicas;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;

public class GerenciadorAluno implements OperacoesBasicas {

    private static ArrayList<Aluno> alunosCadastrados = new ArrayList<Aluno>();
    private static final String PATH = "src\\arquivos\\registrosAlunos.txt";

    @Override
    public void adiciona(Object objeto) {
        GerenciadorAluno.alunosCadastrados.add((Aluno)objeto);
		try {
			this.atualizaBanco();
		} catch(Exception exc) {
			System.out.println("Não foi possível atualizar o banco de dados dos Alunos");
		}
	}

    private void atualizaBanco() throws Exception {
        Utilitario.atualizaBanco(GerenciadorAluno.alunosCadastrados.toArray(), GerenciadorAluno.PATH);
    }

    @Override
    public void imprimeTodos() {
        GerenciadorAluno.alunosCadastrados.forEach(a -> System.out.println(a));
    }

    @Override
    public void inicializa() throws Exception {
        String[] objetos = Utilitario.leArquivo(GerenciadorAluno.PATH);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					String[] campos = i.split("-");
					Curso cursoPrevio = GerenciadorCursos.pesquisaCursoCodigo(campos[campos.length - 1]);
					if(cursoPrevio != null)
						GerenciadorAluno.alunosCadastrados.add(Aluno.fromStorageString(campos, cursoPrevio));
					else
						throw new Exception("Tem aluno chegando sem curso");
				}
			}
		}
	}

    public Aluno pesquisarAlunoCPF(String cpf) {
		if(GerenciadorAluno.alunosCadastrados.size() == 0)
			return null;
		else {
			for(int i = 0; i < GerenciadorAluno.alunosCadastrados.size(); i++) {
				if(GerenciadorAluno.alunosCadastrados.get(i).getCpf().equals(cpf))
					return GerenciadorAluno.alunosCadastrados.get(i);
			}
			return null;
		}
    }
    
    public ArrayList<Aluno> pesquisarAlunoCurso(Curso curso) {
        ArrayList<Aluno> procurados = new ArrayList<Aluno>();
        if(GerenciadorAluno.alunosCadastrados.size() == 0)
            return null;
        for(Aluno i: GerenciadorAluno.alunosCadastrados) {
            if(i.getCurso() == curso)
                procurados.add(i);
        }
        return (procurados.size() == 0) ? null : procurados;
    }

    public TableModel getAlunosTable() {
		String[] header = {
			"Nome", "Curso", "CPF", "Matrícula", "RG", "Data Nascimento", "Data Ingresso"
		};
		String[][] data;
		if(GerenciadorAluno.alunosCadastrados.size() > 0) {
			data = new String[GerenciadorAluno.alunosCadastrados.size()][7];
			for(int i = 0; i < GerenciadorAluno.alunosCadastrados.size(); i++) {
				data[i] = GerenciadorAluno.alunosCadastrados.get(i).getInfoBasicasArray();
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

	public TableModel getAlunosTable(String cpf) {
		String[] header = {
			"Nome", "Curso", "CPF", "Matrícula", "RG", "Data Nascimento", "Data Ingresso"
		};
		boolean existe;
		Aluno alunoProcurado = this.pesquisarAlunoCPF(cpf);
		existe = (alunoProcurado != null) ? true : false;
		String[][] data;
		if(existe) {
			data = new String[1][7];
			data[0] = alunoProcurado.getInfoBasicasArray();
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
    
    public TableModel getAlunosTable(Curso curso) {
		String[] header = {
			"Nome", "Curso", "CPF", "Matrícula", "RG", "Data Nascimento", "Data Ingresso"
		};
		boolean existe;
		ArrayList<Aluno> alunosProcurados = this.pesquisarAlunoCurso(curso);
		existe = (alunosProcurados != null) ? true : false;
		String[][] data;
		if(existe) {
            data = new String[alunosProcurados.size()][7];
            for(int i = 0; i < alunosProcurados.size(); i++) {
                data[i] = alunosProcurados.get(i).getInfoBasicasArray();
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