package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaCurso implements ActionListener {

    private PesquisaCurso principal;

    /**
     *
     * @param principal: PesquisaCurso que possui o botão.
     */
    public ClicouBotaoPesquisaCurso(PesquisaCurso principal) {
        this.principal = principal;
    }

    /**
     *
     * @param e: clicar no botão de pesquisar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.principal.codigoCurso.getText().equals("")) {
            this.principal.cursosPesquisados.setModel(ServidorArmazenamento.gerenciadorCursos.getCursosTable(
                this.principal.codigoCurso.getText()
            ));
            Utilitario.formataEspacamentoTabela(this.principal.cursosPesquisados, 4);
        }
        else {
            this.principal.cursosPesquisados.setModel(ServidorArmazenamento.gerenciadorCursos.getCursosTable());
            Utilitario.formataEspacamentoTabela(this.principal.cursosPesquisados, 4);
        }
    }



}