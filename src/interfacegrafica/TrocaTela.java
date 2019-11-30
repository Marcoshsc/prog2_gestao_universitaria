package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;

public class TrocaTela implements ActionListener {

    private JPanel telaAnterior;
    private JPanel telaAtual;

    /**
     *
     * @param telaAnterior: JPanel anterior
     * @param telaAtual: próximo JPanel
     */
    public TrocaTela(JPanel telaAnterior, JPanel telaAtual) {
        this.telaAnterior = telaAnterior;
        this.telaAtual = telaAtual;
    }

    /**
     *
     * @param e: clicar no botão para trocar de interface
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.telaAnterior.setVisible(false);
        this.telaAtual.setVisible(true);
    }

}