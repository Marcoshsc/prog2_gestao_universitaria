package assistenciaestudantil;

public class Bolsa {

    /**
     * única forma de alterar valores é criando um
     * novo objeto.
     */

    private String codigo;
    private String nome;
    private float valor;

    protected Bolsa(String codigo, String nome, float valor) {
        this.codigo = codigo;
        this.nome = nome;
        this.valor = valor;
    }

    @Override
    public String toString() {
        return String.format("Código da Bolsa: %s\nNome da Bolsa: %s\nValor: R$%.2f\n", this.codigo, this.nome, this.valor);
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
     * @return the valor
     */
    public float getValor() {
        return valor;
    }

}