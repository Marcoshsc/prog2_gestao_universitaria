package pessoas.classeprofessor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import complementares.ContaBancaria;
import complementares.Endereco;
import contratos.ClassesGeral;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.superclasse.PessoaFisica;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Professor extends PessoaFisica implements ClassesGeral {

    private ArrayList<String> disciplinasMinistradas = new ArrayList<String>();
    private LocalDate dataIngresso;
    private float salario = 0f;

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria, sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, endereco, sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, contaBancaria, sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public Professor(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    LocalDate dataIngresso, float salario) {
        super(nome, rg, cpf, dataNascimento, sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setContaBancaria(contaBancaria);
        super.setSexo(sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setContaBancaria(null);
        super.setSexo(sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setContaBancaria(contaBancaria);
        super.setEndereco(null);
        super.setSexo(sexo);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    LocalDate dataIngresso, float salario) {
        super.setNome(nome);
        super.setCpf(cpf);
        super.setRg(rg);
        super.setDataNascimento(dataNascimento);
        super.setSexo(sexo);
        super.setContaBancaria(null);
        super.setEndereco(null);
        this.dataIngresso = dataIngresso;
        this.salario = salario;
    }

    public static Professor fromStorageString(String[] campos) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(campos.length == 7) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador),
            LocalDate.parse(campos[5], formatador), Float.parseFloat(campos[6]));
        }
        else if(campos.length == 12) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new ContaBancaria(campos[5], campos[6], campos[7], campos[8], campos[9]), LocalDate.parse(campos[10], formatador), 
            Float.parseFloat(campos[11]));
        }
        else if(campos.length == 15) {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador),
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            LocalDate.parse(campos[13], formatador), Float.parseFloat(campos[14]));
        }
        else {
            return new Professor(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            new ContaBancaria(campos[13], campos[14], campos[15], campos[16], campos[17]), LocalDate.parse(campos[18], formatador),
            Float.parseFloat(campos[19]));
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Departamento: %s\nSalario: R$%.2f\nData de Ingresso: %s\n",
        this.salario, formatador.format(this.dataIngresso)) + super.toString();
    }

    @Override
    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return super.getStorageString() + '-' + String.format("%s-%s",
        formatador.format(this.dataIngresso), Float.toString(this.salario));
    }

    public ArrayList<String> getDisciplinasMinistradas() {
        return this.disciplinasMinistradas;
    }

    public String[] getInfoBasicasArray() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] infos = new String[6];
        infos[0] = this.getNome();
        infos[1] = this.getCpf();
        infos[2] = Float.toString(this.salario);
        infos[3] = this.getRg();
        infos[4] = formatador.format(this.getDataNascimento());
        infos[5] = formatador.format(this.dataIngresso);
        return infos;
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

    public TableModel getDisciplinasMinistradasTable() {
        String[] header = {
                "Código", "Disciplina", "Data Início", "Data Fim"
        };
        String[][] data;
        if(this.disciplinasMinistradas.size() > 0) {
            data = new String[this.disciplinasMinistradas.size()][6];
            for(int i = 0; i < this.disciplinasMinistradas.size(); i++) {
                DisciplinaAplicada turma = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(this.disciplinasMinistradas.get(i));
                data[i] = turma.getInfoBasicasArrayProfessor(this);
            }
        }
        else {
            data = null;
        }
        return new DefaultTableModel(data, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    /**
     * @return the salario
     */
    public float getSalario() {
        return salario;
    }

    /**
     * @return the dataIngresso
     */
    public LocalDate getDataIngresso() {
        return dataIngresso;
    }
    
}