package ensino.secaodisciplina;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import excecoes.TamanhoIncompativel;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
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
	LocalDate dataFim, int vagasDisponiveis, int semestre, String codigoVigente) {
		super(disciplina.getCodigo(), disciplina.getNome(), disciplina.getCargaHoraria(), disciplina.getMaximoFaltas());
		this.professor = professor;
		this.dataInicio = dataInicio;
		this.dataFim = dataFim;
		this.vagasDisponiveis = vagasDisponiveis;
		this.semestre = semestre;
		this.codigoVigente = codigoVigente;
	}

	public void alterar(String professor, LocalDate dataInicio,
                        LocalDate dataFim, int vagasDisponiveis, int semestre, String codigoVigente) {
        this.professor = professor;
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
        this.vagasDisponiveis = vagasDisponiveis;
        this.semestre = semestre;
        this.codigoVigente = codigoVigente;
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
				a.append(String.format("%s;%s#", i.getAluno().getCpf(), Float.toString(i.getNota())));
			}
			return a.toString();
		}
	}

	public String[] getInfoBasicasArray() {
		DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		String[] infos = new String[5];
		infos[0] = this.codigoVigente;
		infos[1] = Integer.toString(this.semestre);
		infos[2] = this.professor;
		infos[3] = formatador.format(this.dataInicio);
		infos[4] = formatador.format(this.dataFim);
		return infos;
	}

	public ArrayList<Aluno> getArrayListAlunosMatriculados() {
		ArrayList<Aluno> current = new ArrayList<Aluno>();
		for(Boletim i: this.alunosMatriculados) {
			current.add(i.getAluno());
		}
		return current;
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
			aluno.getAluno().getCursando().add(this.getCodigoVigente());
		}
		else {
			throw new TamanhoIncompativel();
		}
	}

	public Boletim pesquisaAluno(String aluno) {
		if(this.alunosMatriculados.size() == 0)
			return null;
		else {
			for(Boletim i: this.alunosMatriculados) {
				if(i.getAluno().getCpf().equals(aluno)) return i;
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
			String[] teste = campos[5].split("#");
			if(!teste[0].equals("none")) {
				for (String i : campos[5].split("#")) {
					String[] dados = i.split(";");
					Boletim actual = new Boletim(ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(dados[0]));
					actual.setNota(Float.parseFloat(dados[1]));
					a.adicionaAluno(actual);
				}
			}
			ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(a.getProfessor())
					.getDisciplinasMinistradas().add(a.getCodigoVigente());
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
