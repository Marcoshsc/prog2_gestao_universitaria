package interfacegrafica;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.DisciplinaConcluida;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GerarRelatorioAprovacao implements ActionListener {

    private RelatorioAprovacao principal;

    public GerarRelatorioAprovacao(RelatorioAprovacao principal) {
        this.principal = principal;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String cursoPrevio = (principal.cursoField.getSelectedItem() != null) ?
                (String)principal.cursoField.getSelectedItem() : null;
        String disciplinaPrevia = (principal.disciplinaField.getSelectedItem() != null) ?
                (String)principal.disciplinaField.getSelectedItem() : null;
        LocalDate dataPrevia = null;
        try {
            if(!Utilitario.formataCampo(this.principal.dataField).replace("/", "").equals(""))
                dataPrevia = LocalDate.parse(this.principal.dataField.getText(), formatador);
        } catch(Exception exc) {
            this.principal.erroPreenchimento("Digite uma data válida.");
            this.principal.setaCampos();
            return;
        }
        if(cursoPrevio == null && disciplinaPrevia == null && dataPrevia == null) {
            this.setValores(this.getMetricas());
            return;
        }
        if(cursoPrevio != null && disciplinaPrevia == null && dataPrevia == null) {
            Curso cursoProcurado = ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome(cursoPrevio);
            if(cursoProcurado == null) {
                this.principal.erroPreenchimento("Curso Inválido.");
                this.principal.setaCampos();
                return;
            }
            this.setValores(this.getMetricas(cursoProcurado));
            return;
        }
        if(cursoPrevio != null && disciplinaPrevia == null && dataPrevia != null) {
            Curso cursoProcurado = ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome(cursoPrevio);
            if(cursoProcurado == null) {
                this.principal.erroPreenchimento("Curso Inválido.");
                this.principal.setaCampos();
                return;
            }
            this.setValores(this.getMetricas(cursoProcurado, dataPrevia));
            return;
        }
        if((cursoPrevio == null && disciplinaPrevia != null && dataPrevia == null) || (cursoPrevio != null &&
                disciplinaPrevia != null && dataPrevia == null)) {
            Disciplina disciplinaProcurada = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(disciplinaPrevia);
            if(disciplinaProcurada == null) {
                this.principal.erroPreenchimento("Disciplina Inválida.");
                this.principal.setaCampos();
                return;
            }
            this.principal.cursoField.setSelectedItem(null);
            this.setValores(this.getMetricas(disciplinaProcurada));
            return;
        }
        if(disciplinaPrevia != null && dataPrevia != null) {
            Disciplina disciplinaProcurada = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(disciplinaPrevia);
            if(disciplinaProcurada == null) {
                this.principal.erroPreenchimento("Disciplina Inválida.");
                this.principal.setaCampos();
                return;
            }
            this.principal.cursoField.setSelectedItem(null);
            this.setValores(this.getMetricas(disciplinaProcurada, dataPrevia));
            return;
        }
        if(disciplinaPrevia == null && cursoPrevio == null && dataPrevia != null) {
            this.setValores(this.getMetricas(dataPrevia));
        }
    }

    private void setValores(int[] valores) {
        this.principal.numeroCursadosField.setText(Integer.toString(valores[0]));
        this.principal.numeroAprovadosField.setText(Integer.toString(valores[1]));
        if(valores[1] != 0)
            this.principal.indiceAprovacaoField.setText(String.format("%.2f%%", ((float)valores[0] / (float)valores[1]) * 100));
        else
            this.principal.indiceAprovacaoField.setText("0.00%");
    }

    private int[] getMetricas() {
        int[] metricas = new int[2];
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            for(DisciplinaConcluida j: i.getDisciplinasConcluidas()) {
                metricas[0]++;
                if(j.getAprovado())
                    metricas[1]++;
            }
        }
        return metricas;
    }

    private int[] getMetricas(LocalDate data) {
        int[] metricas = new int[2];
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            for(DisciplinaConcluida j: i.getDisciplinasConcluidas()) {
                if(j.getDataConclusao().compareTo(data) >= 0) {
                    metricas[0]++;
                    if (j.getAprovado())
                        metricas[1]++;
                }
            }
        }
        return metricas;
    }

    private int[] getMetricas(Curso curso) {
        int[] metricas = new int[2];
        for(Disciplina i: curso.getDisciplinasRelacionadas()) {
            for(Aluno j: GerenciadorAluno.getAlunosCadastrados()) {
                for(DisciplinaConcluida k: j.getDisciplinasConcluidas()) {
                    if(k.getDisciplina().getCodigo().equals(i.getCodigo())) {
                        metricas[0]++;
                        if(k.getAprovado())
                            metricas[1]++;
                    }
                }
            }
        }
        return metricas;
    }

    private int[] getMetricas(Curso curso, LocalDate data) {
        int[] metricas = new int[2];
        for(Disciplina i: curso.getDisciplinasRelacionadas()) {
            for(Aluno j: GerenciadorAluno.getAlunosCadastrados()) {
                for(DisciplinaConcluida k: j.getDisciplinasConcluidas()) {
                    if(k.getDisciplina().getCodigo().equals(i.getCodigo()) && k.getDataConclusao().compareTo(data) >= 0) {
                        metricas[0]++;
                        if(k.getAprovado())
                            metricas[1]++;
                    }
                }
            }
        }
        return metricas;
    }

    private int[] getMetricas(Disciplina disc) {
        int[] metricas = new int[2];
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            for(DisciplinaConcluida j: i.getDisciplinasConcluidas()) {
                if(j.getDisciplina().getCodigo().equals(disc.getCodigo())) {
                    metricas[0]++;
                    if(j.getAprovado())
                        metricas[1]++;
                }
            }
        }
        return metricas;
    }

    private int[] getMetricas(Disciplina disc, LocalDate data) {
        int[] metricas = new int[2];
        for(Aluno i: GerenciadorAluno.getAlunosCadastrados()) {
            for(DisciplinaConcluida j: i.getDisciplinasConcluidas()) {
                if(j.getDisciplina().getCodigo().equals(disc.getCodigo()) && j.getDataConclusao().compareTo(data) >= 0) {
                    metricas[0]++;
                    if(j.getAprovado())
                        metricas[1]++;
                }
            }
        }
        return metricas;
    }

}
