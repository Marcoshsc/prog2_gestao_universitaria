package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

import complementares.Utilitario;
import pessoas.classeprofessor.GerenciadorProfessor;
import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

public class AcaoExcluirProfessor implements ActionListener {

    private JanelaPrincipal parent;
    private JTextField cpfProfessor;

    protected AcaoExcluirProfessor(JanelaPrincipal parent, JTextField cpfProfessor) {
        this.parent = parent;
        this.cpfProfessor = cpfProfessor;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Professor professorPrevio = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(Utilitario.formataCampo(this.cpfProfessor));
        if(professorPrevio != null) {
            if(professorPrevio.temVinculo()) {
                this.parent.erroPreenchimento("Impossível excluir o professor pois há vínculos com disciplinas.");
            }
            GerenciadorProfessor.excluir(professorPrevio);
            JOptionPane.showMessageDialog(this.parent, "Professor excluído com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.parent.cadastroProfessor.setVisible(false);
            this.parent.painelOpcoesProfessor.setVisible(true);
        }
        else {
            this.parent.erroPreenchimento("PROFESSOR NÃO EXISTE. IMPOSSÍVEL EXCLUIR.");
            this.parent.cadastroProfessor.setVisible(false);
            this.parent.painelOpcoesProfessor.setVisible(true);
        }
    }
}