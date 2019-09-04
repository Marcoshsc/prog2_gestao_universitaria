package complementares;

public class ContaBancaria {

    /**
     * O único atributo possível de ser mudado é
     * a agencia. Qualquer outro atributo a ser mudado
     * deve gerar um novo objeto.
     */

    private String agencia;
    private String conta;
    private String cpfTitular;
    private String nomeTitular;

    public ContaBancaria(String agencia, String conta, String cpfTitular, String nomeTitular) {
        this.agencia = agencia;
        this.conta = conta;
        this.cpfTitular = cpfTitular;
        this.nomeTitular = nomeTitular;
    }

    @Override
    public String toString() {
        return String.format("\nAgência: %s\nConta: %s\nCPF Titular: %s\nNome Titular: %s\n", this.agencia, this.conta,
        this.cpfTitular, this.nomeTitular);
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