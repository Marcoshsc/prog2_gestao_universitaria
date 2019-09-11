package pessoas.superclasse;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import complementares.Endereco;
import complementares.ContaBancaria;

public abstract class PessoaFisica {
    
    /**
     * Não faz sentido que seja mudado nome, titulo
     * de eleitor, rg e cpf. Portanto os únicos que
     * podem ser alterados são endereço e contaBancaria.
     * Caso seja necessária outra alteração, deve ser 
     * criado outro objeto para sobrepor o antigo.
     */


    private String nome;
    private String rg;
    private String cpf;
    private String sexo;
    private LocalDate dataNascimento;
    private Endereco endereco;
    private ContaBancaria contaBancaria;

    protected PessoaFisica(String nome, String rg, String cpf, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String sexo) {
        this.sexo = sexo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
        this.contaBancaria = contaBancaria;
    }

    protected PessoaFisica(String nome, String rg, String cpf, LocalDate dataNascimento, Endereco endereco, String sexo) {
        this.sexo = sexo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.endereco = endereco;
    }

    protected PessoaFisica(String nome, String rg, String cpf, LocalDate dataNascimento, ContaBancaria contaBancaria, String sexo) {
        this.sexo = sexo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
        this.contaBancaria = contaBancaria;
    }

    protected PessoaFisica(String nome, String rg, String cpf, LocalDate dataNascimento, String sexo) {
        this.sexo = sexo;
        this.nome = nome;
        this.rg = rg;
        this.cpf = cpf;
        this.dataNascimento = dataNascimento;
    }

    private String escreveOBasico() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Nome: %s\nSexo: %s\nRG: %s\nCPF: %s\nData de Nascimento: %s\n", this.nome, this.sexo, this.rg, this.cpf,
        formatador.format(this.dataNascimento));
    }

    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("%s-%s-%s-%s-%s", this.nome, this.cpf, this.rg, this.sexo, formatador.format(this.dataNascimento)) + 
        '-' + this.endereco.getStorageString() + '-' + this.contaBancaria.getStorageString();
    }

    @Override
    public String toString() {
        if(this.endereco != null && this.contaBancaria != null) {
            return escreveOBasico() + String.format("Endereco:\n%s\nConta Bancária:\n%s\n", this.endereco, this.contaBancaria);
        }
        else if(this.endereco != null || this.contaBancaria != null) {
            if(this.endereco != null)
                return escreveOBasico() + String.format("Endereco:\n%s\nConta Bancária ainda não cadastrada.\n", this.endereco);
            else
                return escreveOBasico() + String.format("Endereço ainda não cadastrado.\nConta Bancaria:\n%s\n", this.contaBancaria);
        }
        else {
            return escreveOBasico() + String.format("Endereço e Conta Bancária ainda não foram cadastrados.\n");
        }
    }

    /**
     * @return the contaBancaria
     */
    public ContaBancaria getContaBancaria() {
        return contaBancaria;
    }

    public void alteraAgencia(String agencia) {
        this.contaBancaria.setAgencia(agencia);
    }

    /**
     * @return the cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * @return the dataNascimento
     */
    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    /**
     * @return the endereco
     */
    public Endereco getEndereco() {
        return endereco;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the rg
     */
    public String getRg() {
        return rg;
    }

}