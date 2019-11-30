package interfacegrafica;

import contratos.PrecisaZerarCampos;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceZerarCampos implements ActionListener {

    private JPanel anterior;
    private PrecisaZerarCampos fonte;

    /**
     *
     * @param fonte: janela a ser mostrada
     * @param anterior: janela onde aconteceu o clique
     */
    public EntrarInterfaceZerarCampos(PrecisaZerarCampos fonte, JPanel anterior) {
        this.fonte = fonte;
        this.anterior = anterior;
    }

    /**
     *
     * @param e: clicar para entrar na interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.fonte.setaCampos();
        this.anterior.setVisible(false);
        ((JPanel)this.fonte).setVisible(true);
    }
}
