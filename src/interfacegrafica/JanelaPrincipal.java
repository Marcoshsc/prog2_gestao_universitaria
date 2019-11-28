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
    protected DisciplinasDoCurso disciplinasDoCurso = new DisciplinasDoCurso(this, this.painelOpcoesCurso);
    protected RelatorioAprovacao relatorioAprovacao = new RelatorioAprovacao(this);

    private JanelaPrincipal() {
        this.setSize(1000, 700);
        this.setLayout(new GridBagLayout());
        this.setTitle("Sistema de Gestão Universitária");
        this.setMinimumSize(new Dimension(1000, 700));

        this.moduloColegiado.historicoAlunoButton.addActionListener(new EntrarInterfaceHistorico(this.historicoAluno, this.moduloColegiado));
        this.moduloColegiado.botaoVoltar.addActionListener(new TrocaTela(this.moduloColegiado, this.hubPrincipal));

        this.relatorioAprovacao.botaoVolta.addActionListener(new TrocaTela(this.relatorioAprovacao, this.moduloColegiado));
        this.moduloColegiado.relatoriosAprovacao.addActionListener(new EntrarInterfaceRelatorios(this.relatorioAprovacao, this.moduloColegiado));

        this.painelOpcoesAluno.disciplinasCursadas.addActionListener(
                new EntrarInterfaceDisciplinasCursadas(this.disciplinasCursadas, this.painelOpcoesAluno));
        this.painelOpcoesProfessor.disciplinasMinistradas.addActionListener(
                new EntrarInterfaceDisciplinasMinistradas(this.disciplinasMinistradas, this.painelOpcoesProfessor));
        this.painelOpcoesCurso.disciplinasCurso.addActionListener(
                new EntrarInterfaceDisciplinasCurso(this.disciplinasDoCurso, this.painelOpcoesCurso));
        
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
        this.add(this.disciplinasDoCurso);
        this.add(this.relatorioAprovacao);

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
}