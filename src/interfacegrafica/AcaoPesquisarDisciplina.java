package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.DefaultComboBoxModel;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarDisciplina implements ActionListener {

    private PainelOpcoesDisciplina origem;
    private PesquisaDisciplina next;
    private String acao;

    public AcaoPesquisarDisciplina(String acao, PainelOpcoesDisciplina origem, PesquisaDisciplina next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.disciplinasPesquisadas.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getDisciplinasTable());
        Utilitario.formataEspacamentoTabela(this.next.disciplinasPesquisadas, 4);
        this.next.opcaoCurso.setModel(new DefaultComboBoxModel<String>(ServidorArmazenamento.gerenciadorCursos.getNomeCursos()));;
        this.next.opcaoCurso.setSelectedItem(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}