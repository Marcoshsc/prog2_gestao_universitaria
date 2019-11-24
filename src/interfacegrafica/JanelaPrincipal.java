package interfacegrafica;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.Dimension;
import java.awt.GridBagConstraints;

public class JanelaPrincipal extends JFrame {

    private static JanelaPrincipal instance = null;
    public static final int PROXIMA_LINHA = 1;
    public static final int PROXIMA_COLUNA = 0;
    public static final Dimension TAMANHO = new Dimension(120, 20);
    public static final Insets ESPACAMENTO_PADRAO = new Insets(5, 5, 5, 5);
    protected TelaInicial telaInicial = this.new TelaInicial();
    protected TelaCadastro telaCadastro = this.new TelaCadastro();
    protected TelaLogin telaLogin = this.new TelaLogin();
    protected PainelOpcoesAluno painelOpcoesAluno = new PainelOpcoesAluno(this);
    protected PainelOpcoesCurso painelOpcoesCurso = new PainelOpcoesCurso(this);
    protected PainelOpcoesProfessor painelOpcoesProfessor = new PainelOpcoesProfessor(this);
    protected PainelOpcoesDisciplina painelOpcoesDisciplina = new PainelOpcoesDisciplina(this);
    protected PainelOpcoesTurma painelOpcoesTurma = new PainelOpcoesTurma(this);
    protected CadastroTurma cadastroTurma = new CadastroTurma(this, this.painelOpcoesTurma);
    protected ModuloColegiado moduloColegiado = new ModuloColegiado(this.painelOpcoesTurma);
    protected HistoricoAluno historicoAluno = new HistoricoAluno(this, this.moduloColegiado);
    protected HubPrincipal hubPrincipal = new HubPrincipal(this.painelOpcoesAluno, this.painelOpcoesCurso, 
    this.painelOpcoesProfessor, this.painelOpcoesDisciplina, this.moduloColegiado);
    protected CadastroAluno cadastroAluno = new CadastroAluno(this, this.painelOpcoesAluno);
    protected CadastroCurso cadastroCurso = new CadastroCurso(this, this.painelOpcoesCurso);
    protected CadastroProfessor cadastroProfessor = new CadastroProfessor(this, this.painelOpcoesProfessor);
    protected CadastroDisciplina cadastroDisciplina = new CadastroDisciplina(this, this.painelOpcoesDisciplina);
    protected PesquisaAluno pesquisaAluno = new PesquisaAluno(this, this.painelOpcoesAluno, this.cadastroAluno);
    protected PesquisaCurso pesquisaCurso = new PesquisaCurso(this, this.painelOpcoesCurso, this.cadastroCurso);
    protected PesquisaProfessor pesquisaProfessor = new PesquisaProfessor(this, this.painelOpcoesProfessor, this.cadastroProfessor);
    protected PesquisaDisciplina pesquisaDisciplina = new PesquisaDisciplina(this, this.painelOpcoesDisciplina, this.cadastroDisciplina);
    protected PesquisaTurma pesquisaTurma = new PesquisaTurma(this, this.painelOpcoesTurma, this.cadastroTurma);
    protected DisciplinasCursadas disciplinasCursadas = new DisciplinasCursadas(this, this.painelOpcoesAluno);
    protected DisciplinasMinistradas disciplinasMinistradas = new DisciplinasMinistradas(this, this.painelOpcoesProfessor);

    public JanelaPrincipal() {
        this.setSize(1000, 700);
        this.setLayout(new GridBagLayout());
        this.setTitle("Sistema de Gestão Universitária");
        this.setMinimumSize(new Dimension(1000, 700));

        this.moduloColegiado.historicoAlunoButton.addActionListener(new EntrarInterfaceHistorico(this.historicoAluno, this.moduloColegiado));
        this.moduloColegiado.botaoVoltar.addActionListener(new TrocaTela(this.moduloColegiado, this.hubPrincipal));

        this.painelOpcoesAluno.disciplinasCursadas.addActionListener(
                new EntrarInterfaceDisciplinasCursadas(this.disciplinasCursadas, this.painelOpcoesAluno));
        this.painelOpcoesProfessor.disciplinasMinistradas.addActionListener(
                new EntrarInterfaceDisciplinasMinistradas(this.disciplinasMinistradas, this.painelOpcoesProfessor));
        
        this.cadastroCurso.botaoVolta.addActionListener(new TrocaTela(this.cadastroCurso, this.painelOpcoesCurso));
        this.painelOpcoesCurso.botaoVolta.addActionListener(new TrocaTela(this.painelOpcoesCurso, this.hubPrincipal));
        this.painelOpcoesCurso.visualizarCurso.addActionListener(new AcaoPesquisarCurso("view", this.painelOpcoesCurso, this.pesquisaCurso));
        this.painelOpcoesCurso.alterarCurso.addActionListener(new AcaoPesquisarCurso("change", this.painelOpcoesCurso, this.pesquisaCurso));

        this.painelOpcoesAluno.visualizarAluno.addActionListener(new AcaoPesquisarAluno("view", this.painelOpcoesAluno, this.pesquisaAluno));
        this.painelOpcoesAluno.alterarAluno.addActionListener(new AcaoPesquisarAluno("change", this.painelOpcoesAluno, this.pesquisaAluno));
        
        this.painelOpcoesProfessor.botaoVoltar.addActionListener(new TrocaTela(this.painelOpcoesProfessor, this.hubPrincipal));
        this.painelOpcoesProfessor.visualizarProfessor.addActionListener(new AcaoPesquisarProfessor("view", this.painelOpcoesProfessor, this.pesquisaProfessor));
        this.painelOpcoesProfessor.alterarProfessor.addActionListener(new AcaoPesquisarProfessor("change", this.painelOpcoesProfessor, this.pesquisaProfessor));

        this.painelOpcoesDisciplina.botaoVolta.addActionListener(new TrocaTela(this.painelOpcoesDisciplina, this.hubPrincipal));
        this.painelOpcoesDisciplina.visualizarDisciplina.addActionListener(new AcaoPesquisarDisciplina("view", this.painelOpcoesDisciplina, this.pesquisaDisciplina));
        this.painelOpcoesDisciplina.alterarDisciplina.addActionListener(new AcaoPesquisarDisciplina("change", this.painelOpcoesDisciplina, this.pesquisaDisciplina));

        this.painelOpcoesTurma.visualizarTurma.addActionListener(new AcaoPesquisarTurma("view", this.painelOpcoesTurma, this.pesquisaTurma));
        this.painelOpcoesTurma.alterarTurma.addActionListener(new AcaoPesquisarTurma("change", this.painelOpcoesTurma, this.pesquisaTurma));

        this.add(this.pesquisaAluno);
        this.add(this.pesquisaCurso);
        this.add(this.pesquisaProfessor);
        this.add(this.pesquisaDisciplina);
        this.add(this.pesquisaTurma);

        this.add(this.historicoAluno);
        this.add(this.disciplinasCursadas);
        this.add(this.disciplinasMinistradas);

        this.add(this.moduloColegiado);

        this.add(this.painelOpcoesAluno);
        this.add(this.painelOpcoesCurso);
        this.add(this.painelOpcoesProfessor);
        this.add(this.painelOpcoesDisciplina);
        this.add(this.painelOpcoesTurma);

        this.add(this.hubPrincipal);
        this.hubPrincipal.setVisible(true);
        this.add(this.cadastroAluno);
        this.add(this.cadastroCurso);
        this.add(this.cadastroProfessor);
        this.add(this.cadastroDisciplina);
        this.add(this.cadastroTurma);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    protected void erroPreenchimento(String mensagem) {
        JOptionPane.showMessageDialog(this, mensagem, "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
    }

    public static JanelaPrincipal getInstance() {
        if(instance == null)
            instance = new JanelaPrincipal();
        return instance;
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