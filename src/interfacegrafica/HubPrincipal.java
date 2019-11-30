package interfacegrafica;

import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

public class HubPrincipal extends JPanel {
    
    private PainelOpcoesAluno nextAluno;
    private PainelOpcoesCurso nextCurso;
    private PainelOpcoesProfessor nextProfessor;
    private PainelOpcoesDisciplina nextDisciplina;
    private ModuloColegiado moduloColegiado;
    private GridBagConstraints constantes = new GridBagConstraints();
    private JButton opcoesAluno = new JButton("MODULO ALUNO");
    private JButton opcoesCurso = new JButton("MODULO CURSO");
    private JButton opcoesProfessor = new JButton("MODULO PROFESSOR");
    private JButton opcoesDisciplina = new JButton("MODULO DISCIPLINA");
    private JButton moduloColegiadoButton = new JButton(("MODULO COLEGIADO"));

    /**
     *
     * @param nextAluno: PainelOpcoesAluno
     * @param nextCurso: PainelOpcoesCurso
     * @param nextProfessor: PainelOpcoesProfessor
     * @param nextDisciplina: PainelOpcoesDisciplina
     * @param moduloColegiado: ModuloColegiado
     */
    protected HubPrincipal(PainelOpcoesAluno nextAluno, PainelOpcoesCurso nextCurso,
    PainelOpcoesProfessor nextProfessor, PainelOpcoesDisciplina nextDisciplina, ModuloColegiado moduloColegiado) {
        this.nextAluno = nextAluno;
        this.nextCurso = nextCurso;
        this.nextProfessor = nextProfessor;
        this.nextDisciplina = nextDisciplina;
        this.moduloColegiado = moduloColegiado;
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

        this.constantes.gridy++;
        this.opcoesDisciplina.addActionListener(new TrocaTela(this, this.nextDisciplina));
        this.add(this.opcoesDisciplina, this.constantes);

        this.constantes.gridy++;
        this.moduloColegiadoButton.addActionListener(new TrocaTela(this, this.moduloColegiado));
        this.add(this.moduloColegiadoButton, this.constantes);

    }

}