package sistema.classes;

import ensino.classecurso.Curso;
import pessoas.classealuno.Aluno;
import complementares.Utilitario;

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
	private static final String PATH_ALUNO = "src\\arquivos\\registrosAlunos.txt";
	private static final String PATH_CURSO = "src\\arquivos\\registrosCursos.txt";

	/**
	 * Imprime todos os alunos cadastrados
	 */
	public static void imprimeAlunosCadastrados() {
		ServidorArmazenamento.alunosCadastrados.forEach(a -> System.out.println(a));
	}

	/**
	 * Adiciona um novo aluno (Aluno aluno)
	 */
	public static void adicionaAluno(Aluno aluno) {
		ServidorArmazenamento.alunosCadastrados.add(aluno);
	}

	public static void adicionaCurso(Curso curso) {
		ServidorArmazenamento.cursosCadastrados.add(curso);
	}

	public static void atualizaBancoAluno() throws IOException {
		Utilitario.atualizaBanco(ServidorArmazenamento.alunosCadastrados.toArray(), "src\\arquivos\\registrosAlunos.txt");
	}

	public static void atualizaBancoCurso() throws Exception {
		Utilitario.atualizaBanco(ServidorArmazenamento.cursosCadastrados.toArray(), "src\\arquivos\\registrosCursos.txt");
	}

	public static Aluno pesquisarAlunoCPF(String cpf) {
		if(ServidorArmazenamento.alunosCadastrados.size() == 0)
			return null;
		else {
			for(Aluno i: ServidorArmazenamento.alunosCadastrados) {
				if(i.getCpf().equals(cpf))
					return i;
			}
			return null;
		}
	}

	public static Curso pesquisaCursoCodigo(String codigo) {
		if(ServidorArmazenamento.cursosCadastrados.size() >= 0) {
			for(Curso i: ServidorArmazenamento.cursosCadastrados) {
				if(i.getCodigo().equals(codigo)) {
					return i;
				}
			}
			return null;
		}
		else
			return null;
	}

	public static Curso pesquisaCursoNome(String nome) {
		if(ServidorArmazenamento.cursosCadastrados.size() >= 0) {
			for(Curso i: ServidorArmazenamento.cursosCadastrados) {
				if(i.getNome().equals(nome)) {
					return i;
				}
			}
			return null;
		}
		else
			return null;
	}

	public static String[] getNomeCursos() {
		if(ServidorArmazenamento.cursosCadastrados.size() == 0) {
			String[] vazia = {""};
			return vazia;
		}
		else {
			String[] nomes = new String[ServidorArmazenamento.cursosCadastrados.size()];
			for(int i = 0; i < nomes.length; i++) {
				nomes[i] = ServidorArmazenamento.cursosCadastrados.get(i).getNome();
			}
			return nomes;
		}
	}

	public static void inicializaAlunos() throws Exception {
		String[] objetos = Utilitario.leArquivo(ServidorArmazenamento.PATH_ALUNO);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					String[] campos = i.split("-");
					Curso cursoPrevio = ServidorArmazenamento.pesquisaCursoCodigo(campos[campos.length - 1]);
					if(cursoPrevio != null)
						ServidorArmazenamento.alunosCadastrados.add(Aluno.fromStorageString(campos, cursoPrevio));
					else
						throw new Exception("Tem aluno chegando sem curso");
				}
			}
		}
	}

	public static void inicializaCursos() throws Exception {
		String[] objetos = Utilitario.leArquivo(ServidorArmazenamento.PATH_CURSO);
		if(objetos == null)
			return;
		if(objetos.length > 0) {
			if(objetos[0] != "") {
				for(String i: objetos) {
					ServidorArmazenamento.cursosCadastrados.add(Curso.fromStorageString(i));
				}
			}
		}
	}

	public static void inicializaTodos() throws Exception {
		ServidorArmazenamento.inicializaCursos();
		ServidorArmazenamento.inicializaAlunos();
	}
	
}
