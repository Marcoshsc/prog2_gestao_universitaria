package pessoas.classeprofessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import complementares.ContaBancaria;
import complementares.Endereco;
import contratos.ClassesGeral;
import pessoas.superclasse.PessoaFisica;

public class Professor extends PessoaFisica implements ClassesGeral {

    private ArrayList<String> disciplinasMinistradas = new ArrayList<String>();
    private String departamento;
    private LocalDate dataIngresso;
    private float salario = 0f;

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String departamento, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria, sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, String departamento, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, endereco, sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String departamento, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, contaBancaria, sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    String departamento, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String departamento, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setContaBancaria(contaBancaria);
        super.setSexo(sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, String departamento, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setSexo(sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String departamento, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setContaBancaria(contaBancaria);
        super.setSexo(sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    String departamento, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setSexo(sexo);
        this.departamento = departamento;
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public static Professor fromStorageString(String[] campos) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(campos.length == 8) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), campos[5],
            LocalDate.parse(campos[6], formatador), Float.parseFloat(campos[7]));
        }
        else if(campos.length == 13) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new ContaBancaria(campos[5], campos[6], campos[7], campos[8], campos[9]), campos[10], LocalDate.parse(campos[11], formatador), 
            Float.parseFloat(campos[12]));
        }
        else if(campos.length == 16) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            campos[13], LocalDate.parse(campos[14], formatador), Float.parseFloat(campos[15]));
        }
        else {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            new ContaBancaria(campos[13], campos[14], campos[15], campos[16], campos[17]),campos[18], LocalDate.parse(campos[19], formatador),
            Float.parseFloat(campos[20]));
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Departamento: %s\nSalario: R$%.2f\nData de Ingresso: %s\n",
        this.departamento, this.salario, formatador.format(this.dataIngresso)) + super.toString();
    }

    @Override
    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return super.getStorageString() + '-' + String.format("%s-%s-%s", this.departamento,
        formatador.format(this.dataIngresso), Float.toString(this.salario));
    }

    public String[] getDisciplinasMinistradas() {
        if(this.disciplinasMinistradas.size() == 0)
            return null;
        String[] disciplinas = new String[this.disciplinasMinistradas.size()];
        for(int i = 0; i < this.disciplinasMinistradas.size(); i++) {
            disciplinas[i] = this.disciplinasMinistradas.get(i);
        }
        return disciplinas;
    }

    protected void removeDisciplina(String codigo) {
        for(String i: this.disciplinasMinistradas) {
            if(i.equals(codigo)) {
                this.disciplinasMinistradas.remove(i);
                return;
            }
        }
        System.out.println("Disciplina nao encontrada nao deu pra excluir");
    }

    protected void adicionaDisciplina(String codigo) {
        if(!codigo.equals(""))
            this.disciplinasMinistradas.add(codigo);
    }

    /**
     * @return the departamento
     */
    public String getDepartamento() {
        return departamento;
    }

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }
    
}