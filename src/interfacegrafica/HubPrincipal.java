package interfacegrafica;

import javax.swing.JButton;
import javax.swing.JPanel;

import complementares.Utilitario;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class HubPrincipal extends JPanel {
    
    private PainelOpcoesAluno nextAluno;
    private PainelOpcoesCurso nextCurso;
    private PainelOpcoesProfessor nextProfessor;
    private GridBagConstraints constantes = new GridBagConstraints();
    private JButton opcoesAluno = new JButton("MODULO ALUNO");
    private JButton opcoesCurso = new JButton("MODULO CURSO");
    private JButton opcoesProfessor = new JButton("MODULO PROFESSOR");

    protected HubPrincipal(PainelOpcoesAluno nextAluno, PainelOpcoesCurso nextCurso, PainelOpcoesProfessor nextProfessor) {
        this.nextAluno = nextAluno;
        this.nextCurso = nextCurso;
        this.nextProfessor = nextProfessor;
        this.setLayout(new GridBagLayout());
        this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;

        this.constantes.gridy++;
        this.opcoesAluno.addActionListener(new TrocaTela(this, this.nextAluno));
        this.nextAluno.botaoVoltar.addActionListener(new TrocaTela(this.nextAluno, this));
        this.add(this.opcoesAluno, this.constantes);

        this.constantes.gridy++;
        this.opcoesCurso.addActionListener(new TrocaTela(this, this.nextCurso));
        this.add(this.opcoesCurso, this.constantes);

        
        this.constantes.gridy++;
        this.opcoesProfessor.addActionListener(new TrocaTela(this, this.nextProfessor));
        this.add(this.opcoesProfessor, this.constantes);

    }

}