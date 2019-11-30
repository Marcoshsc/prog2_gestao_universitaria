package interfacegrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;

import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.GerenciadorDisciplinas;

public class ClicouCelulaDisciplina extends MouseAdapter {
    
    protected JTable tabela;
    protected JanelaPrincipal janelaPrincipal;
    protected JPanel anterior;
    protected CadastroDisciplina proximo;
    protected String acao;

    /**
     *
     * @param acao: view/change
     * @param tabela: tabela a ser escutada
     * @param janelaPrincipal: janela principal que possui o objeto
     * @param anterior: JPanel anterior
     * @param proximo: próximo JPanel
     */
    public ClicouCelulaDisciplina(String acao, JTable tabela, JanelaPrincipal janelaPrincipal, JPanel anterior, CadastroDisciplina proximo) {
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

    /**
     *
     * @param a: clicar duas vezes na célula da tabela de disciplinas
     */
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