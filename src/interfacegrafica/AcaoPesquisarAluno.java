package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarAluno implements ActionListener {

    private PainelOpcoes origem;
    private PesquisaAluno next;

    public AcaoPesquisarAluno(PainelOpcoes origem, PesquisaAluno next) {
        this.origem = origem;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.alunosPesquisados.setModel(ServidorArmazenamento.getAlunosTable());
        Utilitario.formataEspacamentoTabela(this.next.alunosPesquisados);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}