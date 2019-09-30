package pessoas.classeprofessor;

import java.util.ArrayList;

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

    @Override
    public void imprimeTodos() {
        // TODO Auto-generated method stub

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
		if(GerenciadorProfessor.professoresCadastrados.size() == 0)
			return null;
		else {
			for(int i = 0; i < GerenciadorProfessor.professoresCadastrados.size(); i++) {
				if(GerenciadorProfessor.professoresCadastrados.get(i).getCpf().equals(cpf))
					return GerenciadorProfessor.professoresCadastrados.get(i);
			}
			return null;
		}
    }
    
}