package pessoas.classealuno;

import java.util.ArrayList;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import pessoas.superclasse.PessoaFisica;
import ensino.classecurso.Curso;
import assistenciaestudantil.BolsaVigente;
import complementares.ContaBancaria;
import complementares.Endereco;
import contratos.Armazenavel;
import contratos.ClassesGeral;
import ensino.secaodisciplina.DisciplinaConcluida;
import sistema.classes.Usuario;
import excecoes.OperacaoNaoAutorizada;

public class Aluno extends PessoaFisica implements ClassesGeral {

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
    
    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, endereco, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    Endereco endereco, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, endereco, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    ContaBancaria contaBancaria, String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, contaBancaria, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

    public Aluno(String nome, String cpf, String rg, String sexo, LocalDate dataNascimento,
    String numeroMatricula, LocalDate dataIngresso, Curso curso) {
        super(nome, rg, cpf, dataNascimento, sexo);
        this.numeroMatricula = numeroMatricula;
        this.curso = curso;
        this.dataIngresso = dataIngresso;
    }

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

    @Override
    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Número de matrícula: %s\nCurso: %s\nData de Ingresso: %s\n",
        this.numeroMatricula, this.curso, formatador.format(this.dataIngresso)) + super.toString();
    }

    public String getStorageString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return super.getStorageString() + '-' + String.format("%s-%s", this.numeroMatricula,
        formatador.format(this.dataIngresso)) + '-' + this.curso.getCodigo();
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