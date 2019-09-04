package ensino.secaodisciplina;

public class Disciplina {
	
	private String codigo;
	private int cargaHoraria;
	private int maximoFaltas;
	private String departamento;
	
	public Disciplina(String codigo, int cargaHoraria, int maximoFaltas, String departamento) {
		this.codigo = codigo;
		this.cargaHoraria = cargaHoraria;
		this.maximoFaltas = maximoFaltas;
		this.departamento = departamento;
	}

	@Override
	public String toString() {
		return String.format("Código: %s\nCarga Horária(Horas): %d\nMaximo Faltas(dias): %d\nDepartamento: %s\n",
		this.codigo, this.cargaHoraria, this.maximoFaltas, this.departamento);
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
	 * @return the departamento
	 */
	public String getDepartamento() {
		return departamento;
	}

	/**
	 * @return the maximoFaltas
	 */
	public int getMaximoFaltas() {
		return maximoFaltas;
	}

	/**
	 * @param cargaHoraria the cargaHoraria to set
	 */
	public void setCargaHoraria(String permissao, int cargaHoraria) throws Exception {
		if(permissao == "departamento")
			this.cargaHoraria = cargaHoraria;
		else
			throw new Exception("Ação não autorizada");
	}

	/**
	 * @param maximoFaltas the maximoFaltas to set
	 */
	public void setMaximoFaltas(String permissao, int maximoFaltas) throws Exception {
		if(permissao == "departamento")
			this.maximoFaltas = maximoFaltas;
		else
			throw new Exception("Ação não autorizada");
	}
	
}
