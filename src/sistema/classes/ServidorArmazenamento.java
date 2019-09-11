package sistema.classes;

import ensino.classecurso.Curso;
import pessoas.classealuno.Aluno;

import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ServidorArmazenamento {
	
	private static ArrayList<Curso> cursosCadastrados = new ArrayList<Curso>();
	// private Reitor reitorAtual;
	// private Professor diretor;
	// private ArrayList<Departamento> departamentosCadastrados = new ArrayList<Departamento>();
	// private ArrayList<Bloco> blocosCadastrados = new ArrayList<Bloco>();
	private static ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	private static ArrayList<Aluno> alunosCadastrados = new ArrayList<Aluno>();

	public void imprimeAlunosCadastrados() {
		ServidorArmazenamento.alunosCadastrados.forEach(a -> System.out.println(a));
	}

	public void atualizaBancoAluno() {
		File arquivo = new File("arquivos\\registrosAlunos.txt");
	}
	
}
