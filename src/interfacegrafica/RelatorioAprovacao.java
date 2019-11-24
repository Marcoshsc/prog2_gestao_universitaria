package interfacegrafica;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

import javax.swing.*;
import java.awt.*;

public class RelatorioAprovacao extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected JComboBox<String> cursoField = new JComboBox<>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos());
    protected JLabel dataLabel = new JLabel("Data de Filtragem: ");
    protected JTextField dataField = Utilitario.geraField("data");
    protected JLabel disciplinaLabel = new JLabel("Disciplina: ");
    protected JComboBox<String> disciplinaField = new JComboBox<>(
            ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas());
    protected JLabel relatorioLabel = Utilitario.geraTitulo("RELATÓRIO");
    protected JLabel numeroCursadosLabel = new JLabel("Número de Alunos que Cursaram: ");
    protected JTextField numeroCursadosField = Utilitario.geraField();
    protected JLabel numeroAprovadosLabel = new JLabel("Número de Alunos Aprovados: ");
    protected JTextField numeroAprovadosField = Utilitario.geraField();
    protected JLabel indiceAprovacaoLabel = new JLabel("Índice de Alunos Aprovados: ");
    protected JTextField indiceAprovacaoField = Utilitario.geraField();
    protected JButton gerarRelatorioButton = new JButton("GERAR RELATORIO");
    protected JButton botaoVolta = new JButton("VOLTAR");

    public RelatorioAprovacao(JanelaPrincipal parent) {
        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        this.gerarRelatorioButton.addActionListener(new GerarRelatorioAprovacao(this));
        this.cursoField.setSelectedItem(null);
        this.disciplinaField.setSelectedItem(null);
        this.numeroAprovadosField.setEditable(false);
        this.numeroCursadosField.setEditable(false);
        this.indiceAprovacaoField.setEditable(false);

        Utilitario.geraCampoVertical(this.cursoLabel, this.cursoField, this, this.constantes);
        Utilitario.geraCampoHorizontal(this.disciplinaLabel, this.disciplinaField, this, this.constantes);
        Utilitario.geraCampoCentral(this.dataLabel, this.dataField, this, this.constantes);
        Utilitario.geraCampoCentral(this.gerarRelatorioButton, this.botaoVolta, this, this.constantes);
        this.constantes.gridy++;
        this.constantes.gridx--;
        this.constantes.gridwidth = 2;
        this.add(this.relatorioLabel, this.constantes);
        this.constantes.gridwidth = 1;
        Utilitario.geraCampoVertical(this.numeroCursadosLabel, this.numeroCursadosField, this, this.constantes);
        Utilitario.geraCampoHorizontal(this.numeroAprovadosLabel, this.numeroAprovadosField, this, this.constantes);
        Utilitario.geraCampoCentral(this.indiceAprovacaoLabel, this.indiceAprovacaoField, this, this.constantes);

        this.setVisible(false);
    }

    protected void setaCampos() {
        this.cursoField.setModel(new DefaultComboBoxModel<>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));
        this.cursoField.setSelectedItem(null);
        this.dataField.setText(null);
        this.disciplinaField.setModel(new DefaultComboBoxModel<>(ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas()));
        this.disciplinaField.setSelectedItem(null);
        this.numeroAprovadosField.setText(null);
        this.numeroCursadosField.setText(null);
        this.indiceAprovacaoField.setText(null);
    }

    protected void erroPreenchimento(String mensagem) {
        JOptionPane.showMessageDialog(this.parent, mensagem, "Erro de preenchimento", JOptionPane.ERROR_MESSAGE);
    }

}
