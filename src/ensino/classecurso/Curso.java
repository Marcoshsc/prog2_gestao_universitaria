package ensino.classecurso;

public class Curso {

    private String codigo;
    private String nome;
    private int tempoConclusao;
    private int cargaHoraria;

    public Curso(String codigo, String nome, int tempoConclusao, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.tempoConclusao = tempoConclusao;
        this.cargaHoraria = cargaHoraria;
    }

    public String getStorageString() {
        return String.format("%s-%s-%d-%d", this.codigo, this.nome, this.tempoConclusao, this.cargaHoraria);
    }

    @Override
    public String toString() {
        return String.format("\nCódigo: %s\nNome: %s\nTempo conclusão(Semestres): %d\nCarga Horária: %d\n", this.codigo,
        this.nome, this.tempoConclusao, this.cargaHoraria);
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
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the tempoConclusao
     */
    public int getTempoConclusao() {
        return tempoConclusao;
    }

}