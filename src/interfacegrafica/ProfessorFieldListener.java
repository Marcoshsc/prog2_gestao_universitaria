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

    public ProfessorFieldListener(JTextField campo1, JTextField campo2) {
        this.campo1 = campo1;
        this.campo2 = campo2;
    }

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
