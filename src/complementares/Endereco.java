package complementares;

import contratos.ClassesGeral;

public class Endereco implements ClassesGeral {

    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

    /**
     *
     * @param rua: rua da pessoa
     * @param numero: numero da casa
     * @param complemento: casa/loja, apto, etc
     * @param bairro: bairro de residência
     * @param cep: CEP da residência
     * @param cidade: cidade de residência
     * @param estado: estado de residência
     * @param pais: país de residência
     */
    public Endereco(String rua, int numero, String complemento, String bairro, String cep, String cidade, String estado, String pais) {
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.bairro = bairro;
        this.cep = cep;
        this.cidade = cidade;
        this.estado = estado;
        this.pais = pais;
    }

    /**
     *
     * @return String a ser armazenada no banco de dados txt.
     */
    public String getStorageString() {
        return String.format("%s-%d-%s-%s-%s-%s-%s-%s", this.rua, this.numero, this.complemento, this.bairro, this.cep, this.cidade,
        this.estado, this.pais);
    }

    /**
     *
     * @return relatório do endereço
     */
    @Override
    public String toString() {
        return String.format("\nRua: %s\nNúmero: %s\nComplemento: %s\nBairro: %s\nCEP: %s\nCidade: %s\nEstado: %s\nPais: %s\n",
        this.rua, this.numero, this.complemento, this.bairro, this.cep, this.cidade, this.estado, this.pais);
    }

    /**
     * @return the complemento
     */
    public String getComplemento() {
        return complemento;
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