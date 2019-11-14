package ensino.secaodisciplina;

import contratos.ClassesGeral;
import pessoas.classealuno.Aluno;

public class Boletim implements ClassesGeral {

    private Aluno aluno;
    private int faltas = 0;
    private float nota = 0f;

    public Boletim(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @param nota the nota to set
     */
    public void setNota(float nota) {
        this.nota = nota;
    }

    public void setFaltas(int faltas) {
        this.faltas = faltas;
    }

    public boolean isAprovado(Disciplina disc) {
        if(nota >= 60 && faltas <= disc.getMaximoFaltas())
            return true;
        else
            return false;
    }

    public int getFaltas() {
        return faltas;
    }

    @Override
    public String getStorageString() {
        return String.format("%s-%.2f", this.aluno.getCpf(), this.nota);
    }

    /**
     * @return the aluno
     */
    public Aluno getAluno() {
        return aluno;
    }

    /**
     * @return the nota
     */
    public float getNota() {
        return nota;
    }
}