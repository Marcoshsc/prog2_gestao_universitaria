package interfacegrafica;

import ensino.secaodisciplina.Disciplina;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceDisciplinasCursadas implements ActionListener {

    JPanel anterior;
    DisciplinasCursadas fonte;

    public EntrarInterfaceDisciplinasCursadas(DisciplinasCursadas fonte, JPanel anterior) {
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
