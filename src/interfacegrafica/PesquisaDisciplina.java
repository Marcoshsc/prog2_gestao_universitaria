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
import sistema.classes.ServidorArmazenamento;

public class PesquisaDisciplina extends JPanel {

    protected PainelOpcoesDisciplina origem;
    protected JanelaPrincipal patern;
    protected CadastroDisciplina relacionado1;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected String[] cursoStrings = ServidorArmazenamento.gerenciadorCursos.getNomeCursos();
    protected JComboBox<String> opcaoCurso = new JComboBox<String>(this.cursoStrings);
    protected JTable disciplinasPesquisadas = new JTable();
    protected JScrollPane disciplinasContainer = new JScrollPane(this.disciplinasPesquisadas);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");
    protected ClicouCelulaDisciplina clicouCelula;

    protected PesquisaDisciplina(JanelaPrincipal patern, PainelOpcoesDisciplina origem, CadastroDisciplina relacionado1) {
        this.opcaoCurso.addItem(null);
        this.patern = patern;
        this.origem = origem;
        this.relacionado1 = relacionado1;
        this.disciplinasContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.disciplinasContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.disciplinasContainer.setMinimumSize(new Dimension(700, 500));
        this.disciplinasContainer.setPreferredSize(new Dimension(1000, 500));
        this.disciplinasContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.disciplinasPesquisadas.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.disciplinasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasTable());
        this.clicouCelula = new ClicouCelulaDisciplina("cadastrar", this.disciplinasPesquisadas, this.patern, this, this.relacionado1);
        this.disciplinasPesquisadas.addMouseListener(this.clicouCelula);
        Utilitario.formataEspacamentoTabela(this.disciplinasPesquisadas, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaDisciplina(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        Utilitario.geraCampoCentral(this.cursoLabel, this.opcaoCurso, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);

        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfAluno, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.disciplinasContainer);
    }

}