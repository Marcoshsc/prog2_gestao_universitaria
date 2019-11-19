package interfacegrafica;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceHistorico implements ActionListener {

    JPanel anterior;
    HistoricoAluno fonte;

    public EntrarInterfaceHistorico(HistoricoAluno fonte, JPanel anterior) {
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
