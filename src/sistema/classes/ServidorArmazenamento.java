package sistema.classes;

import ensino.classecurso.Curso;
import pessoas.classealuno.Aluno;
import complementares.Utilitario;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class ServidorArmazenamento {
	
	private static ArrayList<Curso> cursosCadastrados = new ArrayList<Curso>();
	// private Reitor reitorAtual;
	// private Professor diretor;
	// private ArrayList<Departamento> departamentosCadastrados = new ArrayList<Departamento>();
	// private ArrayList<Bloco> blocosCadastrados = new ArrayList<Bloco>();
	private static ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	private static ArrayList<Aluno> alunosCadastrados = new ArrayList<Aluno>();

	public static void imprimeAlunosCadastrados() {
		ServidorArmazenamento.alunosCadastrados.forEach(a -> System.out.println(a));
	}

	public static void adicionaAluno(Aluno aluno) {
		ServidorArmazenamento.alunosCadastrados.add(aluno);
	}

	public static void atualizaBancoAluno() throws IOException {
		Utilitario.atualizaBanco(ServidorArmazenamento.alunosCadastrados.toArray(), "src\\arquivos\\registrosAlunos.txt");
	}

	public static void inicizalizaAlunos() throws Exception {
		String[] objetos = Utilitario.leArquivo("src\\arquivos\\registrosAlunos.txt");
		if(objetos[0] != "") {
			for(String i: objetos) {
				ServidorArmazenamento.alunosCadastrados.add(Aluno.fromStorageString(i));
			}
		}
	}
	
}
