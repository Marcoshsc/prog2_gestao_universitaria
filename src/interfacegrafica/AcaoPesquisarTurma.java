package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarTurma implements ActionListener {

    private PainelOpcoesTurma origem;
    private PesquisaTurma next;
    private String acao;

    public AcaoPesquisarTurma(String acao, PainelOpcoesTurma origem, PesquisaTurma next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.turmasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasVigentesTable());
        Utilitario.formataEspacamentoTabela(this.next.turmasPesquisadas, 5);
        this.next.opcaoDisciplina.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas()));;
        this.next.opcaoDisciplina.setSelectedItem(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }

}
