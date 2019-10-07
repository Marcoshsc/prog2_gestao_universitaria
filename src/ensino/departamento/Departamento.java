package ensino.departamento;

import java.util.ArrayList;

import contratos.ClassesGeral;
import ensino.secaodisciplina.Disciplina;
import pessoas.classeprofessor.Professor;

public class Departamento implements ClassesGeral {

    private String codigo;
    private String nome;
    private ArrayList<Professor> professoresAssociados = new ArrayList<Professor>();
    private ArrayList<Disciplina> disciplinasAssociadas = new ArrayList<Disciplina>();

    @Override
    public String getStorageString() {
        // TODO Auto-generated method stub
        return null;
    }
    
}