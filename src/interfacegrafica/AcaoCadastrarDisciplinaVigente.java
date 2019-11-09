package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Boletim;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import excecoes.TamanhoIncompativel;
import pessoas.classealuno.Aluno;
import pessoas.classeprofessor.GerenciadorProfessor;
import sistema.classes.ServidorArmazenamento;

// FALTA ALTERAR AINDA QUE QUANDO ALTERA A DISCIPLINA TEM QUE VER SE O CURSO AINDA É O MESMO OU NAO

public class AcaoCadastrarDisciplinaVigente implements ActionListener {

    private CadastroTurma campos;
    private String acao;
    protected String codigoAnterior;

    public AcaoCadastrarDisciplinaVigente(CadastroTurma campos) {
        this.campos = campos;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        int vagasPrevias, semestrePrevio;
        LocalDate dataInicio, dataFim;
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String codigoPrevio = Utilitario.formataCampo(this.campos.codigoField);
        Disciplina disciplinaMae;
        String professorPrevio = Utilitario.formataCampo(campos.cpfProfessorField);

        if(professorPrevio.equals("") || codigoPrevio.equals("") || Utilitario.formataCampo(campos.semestreField).equals("") ||
                Utilitario.formataCampo(campos.vagasDisponiveisField).equals("")) {
            this.campos.parent.erroPreenchimento("Preencha os campos vazios.");
            return;
        }
        if(ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(professorPrevio) == null) {
            this.campos.parent.erroPreenchimento("Professor não encontrado.");
            return;
        }
        if(campos.disciplinaField.getSelectedItem() == null) {
            this.campos.parent.erroPreenchimento("Selecione uma disciplina.");
            return;
        }
        disciplinaMae = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(
                (String)campos.disciplinaField.getSelectedItem());
        if(disciplinaMae == null) {
            this.campos.parent.erroPreenchimento("Disciplina base não encontrada");
            return;
        }
        try {
            dataInicio = LocalDate.parse(campos.dataInicioField.getText(), formatador);
            dataFim = LocalDate.parse(campos.dataFimField.getText(), formatador);
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("Digite datas válidas.");
            return;
        }

        try {
            vagasPrevias = Integer.parseInt(Utilitario.formataCampo(campos.vagasDisponiveisField));
            semestrePrevio = Integer.parseInt(Utilitario.formataCampo(campos.semestreField));
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("Valores não numericos em (Vagas) e (Semestre).");
            return;
        }

        DisciplinaAplicada disciplinaPrevia = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(codigoPrevio);
        if(this.acao.equals("cadastrar")) {
            if(disciplinaPrevia != null) {
                this.campos.parent.erroPreenchimento("Disciplina já existe.");
                return;
            }
            DisciplinaAplicada seraAdicionada = new DisciplinaAplicada(
                    disciplinaMae, professorPrevio, dataInicio, dataFim, vagasPrevias, semestrePrevio, codigoPrevio);
            for(Aluno i: campos.alunosAdicionados) {
                try {
                    seraAdicionada.adicionaAluno(new Boletim(i));
                } catch(TamanhoIncompativel exc) {
                    this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                    return;
                }
            }

            JOptionPane.showMessageDialog(this.campos.parent, "Turma cadastrada com sucesso.", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            ServidorArmazenamento.gerenciadorDisciplinas.adicionaDisciplinaVigente(seraAdicionada);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }
//        else if(acao.equals("alterar")) {
//            if(disciplinaPrevia == null) {
//                this.campos.parent.erroPreenchimento("Impossível alterar código de Disciplina. Caso o deseje, crie uma nova Disciplina.");
//                return;
//            }
//            else if(!disciplinaPrevia.getCodigo().equals(this.codigoAnterior)) {
//                this.campos.parent.erroPreenchimento("Impossível alterar código de Disciplina. Caso o deseje, crie uma nova Disciplina.");
//                return;
//            }
//            disciplinaPrevia.alterar(codigoPrevio, nomePrevio, maximoFaltasPrevio, cargaHorariaPrevia);
//            if(!disciplinaPrevia.getCodigoCurso().equals(cursoPrevio.getCodigo())) {
//                Curso cursoAtual = GerenciadorCursos.pesquisaCursoCodigo(disciplinaPrevia.getCodigoCurso());
//                cursoAtual.getDisciplinasRelacionadas().remove(disciplinaPrevia);
//                disciplinaPrevia.setCodigoCurso(cursoPrevio.getCodigo());
//                cursoPrevio.getDisciplinasRelacionadas().add(disciplinaPrevia);
//
//            }
//            try {
//                GerenciadorDisciplinas.atualizaBancoDisciplina();
//            } catch(Exception exc) {
//                System.out.println(exc.getMessage());
//                exc.printStackTrace();
//            }
//            JOptionPane.showMessageDialog(this.campos.parent, "Disciplina alterada com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
//            this.campos.setVisible(false);
//            this.campos.origem.setVisible(true);
//        }

    }

}