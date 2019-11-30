package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ClicouCadastrarAlterarCursoButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    /**
     *
     * @param acao: cadastrar/alterar/normal
     * @param patern: janela principal que possui o objeto
     */
    public ClicouCadastrarAlterarCursoButton(String acao, JanelaPrincipal patern) {
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
     * @param evento: cliar no botão de entrar na interface de cadastrar curso
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        this.patern.painelOpcoesCurso.setVisible(false);
        this.patern.cadastroCurso.setVisible(true);
        this.patern.cadastroCurso.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroCurso.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroCurso.setaCampos(null, null);
    }

}