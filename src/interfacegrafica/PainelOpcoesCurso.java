package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelOpcoesCurso extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JButton cadastrarCurso = new JButton("CADASTRAR CURSO");
    protected JButton visualizarCurso = new JButton("PESQUISAR CURSO");
    protected JButton alterarCurso = new JButton("ALTERAR CURSO");
    protected JButton disciplinasCurso = new JButton("CONSULTAR DISCIPLINAS CURSO");
    protected JButton botaoVolta = new JButton("VOLTAR");

    public PainelOpcoesCurso(JanelaPrincipal parent) {
        this.parent = parent;
        
        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        
        this.constantes.gridy++;
        this.cadastrarCurso.addActionListener(new ClicouCadastrarAlterarCursoButton("cadastrar", this.parent));
        this.add(this.cadastrarCurso, this.constantes);

        this.constantes.gridy++;
        this.add(this.visualizarCurso, this.constantes);

        this.constantes.gridy++;
        this.add(this.alterarCurso, this.constantes);

        this.constantes.gridy++;
        this.add(this.disciplinasCurso, this.constantes);

        this.constantes.gridy++;
        this.add(this.botaoVolta, this.constantes);

        this.setVisible(false);


    }

}