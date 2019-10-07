package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import sistema.classes.ServidorArmazenamento;

public class AcaoPesquisarProfessor implements ActionListener {

    private PainelOpcoesProfessor origem;
    private PesquisaProfessor next;
    private String acao;

    public AcaoPesquisarProfessor(String acao, PainelOpcoesProfessor origem, PesquisaProfessor next) {
        this.acao = acao;
        this.origem = origem;
        this.next = next;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.next.professoresPesquisados.setModel(ServidorArmazenamento.gerenciadorProfessores.getProfessoresTable());
        Utilitario.formataEspacamentoTabela(this.next.professoresPesquisados, 6);
        this.next.cpfProfessor.setText(null);
        this.next.clicouCelula.setAcao(this.acao);
        this.origem.setVisible(false);
        this.next.setVisible(true);
    }
    
}