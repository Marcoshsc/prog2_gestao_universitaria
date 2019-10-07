package interfacegrafica;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JTable;

import pessoas.classeprofessor.Professor;
import sistema.classes.ServidorArmazenamento;

public class ClicouCelulaProfessor extends MouseAdapter {
    
    protected JTable tabela;
    protected JanelaPrincipal janelaPrincipal;
    protected PesquisaProfessor anterior;
    protected CadastroProfessor proximo;
    protected String acao;

    public ClicouCelulaProfessor(String acao, JTable tabela, JanelaPrincipal janelaPrincipal, PesquisaProfessor anterior, CadastroProfessor proximo) {
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
            String cpfSelecionado = (String)this.tabela.getValueAt(linha, 1);
            Professor professorSelecionado = ServidorArmazenamento.gerenciadorProfessores.pesquisarProfessorCPF(cpfSelecionado);
            // this.proximo.setCursoField(alunoSelecionado.getCurso().getNome());
            this.proximo.setaCampos(this.acao, professorSelecionado);
            this.anterior.setVisible(false);
            this.proximo.setVisible(true);
        }
    }

}