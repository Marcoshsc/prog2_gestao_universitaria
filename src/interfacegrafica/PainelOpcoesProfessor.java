package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelOpcoesProfessor extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JButton cadastrarProfessor = new JButton("CADASTRAR PROFESSOR");
    protected JButton visualizarProfessor = new JButton("VISUALIZAR PROFESSOR");
    protected JButton alterarProfessor = new JButton("ALTERAR PROFESSOR");
    protected JButton botaoVoltar = new JButton("VOLTAR");

    public PainelOpcoesProfessor(JanelaPrincipal parent) {
        this.parent = parent;
        // this.visualizarProfessor.addActionListener(new AcaoPesquisarProfessor(this, this.relacionado1));
        // this.cadastrarProfessor.addActionListener(new ClicouCadastrarAlterarProfessorButton("cadastrar", this.parent));
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        this.setLayout(new GridBagLayout());
        this.constantes.gridy++;
        this.add(this.cadastrarProfessor, this.constantes);
        this.constantes.gridy++;
        this.add(this.visualizarProfessor, this.constantes);
        this.constantes.gridy++;
        this.add(this.alterarProfessor, this.constantes);
        this.setVisible(false);

        this.constantes.gridy++;
        this.add(this.botaoVoltar, this.constantes);

    }

}

