package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelOpcoes extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JButton cadastrarAluno = new JButton("CADASTRAR ALUNO");
    protected JButton visualizarAluno = new JButton("VISUALIZAR ALUNO");

    public PainelOpcoes(JanelaPrincipal parent) {
        this.parent = parent;
        // this.visualizarAluno.addActionListener(new AcaoPesquisarAluno(this, this.relacionado1));
        this.cadastrarAluno.addActionListener(new ClicouCadastrarAlterarAlunoButton("cadastrar", this.parent));
        this.setLayout(new GridBagLayout());
        this.constantes.gridx++;
        this.add(this.cadastrarAluno, this.constantes);
        this.constantes.gridx++;
        this.add(this.visualizarAluno, this.constantes);
        this.setVisible(false);

    }

}