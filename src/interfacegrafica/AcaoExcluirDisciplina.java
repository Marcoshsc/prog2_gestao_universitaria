package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirDisciplina implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField codigoDisciplina;

    protected AcaoExcluirDisciplina(JanelaPrincipal parent, JTextField codigoDisciplina) {
        this.parent = parent;
        this.codigoDisciplina = codigoDisciplina;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Disciplina disciplinaPrevia = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(Utilitario.formataCampo(this.codigoDisciplina));
        if(disciplinaPrevia != null) {
            if(GerenciadorDisciplinas.verificaVinculoTurma(disciplinaPrevia)) {
                this.parent.erroPreenchimento("Não é possível excluir pois existem vínculos com curso e/ou turmas.");
                return;
            }
            Curso cursoPrevio = GerenciadorCursos.pesquisaCursoCodigo(disciplinaPrevia.getCodigoCurso());
            cursoPrevio.getDisciplinasRelacionadas().remove(disciplinaPrevia);
            ServidorArmazenamento.gerenciadorDisciplinas.excluirDisciplina(disciplinaPrevia);
            JOptionPane.showMessageDialog(this.parent, "Disciplina excluída com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.parent.cadastroDisciplina.setVisible(false);
            this.parent.painelOpcoesDisciplina.setVisible(true);
        }
        else {
            this.parent.erroPreenchimento("DISCIPLINA NÃO EXISTE. IMPOSSÍVEL EXCLUIR.");
            this.parent.cadastroDisciplina.setVisible(false);
            this.parent.painelOpcoesDisciplina.setVisible(true);
        }
    }
}
