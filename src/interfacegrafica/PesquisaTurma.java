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
import ensino.secaodisciplina.GerenciadorDisciplinas;
import sistema.classes.ServidorArmazenamento;

public class PesquisaTurma extends JPanel {

    protected PainelOpcoesTurma origem;
    protected JanelaPrincipal patern;
    protected CadastroTurma relacionado1;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cursoLabel = new JLabel("Disciplina: ");
    protected String[] disciplinasStrings = ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas();
    protected JComboBox<String> opcaoDisciplina = new JComboBox<String>(this.disciplinasStrings);
    protected JTable turmasPesquisadas = new JTable();
    protected JScrollPane turmasContainer = new JScrollPane(this.turmasPesquisadas);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");
    protected ClicouCelulaTurma clicouCelula;

    protected PesquisaTurma(JanelaPrincipal patern, PainelOpcoesTurma origem, CadastroTurma relacionado1) {
        this.opcaoDisciplina.addItem(null);
        this.patern = patern;
        this.origem = origem;
        this.relacionado1 = relacionado1;
        this.turmasContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.turmasContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.turmasContainer.setMinimumSize(new Dimension(700, 500));
        this.turmasContainer.setPreferredSize(new Dimension(1000, 500));
        this.turmasContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.turmasPesquisadas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.turmasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasVigentesTable());
        this.clicouCelula = new ClicouCelulaTurma("cadastrar", this.turmasPesquisadas, this.patern, this, this.relacionado1);
        this.turmasPesquisadas.addMouseListener(this.clicouCelula);
        Utilitario.formataEspacamentoTabela(this.turmasPesquisadas, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaTurma(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        Utilitario.geraCampoCentral(this.cursoLabel, this.opcaoDisciplina, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);

        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfAluno, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.turmasContainer);
    }

}