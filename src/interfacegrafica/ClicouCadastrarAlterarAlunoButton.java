package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import sistema.classes.ServidorArmazenamento;

public class ClicouCadastrarAlterarAlunoButton implements ActionListener {

    private String acao;
    private JanelaPrincipal patern;

    /**
     *
     * @param acao: cadastrar/alterar/normal
     * @param patern: janela principal que possui o objeto
     */
    public ClicouCadastrarAlterarAlunoButton(String acao, JanelaPrincipal patern) {
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
     * @param evento: cliar no botão de entrar na interface de cadastrar aluno
     */
    @Override
    public void actionPerformed(ActionEvent evento) {
        if(this.acao.equals("normal")) {
            this.patern.painelOpcoesAluno.setVisible(false);
            this.patern.pesquisaAluno.setVisible(true);
            return;
        }
        this.patern.painelOpcoesAluno.setVisible(false);
        this.patern.cadastroAluno.setVisible(true);
        this.patern.cadastroAluno.acaoBotaoConfirma.setAcao((this.acao.equals("cadastrar")) ? "cadastrar" : "alterar");
        this.patern.cadastroAluno.botaoConfirma.setText(
        (this.acao.equals("cadastrar")) ? "CONFIRMAR CADASTRO" : "CONFIRMAR ALTERAÇÃO");
        this.patern.cadastroAluno.cursoField.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));
        this.patern.cadastroAluno.setaCampos(null, null);
        this.patern.cadastroAluno.botaoConfirma.setVisible(true);
        this.patern.cadastroAluno.botaoExcluir.setVisible(false);
    }

}