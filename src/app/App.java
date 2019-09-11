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
        try {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        Endereco end1 = new Endereco("Rua aqui", 12, "bairro", "cep", "cidade", "estado", "pais");
        ContaBancaria cont1 = new ContaBancaria("agencia", "conta", "cpfTitular", "nomeTitular");
        Curso curso1 = new Curso("codigo", "nome", 10, 3000);
        GerenciadorBolsas.novaBolsa("codigo", "nome", 400);
        GerenciadorBolsas.novaBolsa("codigo2", "nome2", 300);
        Aluno a1 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador), "Masculino",
         end1, cont1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
         GerenciadorBolsas.novoBolsista(GerenciadorBolsas.procuraBolsa("codigo"), a1,
         "13/02/2019", "13/04/2019");
         GerenciadorBolsas.novoBolsista(GerenciadorBolsas.procuraBolsa("codigo2"), a1,
         "13/02/2019", "13/04/2019");
        Aluno a2 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador), "Masculino", 
         end1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Aluno a3 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador), "Masculino",
         cont1, "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Aluno a4 = new Aluno("Marcos", "1234", "1233", LocalDate.parse("01/12/2000", formatador), "Masculino",
         "numeroMatricula", curso1, LocalDate.parse("13/02/2019", formatador));
        Disciplina d1 = new Disciplina("codigo", 72, 9, "departamento");
        DisciplinaAplicada d2 = new DisciplinaAplicada(d1, "professor", LocalDate.parse("13/02/2019", formatador), 
        LocalDate.parse("22/12/2019", formatador), 21, 50, 2);
        // System.out.println(a1.getBolsasString());
        // System.out.println(GerenciadorBolsas.getAlunosBolsistasString());
        // GerenciadorBolsas.removeBolsa(a2);
        // System.out.println(a1.getBolsasString());
        System.out.println(a1.getStorageString());
        } catch(Exception erro) {
            System.out.println("Fui eu que peguei essa exc.\n");
            erro.printStackTrace();
        }
        
    }
}