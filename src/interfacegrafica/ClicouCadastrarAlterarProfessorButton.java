package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarProfessorButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    /**
     *
     * @param acao: cadastrar/alterar/normal
     * @param patern: janela principal que possui o objeto
     */
    public ClicouCadastrarAlterarProfessorButton(String acao, JanelaPrincipal patern) {
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
     * @param evento: cliar no botão de entrar na interface de cadastrar professor
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
            return;
        }
        this.patern.painelOpcoesProfessor.setVisible(false);
        this.patern.cadastroProfessor.setVisible(true);
        this.patern.cadastroProfessor.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroProfessor.setaCampos(null, null);
        this.patern.cadastroProfessor.acaoBotaoConfirma.setAcao(this.acao);
        this.patern.cadastroProfessor.botaoConfirma.setVisible(true);
        this.patern.cadastroProfessor.botaoExcluir.setVisible(false);
    }

}