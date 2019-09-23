package interfacegrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

import pessoas.classealuno.Aluno;
import sistema.classes.ServidorArmazenamento;

public class ClicouCelula extends MouseAdapter {
    
    protected JTable tabela;
    protected JanelaPrincipal janelaPrincipal;
    protected PesquisaAluno anterior;
    protected CadastroAluno proximo;
    protected String acao;

    public ClicouCelula(String acao, JTable tabela, JanelaPrincipal janelaPrincipal, PesquisaAluno anterior, CadastroAluno proximo) {
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
            String cpfSelecionado = (String)this.tabela.getValueAt(linha, 2);
            Aluno alunoSelecionado = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(cpfSelecionado);
            // this.proximo.setCursoField(alunoSelecionado.getCurso().getNome());
            this.proximo.setaCampos(this.acao, alunoSelecionado);
            this.anterior.setVisible(false);
            this.proximo.setVisible(true);
        }
    }

}