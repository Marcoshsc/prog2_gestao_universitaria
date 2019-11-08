package ensino.secaodisciplina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excecoes.TamanhoIncompativel;
import pessoas.classealuno.Aluno;
import sistema.classes.ServidorArmazenamento;

import java.util.ArrayList;

public class DisciplinaAplicada extends Disciplina {

	private String codigoVigente;
	private String professor;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int vagasDisponiveis;
	private ArrayList<Boletim> alunosMatriculados = new ArrayList<Boletim>();
	private int semestre;

	public DisciplinaAplicada(Disciplina disciplina, String professor, LocalDate dataInicio, 
	LocalDate dataFim, int vagasDisponiveis, int semestre, String codigo) {
		super(disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getMaximoFaltas());
		this.professor = professor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.vagasDisponiveis = vagasDisponiveis;
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("Disciplina:\n%s\nProfessor: %s\nData Início: %s\nData Final: %s\n", super.toString(),
		this.professor, formatador.format(this.dataInicio), formatador.format(this.dataFim)) +
		String.format("Vagas Disponíveis: %d\nSemestre: %d\nCodigo: %s\n", this.vagasDisponiveis, this.semestre, this.codigoVigente);
	}

	private String getStorageStringAlunosMatriculados() {
		if(this.alunosMatriculados.size() == 0)
			return "none";
		else {
			StringBuilder a = new StringBuilder();
			for(Boletim i: this.alunosMatriculados) {
				a.append(String.format("%s;%f#", i.getAluno().getCpf(), i.getNota()));
			}
			return a.toString();
		}
	}

	@Override
	public String getStorageString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("%s-%s-%s-%s-%d-%s-%d-%s", super.getCodigo(), this.professor, formatador.format(this.dataInicio),
				formatador.format(this.dataFim), this.vagasDisponiveis, this.getStorageStringAlunosMatriculados(),
				this.semestre, this.codigoVigente);
	}

	public void adicionaAluno(Boletim aluno) throws TamanhoIncompativel {
		if(this.alunosMatriculados.size() < this.vagasDisponiveis) {
			this.alunosMatriculados.add(aluno);
		}
		else {
			throw new TamanhoIncompativel();
		}
	}

	public Boletim pesquisaAluno(Aluno aluno) {
		if(this.alunosMatriculados.size() == 0) return null;
		else {
			for(Boletim i: this.alunosMatriculados) {
				if(i.getAluno() == aluno) return i;
			}
			return null;
		}
	}

	public static DisciplinaAplicada fromStorageString(String texto) throws Exception {
		String[] campos = texto.split("-");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		Disciplina b = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(campos[0]);
		if(b != null) {
			DisciplinaAplicada a =  new DisciplinaAplicada(b, campos[1], LocalDate.parse(campos[2], formatador),
					LocalDate.parse(campos[3], formatador), Integer.parseInt(campos[4]), Integer.parseInt(campos[6]),
					campos[7]);
			for(String i: campos[5].split("#")) {
				String[] dados = i.split(";");
				Boletim actual = new Boletim(ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(campos[0]));
				actual.setNota(Float.parseFloat(campos[1]));
				a.adicionaAluno(actual);
			}
			return a;
		}
		else
			throw new Exception("Disciplina não existe");

	}

	/**
	 * @return the professor
	 */
	public String getProfessor() {
		return professor;
	}
	
	/**
	 * @return the dataInicio
	 */
	public LocalDate getDataInicio() {
		return dataInicio;
	}

	/**
	 * @return the dataFim
	 */
	public LocalDate getDataFim() {
		return dataFim;
	}

	/**
	 * @return the semestre
	 */
	public int getSemestre() {
		return semestre;
	}

	/**
	 * @return the vagasDisponiveis
	 */
	public int getVagasDisponiveis() {
		return vagasDisponiveis;
	}

	public String getCodigoVigente() {
		return this.codigoVigente;
	}
}
