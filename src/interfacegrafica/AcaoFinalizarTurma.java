package interfacegrafica;

import complementares.Utilitario;
import ensino.secaodisciplina.*;
import sistema.classes.ServidorArmazenamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;

public class AcaoFinalizarTurma implements ActionListener {

    private CadastroTurma campos;

    public AcaoFinalizarTurma(CadastroTurma campos) {
        this.campos = campos;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DisciplinaAplicada turma = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(
                Utilitario.formataCampo(this.campos.codigoField)
        );
        if(turma == null) {
            this.campos.parent.erroPreenchimento("Turma inexistente.");
            return;
        }
        for(Boletim i: turma.getAlunosMatriculados()) {
            i.getAluno().adicionaDisciplinaConcluida(new DisciplinaConcluida((Disciplina)turma, i.getAluno().getCpf(),
                    turma.getSemestre(), i.getNota(), i.getFaltas(), LocalDate.now()));
        }
        try {
            GerenciadorDisciplinas.atualizaBancoDisciplinaConcluida();
        } catch(Exception exc) {
            System.out.println("Não foi possível atualizar o banco de disciplina");
        }
        ServidorArmazenamento.gerenciadorDisciplinas.excluirDisciplinaVigente(turma);
        JOptionPane.showMessageDialog(this.campos.parent, "Disciplina Finalizada com sucesso");
        this.campos.setVisible(false);
        this.campos.origem.setVisible(true);
    }
}
