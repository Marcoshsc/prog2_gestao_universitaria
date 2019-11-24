package interfacegrafica;

import ensino.secaodisciplina.Disciplina;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceDisciplinasMinistradas implements ActionListener {

    private JPanel anterior;
    private DisciplinasMinistradas fonte;

    public EntrarInterfaceDisciplinasMinistradas(DisciplinasMinistradas fonte, JPanel anterior) {
        this.fonte = fonte;
        this.anterior = anterior;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fonte.setaCampos();
        this.anterior.setVisible(false);
        this.fonte.setVisible(true);
    }
}
