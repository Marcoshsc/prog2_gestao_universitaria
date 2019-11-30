package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import complementares.Utilitario;
import ensino.classecurso.Curso;
import ensino.classecurso.GerenciadorCursos;

public class ClicouBotaoPesquisaCursoDisciplina implements ActionListener {

    private DisciplinasDoCurso principal;

    /**
     *
     * @param principal: DisciplinasDoCurso que possui o botão.
     */
    public ClicouBotaoPesquisaCursoDisciplina(DisciplinasDoCurso principal) {
        this.principal = principal;
    }

    /**
     *
     * @param e: clicar no botão de pesquisar
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(!this.principal.codigoCurso.getText().equals("")) {
            Curso procurado = GerenciadorCursos.pesquisaCursoCodigo(
                    (Utilitario.formataCampo(this.principal.codigoCurso)));
            if(procurado == null) {
                this.principal.patern.erroPreenchimento("Curso não encontrado");
                this.principal.codigoCurso.setText(null);
                this.principal.nomeCurso.setText(null);
                this.principal.cursosPesquisados.setModel(this.principal.retornaTabelaVazia());
                Utilitario.formataEspacamentoTabela(this.principal.cursosPesquisados, 4);
                return;
            }
            this.principal.nomeCurso.setText(procurado.getNome());
            this.principal.cursosPesquisados.setModel(procurado.getDisciplinasRelacionadasTable());
            Utilitario.formataEspacamentoTabela(this.principal.cursosPesquisados, 4);
        }
        else {
            this.principal.patern.erroPreenchimento("Curso não encontrado");
            this.principal.nomeCurso.setText(null);
            this.principal.cursosPesquisados.setModel(this.principal.retornaTabelaVazia());
            Utilitario.formataEspacamentoTabela(this.principal.cursosPesquisados, 4);
        }
    }
}
