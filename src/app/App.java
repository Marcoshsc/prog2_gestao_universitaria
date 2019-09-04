package app;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import assistenciaestudantil.*;
import complementares.*;
import ensino.classecurso.*;
import ensino.secaodisciplina.*;
import pessoas.classealuno.*;
import pessoas.superclasse.*;

public class App {
    public static void main(String[] args) throws Exception {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Endereco end1 = new Endereco("Rua aqui", 12, "bairro", "cep", "cidade", "estado", "pais");
        ContaBancaria cont1 = new ContaBancaria("agencia", "conta", "cpfTitular", "nomeTitular");
        Curso curso1 = new Curso("codigo", "nome", 10, 3000);
        Aluno a1 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador),
         end1, cont1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Aluno a2 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador),
         end1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Aluno a3 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador),
         cont1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Aluno a4 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador),
         "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Disciplina d1 = new Disciplina("codigo", 72, 9, "departamento");
        DisciplinaAplicada d2 = new DisciplinaAplicada(d1, "professor", LocalDate.parse("13/02/2019", formatador), 
        LocalDate.parse("22/12/2019", formatador), 21, 50, 2);
         System.out.println(a4);
    }
}