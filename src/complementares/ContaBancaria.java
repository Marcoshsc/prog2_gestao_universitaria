package complementares;

import contratos.ClassesGeral;

public class ContaBancaria implements ClassesGeral {

    /**
     * O único atributo possível de ser mudado é
     * a agencia. Qualquer outro atributo a ser mudado
     * deve gerar um novo objeto.
     */

    private String nomeBanco;
    private String agencia;
    private String conta;
    private String cpfTitular;
    private String nomeTitular;

    public ContaBancaria(String nomeBanco, String agencia, String conta, String cpfTitular, String nomeTitular) {
        this.nomeBanco = nomeBanco;
        this.agencia = agencia;
        this.conta = conta;
        this.cpfTitular = cpfTitular;
        this.nomeTitular = nomeTitular;
    }

    public String getStorageString() {
        return String.format("%s-%s-%s-%s-%s", this.nomeBanco, this.agencia, this.conta, this.cpfTitular, this.nomeTitular);
    }

    @Override
    public String toString() {
        return String.format("\nNome Banco: %s\nAgência: %s\nConta: %s\nCPF Titular: %s\nNome Titular: %s\n", this.nomeBanco, this.agencia, this.conta,
        this.cpfTitular, this.nomeTitular);
    }

    /**
     * @return the nomeBanco
     */
    public String getNomeBanco() {
        return nomeBanco;
    }

    /**
     * @return the agencia
     */
    public String getAgencia() {
        return agencia;
    }

    /**
     * @return the conta
     */
    public String getConta() {
        return conta;
    }

    /**
     * @return the cpfTitular
     */
    public String getCpfTitular() {
        return cpfTitular;
    }

    /**
     * @return the nomeTitular
     */
    public String getNomeTitular() {
        return nomeTitular;
    }

    /**
     * @param agencia the agencia to set
     */
    public void setAgencia(String agencia) {
        this.agencia = agencia;
    }

}