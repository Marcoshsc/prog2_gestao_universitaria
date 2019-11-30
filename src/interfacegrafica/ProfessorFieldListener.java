package interfacegrafica;

import complementares.Utilitario;
import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ProfessorFieldListener extends KeyAdapter {

    private JTextField campo1;
    private JTextField campo2;

    /**
     *
     * @param campo1: campo onde serão digitadas as informações
     * @param campo2: campo onde será mostrado o nome do professor
     */
    public ProfessorFieldListener(JTextField campo1, JTextField campo2) {
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

    /**
     *
     * @param e: digitar no campo do professor
     */
    @Override
    public void keyReleased(KeyEvent e) {
        Professor a = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(Utilitario.formataCampo(
                campo1
        ));
        if(a != null) {
            String b = a.getNome();
            campo2.setText(b);
        }
        else
            campo2.setText("NÃO ENCONTRADO");
    }
}
