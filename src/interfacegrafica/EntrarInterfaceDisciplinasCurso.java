package interfacegrafica;

import complementares.Utilitario;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EntrarInterfaceDisciplinasCurso implements ActionListener {

    private JPanel anterior;
    private DisciplinasDoCurso fonte;

    public EntrarInterfaceDisciplinasCurso(DisciplinasDoCurso fonte, JPanel anterior) {
        this.fonte = fonte;
        this.anterior = anterior;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.fonte.setaCampos();
        Utilitario.formataEspacamentoTabela(this.fonte.cursosPesquisados, 4);
        this.anterior.setVisible(false);
        this.fonte.setVisible(true);
    }
}
