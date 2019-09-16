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

    public ClicouCelula(JTable tabela, JanelaPrincipal janelaPrincipal, PesquisaAluno anterior, CadastroAluno proximo) {
        this.tabela = tabela;
        this.janelaPrincipal = janelaPrincipal;
        this.anterior = anterior;
        this.proximo = proximo;
    }

    @Override
    public void mouseClicked(MouseEvent a) {
        if(a.getClickCount() == 2 && !a.isConsumed()) {
            a.consume();
            int linha = this.tabela.rowAtPoint(a.getPoint());
            String cpfSelecionado = (String)this.tabela.getValueAt(linha, 1);
            Aluno alunoSelecionado = ServidorArmazenamento.pesquisarAlunoCPF(cpfSelecionado);
            // this.proximo.setCursoField(alunoSelecionado.getCurso().getNome());
            CadastroAluno.alteraNome(this.proximo);
            this.anterior.setVisible(false);
            this.proximo.setVisible(true);
        }
    }

}