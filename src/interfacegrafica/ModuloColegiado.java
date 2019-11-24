package interfacegrafica;

import javax.swing.*;
import java.awt.*;

public class ModuloColegiado extends JPanel {

    private JButton opcoesTurma = new JButton("MODULO TURMA");
    protected JButton historicoAlunoButton = new JButton("HISTÓRICO ALUNO");
    protected JButton botaoVoltar = new JButton("VOLTAR");
    protected JButton relatoriosAprovacao = new JButton("RELATORIO APROVAÇÕES");
    private GridBagConstraints constantes = new GridBagConstraints();
    private PainelOpcoesTurma nextTurma;

    public ModuloColegiado(PainelOpcoesTurma opcoesTurma) {
        this.nextTurma = opcoesTurma;

        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        this.constantes.gridy++;
        this.opcoesTurma.addActionListener(new TrocaTela(this, this.nextTurma));
        this.nextTurma.botaoVoltar.addActionListener(new TrocaTela(this.nextTurma, this));
        this.add(this.opcoesTurma, this.constantes);

        this.constantes.gridy++;
        this.add(this.historicoAlunoButton, this.constantes);

        this.constantes.gridy++;
        this.add(this.relatoriosAprovacao, this.constantes);

        this.constantes.gridy++;
        this.add(this.botaoVoltar, this.constantes);

        this.setVisible(false);
    }


}
