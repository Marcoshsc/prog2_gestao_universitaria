package interfacegrafica;

import javax.swing.*;

import complementares.Utilitario;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Disciplina;
import sistema.classes.ServidorArmazenamento;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class CadastroDisciplina extends JPanel {
    
    protected PainelOpcoesDisciplina origem;
    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JLabel cursoLabel = new JLabel("Curso: ");
    protected JComboBox<String> cursoField = new JComboBox<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos());
    protected JLabel codigoLabel = new JLabel("Código Disciplina: ");
    protected JTextField codigoField = Utilitario.geraField();
    protected JLabel nomeLabel = new JLabel("Nome Disciplina: ");
    protected JTextField nomeField = Utilitario.geraField();
    protected JLabel maximoFaltasLabel = new JLabel("Maximo Faltas: ");
    protected JTextField maximoFaltasField = Utilitario.geraField();
    protected JLabel cargaHorariaLabel = new JLabel("Carga Horária (Horas): ");
    protected JTextField cargaHorariaField = Utilitario.geraField();
    protected JButton botaoConfirma = new JButton("CONFIRMAR CADASTRO");
    protected JButton botaoVolta = new JButton("VOLTAR");
    protected JButton botaoExcluir = new JButton("EXCLUIR DISCIPLINA");
    protected AcaoCadastrarDisciplina acaoBotaoConfirma = new AcaoCadastrarDisciplina(this);

    /**
     *
     * @param parent: janela principal que possui o objeto
     * @param origem: PainelOpcoesDisciplina onde ocorreu o clique
     */
    public CadastroDisciplina(JanelaPrincipal parent, PainelOpcoesDisciplina origem) {

        this.parent = parent;
        this.origem = origem;

        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        this.botaoExcluir.addActionListener(new AcaoExcluirDisciplina(this.parent, this.codigoField));
        this.botaoConfirma.addActionListener(this.acaoBotaoConfirma);
        this.botaoVolta.addActionListener(new TrocaTela(this, this.origem));
        Utilitario.geraCampoCentral(this.cursoLabel, this.cursoField, this, this.constantes);
        Utilitario.geraCampoVertical(this.codigoLabel, this.codigoField, this, this.constantes);
        Utilitario.geraCampoHorizontal(this.nomeLabel, this.nomeField, this, this.constantes);
        Utilitario.geraCampoVertical(this.maximoFaltasLabel, this.maximoFaltasField, this, this.constantes);
        Utilitario.geraCampoHorizontal(this.cargaHorariaLabel, this.cargaHorariaField, this, this.constantes);
        this.constantes.gridy++;
        this.constantes.gridx = 1;
        this.add(this.botaoVolta, this.constantes);
        this.constantes.gridx++;
        this.add(this.botaoExcluir, this.constantes);
        this.constantes.gridx++;
        this.add(this.botaoConfirma, this.constantes);

        this.setVisible(false);
    }

    /**
     *
     * @param acao: view ou change
     * @param disciplina: disciplina cujas informações preencherão os campos
     */
    public void setaCampos(String acao, Disciplina disciplina) {
        if(disciplina != null) {
            this.codigoField.setText(disciplina.getCodigo());
            this.nomeField.setText(disciplina.getNome());
            this.cargaHorariaField.setText(Integer.toString(disciplina.getCargaHoraria()));
            this.maximoFaltasField.setText(Integer.toString(disciplina.getMaximoFaltas()));
            this.cursoField.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));
            this.cursoField.addItem(null);
            this.cursoField.setSelectedItem(null);
            this.cursoField.setSelectedItem(GerenciadorCursos.pesquisaCursoCodigo(disciplina.getCodigoCurso()).getNome());
            if(acao.equals("view")) {
                Utilitario.mudarVisualizacao(false, this.getComponents());
                this.botaoConfirma.setVisible(false);
                this.botaoExcluir.setVisible(false);
            }
            else if(acao.equals("change")) {
                Utilitario.mudarVisualizacao(true, this.getComponents());
                this.botaoConfirma.setText("ALTERAR DISCIPLINA");
                this.codigoField.setEditable(false);
                this.acaoBotaoConfirma.setAcao("alterar");
                this.botaoExcluir.setVisible(true);
                this.botaoConfirma.setVisible(true);
            }
        }
        else {
            Utilitario.mudarVisualizacao(true, this.getComponents());
            this.cursoField.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));
            this.cursoField.addItem(null);
            this.cursoField.setSelectedItem(null);
            this.codigoField.setText(null);
            this.nomeField.setText(null);
            this.cargaHorariaField.setText(null);
            this.maximoFaltasField.setText(null);
            this.botaoConfirma.setVisible(true);
            this.botaoExcluir.setVisible(false);
        }
    }

}