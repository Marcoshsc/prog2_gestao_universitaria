package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import sistema.classes.ServidorArmazenamento;


public class AcaoCadastrarDisciplina implements ActionListener {

    private CadastroDisciplina campos;
    private String acao;

    public AcaoCadastrarDisciplina(CadastroDisciplina campos) {
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

        String nomePrevio = campos.nomeField.getText().replace("-", "");
        String codigoPrevio = Utilitario.formataCampo(this.campos.codigoField);
        int maximoFaltasPrevio;
        int cargaHorariaPrevia;
        if(campos.cursoField.getSelectedItem() == null) {
            this.campos.parent.erroPreenchimento("Selecione um curso respectivo para a disciplina.");
            return;
        }
        Curso cursoPrevio = ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)campos.cursoField.getSelectedItem());
        if(nomePrevio.equals("") || codigoPrevio.equals("") || Utilitario.formataCampo(campos.maximoFaltasField).equals("") ||
        Utilitario.formataCampo(campos.cargaHorariaField).equals("")) {
            this.campos.parent.erroPreenchimento("Preencha os campos vazios.");
            return;
        }
        try {
            maximoFaltasPrevio = Integer.parseInt(Utilitario.formataCampo(campos.maximoFaltasField));
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores numéricos no campo (Maximo faltas).");
            return;
        }
        try {
            cargaHorariaPrevia = Integer.parseInt(Utilitario.formataCampo(campos.cargaHorariaField));
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores numéricos no campo (Carga Horária).");
            return;
        }
        if(cargaHorariaPrevia <= 0 || maximoFaltasPrevio <= 0) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores positivos nos campos (Carga Horária) e (Tempo Conclusão).");
            return;
        }
        Disciplina disciplinaPrevia = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(codigoPrevio);
        if(this.acao.equals("cadastrar")) {
            if(disciplinaPrevia != null) {
                this.campos.parent.erroPreenchimento("Disciplina já existe.");
                return;
            }
            Disciplina seraAdicionada = new Disciplina(codigoPrevio, nomePrevio, cargaHorariaPrevia, maximoFaltasPrevio);
            cursoPrevio.adicionaDisciplina(seraAdicionada);
             JOptionPane.showMessageDialog(this.campos.parent, "Disciplina cadastrada com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            seraAdicionada.setCodigoCurso(cursoPrevio.getCodigo());
            ServidorArmazenamento.gerenciadorDisciplinas.adicionaDisciplina(seraAdicionada);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }
        else if(acao.equals("alterar")) {
            if(disciplinaPrevia == null) {
                this.campos.parent.erroPreenchimento("Impossível alterar código de Disciplina. Caso o deseje, crie uma nova Disciplina.");
                return;
            }
            disciplinaPrevia.alterar(codigoPrevio, nomePrevio, cargaHorariaPrevia, maximoFaltasPrevio);
            if(!disciplinaPrevia.getCodigoCurso().equals(cursoPrevio.getCodigo())) {
                Curso cursoAtual = GerenciadorCursos.pesquisaCursoCodigo(disciplinaPrevia.getCodigoCurso());
                cursoAtual.getDisciplinasRelacionadas().remove(disciplinaPrevia);
                disciplinaPrevia.setCodigoCurso(cursoPrevio.getCodigo());
                cursoPrevio.getDisciplinasRelacionadas().add(disciplinaPrevia);

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