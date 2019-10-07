package ensino.secaodisciplina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import pessoas.classealuno.Aluno;
import java.util.ArrayList;

public class DisciplinaAplicada extends Disciplina {

	private String professor;
	private LocalDate dataInicio;
	private LocalDate dataFim;
	private int turma;
	private int vagasDisponiveis;
	private ArrayList<Boletim> alunosMatriculados = new ArrayList<Boletim>();
	private int semestre;

	public DisciplinaAplicada(Disciplina disciplina, String professor, LocalDate dataInicio, 
	LocalDate dataFim, int turma, int vagasDisponiveis, int semestre) {
		super(disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getMaximoFaltas());
		this.professor = professor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.turma = turma;
		this.vagasDisponiveis = vagasDisponiveis;
		this.semestre = semestre;
	}

	@Override
	public String toString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("Disciplina:\n%s\nProfessor: %s\nData Início: %s\nData Final: %s\nTurma: %d\n", super.toString(),
		this.professor, formatador.format(this.dataInicio), formatador.format(this.dataFim), this.turma) +
		String.format("Vagas Disponíveis: %d\nSemestre: %d", this.vagasDisponiveis, this.semestre);
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
	 * @return the turma
	 */
	public int getTurma() {
		return turma;
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

}
