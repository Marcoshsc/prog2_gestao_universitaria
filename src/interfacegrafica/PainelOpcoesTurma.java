package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelOpcoesTurma extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JButton cadastrarTurma = new JButton("CADASTRAR TURMA");
    protected JButton visualizarTurma = new JButton("VISUALIZAR TURMA");
    protected JButton alterarTurma = new JButton("ALTERAR/ LANÇAR NOTAS/ FINALIZAR TURMA");
    protected JButton botaoVoltar = new JButton("VOLTAR");

    /**
     *
     * @param parent: janela principal que possui o objeto.
     */
    public PainelOpcoesTurma(JanelaPrincipal parent) {
        this.parent = parent;
        this.cadastrarTurma.addActionListener(new ClicouCadastrarAlterarTurmaButton("cadastrar", this.parent));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        this.setLayout(new GridBagLayout());
        this.constantes.gridy++;
        this.add(this.cadastrarTurma, this.constantes);
        this.constantes.gridy++;
        this.add(this.visualizarTurma, this.constantes);
        this.constantes.gridy++;
        this.add(this.alterarTurma, this.constantes);
        this.setVisible(false);

        this.constantes.gridy++;
        this.add(this.botaoVoltar, this.constantes);

    }

}
