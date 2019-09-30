package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirCurso implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField codigoCurso;

    protected AcaoExcluirCurso(JanelaPrincipal parent, JTextField codigoCurso) {
        this.parent = parent;
        this.codigoCurso = codigoCurso;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Curso cursoPrevio = GerenciadorCursos.pesquisaCursoCodigo(Utilitario.formataCampo(this.codigoCurso));
        if(cursoPrevio != null) {
            if(GerenciadorAluno.verificaVinculoCurso(cursoPrevio)) {
                this.parent.erroPreenchimento("Há Vínculos de alunos com este curso. Impossível Excluir.");
                return;
            }
            GerenciadorCursos.excluir(cursoPrevio);
            JOptionPane.showMessageDialog(this.parent, "Curso excluído com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.parent.cadastroCurso.setVisible(false);
            this.parent.painelOpcoesCurso.setVisible(true);
        }
        else {
            this.parent.erroPreenchimento("CURSO NÃO EXISTE. IMPOSSÍVEL EXCLUIR.");
            this.parent.cadastroAluno.setVisible(false);
            this.parent.painelOpcoesAluno.setVisible(true);
        }
    }
}