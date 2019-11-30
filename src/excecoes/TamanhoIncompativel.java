package excecoes;

public class TamanhoIncompativel extends Exception {

        @Override
        public String getMessage() {
            return "ERRO - Item já Cadastrado.\n";
        }

}
