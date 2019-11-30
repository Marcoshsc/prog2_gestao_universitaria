package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarDisciplinaButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    /**
     *
     * @param acao: cadastrar/alterar/normal
     * @param patern: janela principal que possui o objeto
     */
    public ClicouCadastrarAlterarDisciplinaButton(String acao, JanelaPrincipal patern) {
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
     * @param evento: cliar no botão de entrar na interface de cadastrar disciplina
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        this.patern.painelOpcoesDisciplina.setVisible(false);
        this.patern.cadastroDisciplina.setVisible(true);
        this.patern.cadastroDisciplina.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroDisciplina.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroDisciplina.setaCampos(null, null);
    }

}