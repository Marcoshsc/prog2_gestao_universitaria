package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarProfessorButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

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

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
            //this.patern.painelOpcoesProfessor.setVisible(false);
            //this.patern.pesquisaProfessor.setVisible(true);
            return;
        }
        this.patern.painelOpcoesProfessor.setVisible(false);
        this.patern.cadastroProfessor.setVisible(true);
        //this.patern.cadastroProfessor.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroProfessor.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroProfessor.setaCampos(null, null);
        this.patern.cadastroProfessor.acaoBotaoConfirma.setAcao(this.acao);
        this.patern.cadastroProfessor.botaoConfirma.setVisible(true);
        this.patern.cadastroProfessor.botaoExcluir.setVisible(false);
    }

}