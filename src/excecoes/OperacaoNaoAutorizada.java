package excecoes;

public class OperacaoNaoAutorizada extends Exception {

    @Override
    public String getMessage() {
        return String.format("ERRO - Item já Cadastrado.\n");
    }

}