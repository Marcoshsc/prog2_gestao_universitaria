package pessoas.classealuno;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import pessoas.superclasse.PessoaFisica;
import ensino.classecurso.Curso;
import assistenciaestudantil.BolsaVigente;
import complementares.ContaBancaria;
import complementares.Endereco;
import ensino.secaodisciplina.DisciplinaConcluida;
import sistema.classes.Usuario;

public class Aluno extends PessoaFisica {

    public static final String ID = "aluno";
    private String numeroMatricula;
    private Curso curso;
    private ArrayList<String> cursando = new ArrayList<String>();
    private Float coeficienteSemestral = 0f;
    private LocalDate dataIngresso;
    private ArrayList<BolsaVigente> bolsas = new ArrayList<BolsaVigente>();
    private ArrayList<DisciplinaConcluida> disciplinasConcluidas = new ArrayList<DisciplinaConcluida>();
    private Usuario sistema;
    private boolean ativo = true;
    
    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento,
    Endereco endereco, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, endereco);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, contaBancaria);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento,
    String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Número de matrícula: %s\nCurso: %s\nData de Ingresso: %s\n",
        this.numeroMatricula, this.curso, formatador.format(this.dataIngresso)) + super.toString();
    }

    /**
     * @return the bolsas
     */
    public BolsaVigente[] getBolsas() {
        BolsaVigente[] bolsas = new BolsaVigente[this.bolsas.size()];
        int cont = 0;
        for(BolsaVigente i : this.bolsas) {
            bolsas[cont] = i;
            cont++;
        }
        return bolsas;
    }

    public String getBolsasString() {
        String acumuladora = "";
        for(BolsaVigente i : this.bolsas) {
            acumuladora += i.toString();
        }
        return acumuladora;
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
     * @return the coeficienteSemestral
     */
    public Float getCoeficienteSemestral() {
        return coeficienteSemestral;
    }

    /**
     * @return the sistema
     */
    public Usuario getSistema() {
        return sistema;
    }

    /**
     * @param sistema the sistema to set
     */
    public void setSistema(String permissao, Usuario sistema) throws Exception {
        if(permissao == "servidor_armazenamento")
            this.sistema = sistema;
        else
            throw new Exception("Tentativa não permitida de modificação de usuário.");
    }

    public boolean getAtivo() {
        return this.ativo;
    }

    /**
     * @param ativo the ativo to set
     */
    public void setAtivo(String permissao, boolean ativo) throws Exception {
        if(permissao == "servidor_armazenamento")
            this.ativo = ativo;
        else
            throw new Exception("Ação não autorizada.");
    }

    // fazer getters e setters no visualstudiocode

}