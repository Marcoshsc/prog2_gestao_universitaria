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
    protected String codigoAnteriorTurma;
    protected String codigoAnteriorDisc;

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
            for(int i = 0; i < campos.alunosAdicionados.size(); i++) {
                try {
                    Boletim previo = new Boletim(campos.alunosAdicionados.get(i));
                    seraAdicionada.adicionaAluno(previo);
                    float notaPrevia = Float.parseFloat((String)campos.alunosPesquisados.getValueAt(i, 4));
                    if(notaPrevia < 0 || notaPrevia > 10) {
                        this.campos.parent.erroPreenchimento("Digite somente notas entre 0 e 10.");
                        return;
                    }
                    if(notaPrevia > 0) {
                        previo.setNota(notaPrevia);
                    }
                } catch(TamanhoIncompativel exc) {
                    System.out.println("entrou aqui");
                    this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                    return;
                } catch(Exception exc) {
                    this.campos.parent.erroPreenchimento("Erro de preenchimento nas notas.");
                    return;
                }
            }
            ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(seraAdicionada.getProfessor())
                    .getDisciplinasMinistradas().add(seraAdicionada.getCodigoVigente());
            JOptionPane.showMessageDialog(this.campos.parent, "Turma cadastrada com sucesso.", "INFO",
                    JOptionPane.INFORMATION_MESSAGE);
            ServidorArmazenamento.gerenciadorDisciplinas.adicionaDisciplinaVigente(seraAdicionada);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }
        else if(acao.equals("alterar")) {
            if(disciplinaPrevia == null) {
                this.campos.parent.erroPreenchimento("Impossível alterar código de Turma. Caso o deseje, crie uma nova Turma.");
                return;
            }
            else if(!disciplinaPrevia.getCodigoVigente().equals(this.codigoAnteriorTurma)) {
                this.campos.parent.erroPreenchimento("Impossível alterar código de Turma. Caso o deseje, crie uma nova Turma.");
                return;
            }
            if(!((String)this.campos.disciplinaField.getSelectedItem()).equals(this.codigoAnteriorDisc)) {
                this.campos.parent.erroPreenchimento("Não é possível trocar a disciplina de uma turma. Se necessário, crie uma nova turma.");
                return;
            }
            disciplinaPrevia.alterar(professorPrevio, dataInicio, dataFim, vagasPrevias, semestrePrevio, codigoPrevio);
            disciplinaPrevia.getArrayListAlunosMatriculados().clear();
            for(int i = 0; i < campos.alunosAdicionados.size(); i++) {
                try {
                    disciplinaPrevia.getArrayListAlunosMatriculados().clear();
                    Boletim previo = new Boletim(campos.alunosAdicionados.get(i));
                    disciplinaPrevia.adicionaAluno(previo);
                    float notaPrevia = Float.parseFloat((String)campos.alunosPesquisados.getValueAt(i, 4));
                    if(notaPrevia < 0 || notaPrevia > 10) {
                        this.campos.parent.erroPreenchimento("Digite somente notas entre 0 e 10.");
                        return;
                    }
                    if(notaPrevia > 0) {
                        previo.setNota(notaPrevia);
                    }
                } catch(TamanhoIncompativel exc) {
                    this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                    return;
                } catch(Exception exc) {
                    this.campos.parent.erroPreenchimento("Erro de preenchimento nas notas.");
                    return;
                }
            }
            try {
                GerenciadorDisciplinas.atualizaBancoDisciplina();
            } catch(Exception exc) {
                System.out.println(exc.getMessage());
                exc.printStackTrace();
            }
            JOptionPane.showMessageDialog(this.campos.parent, "Disciplina alterada com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }

    }

}