package sistema.classes;

import ensino.classecurso.GerenciadorCursos;
import pessoas.classealuno.GerenciadorAluno;

import java.util.ArrayList;


public class ServidorArmazenamento {

	// private Reitor reitorAtual;
	// private Professor diretor;
	// private ArrayList<Departamento> departamentosCadastrados = new
	// ArrayList<Departamento>();
	// private ArrayList<Bloco> blocosCadastrados = new ArrayList<Bloco>();
	private static ArrayList<Usuario> usuariosCadastrados = new ArrayList<Usuario>();
	public static GerenciadorCursos gerenciadorCursos = new GerenciadorCursos();
	public static GerenciadorAluno gerenciadorAlunos = new GerenciadorAluno();

	public static void inicializaTodos() throws Exception {
		ServidorArmazenamento.gerenciadorCursos.inicializa();
		ServidorArmazenamento.gerenciadorAlunos.inicializa();
	}

}
