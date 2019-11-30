package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import sistema.classes.ServidorArmazenamento;

public class ClicouCadastrarAlterarTurmaButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    /**
     *
     * @param acao: cadastrar/alterar/normal
     * @param patern: janela principal que possui o objeto
     */
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

    /**
     *
     * @param evento: cliar no botão de entrar na interface de cadastrar turma
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
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
