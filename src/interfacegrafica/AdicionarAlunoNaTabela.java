package interfacegrafica;

import complementares.Utilitario;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.Aluno;
import sistema.classes.ServidorArmazenamento;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class AdicionarAlunoNaTabela implements ActionListener {

    private JTable tabela;
    private JTextField campoCPF;
    private JTextField campoDisc;
    private ArrayList<Aluno> array;
    private CadastroTurma pai;

    /**
     *
     * @param tabela: tabela a receber a adição
     * @param campoCPF: campo referente ao cpf aluno
     * @param campoDisc: campo referente ao codigo disciplina
     * @param array: lista de alunos
     * @param pai: CadastroTurma referente
     */
    public AdicionarAlunoNaTabela(JTable tabela, JTextField campoCPF, JTextField campoDisc, ArrayList<Aluno> array,
                                  CadastroTurma pai) {
        this.tabela = tabela;
        this.campoCPF = campoCPF;
        this.campoDisc = campoDisc;
        this.array = array;
        this.pai = pai;
    }

    /**
     *
     * @param e: clicar no botão adicionar aluno, no painel de cadastro de turma
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        DisciplinaAplicada disc = GerenciadorDisciplinas.pesquisaDisciplinaVigenteCodigo(Utilitario.formataCampo(campoDisc));
        Aluno current = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(Utilitario.formataCampo(campoCPF));
        if(disc == null) {
            if(current == null) {
                this.pai.parent.erroPreenchimento("Aluno não encontrado.");
                this.campoCPF.setText(null);
                return;
            }
            else {
                if(!array.contains(current)) {
                    array.add(current);
                    this.tabela.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(array, disc));
                    Utilitario.formataEspacamentoTabela(this.tabela, 5);
                    this.campoCPF.setText(null);
                }
                else {
                    this.pai.parent.erroPreenchimento("Aluno já está na lista.");
                    this.campoCPF.setText(null);
                    return;
                }
            }
        }
        else {
            if(current == null) {
                this.pai.parent.erroPreenchimento("Aluno não encontrado.");
                this.campoCPF.setText(null);
                return;
            }
            else {
                if(!array.contains(current)) {
                    array.add(current);
                    this.tabela.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(array, disc));
                    Utilitario.formataEspacamentoTabela(this.tabela, 5);
                    this.campoCPF.setText(null);
                }
                else {
                    this.pai.parent.erroPreenchimento("Aluno já está na lista.");
                    this.campoCPF.setText(null);
                    return;
                }
            }
        }
    }

}
