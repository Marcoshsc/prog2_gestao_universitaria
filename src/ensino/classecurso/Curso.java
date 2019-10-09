package ensino.classecurso;

import java.util.ArrayList;

import contratos.ClassesGeral;
import ensino.secaodisciplina.Disciplina;

public class Curso implements ClassesGeral {

    private String codigo;
    private String nome;
    private int tempoConclusao;
    private int cargaHoraria;
    private ArrayList<Disciplina> disciplinasRelacionadas = new ArrayList<Disciplina>();

    public Curso(String codigo, String nome, int tempoConclusao, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.tempoConclusao = tempoConclusao;
        this.cargaHoraria = cargaHoraria;
    }

    public void alterar(String nome, int tempoConclusao, int cargaHoraria) {
        this.nome = nome;
        this.tempoConclusao = tempoConclusao;
        this.cargaHoraria = cargaHoraria;
    }

    public String getStorageString() {
        return String.format("%s-%s-%d-%d", this.codigo, this.nome, this.tempoConclusao, this.cargaHoraria);
    }

    public static Curso fromStorageString(String texto) {
        String[] campos = texto.split("-");
        return new Curso(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
    }

    @Override
    public String toString() {
        return String.format("\nCódigo: %s\nNome: %s\nTempo conclusão(Semestres): %d\nCarga Horária: %d\n", this.codigo,
        this.nome, this.tempoConclusao, this.cargaHoraria);
    }

    public String[] getInfoBasicasArray() {
        String[] infos = new String[4];
        infos[0] = this.codigo;
        infos[1] = this.nome;
        infos[2] = Integer.toString(this.cargaHoraria);
        infos[3] = Integer.toString(this.tempoConclusao);
        return infos;
    }

    /**
     * @return the disciplinasRelacionadas
     */
    public ArrayList<Disciplina> getDisciplinasRelacionadas() {
        return disciplinasRelacionadas;
    }

    /**
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the tempoConclusao
     */
    public int getTempoConclusao() {
        return tempoConclusao;
    }

    public boolean isDisciplinaInside(Disciplina disciplina) {
        if(this.disciplinasRelacionadas.contains(disciplina)) {
            return true;
        }
        else {
            return false;
        }
    }

    public void adicionaDisciplina(Disciplina disciplina) {
        this.disciplinasRelacionadas.add(disciplina);
    }

}