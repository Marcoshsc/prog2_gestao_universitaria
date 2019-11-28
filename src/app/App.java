package app;

import interfacegrafica.JanelaPrincipal;
import sistema.classes.ServidorArmazenamento;

public class App {
    public static void main(String[] args) throws Exception {
        try {
            ServidorArmazenamento.inicializaTodos();
            JanelaPrincipal.getInstance();
        } catch(Exception erro) {
            System.out.println("Erro Inesperado no funcionamento do programa.\n");
            erro.printStackTrace();
        }
    }
}