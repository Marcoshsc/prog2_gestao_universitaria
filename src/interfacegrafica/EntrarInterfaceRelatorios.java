package interfacegrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceRelatorios implements ActionListener {

    JPanel anterior;
    RelatorioAprovacao fonte;

    public EntrarInterfaceRelatorios(RelatorioAprovacao fonte, JPanel anterior) {
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
