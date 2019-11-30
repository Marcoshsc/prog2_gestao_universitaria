package interfacegrafica;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PainelOpcoesDisciplina extends JPanel {

    protected JanelaPrincipal parent;

    protected GridBagConstraints constantes = new GridBagConstraints();
    protected JButton cadastrarDisciplina = new JButton("CADASTRAR DISCIPLINA");
    protected JButton visualizarDisciplina = new JButton("PESQUISAR DISCIPLINA");
    protected JButton alterarDisciplina = new JButton("ALTERAR DISCIPLINA");
    protected JButton botaoVolta = new JButton("VOLTAR");

    /**
     *
     * @param parent: janela principal que possui o objeto.
     */
    public PainelOpcoesDisciplina(JanelaPrincipal parent) {
        this.parent = parent;
        
        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        
        this.constantes.gridy++;
        this.cadastrarDisciplina.addActionListener(new ClicouCadastrarAlterarDisciplinaButton("cadastrar", this.parent));
        this.add(this.cadastrarDisciplina, this.constantes);

        this.constantes.gridy++;
        this.add(this.visualizarDisciplina, this.constantes);

        this.constantes.gridy++;
        this.add(this.alterarDisciplina, this.constantes);

        this.constantes.gridy++;
        this.add(this.botaoVolta, this.constantes);

        this.setVisible(false);


    }

}