package interfacegrafica;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import complementares.Utilitario;
import ensino.classecurso.Curso;

public class CadastroCurso extends JPanel {
    
    protected PainelOpcoesCurso origem;
    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JLabel codigoLabel = new JLabel("Código Curso: ");
    protected JTextField codigoField = Utilitario.geraField();
    protected JLabel nomeLabel = new JLabel("Nome Curso: ");
    protected JTextField nomeField = Utilitario.geraField();
    protected JLabel tempoConclusaoLabel = new JLabel("Tempo de Conclusão (Semestres): ");
    protected JTextField tempoConclusaoField = Utilitario.geraField();
    protected JLabel cargaHorariaLabel = new JLabel("Carga Horária (Horas): ");
    protected JTextField cargaHorariaField = Utilitario.geraField();
    protected JButton botaoConfirma = new JButton("CONFIRMAR CADASTRO");
    protected JButton botaoVolta = new JButton("VOLTAR");
    protected JButton botaoExcluir = new JButton("EXCLUIR CURSO");
    protected AcaoCadastrarCurso acaoBotaoConfirma = new AcaoCadastrarCurso(this);

    public CadastroCurso(JanelaPrincipal parent, PainelOpcoesCurso origem) {

        this.parent = parent;
        this.origem = origem;

        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        this.botaoExcluir.addActionListener(new AcaoExcluirCurso(this.parent, this.codigoField));
        this.botaoConfirma.addActionListener(this.acaoBotaoConfirma);
        Utilitario.geraCampoVertical(this.codigoLabel, this.codigoField, this, this.constantes);
        Utilitario.geraCampoHorizontal(this.nomeLabel, this.nomeField, this, this.constantes);
        Utilitario.geraCampoVertical(this.tempoConclusaoLabel, this.tempoConclusaoField, this, this.constantes);
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

    public void setaCampos(String acao, Curso curso) {
        if(curso != null) {
            this.codigoField.setText(curso.getCodigo());
            this.nomeField.setText(curso.getNome());
            this.cargaHorariaField.setText(Integer.toString(curso.getCargaHoraria()));
            this.tempoConclusaoField.setText(Integer.toString(curso.getTempoConclusao()));
            if(acao.equals("view")) {
                Utilitario.mudarVisualizacao(false, this.getComponents());
                this.botaoConfirma.setVisible(false);
                this.botaoExcluir.setVisible(false);
            }
            else if(acao.equals("change")) {
                Utilitario.mudarVisualizacao(true, this.getComponents());
                this.botaoConfirma.setText("ALTERAR CURSO");
                this.acaoBotaoConfirma.setAcao("alterar");
                this.codigoField.setEditable(false);
                this.nomeField.setEditable(false);
                this.botaoExcluir.setVisible(true);
                this.botaoConfirma.setVisible(true);
            }
        }
        else {
            Utilitario.mudarVisualizacao(true, this.getComponents());
            this.codigoField.setText(null);
            this.nomeField.setText(null);
            this.cargaHorariaField.setText(null);
            this.tempoConclusaoField.setText(null);
            this.botaoConfirma.setVisible(true);
            this.botaoExcluir.setVisible(false);
        }
    }

}