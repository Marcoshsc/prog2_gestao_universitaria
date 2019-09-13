package complementares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTextField;

import contratos.ClassesGeral;

public interface Utilitario {

	public static boolean validaCPF(String cpf) {

		String[] cpfNumerico = cpf.split("");
		if(cpfNumerico.length != 11)
			return false;
        int[] digitos = new int[cpfNumerico.length];
        int[] verificacao = {0, 0};
        int[] pesos = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0};
        for(int i = 0; i < cpfNumerico.length; i++) {
            digitos[i] = Integer.parseInt(cpfNumerico[i]);
        }
        for(int i = 0; i < 10; i++) {
            if(i < 9)
                verificacao[0] += pesos[i] * digitos[i];
            pesos[i]++;
            verificacao[1] += pesos[i] * digitos[i];
        }
        if(verificacao[0] % 11 == 0 || verificacao[0] % 11 == 1) {
            if(digitos[9] != 0)
                return false;
        }
        else {
            if(digitos[9] != 11 - verificacao[0] % 11)
                return false;
        }
        if(verificacao[1] % 11 == 0 || verificacao[1] % 11 == 1) {
            if(digitos[10] != 0)
                return false;
        }
        else {
            if(digitos[10] != 11 - verificacao[1] % 11)
                return false;
        }
        return true;

    }

	public static String formataCampo(JTextField componente) {
		return componente.getText().replace("-", "").replace(" ", "").replace(".", "");
	}

    public static String[] leArquivo(String path) throws Exception {
        File arquivo = new File(path);
        if(arquivo.exists()) {
            FileReader a = new FileReader(arquivo);
            BufferedReader leitor = new BufferedReader(a);
            String atual;
            String textoCompleto = "";
            while((atual = leitor.readLine()) != null) {
                textoCompleto += atual + '\n';
            }
            leitor.close();
            return textoCompleto.split("\n");
        }
        else {
            return null;
        }
    }

    public static void atualizaBanco(Object[] objetos, String path) {
        final File documento = new File(path);
		FileWriter printerBasico = null;
		BufferedWriter printerArquivo = null;
		if(!documento.exists()) {
			try {
				documento.createNewFile();
				printerBasico = new FileWriter(documento);
				printerArquivo = new BufferedWriter(printerBasico);
				String alunosBanco = "";
				for (int i = 0; i < objetos.length; i++) {
					alunosBanco += ((ClassesGeral)objetos[i]).getStorageString() + '\n';
				}
				printerArquivo.write(alunosBanco);
				printerArquivo.flush();
				printerArquivo.close();
			} catch(IOException exc) {
				System.out.println("Erro ao atualizar o banco de dados.");
                if(printerArquivo != null) {
                    try {
                        printerArquivo.close();
                    } catch(IOException excecao) { System.out.println("Não deu pra fechar o arquivo, CAUTION"); }
                }
			}
		}
		else {
			try {
				printerBasico = new FileWriter(documento);
				printerArquivo = new BufferedWriter(printerBasico);
				String alunosBanco = "";
				for (int i = 0; i < objetos.length; i++) {
					alunosBanco += ((ClassesGeral)objetos[i]).getStorageString() + '\n';
				}
				printerArquivo.write(alunosBanco);
				printerArquivo.flush();
				printerArquivo.close();
			} catch(IOException exc) {
				System.out.println("Erro ao atualizar o banco de dados.");
				if(printerArquivo != null) {
                    try {
                        printerArquivo.close();
                    } catch(IOException excecao) { System.out.println("Não deu pra fechar o arquivo, CAUTION"); }
                }
			}
		}
    }

}
