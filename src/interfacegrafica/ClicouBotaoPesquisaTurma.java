package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaTurma implements ActionListener {

    private PesquisaTurma principal;

    /**
     *
     * @param principal: PesquisaTurma que possui o botão.
     */
    public ClicouBotaoPesquisaTurma(PesquisaTurma principal) {
        this.principal = principal;
    }

    /**
     *
     * @param e: clicar no botão de pesquisar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(this.principal.opcaoDisciplina.getSelectedItem() == null)) {
            this.principal.turmasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasVigentesTable(
                   (String)this.principal.opcaoDisciplina.getSelectedItem())
            );
            Utilitario.formataEspacamentoTabela(this.principal.turmasPesquisadas, 5);
        }
        else {
            this.principal.turmasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasVigentesTable());
            Utilitario.formataEspacamentoTabela(this.principal.turmasPesquisadas, 4);
            this.principal.opcaoDisciplina.setSelectedItem(null);
        }
    }



}
