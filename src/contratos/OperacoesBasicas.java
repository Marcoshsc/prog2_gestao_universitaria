package contratos;

public interface OperacoesBasicas {

    /**
     *
     * @param objeto: Objeto a ser adicionado
     */
    void adiciona(Object objeto);

    /**
     *
     */
    void imprimeTodos();

    /**
     *
     * @throws Exception
     */
    void inicializa() throws Exception;

}