package app;

import java.util.ArrayList;

import interfacegrafica.JanelaPrincipal;
import sistema.classes.ServidorArmazenamento;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ServidorArmazenamento.inicializaTodos();
            new JanelaPrincipal();
            // System.out.println(Utilitario.validaCPF("10732807645"));
        
        // DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        // Endereco end1 = new Endereco("Rua aqui", 12, "complemento", "bairro", "cep", "cidade", "estado", "pais");
        // ContaBancaria cont1 = new ContaBancaria("nomeBanco", "agencia", "conta", "cpfTitular", "nomeTitular");
        // Professor a = new Professor("nome", "cpf", "rg", "sexo", LocalDate.parse("01/12/2000", formatador), end1, cont1,
        // LocalDate.parse("01/12/2015", formatador), 5600.55f);
        // String[] campos = a.getStorageString().split("-");
        // Professor b = Professor.fromStorageString(campos);
        // System.out.println(b.getStorageString());
        // System.out.println(b.getStorageString());
        // Curso curso1 = new Curso("codigo", "nome", 10, 3000);
        // GerenciadorBolsas.novaBolsa("codigo", "nome", 400);
        // GerenciadorBolsas.novaBolsa("codigo2", "nome2", 300);
        // ServidorArmazenamento.adicionaCurso(curso1);
        // Aluno a1 = new Aluno("Marcos", "1234", "1233", "Masculino", LocalDate.parse("01/12/2000", formatador),
        //  end1, cont1, "numeroMatricula", LocalDate.parse("13/02/2019", formatador), curso1);
        //  GerenciadorBolsas.novoBolsista(GerenciadorBolsas.procuraBolsa("codigo"), a1,
        //  "13/02/2019", "13/04/2019");
        //  GerenciadorBolsas.novoBolsista(GerenciadorBolsas.procuraBolsa("codigo2"), a1,
        //  "13/02/2019", "13/04/2019");
        // Aluno a2 = new Aluno("Marcos", "1234", "1233", "Masculino", LocalDate.parse("01/12/2000", formatador), 
        //  end1, "numeroMatricula", LocalDate.parse("13/02/2019", formatador), curso1);
        // Aluno a3 = new Aluno("Marcos", "1234", "1233", "Masculino", LocalDate.parse("01/12/2000", formatador),
        //  cont1, "numeroMatricula", LocalDate.parse("13/02/2019", formatador), curso1);
        // Aluno a4 = new Aluno("Marcos", "1234", "1233", "Masculino", LocalDate.parse("01/12/2000", formatador),
        //  "numeroMatricula", LocalDate.parse("13/02/2019", formatador), curso1);
        // Disciplina d1 = new Disciplina("codigo", 72, 9, "departamento");
        // DisciplinaAplicada d2 = new DisciplinaAplicada(d1, "professor", LocalDate.parse("13/02/2019", formatador), 
        // LocalDate.parse("22/12/2019", formatador), 21, 50, 2);
        // ServidorArmazenamento.adicionaAluno(a1);
        // ServidorArmazenamento.adicionaAluno(a2);
        // ServidorArmazenamento.adicionaAluno(a3);
        // ServidorArmazenamento.adicionaAluno(a4);
        // System.out.println(a1.getBolsasString());
        // System.out.println(GerenciadorBolsas.getAlunosBolsistasString());
        // GerenciadorBolsas.removeBolsa(a2);
        // System.out.println(a1.getBolsasString());
        // System.out.println(a1.getStorageString().split("-").length - curso1.getStorageString().split("-").length - 2);
        // System.out.println(a2.getStorageString().split("-").length - curso1.getStorageString().split("-").length - 2);
        // System.out.println(a3.getStorageString().split("-").length - curso1.getStorageString().split("-").length - 2);
        // System.out.println(a4.getStorageString().split("-").length - curso1.getStorageString().split("-").length - 2);
        // ServidorArmazenamento.atualizaBancoAluno();
        //ServidorArmazenamento.gerenciadorAlunos.imprimeTodos();
        // ServidorArmazenamento.gerenciadorAlunos.atualizaBanco();
        } catch(Exception erro) {
            System.out.println("Fui eu que peguei essa exc.\n");
            erro.printStackTrace();
        }
        
    }
}