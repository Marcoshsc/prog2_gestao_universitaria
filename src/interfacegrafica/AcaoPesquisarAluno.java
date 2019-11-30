package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarAluno implements ActionListener {

    private PainelOpcoesAluno origem;
    private PesquisaAluno next;
    private String acao;

    /**
     *
     * @param acao: view ou change
     * @param origem: PainelOpcoesAluno referente
     * @param next: proximo campo
     */
    public AcaoPesquisarAluno(String acao, PainelOpcoesAluno origem, PesquisaAluno next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    /**
     *
     * @param e: clicar no botão visualizar aluno
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable());
        Utilitario.formataEspacamentoTabela(this.next.alunosPesquisados, 7);
        this.next.opcaoCurso.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));
        this.next.opcaoCurso.setSelectedItem(null);
        this.next.cpfAluno.setText(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}