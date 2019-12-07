package ensino.secaodisciplina;

import contratos.ClassesGeral;

public class Disciplina implements ClassesGeral {
	
	private String codigo;
	private String nome;
	private int cargaHoraria;
	private int maximoFaltas;
	private String codigoCurso;

	/**
	 *
	 * @param codigo: codigo disciplina
	 * @param nome: nome disciplina
	 * @param cargaHoraria: carga horária disciplina
	 * @param maximoFaltas: máximo faltas disciplina
	 */
	public Disciplina(String codigo, String nome, int cargaHoraria, int maximoFaltas) {
		this.codigo = codigo;
		this.cargaHoraria = cargaHoraria;
		this.maximoFaltas = maximoFaltas;
		this.nome = nome;
	}

	/**
	 *
	 * @param codigo: codigo disciplina
	 * @param nome: nome disciplina
	 * @param cargaHoraria: carga horária disciplina
	 * @param maximoFaltas: máximo faltas disciplina
	 */
	public void alterar(String codigo, String nome, int cargaHoraria, int maximoFaltas) {
		this.codigo = codigo;
		this.cargaHoraria = cargaHoraria;
		this.maximoFaltas = maximoFaltas;
		this.nome = nome;
	}

	/**
	 *
	 * @return informações sobre uma disciplina
	 */
	@Override
	public String toString() {
		return String.format("Código: %s\nCarga Horária(Horas): %d\nMaximo Faltas(dias): %d\n",
		this.codigo, this.cargaHoraria, this.maximoFaltas);
	}

	/**
	 *
	 * @return string a ser cadastrada no banco de dados txt.
	 */
	@Override
	public String getStorageString() {
		return String.format("%s-%s-%d-%d-%s", this.codigo, this.nome, this.cargaHoraria, this.maximoFaltas, this.codigoCurso);
	}

	/**
	 *
	 * @param texto: texto recebido do banco de dados txt com informação de disciplina
	 * @throws Exception caso não seja possível instanciar a Disciplina
	 * @return disciplina referente ao texto recebido.
	 */
	public static Disciplina fromStorageString(String texto) throws Exception {
        String[] campos = texto.split("-");
        Disciplina a = new Disciplina(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
        a.setCodigoCurso(campos[4]);
        return a;
        
	}

	/**
	 *
	 * @return vetor de informações básicas de uma disciplina
	 */
	public String[] getInfoBasicasArray() {
        String[] infos = new String[4];
        infos[0] = this.codigo;
        infos[1] = this.nome;
        infos[2] = Integer.toString(this.cargaHoraria);
        infos[3] = Integer.toString(this.maximoFaltas);
        return infos;
    }

	/**
	 *
	 * @param codigoCurso codigo a ser definido
	 */
	public void setCodigoCurso(String codigoCurso) {
		this.codigoCurso = codigoCurso;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the codigoCurso
	 */
	public String getCodigoCurso() {
		return this.codigoCurso;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @return the cargaHoraria
	 */
	public int getCargaHoraria() {
		return cargaHoraria;
	}

	/**
	 * @return the codigo
	 */
	public String getCodigo() {
		return codigo;
	}

	/**
	 * @return the maximoFaltas
	 */
	public int getMaximoFaltas() {
		return maximoFaltas;
	}
	
}
