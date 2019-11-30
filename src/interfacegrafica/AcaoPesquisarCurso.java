package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarCurso implements ActionListener {

    private PainelOpcoesCurso origem;
    private PesquisaCurso next;
    private String acao;

    /**
     *
     * @param acao: view ou change
     * @param origem: PainelOpcoesCurso referente
     * @param next: proximo campo
     */
    public AcaoPesquisarCurso(String acao, PainelOpcoesCurso origem, PesquisaCurso next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    /**
     *
     * @param e: clicar no botão visualizar curso
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.cursosPesquisados.setModel(ServidorArmazenamento.gerenciadorCursos.getCursosTable());
        Utilitario.formataEspacamentoTabela(this.next.cursosPesquisados, 4);
        this.next.codigoCurso.setText(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}