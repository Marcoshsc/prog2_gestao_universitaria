package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaProfessor implements ActionListener {

    private PesquisaProfessor principal;

    /**
     *
     * @param principal: PesquisaProfessor que possui o botão.
     */
    public ClicouBotaoPesquisaProfessor(PesquisaProfessor principal) {
        this.principal = principal;
    }

    /**
     *
     * @param e: clicar no botão de pesquisar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!Utilitario.formataCampo(principal.cpfProfessor).equals("")) {
            this.principal.professoresPesquisados.setModel(ServidorArmazenamento.gerenciadorProfessores.getProfessoresTable(
                this.principal.cpfProfessor.getText()
            ));
            Utilitario.formataEspacamentoTabela(this.principal.professoresPesquisados, 6);
        }
        else {
            this.principal.professoresPesquisados.setModel(ServidorArmazenamento.gerenciadorProfessores.getProfessoresTable());
            Utilitario.formataEspacamentoTabela(this.principal.professoresPesquisados, 6);
        }
    }



}