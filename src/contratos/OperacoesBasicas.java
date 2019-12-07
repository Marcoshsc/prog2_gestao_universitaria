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
     * @throws Exception caso não seja possível inicializar o banco de dados
     */
    void inicializa() throws Exception;

}