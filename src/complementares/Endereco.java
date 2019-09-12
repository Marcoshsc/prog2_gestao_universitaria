package complementares;

import contratos.ClassesGeral;

public class Endereco implements ClassesGeral {

    /**
     * Um endereço só pode ser alterado criando outro.
     */

    private String rua;
    private int numero;
    private String complemento;
    private String bairro;
    private String cep;
    private String cidade;
    private String estado;
    private String pais;

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

    public String getStorageString() {
        return String.format("%s-%d-%s-%s-%s-%s-%s-%s", this.rua, this.numero, this.complemento, this.bairro, this.cep, this.cidade,
        this.estado, this.pais);
    }

    public Endereco fromStorageString(String texto) {
        String[] campos = texto.split("-");
        return new Endereco(campos[0], Integer.parseInt(campos[1]), campos[2], campos[3], campos[4], campos[5], campos[6], campos[7]);
    }

    @Override
    public String toString() {
        return String.format("\nRua: %s\nNúmero: %s\nComplemento: %s\nBairro: %s\nCEP: %s\nCidade: %s\nEstado: %s\nPais: %s\n",
        this.rua, this.numero, this.complemento, this.bairro, this.cep, this.cidade, this.estado, this.pais);
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