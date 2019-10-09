package interfacegrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.GerenciadorDisciplinas;

public class ClicouCelulaDisciplina extends MouseAdapter {
    
    protected JTable tabela;
    protected JanelaPrincipal janelaPrincipal;
    protected PesquisaDisciplina anterior;
    protected CadastroDisciplina proximo;
    protected String acao;

    public ClicouCelulaDisciplina(String acao, JTable tabela, JanelaPrincipal janelaPrincipal, PesquisaDisciplina anterior, CadastroDisciplina proximo) {
        this.acao = acao;
        this.tabela = tabela;
        this.janelaPrincipal = janelaPrincipal;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public void mouseClicked(MouseEvent a) {
        if(a.getClickCount() == 2 && !a.isConsumed()) {
            a.consume();
            int linha = this.tabela.rowAtPoint(a.getPoint());
            String codigoSelecionado = (String)this.tabela.getValueAt(linha, 0);
            Disciplina disciplinaSelecionada = GerenciadorDisciplinas.pesquisaDisciplinaCodigo(codigoSelecionado);
            // this.proximo.setCursoField(alunoSelecionado.getCurso().getNome());
            this.proximo.setaCampos(this.acao, disciplinaSelecionada);
            this.anterior.setVisible(false);
            this.proximo.setVisible(true);
        }
    }

}