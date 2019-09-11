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
import excecoes.OperacaoNaoAutorizada;

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
    
    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento, String sexo,
    Endereco endereco, ContaBancaria contaBancaria, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento, String sexo,
    Endereco endereco, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, endereco, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento, String sexo,
    ContaBancaria contaBancaria, String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String rg, String cpf, LocalDate dataNascimento, String sexo,
    String numeroMatricula, Curso curso, LocalDate dataIngresso) {
        super(nome, rg, cpf, dataNascimento, sexo);
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

    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return super.getStorageString() + '-' + String.format("%s-%s", this.numeroMatricula,
        formatador.format(this.dataIngresso)) + '-' + this.curso.getStorageString();
    }

    public void adicionaBolsa(BolsaVigente bolsa) throws OperacaoNaoAutorizada {
        if(this.bolsas.stream().filter(a -> bolsa == a).toArray().length == 0) {
            this.bolsas.add(bolsa);
        }
        else
            throw new OperacaoNaoAutorizada();
    }

    public boolean temBolsa() {
        if(this.bolsas.size() > 0)
            return true;
        else
            return false;
    }

    public BolsaVigente returnBolsaPorCodigo(String codigo) throws Exception {
        if(!this.temBolsa())
            throw new Exception(String.format("O aluno %s não possui Bolsas cadastradas.\n", this.getNome()));
        else {
            for(BolsaVigente i: this.bolsas) {
                if(i.getCodigo().equals(codigo))
                    return i;
            }
            throw new Exception(String.format("Bolsa não encontrada."));
        }
    }

    /**
     * @return the bolsas
     */
    public BolsaVigente[] getBolsasArray() {
        if(!(this.bolsas.size() >= 0))
            return null;
        BolsaVigente[] bolsas = new BolsaVigente[this.bolsas.size()];
        int cont = 0;
        for(BolsaVigente i : this.bolsas) {
            bolsas[cont] = i;
            cont++;
        }
        return bolsas;
    }

    public String getBolsasString() {
        if(this.bolsas.size() == 0)
            return String.format("Não existem bolsas cadastradas para %s.\n",
            this.getNome());
        String acumuladora = "";
        acumuladora += String.format("Bolsas de %s:\n", this.getNome());
        for(BolsaVigente i : this.bolsas) {
            acumuladora += i.toString();
        }
        return acumuladora;
    }

    /**
     * @return the bolsas
     */
    public ArrayList<BolsaVigente> getBolsas() {
        return bolsas;
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