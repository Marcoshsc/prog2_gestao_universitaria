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
        this.next.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable());
        Utilitario.formataEspacamentoTabela(this.next.alunosPesquisados);
        this.next.opcaoCurso.setSelectedItem(null);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}