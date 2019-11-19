package ensino.secaodisciplina;

import contratos.ClassesGeral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class DisciplinaConcluida implements ClassesGeral {

	private Disciplina disciplina;
	private String cpfAluno;
	private int semestre;
	private float nota;
	private int faltas;
	private LocalDate dataConclusao;
	private boolean aprovado;

	public DisciplinaConcluida(Disciplina disciplina, String cpfAluno, int semestre, float nota,
                               int faltas, LocalDate dataConclusao) {
		this.disciplina = disciplina;
		this.cpfAluno = cpfAluno;
		this.semestre = semestre;
		this.nota = nota;
		this.faltas = faltas;
		this.dataConclusao = dataConclusao;
		this.setAprovado(nota, faltas, disciplina);
	}

	@Override
	public String getStorageString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("%s-%s-%d-%s-%s-%b-%d", this.disciplina.getCodigo(), this.cpfAluno, this.semestre,
				Float.toString(this.nota), formatador.format(this.dataConclusao), this.aprovado, this.faltas);
	}

	public static DisciplinaConcluida fromStorageString(String texto) {
		String[] campos = texto.split("-");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DisciplinaConcluida a = new DisciplinaConcluida(GerenciadorDisciplinas.pesquisaDisciplinaCodigo(campos[0]),
				campos[1], Integer.parseInt(campos[2]), Float.parseFloat(campos[3]), Integer.parseInt(campos[6]),
                LocalDate.parse(campos[4], formatador));
		a.aprovado = Boolean.parseBoolean(campos[5]);
		return a;
	}

	public String[] getInfoBasicasArray() {
	    String[] dados = new String[6];
	    DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	    dados[0] = this.disciplina.getNome();
	    dados[1] = formatador.format(this.dataConclusao);
	    dados[2] = Integer.toString(this.semestre);
	    dados[3] = Float.toString(this.nota);
	    dados[4] = Integer.toString(this.faltas);
	    dados[5] = (this.aprovado) ? "SIM" : "NÃO";
	    return dados;
    }

	private void setAprovado(float nota, int faltas, Disciplina disc) {
		this.aprovado = nota >= 6 && faltas <= disc.getMaximoFaltas();
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
