package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirAluno implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField cpfCampo;

    protected AcaoExcluirAluno(JanelaPrincipal parent, JTextField cpfCampo) {
        this.parent = parent;
        this.cpfCampo = cpfCampo;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Aluno alunoPrevio = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(Utilitario.formataCampo(this.cpfCampo));
        if(alunoPrevio != null) {
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