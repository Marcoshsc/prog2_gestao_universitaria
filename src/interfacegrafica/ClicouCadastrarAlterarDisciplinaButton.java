package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarDisciplinaButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

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

    @Override
    public void actionPerformed(ActionEvent evento) {
        // if(this.acao.equals("normal")) {
        //     this.patern.painelOpcoesAluno.setVisible(false);
        //     this.patern.pesquisaAluno.setVisible(true);
        //     return;
        // }
        this.patern.painelOpcoesDisciplina.setVisible(false);
        this.patern.cadastroDisciplina.setVisible(true);
        this.patern.cadastroDisciplina.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroDisciplina.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroDisciplina.setaCampos(null, null);
    }

}