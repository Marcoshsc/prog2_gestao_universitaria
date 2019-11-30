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
import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

// FALTA ALTERAR AINDA QUE QUANDO ALTERA A DISCIPLINA TEM QUE VER SE O CURSO AINDA É O MESMO OU NAO

public class AcaoCadastrarDisciplinaVigente implements ActionListener {

    private CadastroTurma campos;
    private String acao;

    /**
     *
     * @param campos: CadastroTurma referente
     */
    public AcaoCadastrarDisciplinaVigente(CadastroTurma campos) {
        this.campos = campos;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    /**
     *
     * @param evento: clicar no botão de cadastrar/alterar turma
     */
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
                this.campos.parent.erroPreenchimento("Turma já existe.");
                return;
            }
            DisciplinaAplicada seraAdicionada = new DisciplinaAplicada(
                    disciplinaMae, professorPrevio, dataInicio, dataFim, vagasPrevias, semestrePrevio, codigoPrevio);
            for(int i = 0; i < campos.alunosAdicionados.size(); i++) {
                try {
                    Boletim previo = new Boletim(campos.alunosAdicionados.get(i));
                    seraAdicionada.adicionaAluno(previo);
                    float notaPrevia = Float.parseFloat((String)campos.alunosPesquisados.getValueAt(i, 4));
                    int faltasPrevias = Integer.parseInt((String)campos.alunosPesquisados.getValueAt(i, 5));
                    if(notaPrevia < 0 || notaPrevia > 10) {
                        this.campos.parent.erroPreenchimento("Digite somente notas entre 0 e 10.");
                        return;
                    }
                    if(faltasPrevias < 0) {
                        this.campos.parent.erroPreenchimento("Digite somente um número positivo de faltas.");
                        return;
                    }
                    if(notaPrevia > 0) {
                        previo.setNota(notaPrevia);
                    }
                    if(faltasPrevias > 0) {
                        previo.setFaltas(faltasPrevias);
                    }
                } catch(TamanhoIncompativel exc) {
                    this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                    return;
                } catch(Exception exc) {
                    this.campos.parent.erroPreenchimento("Erro de preenchimento nas notas/faltas.");
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
            if(disciplinaPrevia != null)
                disciplinaPrevia.alterar(professorPrevio, dataInicio, dataFim, vagasPrevias, semestrePrevio, codigoPrevio);
            else
                System.out.println("deu muito ruim algo e eu não sei o que aconteceu");
            for(int i = 0; i < campos.alunosAdicionados.size(); i++) {
                float notaPrevia = Float.parseFloat((String)campos.alunosPesquisados.getValueAt(i, 4));
                int faltasPrevias = Integer.parseInt((String)campos.alunosPesquisados.getValueAt(i, 5));
                if(notaPrevia < 0 || notaPrevia > 10) {
                    this.campos.parent.erroPreenchimento("Digite somente notas entre 0 e 10.");
                    return;
                }
                if(faltasPrevias < 0) {
                    this.campos.parent.erroPreenchimento("Digite somente um número positivo de faltas.");
                    return;
                }
            }
            if(disciplinaPrevia.getVagasDisponiveis() < this.campos.alunosAdicionados.size()) {
                this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                return;
            }
            disciplinaPrevia.zerarMatriculados();
            for(int i = 0; i < campos.alunosAdicionados.size(); i++) {
                try {
                    Boletim previo = new Boletim(campos.alunosAdicionados.get(i));
                    disciplinaPrevia.adicionaAluno(previo);
                    float notaPrevia = Float.parseFloat((String)campos.alunosPesquisados.getValueAt(i, 4));
                    int faltasPrevias = Integer.parseInt((String)campos.alunosPesquisados.getValueAt(i, 5));
                    if(notaPrevia < 0 || notaPrevia > 10) {
                        this.campos.parent.erroPreenchimento("Digite somente notas entre 0 e 10.");
                        return;
                    }
                    if(notaPrevia > 0) {
                        previo.setNota(notaPrevia);
                    }
                    if(faltasPrevias > 0) {
                        previo.setFaltas(faltasPrevias);
                    }
                } catch(TamanhoIncompativel exc) {
                    this.campos.parent.erroPreenchimento("Limite de capacidade da turma excedido.");
                    return;
                } catch(Exception exc) {
                    this.campos.parent.erroPreenchimento("Erro de preenchimento nas notas.");
                    return;
                }
            }
            if(!this.campos.professorAtual.equals(disciplinaPrevia.getProfessor())) {
                Professor antigoProfessor = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(this.campos.professorAtual);
                Professor novoProfessor = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(disciplinaPrevia.getProfessor());
                antigoProfessor.getDisciplinasMinistradas().remove(disciplinaPrevia.getCodigoVigente());
                novoProfessor.getDisciplinasMinistradas().add(disciplinaPrevia.getCodigoVigente());
            }
            for(Aluno i: this.campos.alunosExcluidos) {
                i.getCursando().remove(disciplinaPrevia.getCodigoVigente());
            }
            try {
                GerenciadorDisciplinas.atualizaBancoDisciplinaVigente();
            } catch(Exception exc) {
                System.out.println(exc.getMessage());
                exc.printStackTrace();
            }
            JOptionPane.showMessageDialog(this.campos.parent, "Turma alterada com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }

    }

}