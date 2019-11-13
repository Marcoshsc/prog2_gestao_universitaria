package ensino.secaodisciplina;

import contratos.ClassesGeral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisciplinaConcluida implements ClassesGeral {

	private Disciplina disciplina;
	private String cpfAluno;
	private int semestre;
	private float nota;
	private LocalDate dataConclusao;
	private boolean aprovado;

	public DisciplinaConcluida(Disciplina disciplina, String cpfAluno, int semestre, float nota, LocalDate dataConclusao) {
		this.disciplina = disciplina;
		this.cpfAluno = cpfAluno;
		this.semestre = semestre;
		this.nota = nota;
		this.dataConclusao = dataConclusao;
		this.setAprovado(nota);
	}

	@Override
	public String getStorageString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("%s-%s-%d-%s-%s-%b", this.disciplina.getCodigo(), this.cpfAluno, this.semestre,
				Float.toString(this.nota), formatador.format(this.dataConclusao), this.aprovado);
	}

	public static DisciplinaConcluida fromStorageString(String texto) {
		String[] campos = texto.split("-");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DisciplinaConcluida a = new DisciplinaConcluida(GerenciadorDisciplinas.pesquisaDisciplinaCodigo(campos[0]),
				campos[1], Integer.parseInt(campos[2]), Float.parseFloat(campos[3]), LocalDate.parse(campos[4], formatador));
		a.setAprovado(a.getNota());
		return a;
	}

	private void setAprovado(float nota) {
		this.aprovado = nota >= 6;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public float getNota() {
		return nota;
	}

	public int getSemestre() {
		return semestre;
	}

	public boolean getAprovado() {
		return this.aprovado;
	}

	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	public String getCpfAluno() {
		return cpfAluno;
	}
}
