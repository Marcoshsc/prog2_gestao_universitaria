package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import pessoas.classealuno.Aluno;
import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

public class ClicouBotaoPesquisaProfessorMinistradas implements ActionListener {

    private DisciplinasMinistradas principal;

    public ClicouBotaoPesquisaProfessorMinistradas(DisciplinasMinistradas principal) {
        this.principal = principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.principal.cpfProfessor.getText().equals("")) {
            Professor procurado = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(
                    (Utilitario.formataCampo(this.principal.cpfProfessor)));
            if(procurado == null) {
                this.principal.patern.erroPreenchimento("Professor não encontrado");
                this.principal.cpfProfessor.setText(null);
                this.principal.nomeProfessor.setText(null);
                this.principal.alunosPesquisados.setModel(this.principal.retornaTabelaVazia());
                Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados, 4);
                return;
            }
            this.principal.nomeProfessor.setText(procurado.getNome());
            this.principal.alunosPesquisados.setModel(procurado.getDisciplinasMinistradasTable());
            Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados, 4);
        }
        else {
            this.principal.patern.erroPreenchimento("Professor não encontrado");
            this.principal.nomeProfessor.setText(null);
            this.principal.alunosPesquisados.setModel(this.principal.retornaTabelaVazia());
            Utilitario.formataEspacamentoTabela(this.principal.alunosPesquisados, 4);
        }
    }
}
