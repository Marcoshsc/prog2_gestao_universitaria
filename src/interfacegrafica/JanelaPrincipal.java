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

import complementares.ContaBancaria;
import complementares.Endereco;
import complementares.Utilitario;
import ensino.classecurso.Curso;
import pessoas.classealuno.Aluno;

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

    public static final int PROXIMA_LINHA = 1;
    public static final int PROXIMA_COLUNA = 0;
    public static final Dimension TAMANHO = new Dimension(120, 20);
    public static final Insets ESPACAMENTO_PADRAO = new Insets(5, 5, 5, 5);
    protected TelaInicial telaInicial = this.new TelaInicial();
    protected TelaCadastro telaCadastro = this.new TelaCadastro();
    protected TelaLogin telaLogin = this.new TelaLogin();
    protected PainelOpcoes painelOpcoes = new PainelOpcoes(this);
    protected CadastroAluno cadastroAluno = new CadastroAluno(this, this.painelOpcoes);
    protected PesquisaAluno pesquisaAluno = new PesquisaAluno(this, this.painelOpcoes, this.cadastroAluno);

    public JanelaPrincipal() {
        this.setSize(800, 700);
        this.setLayout(new GridBagLayout());
        this.setTitle("Sistema de Gestão Universitária");
        this.setMinimumSize(new Dimension(800, 700));
        
        this.add(this.pesquisaAluno);
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

    protected void erroPreenchimento(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
    }

    // protected static JTextField geraField(String codigo) {
    //     JFormattedTextField previo;
    //     try {
    //         switch(codigo) {
    //             case "cpf": {
    //                 previo = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "matricula": {
    //                 previo = new JFormattedTextField();
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "data": {
    //                 previo = new JFormattedTextField(new MaskFormatter("##/##/####"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "rg": {
    //                 previo = new JFormattedTextField(new MaskFormatter("UU-##########"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "agencia": {
    //                 previo = new JFormattedTextField(new MaskFormatter("####-#"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "conta": {
    //                 previo = new JFormattedTextField(new MaskFormatter("#####-#"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "numeroCasa": {
    //                 previo = new JFormattedTextField();
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             case "cep": {
    //                 previo = new JFormattedTextField(new MaskFormatter("#####-###"));
    //                 previo.setColumns(20);
    //                 previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //             } break;
    //             default: {
    //                 throw new Exception("Não foi possível criar o Campo.");
    //             }
    //         }
    //         return previo;
    //     } catch(Exception exc) {
    //         System.out.println("Não foi possível criar o campo.");
    //         return null;
    //     }
    // }

    // protected static JTextField geraField() {
    //     try {
    //         JTextField previo = new JTextField(20);
    //         previo.setMinimumSize(JanelaPrincipal.TAMANHO);
    //         return previo;
    //     } catch(Exception exc) {
    //         System.out.println("Não foi possível criar o campo.");
    //         return null;
    //     }
    // }
    
    // protected static JLabel geraTitulo(String texto) {
    //     JLabel previo = new JLabel(texto);
    //     previo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
    //         BorderFactory.createEmptyBorder(5, 5, 5, 5)));
    //     return previo;
    // }

    // protected static void geraCampoVertical(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
    //     constantes.gridy++;
    //     constantes.gridx = 0;
    //     alvo.add(label, constantes);
    //     constantes.gridx++;
    //     alvo.add(campo, constantes);
    // }

    // protected static void geraCampoHorizontal(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
    //     constantes.gridx++;
    //     alvo.add(label, constantes);
    //     constantes.gridx++;
    //     alvo.add(campo, constantes);
    // }

    // protected static void geraCampoCentral(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
    //     constantes.gridy++;
    //     constantes.gridx = 1;
    //     alvo.add(label, constantes);
    //     constantes.gridx++;
    //     alvo.add(campo, constantes);
    // }

    // protected static void posicionaTitulo(Component titulo, JPanel alvo, GridBagConstraints constantes) {
    //     constantes.gridwidth = 4;
    //     constantes.gridy++;
    //     constantes.gridx = 0;
    //     constantes.insets = new Insets(20, 0, 20, 0);
    //     alvo.add(titulo, constantes);
    //     constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
    //     constantes.gridwidth = 1;
    // }

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
            this.botaoCadastro.addActionListener(new TrocaTela(this, JanelaPrincipal.this.telaCadastro));
            this.botaoLogin.addActionListener(new TrocaTela(this, JanelaPrincipal.this.telaLogin));
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
            this.retornar.addActionListener(new TrocaTela(this, JanelaPrincipal.this.telaInicial));
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
            this.retornar.addActionListener(new TrocaTela(this, JanelaPrincipal.this.telaInicial));
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

    // public class PainelOpcoes extends JPanel {

    //     private GridBagConstraints constantes = new GridBagConstraints();
    //     private JButton cadastrarAluno = new JButton("CADASTRAR ALUNO");
    //     private JButton visualizarAluno = new JButton("VISUALIZAR ALUNO");

    //     public PainelOpcoes() {
    //         this.visualizarAluno.addActionListener(new TrocaTela(this, JanelaPrincipal.this.pesquisaAluno));
    //         this.cadastrarAluno.addActionListener(new ClicouCadastrarAlterarAlunoButton("cadastrar", JanelaPrincipal.this));
    //         this.setLayout(new GridBagLayout());
    //         this.constantes.gridx++;
    //         this.add(this.cadastrarAluno, this.constantes);
    //         this.constantes.gridx++;
    //         this.add(this.visualizarAluno, this.constantes);
    //         this.setVisible(false);

    //     }

    // }

    // public class ClicouBotaoCadastro implements ActionListener {
    
    //     @Override
    //     public void actionPerformed(ActionEvent evento) {
    //         JanelaPrincipal.this.telaInicial.setVisible(false);
    //         JanelaPrincipal.this.telaCadastro.setVisible(true);
    //     }
    
    // }

    // public class ClicouBotaoLogin implements ActionListener {
        
    //     @Override
    //     public void actionPerformed(ActionEvent evento) {
    //         JanelaPrincipal.this.telaInicial.setVisible(false);
    //         JanelaPrincipal.this.telaLogin.setVisible(true);
    //     }

    // }

    // public class ClicouBotaoVoltar implements ActionListener {
        
    //     private String mudanca;

    //     public ClicouBotaoVoltar(String codigo) {
    //         this.mudanca = codigo;
    //     }

    //     @Override
    //     public void actionPerformed(ActionEvent evento) {
    //         if(this.mudanca == "login") {
    //             JanelaPrincipal.this.telaLogin.setVisible(false);
    //             JanelaPrincipal.this.telaInicial.setVisible(true);
    //         }
    //         else if(this.mudanca == "opcoesAluno") {
    //             JanelaPrincipal.this.cadastroAluno.setVisible(false);
    //             JanelaPrincipal.this.painelOpcoes.setVisible(true);
    //         }
    //     }

    //}
}