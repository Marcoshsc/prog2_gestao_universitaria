package sistema.classes;

import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.GerenciadorAluno;
import pessoas.classeprofessor.GerenciadorProfessor;

import java.util.ArrayList;


public class ServidorArmazenamento {

	public static GerenciadorCursos gerenciadorCursos = new GerenciadorCursos();
	public static GerenciadorAluno gerenciadorAlunos = new GerenciadorAluno();
	public static GerenciadorProfessor gerenciadorProfessores = new GerenciadorProfessor();
	public static GerenciadorDisciplinas gerenciadorDisciplinas = new GerenciadorDisciplinas();

	public static void inicializaTodos() throws Exception {
		ServidorArmazenamento.gerenciadorProfessores.inicializa();
		ServidorArmazenamento.gerenciadorCursos.inicializa();
		ServidorArmazenamento.gerenciadorAlunos.inicializa();
		ServidorArmazenamento.gerenciadorDisciplinas.inicializa();
	}

}
