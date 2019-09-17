package sistema.classes;

import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import complementares.Utilitario;

import java.io.IOException;
import java.util.ArrayList;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

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
