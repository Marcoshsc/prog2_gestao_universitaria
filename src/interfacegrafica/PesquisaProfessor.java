package interfacegrafica;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class PesquisaProfessor extends JPanel {

    protected PainelOpcoesProfessor origem;
    protected JanelaPrincipal patern;
    protected CadastroProfessor relacionado1;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cpfLabel = new JLabel("CPF Professor: ");
    protected JTextField cpfProfessor = Utilitario.geraField();
    protected JTable professoresPesquisados = new JTable();
    protected JScrollPane professoresContainer = new JScrollPane(this.professoresPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");
    protected ClicouCelulaProfessor clicouCelula;

    protected PesquisaProfessor(JanelaPrincipal patern, PainelOpcoesProfessor origem, CadastroProfessor relacionado1) {
        
        this.patern = patern;
        this.origem = origem;
        this.relacionado1 = relacionado1;
        this.professoresContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.professoresContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.professoresContainer.setMinimumSize(new Dimension(700, 500));
        this.professoresContainer.setPreferredSize(new Dimension(1000, 500));
        this.professoresContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.professoresPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.professoresPesquisados.setModel(ServidorArmazenamento.gerenciadorProfessores.getProfessoresTable());
        this.clicouCelula = new ClicouCelulaProfessor("cadastrar", this.professoresPesquisados, this.patern, this, this.relacionado1);
        this.professoresPesquisados.addMouseListener(this.clicouCelula);
        Utilitario.formataEspacamentoTabela(this.professoresPesquisados, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaProfessor(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        Utilitario.geraCampoHorizontal(this.cpfLabel, this.cpfProfessor, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);

        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfAluno, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.professoresContainer);
    }

}