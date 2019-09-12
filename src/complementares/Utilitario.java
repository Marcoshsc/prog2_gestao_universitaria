package complementares;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import contratos.ClassesGeral;

public interface Utilitario {

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
