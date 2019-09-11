package excecoes;

public class ItemNaoEncontrado extends Exception {

    @Override
    public String getMessage() {
        return String.format("Item não pôde ser encontrado.\n");
    }
    
}