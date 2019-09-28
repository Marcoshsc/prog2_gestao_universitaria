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

public class PesquisaCurso extends JPanel {

    protected PainelOpcoesCurso origem;
    protected JanelaPrincipal patern;
    protected CadastroCurso relacionado1;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel codigoLabel = new JLabel("Codigo Curso: ");
    protected JTextField codigoCurso = Utilitario.geraField();
    protected JTable cursosPesquisados = new JTable();
    protected JScrollPane cursosContainer = new JScrollPane(this.cursosPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");
    protected ClicouCelulaCurso clicouCelula;

    protected PesquisaCurso(JanelaPrincipal patern, PainelOpcoesCurso origem, CadastroCurso relacionado1) {
        
        this.patern = patern;
        this.origem = origem;
        this.relacionado1 = relacionado1;
        this.cursosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.cursosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.cursosContainer.setMinimumSize(new Dimension(700, 500));
        this.cursosContainer.setPreferredSize(new Dimension(1000, 500));
        this.cursosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.cursosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.cursosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable());
        this.clicouCelula = new ClicouCelulaCurso("cadastrar", this.cursosPesquisados, this.patern, this, this.relacionado1);
        this.cursosPesquisados.addMouseListener(this.clicouCelula);
        Utilitario.formataEspacamentoTabela(this.cursosPesquisados, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaCurso(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        Utilitario.geraCampoHorizontal(this.codigoLabel, this.codigoCurso, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);

        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfAluno, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.cursosContainer);
    }

}