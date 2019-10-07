package ensino.secaodisciplina;

import contratos.ClassesGeral;
import pessoas.classealuno.Aluno;

public class Boletim implements ClassesGeral {

    private Aluno aluno;
    private float nota = 0f;

    public Boletim(Aluno aluno) {
        this.aluno = aluno;
    }

    /**
     * @param nota the nota to set
     */
    protected void setNota(float nota) {
        this.nota = nota;
    }
    
    public boolean isAprovado() {
        if(nota >= 60)
            return true;
        else
            return false;
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