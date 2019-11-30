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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import complementares.Utilitario;
import contratos.PrecisaZerarCampos;
import interfacegrafica.PainelOpcoesAluno;
import sistema.classes.ServidorArmazenamento;

public class DisciplinasDoCurso extends JPanel implements PrecisaZerarCampos {

    protected PainelOpcoesCurso origem;
    protected JanelaPrincipal patern;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel codigoLabel = new JLabel("Código Curso: ");
    protected JTextField codigoCurso = Utilitario.geraField();
    protected JLabel nomeLabel = new JLabel("Nome: ");
    protected JTextField nomeCurso = Utilitario.geraField();
    protected JTable cursosPesquisados = new JTable();
    protected JScrollPane cursosContainer = new JScrollPane(this.cursosPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");

    /**
     *
     * @param patern: janela principal que possui o objeto
     * @param origem: PainelOpcoesCurso onde aconteceu o clique
     */
    protected DisciplinasDoCurso(JanelaPrincipal patern, PainelOpcoesCurso origem) {

        this.patern = patern;
        this.origem = origem;
        this.nomeCurso.setEditable(false);
        this.cursosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.cursosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.cursosContainer.setMinimumSize(new Dimension(700, 500));
        this.cursosContainer.setPreferredSize(new Dimension(1000, 500));
        this.cursosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.cursosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.cursosPesquisados.setModel(this.retornaTabelaVazia());
        this.cursosPesquisados.addMouseListener(new ClicouCelulaDisciplina("view", this.cursosPesquisados,
                this.patern, this, this.patern.cadastroDisciplina));
        Utilitario.formataEspacamentoTabela(this.cursosPesquisados, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaCursoDisciplina(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        Utilitario.geraCampoHorizontal(this.codigoLabel, this.codigoCurso, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);
        Utilitario.geraCampoVertical(this.nomeLabel, this.nomeCurso, this.painelSegurador, this.constantes);
        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.codigoLabel, this.codigoCurso, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.cursosContainer);
    }

    /**
     *
     * @return tabela vazia para mostrar na tela.
     */
    protected TableModel retornaTabelaVazia() {
        String[] header = {
                "Codigo", "Nome", "Carga Horária", "Maximo Faltas"
        };
        return new DefaultTableModel(null, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    /**
     *
     */
    public void setaCampos() {
        this.cursosPesquisados.setModel(this.retornaTabelaVazia());
        Utilitario.formataEspacamentoTabela(this.cursosPesquisados, 4);
        this.codigoCurso.setText(null);
        this.nomeCurso.setText(null);
    }

}
