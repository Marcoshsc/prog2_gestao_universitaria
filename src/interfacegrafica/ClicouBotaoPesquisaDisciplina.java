package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaDisciplina implements ActionListener {

    private PesquisaDisciplina principal;

    public ClicouBotaoPesquisaDisciplina(PesquisaDisciplina principal) {
        this.principal = principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!(this.principal.opcaoCurso.getSelectedItem() == null)) {
            this.principal.disciplinasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasTable(
                ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.principal.opcaoCurso.getSelectedItem())
            ));
            Utilitario.formataEspacamentoTabela(this.principal.disciplinasPesquisadas, 4);
        }
        else {
            this.principal.disciplinasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasTable());
            Utilitario.formataEspacamentoTabela(this.principal.disciplinasPesquisadas, 4);
            this.principal.opcaoCurso.setSelectedItem(null);
        }
    }



}