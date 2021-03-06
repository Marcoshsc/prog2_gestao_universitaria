package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirAluno implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField cpfCampo;

    /**
     *
     * @param parent JanelaPrincipal que possui o objeto
     * @param cpfCampo: JTextField referente ao CPF do aluno
     */
    protected AcaoExcluirAluno(JanelaPrincipal parent, JTextField cpfCampo) {
        this.parent = parent;
        this.cpfCampo = cpfCampo;
    }

    /**
     *
     * @param e: clicar no botão de excluir aluno.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        Aluno alunoPrevio = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(Utilitario.formataCampo(this.cpfCampo));
        if(alunoPrevio != null) {
            if(alunoPrevio.temVinculo() || GerenciadorDisciplinas.verificaVinculoAluno(alunoPrevio)) {
                this.parent.erroPreenchimento("Não foi possível excluir aluno pois existe vínculo com disciplinas concluídas e/ou turmas.");
                return;
            }
            GerenciadorAluno.excluir(alunoPrevio);
            JOptionPane.showMessageDialog(this.parent, "Aluno excluído com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.parent.cadastroAluno.setVisible(false);
            this.parent.painelOpcoesAluno.setVisible(true);
        }
        else {
            this.parent.erroPreenchimento("ALUNO NÃO EXISTE. IMPOSSÍVEL EXCLUIR.");
            this.parent.cadastroAluno.setVisible(false);
            this.parent.painelOpcoesAluno.setVisible(true);
        }
    }
}