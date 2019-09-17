package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JTable;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaAluno implements ActionListener {

    private PesquisaAluno principal;

    public ClicouBotaoPesquisaAluno(PesquisaAluno principal) {
        this.principal = principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.principal.cpfAluno.getText().equals("")) {
            this.principal.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable(
                this.principal.cpfAluno.getText()
            ));
            this.principal.opcaoCurso.setSelectedItem(null);
            Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados);
        }
        else {
            this.principal.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorAlunos.getAlunosTable(
                ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)principal.opcaoCurso.getSelectedItem())
            ));
            Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados);
        }
    }



}