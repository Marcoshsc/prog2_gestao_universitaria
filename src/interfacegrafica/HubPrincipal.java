package interfacegrafica;

import javax.swing.JButton;
import javax.swing.JPanel;

import complementares.Utilitario;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class HubPrincipal extends JPanel {
    
    private PainelOpcoes next;
    private GridBagConstraints constantes = new GridBagConstraints();
    private JButton opcoesAluno = new JButton("MODULO ALUNO");

    protected HubPrincipal(PainelOpcoes next) {
        this.next = next;
        this.setLayout(new GridBagLayout());
        this.constantes.gridy++;
        this.opcoesAluno.addActionListener(new TrocaTela(this, this.next));
        this.add(this.opcoesAluno, this.constantes);
        this.next.botaoVoltar.addActionListener(new TrocaTela(this.next, this));


    }

}