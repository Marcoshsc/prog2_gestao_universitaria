package ensino.secaodisciplina;

import contratos.ClassesGeral;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisciplinaConcluida implements ClassesGeral {

	private Disciplina disciplina;
	private String cpfAluno;
	private int semestre;
	private float nota;
	private int faltas;
	private LocalDate dataConclusao;
	private boolean aprovado;

	/**
	 *
	 * @param disciplina: a disciplina referente
	 * @param cpfAluno: cpf do aluno que concluiu
	 * @param semestre: semestre
	 * @param nota: nota final
	 * @param faltas: número de faltas final
	 * @param dataConclusao: data de conclusão da disciplina concluída.
	 */
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

	/**
	 *
	 * @return informações sobre uma disciplina concluída para ser cadastrada no banco de dados.
	 */
	@Override
	public String getStorageString() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		return String.format("%s-%s-%d-%s-%s-%b-%d", this.disciplina.getCodigo(), this.cpfAluno, this.semestre,
				Float.toString(this.nota), formatador.format(this.dataConclusao), this.aprovado, this.faltas);
	}

	/**
	 *
	 * @param texto: informações sobre uma disciplina concluída do banco de dados
	 * @return disciplina concluída relativa às informações
	 */
	public static DisciplinaConcluida fromStorageString(String texto) {
		String[] campos = texto.split("-");
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		DisciplinaConcluida a = new DisciplinaConcluida(GerenciadorDisciplinas.pesquisaDisciplinaCodigo(campos[0]),
				campos[1], Integer.parseInt(campos[2]), Float.parseFloat(campos[3]), Integer.parseInt(campos[6]),
                LocalDate.parse(campos[4], formatador));
		a.aprovado = Boolean.parseBoolean(campos[5]);
		return a;
	}

	/**
	 *
	 * @return vetor de informações básicas da discpilina concluída
	 */
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

	/**
	 *
	 * @param nota: nota do aluno
	 * @param faltas: faltas do aluno
	 * @param disc: disciplina para ser comparada
	 */
	private void setAprovado(float nota, int faltas, Disciplina disc) {
		this.aprovado = nota >= 6 && faltas <= disc.getMaximoFaltas();
	}

	/**
	 *
	 * @return a disciplina
	 */
	public Disciplina getDisciplina() {
		return disciplina;
	}

	/**
	 *
	 * @return se está aprovado ou não
	 */
	public boolean getAprovado() {
		return this.aprovado;
	}

	/**
	 *
	 * @return data de conclusão
	 */
	public LocalDate getDataConclusao() {
		return dataConclusao;
	}

	/**
	 *
	 * @return cpf aluno
	 */
	public String getCpfAluno() {
		return cpfAluno;
	}
}
