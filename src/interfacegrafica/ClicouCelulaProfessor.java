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

    /**
     *
     * @param acao: view/change
     * @param tabela: tabela a ser escutada
     * @param janelaPrincipal: janela principal que possui o objeto
     * @param anterior: JPanel anterior
     * @param proximo: próximo JPanel
     */
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

    /**
     *
     * @param a: clicar duas vezes na célula da tabela de professores
     */
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