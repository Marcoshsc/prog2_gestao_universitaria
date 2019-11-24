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
import interfacegrafica.*;
import sistema.classes.ServidorArmazenamento;

public class DisciplinasMinistradas extends JPanel {

    protected PainelOpcoesProfessor origem;
    protected JanelaPrincipal patern;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cpfLabel = new JLabel("CPF Professor: ");
    protected JTextField cpfProfessor = Utilitario.geraField();
    protected JLabel nomeLabel = new JLabel("Nome: ");
    protected JTextField nomeProfessor = Utilitario.geraField();
    protected JTable alunosPesquisados = new JTable();
    protected JScrollPane alunosContainer = new JScrollPane(this.alunosPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");

    protected DisciplinasMinistradas(JanelaPrincipal patern, PainelOpcoesProfessor origem) {

        this.patern = patern;
        this.origem = origem;
        this.nomeProfessor.setEditable(false);
        this.alunosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setMinimumSize(new Dimension(700, 500));
        this.alunosContainer.setPreferredSize(new Dimension(1000, 500));
        this.alunosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.alunosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.alunosPesquisados.setModel(this.retornaTabelaVazia());
        this.alunosPesquisados.addMouseListener(new ClicouCelulaTurma("view", this.alunosPesquisados,
                this.patern, this, this.patern.cadastroTurma));
        Utilitario.formataEspacamentoTabela(this.alunosPesquisados, 4);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaProfessorMinistradas(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        Utilitario.geraCampoHorizontal(this.cpfLabel, this.cpfProfessor, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);
        Utilitario.geraCampoVertical(this.nomeLabel, this.nomeProfessor, this.painelSegurador, this.constantes);
        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfProfessor, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.alunosContainer);
    }

    protected TableModel retornaTabelaVazia() {
        String[] header = {
                "Código", "Disciplina", "Data Início", "Data Fim"
        };
        return new DefaultTableModel(null, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    public void setaCampos() {
        this.alunosPesquisados.setModel(this.retornaTabelaVazia());
        this.cpfProfessor.setText(null);
        this.nomeProfessor.setText(null);
    }

}
