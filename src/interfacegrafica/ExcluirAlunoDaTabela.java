package interfacegrafica;

import complementares.Utilitario;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ExcluirAlunoDaTabela implements ActionListener {

    private JTable tabela;
    private ArrayList<Aluno> array;
    private CadastroTurma pai;
    private JTextField campoDisc;

    public ExcluirAlunoDaTabela(JTable tabela, ArrayList<Aluno> array, CadastroTurma pai, JTextField campoDisc) {
        this.tabela = tabela;
        this.array = array;
        this.pai = pai;
        this.campoDisc = campoDisc;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(tabela.getSelectedRow() == -1) {
            this.pai.parent.erroPreenchimento("Nenhuma célula selecionada");
            return;
        }
        DisciplinaAplicada disc = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(Utilitario.formataCampo(campoDisc));
        Aluno selected = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF((String)tabela.getValueAt(
                tabela.getSelectedRow(), 2));
        array.remove(selected);
        tabela.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(array, disc));
        Utilitario.formataEspacamentoTabela(tabela, 5);
    }
}
