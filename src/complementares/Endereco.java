package complementares;

public class Endereco {

    /**
     * Um endereço só pode ser alterado criando outro.
     */

    private String rua;
    private int numero;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    public Endereco(String rua, int numero, String bairro, String cep, String cidade, String estado, String pais) {
        this.rua = rua;
        this.numero = numero;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    @Override
    public String toString() {
        return String.format("\nRua: %s\nNúmero: %s\nBairro: %s\nCEP: %s\nCidade: %s\nEstado: %s\nPais: %s\n", this.rua,
        this.numero, this.bairro, this.cep, this.cidade, this.estado, this.pais);
    }

    /**
     * @return the bairro
     */
    public String getBairro() {
        return bairro;
    }

    /**
     * @return the cep
     */
    public String getCep() {
        return cep;
    }

    /**
     * @return the cidade
     */
    public String getCidade() {
        return cidade;
    }

    /**
     * @return the estado
     */
    public String getEstado() {
        return estado;
    }

    /**
     * @return the numero
     */
    public int getNumero() {
        return numero;
    }
    
    /**
     * @return the pais
     */
    public String getPais() {
        return pais;
    }
    /**
     * @return the rua
     */
    public String getRua() {
        return rua;
    }

}