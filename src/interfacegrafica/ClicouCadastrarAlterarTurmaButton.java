package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import sistema.classes.ServidorArmazenamento;

public class ClicouCadastrarAlterarTurmaButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    public ClicouCadastrarAlterarTurmaButton(String acao, JanelaPrincipal patern) {
        this.acao = acao;
        this.patern = patern;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
            //this.patern.painelOpcoesTurma.setVisible(false);
            //this.patern.pesquisaTurma.setVisible(true);
            return;
        }
        this.patern.painelOpcoesTurma.setVisible(false);
        this.patern.cadastroTurma.setVisible(true);
        this.patern.cadastroTurma.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroTurma.botaoConfirma.setText(
                (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroTurma.disciplinaField.setModel(new DefaultComboBoxModel<String>(
                ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas()));
        this.patern.cadastroTurma.setaCampos(null, null);
        this.patern.cadastroTurma.botaoConfirma.setVisible(true);
        this.patern.cadastroTurma.botaoExcluir.setVisible(false);
    }

}
