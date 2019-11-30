package interfacegrafica;

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import complementares.Utilitario;
import interfacegrafica.PainelOpcoesAluno;
import sistema.classes.ServidorArmazenamento;

public class PesquisaAluno extends JPanel {

    protected PainelOpcoesAluno origem;
    protected JanelaPrincipal patern;
    protected CadastroAluno relacionado1;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cpfLabel = new JLabel("CPF Aluno: ");
    protected JTextField cpfAluno = Utilitario.geraField();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected String[] cursoStrings = ServidorArmazenamento.gerenciadorCursos.getNomeCursos();
    protected JComboBox<String> opcaoCurso = new JComboBox<String>(this.cursoStrings);
    protected JTable alunosPesquisados = new JTable();
    protected JScrollPane alunosContainer = new JScrollPane(this.alunosPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");
    protected ClicouCelulaAluno clicouCelula;

    /**
     *
     * @param patern: janela principal que possui o objeto.
     * @param origem: interface onde aconteceu o clique
     * @param relacionado1: interface onde serão mostrados os dados
     */
    protected PesquisaAluno(JanelaPrincipal patern, PainelOpcoesAluno origem, CadastroAluno relacionado1) {
        
        this.patern = patern;
        this.origem = origem;
        this.relacionado1 = relacionado1;
        this.alunosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setMinimumSize(new Dimension(700, 500));
        this.alunosContainer.setPreferredSize(new Dimension(1000, 500));
        this.alunosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.alunosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable());
        this.clicouCelula = new ClicouCelulaAluno("cadastrar", this.alunosPesquisados, this.patern, this, this.relacionado1);
        this.alunosPesquisados.addMouseListener(this.clicouCelula);
        Utilitario.formataEspacamentoTabela(this.alunosPesquisados, 7);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaAluno(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        Utilitario.geraCampoHorizontal(this.cpfLabel, this.cpfAluno, this.painelSegurador, this.constantes);
        Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        this.add(this.alunosContainer);
    }

}