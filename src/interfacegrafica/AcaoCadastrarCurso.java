package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;
import sistema.classes.ServidorArmazenamento;

public class AcaoCadastrarCurso implements ActionListener {

    private CadastroCurso campos;
    private String acao;

    public AcaoCadastrarCurso(CadastroCurso campos) {
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
        int tempoConclusaoPrevio;
        int cargaHorariaPrevia;
        if(nomePrevio.equals("") || codigoPrevio.equals("") || Utilitario.formataCampo(campos.tempoConclusaoField).equals("") ||
        Utilitario.formataCampo(campos.cargaHorariaField).equals("")) {
            this.campos.parent.erroPreenchimento("Preencha os campos vazios.");
            return;
        }
        try {
            tempoConclusaoPrevio = Integer.parseInt(Utilitario.formataCampo(campos.tempoConclusaoField));
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores numéricos no campo (Tempo Conclusão).");
            return;
        }
        try {
            cargaHorariaPrevia = Integer.parseInt(Utilitario.formataCampo(campos.cargaHorariaField));
        } catch(Exception exc) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores numéricos no campo (Carga Horária).");
            return;
        }
        if(cargaHorariaPrevia <= 0 || tempoConclusaoPrevio <= 0) {
            this.campos.parent.erroPreenchimento("ERRO. Digite Apenas valores positivos nos campos (Carga Horária) e (Tempo Conclusão).");
            return;
        }
        Curso cursoPrevio = GerenciadorCursos.pesquisaCursoCodigo(codigoPrevio);
        if(this.acao.equals("cadastrar")) {
            if(cursoPrevio != null) {
                this.campos.parent.erroPreenchimento("Curso já existe.");
                return;
            }
            ServidorArmazenamento.gerenciadorCursos.adiciona(new Curso(codigoPrevio, nomePrevio, tempoConclusaoPrevio,
             cargaHorariaPrevia));
             JOptionPane.showMessageDialog(this.campos.parent, "Curso cadastrado com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }
        else if(acao.equals("alterar")) {
            if(cursoPrevio == null) {
                this.campos.parent.erroPreenchimento("Impossível alterar código de curso. Caso o deseje, crie um novo curso.");
                return;
            }
            cursoPrevio.alterar(nomePrevio, tempoConclusaoPrevio, cargaHorariaPrevia);
            GerenciadorCursos.atualizaBanco();
            JOptionPane.showMessageDialog(this.campos.parent, "Curso alterado com sucesso.", "INFO", JOptionPane.INFORMATION_MESSAGE);
            this.campos.setVisible(false);
            this.campos.origem.setVisible(true);
        }

    }

}