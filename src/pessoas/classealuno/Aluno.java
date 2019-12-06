package pessoas.classealuno;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.superclasse.PessoaFisica;
import ensino.classecurso.Curso;
import complementares.ContaBancaria;
import complementares.Endereco;
import contratos.ClassesGeral;
import ensino.secaodisciplina.DisciplinaConcluida;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Aluno extends PessoaFisica implements ClassesGeral {

    public static final String ID = "aluno";
    private String numeroMatricula;
    private Curso curso;
    private ArrayList<String> cursando = new ArrayList<String>();
    private Float coeficienteSemestral = 0f;
    private LocalDate dataIngresso;
    private ArrayList<DisciplinaConcluida> disciplinasConcluidas = new ArrayList<DisciplinaConcluida>();
    private boolean ativo = true;

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param endereco: endereço do aluno
     * @param contaBancaria: número conta bancária do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param endereco: endereço do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, endereco, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param contaBancaria: número conta bancária do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param endereco: endereço do aluno
     * @param contaBancaria: número conta bancária do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super.setNome(nome);
        super.setRg(rg);
        super.setCpf(cpf);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setContaBancaria(contaBancaria);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param endereco: endereço do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super.setNome(nome);
        super.setRg(rg);
        super.setCpf(cpf);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(endereco);
        super.setContaBancaria(null);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param contaBancaria: número conta bancária do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super.setNome(nome);
        super.setRg(rg);
        super.setCpf(cpf);
        super.setDataNascimento(dataNascimento);
        super.setContaBancaria(contaBancaria);
        super.setEndereco(null);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @param nome: nome do aluno
     * @param cpf: cpf do aluno
     * @param rg: rg do aluno
     * @param sexo: sexo do aluno
     * @param dataNascimento: data de nascimento do aluno
     * @param numeroMatricula: numero de matrícula do aluno
     * @param dataIngresso: data de ingresso do aluno
     * @param curso: curso do aluno
     */
    public void alterar(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super.setNome(nome);
        super.setRg(rg);
        super.setCpf(cpf);
        super.setDataNascimento(dataNascimento);
        super.setEndereco(null);
        super.setContaBancaria(null);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    /**
     *
     * @return true caso tenha vínculo, false caso contrário.
     */
    public boolean temVinculo() {
        return this.getCursando().size() != 0;
    }

    /**
     *
     * @param campos: dados do aluno retirados do banco de dados
     * @param curso: curso do aluno
     * @return Aluno referente aos dados recebidos
     */
    public static Aluno fromStorageString(String[] campos, Curso curso) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(campos.length == 8) {
            return new Aluno(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), campos[5],
            LocalDate.parse(campos[6], formatador), curso);
        }
        else if(campos.length == 13) {
            return new Aluno(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new ContaBancaria(campos[5], campos[6], campos[7], campos[8], campos[9]), campos[10], LocalDate.parse(campos[11], formatador), 
            curso);
        }
        else if(campos.length == 16) {
            return new Aluno(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            campos[13], LocalDate.parse(campos[14], formatador), curso);
        }
        else {
            return new Aluno(campos[0], campos[1], campos[2], campos[3], LocalDate.parse(campos[4], formatador), 
            new Endereco(campos[5], Integer.parseInt(campos[6]), campos[7], campos[8], campos[9], campos[10], campos[11], campos[12]),
            new ContaBancaria(campos[13], campos[14], campos[15], campos[16], campos[17]),campos[18], LocalDate.parse(campos[19], formatador),
            curso);
        }

    }

    /**
     *
     * @return array de strings com as informações básicas do aluno
     */
    public String[] getInfoBasicasArray() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String[] infos = new String[7];
        infos[0] = this.getNome();
        infos[1] = this.curso.getNome();
        infos[2] = this.getCpf();
        infos[3] = this.numeroMatricula;
        infos[4] = this.getRg();
        infos[5] = formatador.format(this.getDataNascimento());
        infos[6] = formatador.format(this.dataIngresso);
        return infos;
    }

    /**
     *
     * @return informações de um aluno
     */
    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Número de matrícula: %s\nCurso: %s\nData de Ingresso: %s\n",
        this.numeroMatricula, this.curso, formatador.format(this.dataIngresso)) + super.toString();
    }

    /**
     *
     * @return string para ser armazenada no banco de dados, referente a um aluno
     */
    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return super.getStorageString() + '-' + String.format("%s-%s", this.numeroMatricula,
        formatador.format(this.dataIngresso)) + '-' + this.curso.getCodigo();
    }

    /**
     *
     * @param disc: disciplina a ser adicionada
     */
    public void adicionaDisciplinaConcluida(DisciplinaConcluida disc) {
        this.disciplinasConcluidas.add(disc);
    }

    /**
     *
     * @return TableModel com todas as disciplinas concluídas pelo aluno
     */
    public TableModel getDisciplinasConcluidasTable() {
        String[] header = {
                "Disciplina", "Data Conclusão", "Semestre", "Nota", "Faltas", "Aprovado"
        };
        String[][] data;
        if(this.disciplinasConcluidas.size() > 0) {
            data = new String[this.disciplinasConcluidas.size()][6];
            for(int i = 0; i < this.disciplinasConcluidas.size(); i++) {
                data[i] = this.disciplinasConcluidas.get(i).getInfoBasicasArray();
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
     *
     * @return TableModel com todas as disciplinas cursadas pelo aluno
     */
    public TableModel getDisciplinasCursadasTable() {
        String[] header = {
                "Código", "Professor", "Data Início", "Data Fim", "Nota", "Frequência"
        };
        String[][] data;
        if(this.cursando.size() > 0) {
            data = new String[this.cursando.size()][6];
            for(int i = 0; i < this.cursando.size(); i++) {
                DisciplinaAplicada turma = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(this.cursando.get(i));
                data[i] = turma.getInfoBasicasArrayAluno(this);
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
     * @return the curso
     */
    public Curso getCurso() {
        return curso;
    }
    
    /**
     * @return the numeroMatricula
     */
    public String getNumeroMatricula() {
        return numeroMatricula;
    }

    /**
     * @return the dataIngresso
     */
    public LocalDate getDataIngresso() {
        return dataIngresso;
    }

    /**
     *
     * @return SIM caso ativo == true, NÃO caso ativo == false
     */
    public String getAtivo() {
        if(this.ativo)
            return "SIM";
        else
            return "NÃO";
    }

    /**
     *
     * @return the cursando
     */
    public ArrayList<String> getCursando() {
        return cursando;
    }

    /**
     *
     * @return the disciplinasConcluidas
     */
    public ArrayList<DisciplinaConcluida> getDisciplinasConcluidas() {
        return disciplinasConcluidas;
    }

    // fazer getters e setters no visualstudiocode

}