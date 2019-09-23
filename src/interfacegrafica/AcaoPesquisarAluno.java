package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarAluno implements ActionListener {

    private PainelOpcoes origem;
    private PesquisaAluno next;
    private String acao;

    public AcaoPesquisarAluno(String acao, PainelOpcoes origem, PesquisaAluno next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable());
        Utilitario.formataEspacamentoTabela(this.next.alunosPesquisados);
        this.next.opcaoCurso.setSelectedItem(null);
        this.next.cpfAluno.setText(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}