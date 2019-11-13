package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirTurma implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField codigoTurma;

    protected AcaoExcluirTurma(JanelaPrincipal parent, JTextField codigoTurma) {
        this.parent = parent;
        this.codigoTurma = codigoTurma;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DisciplinaAplicada turmaPrevia = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(Utilitario.formataCampo(this.codigoTurma));
        if(turmaPrevia != null) {
//            if(GerenciadorAluno.verificaVinculoCurso(cursoPrevio)) {
//                VERIFICAR EXISTENCIA DE VINCULOS COM TURMAS
//            }
            ServidorArmazenamento.gerenciadorDisciplinas.excluirDisciplinaVigente(turmaPrevia);
            JOptionPane.showMessageDialog(this.parent, "Disciplina excluída com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.parent.cadastroTurma.setVisible(false);
            this.parent.painelOpcoesTurma.setVisible(true);
        }
        else {
            this.parent.erroPreenchimento("TURMA NÃO EXISTE. IMPOSSÍVEL EXCLUIR.");
            this.parent.cadastroTurma.setVisible(false);
            this.parent.painelOpcoesTurma.setVisible(true);
        }
    }
}
