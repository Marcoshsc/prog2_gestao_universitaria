package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.time.format.DateTimeFormatter;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import sistema.classes.ServidorArmazenamento;
import complementares.ContaBancaria;
import complementares.Endereco;
import complementares.Utilitario;
import interfacegrafica.PainelOpcoes;
import pessoas.classealuno.Aluno;

public class CadastroAluno extends JPanel {

    protected PainelOpcoes origem;
    protected JanelaPrincipal patern;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JLabel dataIngressoLabel = new JLabel("Data de Ingresso: ");
    protected JTextField dataIngressoField = Utilitario.geraField("data");
    protected JLabel ativoLabel = new JLabel("Ativo: ");
    protected String[] ativoString = {"SIM", "NÃO"};
    protected JComboBox<String> ativoField = new JComboBox<String>(ativoString);
    protected JLabel nomeLabel = new JLabel("Nome: ");
    public JTextField nomeField = Utilitario.geraField();
    protected JLabel sexoLabel = new JLabel("Sexo: ");
    protected String[] sexoString = {"Masculino", "Feminino"};
    protected JComboBox<String> sexoField = new JComboBox<String>(sexoString);
    protected JLabel informacoesGeraisLabel = Utilitario.geraTitulo("INFORMAÇÕES GERAIS");
    protected JLabel cpfLabel = new JLabel("CPF: ");
    protected JTextField cpfField = Utilitario.geraField("cpf");
    protected JLabel matriculaLabel = new JLabel("Matrícula: ");
    protected JTextField matriculaField = Utilitario.geraField();
    protected JLabel dataNascimentoLabel = new JLabel("Data de Nascimento: ");
    protected JTextField dataNascimentoField = Utilitario.geraField("data");
    protected JLabel identidadeLabel = new JLabel("RG: ");
    protected JTextField identidadeField = Utilitario.geraField();
    protected JLabel contaBancariaLabel = Utilitario.geraTitulo("INFORMAÇÕES DE CONTA BANCÁRIA");
    protected JLabel agenciaLabel = new JLabel("Agência: ");
    protected JTextField agenciaField = Utilitario.geraField();
    protected JLabel contaLabel = new JLabel("Conta: ");
    protected JTextField contaField = Utilitario.geraField();
    protected JLabel cpfTitularLabel = new JLabel("CPF do Titular: ");
    protected JTextField cpfTitularField = Utilitario.geraField();
    protected JLabel nomeTitularLabel = new JLabel("Nome do Titular: ");
    protected JTextField nomeTitularField = Utilitario.geraField();
    protected JLabel bancoLabel = new JLabel("Nome do Banco: ");
    protected String[] bancoString = {"BANCO DO BRASIL", "ITAÚ", "BRADESCO", "SANTANDER"};
    protected JComboBox<String> bancoField = new JComboBox<String>(this.bancoString);
    protected JLabel enderecoLabel = Utilitario.geraTitulo("INFORMAÇÕES DE ENDERECO");
    protected JLabel ruaLabel = new JLabel("Rua: ");
    protected JTextField ruaField = Utilitario.geraField();
    protected JLabel numeroLabel = new JLabel("Número: ");
    protected JTextField numeroField = Utilitario.geraField();
    protected JLabel complementoLabel = new JLabel("Complemento: ");
    protected JTextField complementoField = Utilitario.geraField();
    protected JLabel bairroLabel = new JLabel("Bairro: ");
    protected JTextField bairroField = Utilitario.geraField();
    protected JLabel cepLabel = new JLabel("CEP: ");
    protected JTextField cepField = Utilitario.geraField();
    protected JLabel cidadeLabel = new JLabel("Cidade: ");
    protected JTextField cidadeField = Utilitario.geraField();
    protected JLabel estadoLabel = new JLabel("Estado: ");
    protected JTextField estadoField = Utilitario.geraField();
    protected JLabel paisLabel = new JLabel("País: ");
    protected JTextField paisField = Utilitario.geraField();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected String[] cursoStrings = ServidorArmazenamento.gerenciadorCursos.getNomeCursos();
    protected JComboBox<String> cursoField = new JComboBox<String>(cursoStrings);
    protected JButton botaoConfirma = new JButton("CONFIRMAR CADASTRO");
    protected JButton botaoVolta = new JButton("VOLTAR");
    protected AcaoCadastrarAluno acaoBotaoConfirma = new AcaoCadastrarAluno(this);

    public CadastroAluno(JanelaPrincipal patern, PainelOpcoes origem) {

        this.patern = patern;
        this.origem = origem;
        this.setLayout(new GridBagLayout());
        this.constantes.insets = new Insets(5, 5, 5, 5);
        this.botaoConfirma.addActionListener(this.acaoBotaoConfirma);
        this.botaoVolta.addActionListener(new TrocaTela(this, this.origem));
        this.ativoField.setEnabled(false);

        // campo das Informações gerais
        Utilitario.posicionaTitulo(this.informacoesGeraisLabel, this, this.constantes);
        //// campo do curso
        Utilitario.geraCampoCentral(this.cursoLabel, this.cursoField, this, this.constantes);
        //// campo do nome
        Utilitario.geraCampoVertical(this.nomeLabel, this.nomeField, this, this.constantes);
        //// campo do sexo
        Utilitario.geraCampoHorizontal(this.sexoLabel, this.sexoField, this, this.constantes);
        //// campo do dataIngresso
        Utilitario.geraCampoVertical(this.dataIngressoLabel, this.dataIngressoField, this, this.constantes);
        //// campo do ativo
        Utilitario.geraCampoHorizontal(this.ativoLabel, this.ativoField, this, this.constantes);
        //// campo do CPF
        Utilitario.geraCampoVertical(this.cpfLabel, this.cpfField,
         this, this.constantes);
        //// campo da matricula
        Utilitario.geraCampoHorizontal(this.matriculaLabel, this.matriculaField,
         this, this.constantes);
        //// campo da data de nascimento
        Utilitario.geraCampoVertical(this.dataNascimentoLabel, this.dataNascimentoField,
         this, this.constantes);
        //// campo do RG
        Utilitario.geraCampoHorizontal(this.identidadeLabel, this.identidadeField,
         this, this.constantes);

        // campo da Conta Bancária
        Utilitario.posicionaTitulo(this.contaBancariaLabel, this, this.constantes);
        //// campo da escolha do banco
        Utilitario.geraCampoCentral(this.bancoLabel, this.bancoField, this, this.constantes);
        //// campo da agencia
        Utilitario.geraCampoVertical(this.agenciaLabel, this.agenciaField,
         this, this.constantes);
        //// campo da conta
        Utilitario.geraCampoHorizontal(this.contaLabel, this.contaField,
         this, this.constantes);
        //// campo do CPF do Titular
        Utilitario.geraCampoVertical(this.cpfTitularLabel, this.cpfTitularField,
         this, this.constantes);
        //// campo do Nome do Titular
        Utilitario.geraCampoHorizontal(this.nomeTitularLabel, this.nomeTitularField,
         this, this.constantes);

        // campo do Endereço
        Utilitario.posicionaTitulo(this.enderecoLabel, this, this.constantes);
        //// campo da rua
        Utilitario.geraCampoVertical(this.ruaLabel, this.ruaField,
         this, this.constantes);
        //// campo do numero
        Utilitario.geraCampoHorizontal(this.numeroLabel, this.numeroField,
         this, this.constantes);
         //// campo do complemento
        Utilitario.geraCampoVertical(this.complementoLabel, this.complementoField,
        this, this.constantes);
       //// campo do cep
       Utilitario.geraCampoHorizontal(this.cepLabel, this.cepField,
        this, this.constantes);
        //// campo da bairro
        Utilitario.geraCampoVertical(this.bairroLabel, this.bairroField,
         this, this.constantes);
        //// campo do cidade
        Utilitario.geraCampoHorizontal(this.cidadeLabel, this.cidadeField,
         this, this.constantes);
         //// campo da estado
        Utilitario.geraCampoVertical(this.estadoLabel, this.estadoField,
        this, this.constantes);
       //// campo do pais
       Utilitario.geraCampoHorizontal(this.paisLabel, this.paisField,
        this, this.constantes);
        // campo botao de voltar e de confirmar
        Utilitario.geraCampoCentral(this.botaoVolta, this.botaoConfirma,
         this, this.constantes);

        this.setVisible(false);
    }

    /**
     * @return the botaoConfirma
     */
    public JButton getBotaoConfirma() {
        return botaoConfirma;
    }

    public void setaCampos(Aluno aluno) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        if(aluno != null) {
            this.nomeField.setText(aluno.getNome());
            this.cursoField.setSelectedItem(aluno.getCurso().getNome());
            this.sexoField.setSelectedItem(aluno.getSexo());
            this.dataIngressoField.setText(formatador.format(aluno.getDataIngresso()));
            this.ativoField.setSelectedItem(aluno.getAtivo());
            this.cpfField.setText(aluno.getCpf());
            this.matriculaField.setText(aluno.getNumeroMatricula());
            this.dataNascimentoField.setText(formatador.format(aluno.getDataNascimento()));
            this.identidadeField.setText(aluno.getRg());
            this.botaoConfirma.setVisible(false);
            if(aluno.getContaBancaria() != null && aluno.getEndereco() != null) {
                ContaBancaria contaDoAluno = aluno.getContaBancaria();
                this.bancoField.setSelectedItem(contaDoAluno.getNomeBanco());
                this.agenciaField.setText(contaDoAluno.getAgencia());
                this.contaField.setText(contaDoAluno.getConta());
                this.cpfTitularField.setText(contaDoAluno.getCpfTitular());
                this.nomeTitularField.setText(contaDoAluno.getNomeTitular());
                Endereco enderecoDoAluno = aluno.getEndereco();
                this.ruaField.setText(enderecoDoAluno.getRua());
                this.numeroField.setText(String.valueOf(enderecoDoAluno.getNumero()));
                this.complementoField.setText(enderecoDoAluno.getComplemento());
                this.cepField.setText(enderecoDoAluno.getCep());
                this.bairroField.setText(enderecoDoAluno.getBairro());
                this.cidadeField.setText(enderecoDoAluno.getCidade());
                this.estadoField.setText(enderecoDoAluno.getEstado());
                this.paisField.setText(enderecoDoAluno.getPais());
            }
            else if(aluno.getContaBancaria() != null) {
                ContaBancaria contaDoAluno = aluno.getContaBancaria();
                this.bancoField.setSelectedItem(contaDoAluno.getNomeBanco());
                this.agenciaField.setText(contaDoAluno.getAgencia());
                this.contaField.setText(contaDoAluno.getConta());
                this.cpfTitularField.setText(contaDoAluno.getCpfTitular());
                this.nomeTitularField.setText(contaDoAluno.getNomeTitular());
                this.ruaField.setText(null);
                this.numeroField.setText(null);
                this.complementoField.setText(null);
                this.cepField.setText(null);
                this.bairroField.setText(null);
                this.cidadeField.setText(null);
                this.estadoField.setText(null);
                this.paisField.setText(null);
            }
            else if(aluno.getEndereco() != null) {
                Endereco enderecoDoAluno = aluno.getEndereco();
                this.ruaField.setText(enderecoDoAluno.getRua());
                this.numeroField.setText(String.valueOf(enderecoDoAluno.getNumero()));
                this.complementoField.setText(enderecoDoAluno.getComplemento());
                this.cepField.setText(enderecoDoAluno.getCep());
                this.bairroField.setText(enderecoDoAluno.getBairro());
                this.cidadeField.setText(enderecoDoAluno.getCidade());
                this.estadoField.setText(enderecoDoAluno.getEstado());
                this.paisField.setText(enderecoDoAluno.getPais());
                this.bancoField.setSelectedItem(null);
                this.agenciaField.setText(null);
                this.contaField.setText(null);
                this.cpfTitularField.setText(null);
                this.nomeTitularField.setText(null);
            }
            else {
                this.bancoField.setSelectedItem(null);
                this.agenciaField.setText(null);
                this.contaField.setText(null);
                this.cpfTitularField.setText(null);
                this.nomeTitularField.setText(null);
                this.ruaField.setText(null);
                this.numeroField.setText(null);
                this.complementoField.setText(null);
                this.cepField.setText(null);
                this.bairroField.setText(null);
                this.cidadeField.setText(null);
                this.estadoField.setText(null);
                this.paisField.setText(null);
            }
        }
        else {
            this.nomeField.setText(null);
            this.cursoField.setSelectedItem(null);
            this.sexoField.setSelectedItem(null);
            this.dataIngressoField.setText(null);
            this.ativoField.setSelectedItem("SIM");
            this.cpfField.setText(null);
            this.matriculaField.setText(null);
            this.dataNascimentoField.setText(null);
            this.identidadeField.setText(null);
            this.bancoField.setSelectedItem(null);
            this.agenciaField.setText(null);
            this.contaField.setText(null);
            this.cpfTitularField.setText(null);
            this.nomeTitularField.setText(null);
            this.ruaField.setText(null);
            this.numeroField.setText(null);
            this.complementoField.setText(null);
            this.cepField.setText(null);
            this.bairroField.setText(null);
            this.cidadeField.setText(null);
            this.estadoField.setText(null);
            this.paisField.setText(null);
        }
    }

    private void direciona(int comando) {
        if(comando == JanelaPrincipal.PROXIMA_LINHA) {
            this.constantes.gridy = this.constantes.gridy + 1;
            this.constantes.gridx = 0;
        }
        else {
            this.constantes.gridx = this.constantes.gridx + 1;
        }
    }

}