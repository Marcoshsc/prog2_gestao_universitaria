package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.text.MaskFormatter;

import ensino.classecurso.Curso;

import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

import sistema.classes.ServidorArmazenamento;

public class JanelaPrincipal extends JFrame {

    private static final int PROXIMA_LINHA = 1;
    private static final int PROXIMA_COLUNA = 0;
    private static final Dimension TAMANHO = new Dimension(120, 20);
    private static final Insets ESPACAMENTO_PADRAO = new Insets(5, 5, 5, 5);
    TelaInicial telaInicial = this.new TelaInicial();
    TelaCadastro telaCadastro = this.new TelaCadastro();
    TelaLogin telaLogin = this.new TelaLogin();
    CadastroAluno cadastroAluno = this.new CadastroAluno();
    PainelOpcoes painelOpcoes = this.new PainelOpcoes();

    public JanelaPrincipal() {
        this.setSize(600, 600);
        this.setLayout(new GridBagLayout());
        this.setTitle("Sistema de Gestão Universitária");
        this.setMinimumSize(new Dimension(600, 700));
        
        this.add(this.painelOpcoes);
        this.painelOpcoes.setVisible(true);
        this.add(this.cadastroAluno);
        // this.add(this.telaInicial);
        // this.telaInicial.setVisible(true);
        // this.add(this.telaCadastro);
        // this.add(this.telaLogin);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private static JTextField geraField(String codigo) {
        JFormattedTextField previo;
        try {
            switch(codigo) {
                case "cpf": {
                    previo = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "matricula": {
                    previo = new JFormattedTextField(new MaskFormatter("####"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "data": {
                    previo = new JFormattedTextField(new MaskFormatter("##/##/####"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "rg": {
                    previo = new JFormattedTextField(new MaskFormatter("UU-##########"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "agencia": {
                    previo = new JFormattedTextField(new MaskFormatter("####-#"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "conta": {
                    previo = new JFormattedTextField(new MaskFormatter("########-#"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "numeroCasa": {
                    previo = new JFormattedTextField(new MaskFormatter("#####"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "cep": {
                    previo = new JFormattedTextField(new MaskFormatter("#####-###"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                default: {
                    throw new Exception("Não foi possível criar o Campo.");
                }
            }
            return previo;
        } catch(Exception exc) {
            System.out.println("Não foi possível criar o campo.");
            return null;
        }
    }

    private static JTextField geraField() {
        try {
            JTextField previo = new JTextField(20);
            previo.setMinimumSize(JanelaPrincipal.TAMANHO);
            return previo;
        } catch(Exception exc) {
            System.out.println("Não foi possível criar o campo.");
            return null;
        }
    }
    
    private static JLabel geraTitulo(String texto) {
        JLabel previo = new JLabel(texto);
        previo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return previo;
    }

    private static void geraCampoVertical(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridy++;
        constantes.gridx = 0;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    private static void geraCampoHorizontal(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridx++;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    private static void geraCampoCentral(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridy++;
        constantes.gridx = 1;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    private static void posicionaTitulo(Component titulo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridwidth = 4;
        constantes.gridy++;
        constantes.gridx = 0;
        constantes.insets = new Insets(20, 0, 20, 0);
        alvo.add(titulo, constantes);
        constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        constantes.gridwidth = 1;
    }

    public class TelaInicial extends JPanel {

        private JButton botaoLogin = new JButton("LOGIN");
        private JButton botaoCadastro = new JButton("CADASTRO");
        private JLabel mensagemBoasVindas = new JLabel("SISTEMA DE GESTÃO UNIVERSITÁRIA", JLabel.CENTER);
        private GridBagConstraints constantes = new GridBagConstraints();
    
        public TelaInicial() {
            setLayout(new GridBagLayout());
            this.mensagemBoasVindas.setVisible(true);
            constantes.insets = new Insets(5, 5, 5, 5);
            direciona(JanelaPrincipal.PROXIMA_LINHA);
            add(this.mensagemBoasVindas, constantes);
            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.botaoCadastro.addActionListener(new ClicouBotaoCadastro());
            this.botaoLogin.addActionListener(new ClicouBotaoLogin());
            add(this.botaoCadastro, constantes);
            direciona(JanelaPrincipal.PROXIMA_LINHA);
            add(this.botaoLogin, constantes);
            direciona(JanelaPrincipal.PROXIMA_LINHA);
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
    
        /**
         * @return the botaoLogin
         */
        public JButton getBotaoLogin() {
            return botaoLogin;
        }
    
        /**
         * @return the botaoCadastro
         */
        public JButton getBotaoCadastro() {
            return botaoCadastro;
        }
    
    }

    public class TelaCadastro extends JPanel {

        private GridBagConstraints constantes = new GridBagConstraints();
        private JLabel cpfLabel = new JLabel("CPF: ");
        private JTextField cpfField = new JTextField(20);
        private JLabel userLabel = new JLabel("Cadastre um Usuário: ");
        private JLabel passwordLabel = new JLabel("Cadastre uma Senha: ");
        private JTextField userField = new JTextField(20);
        private JPasswordField passwordField = new JPasswordField(20);
        private JButton botaoConfirma = new JButton("ENTRAR NO SISTEMA");
        private JButton retornar = new JButton("VOLTAR");

        public TelaCadastro() {   
            this.setLayout(new GridBagLayout());
            this.constantes.insets = new Insets(5, 5, 5, 5);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.add(this.cpfLabel, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.cpfField, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.add(this.userLabel, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.userField, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.add(this.passwordLabel, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.passwordField, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.retornar.addActionListener(new ClicouBotaoVoltar("cadastro"));
            this.add(this.retornar, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.botaoConfirma, this.constantes);

            this.setVisible(false);
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

    public class TelaLogin extends JPanel {

        private GridBagConstraints constantes = new GridBagConstraints();
        private JLabel userLabel = new JLabel("Usuário: ");
        private JLabel passwordLabel = new JLabel("Senha: ");
        private JTextField userField = new JTextField(20);
        private JPasswordField passwordField = new JPasswordField(20);
        private JButton confirma = new JButton("LOGIN");
        private JButton retornar = new JButton("VOLTAR");

        public TelaLogin() {
            this.setLayout(new GridBagLayout());
            this.constantes.insets = new Insets(5, 5, 5, 5);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.add(this.userLabel, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.userField, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.add(this.passwordLabel, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.passwordField, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_LINHA);
            this.retornar.addActionListener(new ClicouBotaoVoltar("login"));
            this.add(this.retornar, this.constantes);

            direciona(JanelaPrincipal.PROXIMA_COLUNA);
            this.add(this.confirma, this.constantes);

            this.setVisible(false);
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

    public class CadastroAluno extends JPanel {

        private GridBagConstraints constantes = new GridBagConstraints();
        private JLabel dataIngressoLabel = new JLabel("Data de Ingresso: ");
        private JTextField dataIngressoField = geraField("data");
        private JLabel ativoLabel = new JLabel("Ativo: ");
        private String[] ativoString = {"SIM", "NÃO"};
        private JComboBox<String> ativoField = new JComboBox<String>(ativoString);
        private JLabel nomeLabel = new JLabel("Nome: ");
        private JTextField nomeField = geraField();
        private JLabel sexoLabel = new JLabel("Sexo: ");
        private String[] sexoString = {"Masculino", "Feminino"};
        private JComboBox<String> sexoField = new JComboBox<String>(sexoString);
        private JLabel informacoesGeraisLabel = geraTitulo("INFORMAÇÕES GERAIS");
        private JLabel cpfLabel = new JLabel("CPF: ");
        private JTextField cpfField = geraField("cpf");
        private JLabel matriculaLabel = new JLabel("Matrícula: ");
        private JTextField matriculaField = geraField("matricula");
        private JLabel dataNascimentoLabel = new JLabel("Data de Nascimento: ");
        private JTextField dataNascimentoField = geraField("data");
        private JLabel identidadeLabel = new JLabel("RG: ");
        private JTextField identidadeField = geraField("rg");
        private JLabel contaBancariaLabel = geraTitulo("INFORMAÇÕES DE CONTA BANCÁRIA");
        private JLabel agenciaLabel = new JLabel("Agência: ");
        private JTextField agenciaField = geraField("agencia");
        private JLabel contaLabel = new JLabel("Conta: ");
        private JTextField contaField = geraField("conta");
        private JLabel cpfTitularLabel = new JLabel("CPF do Titular: ");
        private JTextField cpfTitularField = geraField("cpf");
        private JLabel nomeTitularLabel = new JLabel("Nome do Titular: ");
        private JTextField nomeTitularField = geraField();
        private JLabel bancoLabel = new JLabel("Nome do Banco: ");
        private String[] bancoString = {"BANCO DO BRASIL", "ITAÚ", "BRADESCO", "SANTANDER"};
        private JComboBox<String> bancoField = new JComboBox<String>(this.bancoString);
        private JLabel enderecoLabel = geraTitulo("INFORMAÇÕES DE ENDERECO");
        private JLabel ruaLabel = new JLabel("Rua: ");
        private JTextField ruaField = geraField();
        private JLabel numeroLabel = new JLabel("Número: ");
        private JTextField numeroField = geraField("numeroCasa");
        private JLabel complementoLabel = new JLabel("Complemento: ");
        private JTextField complementoField = geraField();
        private JLabel bairroLabel = new JLabel("Bairro: ");
        private JTextField bairroField = geraField();
        private JLabel cepLabel = new JLabel("CEP: ");
        private JTextField cepField = geraField("cep");
        private JLabel cidadeLabel = new JLabel("Cidade: ");
        private JTextField cidadeField = geraField();
        private JLabel estadoLabel = new JLabel("Estado: ");
        private JTextField estadoField = geraField();
        private JLabel paisLabel = new JLabel("País: ");
        private JTextField paisField = geraField();
        private JLabel cursoLabel = new JLabel("Curso: ");
        private String[] cursoStrings = ServidorArmazenamento.getNomeCursos();
        private JComboBox<String> cursoField = new JComboBox<String>(cursoStrings);
        private JButton botaoConfirma = new JButton("CONFIRMAR CADASTRO");
        private JButton botaoVolta = new JButton("VOLTAR");

        public CadastroAluno() {
            this.setLayout(new GridBagLayout());
            this.constantes.insets = new Insets(5, 5, 5, 5);
            this.botaoConfirma.addActionListener(new AcaoCadastrarAluno(this));

            // campo das Informações gerais
            JanelaPrincipal.posicionaTitulo(this.informacoesGeraisLabel, this, this.constantes);
            //// campo do curso
            JanelaPrincipal.geraCampoCentral(this.cursoLabel, this.cursoField, this, this.constantes);
            //// campo do nome
            JanelaPrincipal.geraCampoVertical(this.nomeLabel, this.nomeField, this, this.constantes);
            //// campo do sexo
            JanelaPrincipal.geraCampoHorizontal(this.sexoLabel, this.sexoField, this, this.constantes);
            //// campo do dataIngresso
            JanelaPrincipal.geraCampoVertical(this.dataIngressoLabel, this.dataIngressoField, this, this.constantes);
            //// campo do ativo
            JanelaPrincipal.geraCampoHorizontal(this.ativoLabel, this.ativoField, this, this.constantes);
            //// campo do CPF
            JanelaPrincipal.geraCampoVertical(this.cpfLabel, this.cpfField,
             this, this.constantes);
            //// campo da matricula
            JanelaPrincipal.geraCampoHorizontal(this.matriculaLabel, this.matriculaField,
             this, this.constantes);
            //// campo da data de nascimento
            JanelaPrincipal.geraCampoVertical(this.dataNascimentoLabel, this.dataNascimentoField,
             this, this.constantes);
            //// campo do RG
            JanelaPrincipal.geraCampoHorizontal(this.identidadeLabel, this.identidadeField,
             this, this.constantes);

            // campo da Conta Bancária
            JanelaPrincipal.posicionaTitulo(this.contaBancariaLabel, this, this.constantes);
            //// campo da escolha do banco
            JanelaPrincipal.geraCampoCentral(this.bancoLabel, this.bancoField, this, this.constantes);
            //// campo da agencia
            JanelaPrincipal.geraCampoVertical(this.agenciaLabel, this.agenciaField,
             this, this.constantes);
            //// campo da conta
            JanelaPrincipal.geraCampoHorizontal(this.contaLabel, this.contaField,
             this, this.constantes);
            //// campo do CPF do Titular
            JanelaPrincipal.geraCampoVertical(this.cpfTitularLabel, this.cpfTitularField,
             this, this.constantes);
            //// campo do Nome do Titular
            JanelaPrincipal.geraCampoHorizontal(this.nomeTitularLabel, this.nomeTitularField,
             this, this.constantes);

            // campo do Endereço
            JanelaPrincipal.posicionaTitulo(this.enderecoLabel, this, this.constantes);
            //// campo da rua
            JanelaPrincipal.geraCampoVertical(this.ruaLabel, this.ruaField,
             this, this.constantes);
            //// campo do numero
            JanelaPrincipal.geraCampoHorizontal(this.numeroLabel, this.numeroField,
             this, this.constantes);
             //// campo do complemento
            JanelaPrincipal.geraCampoVertical(this.complementoLabel, this.complementoField,
            this, this.constantes);
           //// campo do cep
           JanelaPrincipal.geraCampoHorizontal(this.cepLabel, this.cepField,
            this, this.constantes);
            //// campo da bairro
            JanelaPrincipal.geraCampoVertical(this.bairroLabel, this.bairroField,
             this, this.constantes);
            //// campo do cidade
            JanelaPrincipal.geraCampoHorizontal(this.cidadeLabel, this.cidadeField,
             this, this.constantes);
             //// campo da estado
            JanelaPrincipal.geraCampoVertical(this.estadoLabel, this.estadoField,
            this, this.constantes);
           //// campo do pais
           JanelaPrincipal.geraCampoHorizontal(this.paisLabel, this.paisField,
            this, this.constantes);
            // campo botao de voltar e de confirmar
            JanelaPrincipal.geraCampoCentral(this.botaoVolta, this.botaoConfirma,
             this, this.constantes);

            this.setVisible(false);
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

    public class PainelOpcoes extends JPanel {

        private GridBagConstraints constantes = new GridBagConstraints();
        private JButton cadastrarAluno = new JButton("CADASTRAR ALUNO");

        public PainelOpcoes() {

            this.cadastrarAluno.addActionListener(JanelaPrincipal.this.new ClicouCadastrarAlunoButton());
            this.setLayout(new GridBagLayout());
            this.constantes.gridx++;
            this.add(cadastrarAluno, constantes);
            this.setVisible(false);

        }

    }

    public class AcaoCadastrarAluno implements ActionListener {

        private CadastroAluno campos;

        public AcaoCadastrarAluno(CadastroAluno campos) {
            this.campos = campos;
        }

        @Override
        public void actionPerformed(ActionEvent evento) {
            Curso cursoSelecionado = ServidorArmazenamento.pesquisaCursoNome(
            (String)this.campos.cursoField.getSelectedItem());
            if(cursoSelecionado == null) {
                JOptionPane.showMessageDialog(JanelaPrincipal.this, "Curso inválido.");
            }
            else {
                
            }
        }

    }

    public class ClicouCadastrarAlunoButton implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent evento) {
            JanelaPrincipal.this.painelOpcoes.setVisible(false);
            JanelaPrincipal.this.cadastroAluno.setVisible(true);
        }

    }

    public class ClicouBotaoCadastro implements ActionListener {
    
        @Override
        public void actionPerformed(ActionEvent evento) {
            JanelaPrincipal.this.telaInicial.setVisible(false);
            JanelaPrincipal.this.telaCadastro.setVisible(true);
        }
    
    }

    public class ClicouBotaoLogin implements ActionListener {
        
        @Override
        public void actionPerformed(ActionEvent evento) {
            JanelaPrincipal.this.telaInicial.setVisible(false);
            JanelaPrincipal.this.telaLogin.setVisible(true);
        }

    }

    public class ClicouBotaoVoltar implements ActionListener {
        
        private String mudanca;

        public ClicouBotaoVoltar(String codigo) {
            this.mudanca = codigo;
        }

        @Override
        public void actionPerformed(ActionEvent evento) {
            if(this.mudanca == "login") {
                JanelaPrincipal.this.telaLogin.setVisible(false);
                JanelaPrincipal.this.telaInicial.setVisible(true);
            }
            else {
                JanelaPrincipal.this.telaCadastro.setVisible(false);
                JanelaPrincipal.this.telaInicial.setVisible(true);
            }
        }

    }
}