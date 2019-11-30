package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import pessoas.classealuno.Aluno;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaAlunoHistorico implements ActionListener {

    private HistoricoAluno principal;

    /**
     *
     * @param principal: HistoricoAluno que possui o botão.
     */
    public ClicouBotaoPesquisaAlunoHistorico(HistoricoAluno principal) {
        this.principal = principal;
    }

    /**
     *
     * @param e: clicar no botão de pesquisar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.principal.cpfAluno.getText().equals("")) {
            Aluno procurado = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(
                    (Utilitario.formataCampo(this.principal.cpfAluno)));
            if(procurado == null) {
                this.principal.patern.erroPreenchimento("Aluno não encontrado");
                this.principal.cpfAluno.setText(null);
                this.principal.nomeAluno.setText(null);
                this.principal.cursoAluno.setText(null);
                this.principal.matriculaAluno.setText(null);
                this.principal.setaCampos();
                return;
            }
            this.principal.nomeAluno.setText(procurado.getNome());
            this.principal.cursoAluno.setText(procurado.getCurso().getNome());
            this.principal.matriculaAluno.setText(procurado.getNumeroMatricula());
            this.principal.alunosPesquisados.setModel(procurado.getDisciplinasConcluidasTable());
            Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados, 6);
        }
        else {
            this.principal.patern.erroPreenchimento("Aluno não encontrado");
            this.principal.nomeAluno.setText(null);
            this.principal.cursoAluno.setText(null);
            this.principal.matriculaAluno.setText(null);
            this.principal.setaCampos();
        }
    }
}
