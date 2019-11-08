package excecoes;

public class TamanhoIncompativel extends Exception {

        @Override
        public String getMessage() {
            return String.format("ERRO - Item já Cadastrado.\n");
        }

}
