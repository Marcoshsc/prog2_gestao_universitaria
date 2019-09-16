package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarAlunoButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    public ClicouCadastrarAlterarAlunoButton(String acao, JanelaPrincipal patern) {
        this.acao = acao;
        this.patern = patern;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
            this.patern.painelOpcoes.setVisible(false);
            this.patern.pesquisaAluno.setVisible(true);
            return;
        }
        this.patern.painelOpcoes.setVisible(false);
        this.patern.cadastroAluno.setVisible(true);
        this.patern.cadastroAluno.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroAluno.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
    }

}