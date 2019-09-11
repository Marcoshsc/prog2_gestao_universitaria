package assistenciaestudantil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

import pessoas.classealuno.Aluno;
import excecoes.ItemNaoEncontrado;

public class GerenciadorBolsas {

    private static ArrayList<Bolsa> bolsasCadastradas = new ArrayList<Bolsa>();
    private static ArrayList<Aluno> alunosBolsistas = new ArrayList<Aluno>();

    // fazer o criar bolsa que vai pedir infos do user public static void criarBolsa()

    public static void novaBolsa(String codigo, String nome, float valor) {
        Bolsa bolsa = new Bolsa(codigo, nome, valor);
        GerenciadorBolsas.bolsasCadastradas.add(bolsa);
    }
    // falta um "novo bolsista, que pega uma bolsa e gera uma bolsa aplicada"

    private static boolean alunoEstaCadastrado(Aluno aluno) {
        if(GerenciadorBolsas.alunosBolsistas.size() == 0)
            return false;
        for(Aluno i: GerenciadorBolsas.alunosBolsistas) {
            if(i == aluno)
                return true;
        }
        return false;
    }

    public static void novoBolsista(Bolsa bolsa, Aluno aluno, String dataInicio, String dataFim) throws Exception {
        // posteriormente aqui dentro que vai ser feita a busca pela bolsa.
        try {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            try {
                aluno.adicionaBolsa(new BolsaVigente(bolsa, LocalDate.parse(dataInicio, formatador),
                LocalDate.parse(dataFim, formatador)));
            } catch(Exception erro) {
                System.out.println("Disparou exc no novoBolsista.\n");
                erro.printStackTrace();
            }
            if(!GerenciadorBolsas.alunoEstaCadastrado(aluno))
                GerenciadorBolsas.alunosBolsistas.add(aluno);
        }
        catch(Exception erro) {
            throw erro;
        }
    }

    public static String getAlunosBolsistasString() {
        String acumulador = "";
        acumulador += "Alunos Bolsistas:\n";
        for(Aluno i: GerenciadorBolsas.alunosBolsistas) {
            acumulador += i.toString();
        }
        return acumulador;
    }

    public static Bolsa procuraBolsa(String codigo) throws Exception {
        for(Bolsa i: GerenciadorBolsas.bolsasCadastradas) {
            if(codigo == i.getCodigo())
                return i;
        }
        throw new ItemNaoEncontrado();
    }


    //ainda vai faltar procurar pelo aluno pelo usuario.
    public static void removeBolsa(Aluno aluno) throws Exception {
        if(!GerenciadorBolsas.alunoEstaCadastrado(aluno))
            throw new ItemNaoEncontrado();
        else if(!aluno.temBolsa()) {
            throw new Exception(String.format("O aluno %s não possui bolsas cadastradas.\n", aluno.getNome()));
        }
        else {
            Scanner input = new Scanner(System.in);
            aluno.getBolsasString();
            // DEPOIS TEM QUE COLOCAR PERMISSÃO NISSO AQUI, PEDIR SENHA E TAL
            System.out.println("Digite o código da Bolsa a ser removida: ");
            String codigo = input.next();
            try {
                BolsaVigente bolsa =  aluno.returnBolsaPorCodigo(codigo);// GerenciadorBolsas.procuraBolsa(codigo); trocando...
                aluno.getBolsas().remove(bolsa);
            } catch(Exception exc) {
                input.close();
                throw exc;
            }
            input.close();
        }
    }
    

}