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

public class HistoricoAluno extends JPanel implements PrecisaZerarCampos {

    protected ModuloColegiado origem;
    protected JanelaPrincipal patern;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JPanel painelSegurador = new JPanel();
    protected JLabel cpfLabel = new JLabel("CPF Aluno: ");
    protected JTextField cpfAluno = Utilitario.geraField();
    protected JLabel nomeLabel = new JLabel("Nome: ");
    protected JTextField nomeAluno = Utilitario.geraField();
    protected JLabel matriculaLabel = new JLabel("Matrícula: ");
    protected JTextField matriculaAluno = Utilitario.geraField();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected JTextField cursoAluno = Utilitario.geraField();
    protected JTable alunosPesquisados = new JTable();
    protected JScrollPane alunosContainer = new JScrollPane(this.alunosPesquisados);
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton botaoPesquisar = new JButton("PESQUISAR");

    /**
     *
     * @param patern: janela principal que possui o objeto
     * @param origem: ModuloColegiado onde ocorreu o clique
     */
    protected HistoricoAluno(JanelaPrincipal patern, ModuloColegiado origem) {

        this.patern = patern;
        this.origem = origem;
        this.nomeAluno.setEditable(false);
        this.matriculaAluno.setEditable(false);
        this.cursoAluno.setEditable(false);
        this.alunosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        this.alunosContainer.setMinimumSize(new Dimension(700, 500));
        this.alunosContainer.setPreferredSize(new Dimension(1000, 500));
        this.alunosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));

        this.alunosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        this.alunosPesquisados.setModel(this.retornaTabelaVazia());
        Utilitario.formataEspacamentoTabela(this.alunosPesquisados, 6);

        this.painelSegurador.setLayout(new GridBagLayout());
        this.botaoPesquisar.addActionListener(new ClicouBotaoPesquisaAlunoHistorico(this));
        this.botaoVoltar.addActionListener(new TrocaTela(this, this.origem));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        Utilitario.geraCampoHorizontal(this.cpfLabel, this.cpfAluno, this.painelSegurador, this.constantes);
        this.constantes.gridy++;
        Utilitario.geraCampoCentral(this.botaoVoltar, this.botaoPesquisar, this.painelSegurador, this.constantes);
        Utilitario.geraCampoVertical(this.nomeLabel, this.nomeAluno, this.painelSegurador, this.constantes);
        Utilitario.geraCampoVertical(this.matriculaLabel, this.matriculaAluno, this.painelSegurador, this.constantes);
        Utilitario.geraCampoVertical(this.cursoLabel, this.cursoAluno, this.painelSegurador, this.constantes);
        // this.setLayout(new GridBagLayout());
        this.setVisible(false);
        this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        this.add(this.painelSegurador);
        // Utilitario.geraCampoVertical(this.cpfLabel, this.cpfAluno, this, this.constantes);
        // Utilitario.geraCampoHorizontal(this.cursoLabel, this.opcaoCurso, this, this.constantes);
        // this.constantes.gridx++;
        this.add(this.alunosContainer);
    }

    /**
     *
     * @return tabela vazia para ser mostrada na tela.
     */
    protected TableModel retornaTabelaVazia() {
        String[] header = {
                "Disciplina", "Data Conclusão", "Semestre", "Nota", "Faltas", "Aprovado"
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
        this.alunosPesquisados.setModel(this.retornaTabelaVazia());
        Utilitario.formataEspacamentoTabela(this.alunosPesquisados, 6);
        this.cpfAluno.setText(null);
        this.nomeAluno.setText(null);
        this.matriculaAluno.setText(null);
        this.cursoAluno.setText(null);
    }

}
