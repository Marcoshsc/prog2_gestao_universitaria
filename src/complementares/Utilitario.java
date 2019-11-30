package complementares;

import java.awt.Color;
import java.awt.Component;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableColumn;
import javax.swing.text.MaskFormatter;

import contratos.ClassesGeral;
import interfacegrafica.JanelaPrincipal;

public interface Utilitario {

    /**
     *
     * @param cpf: cpf a ser validado
     * @return resultado da validação
     */
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

    /**
     *
     * @param componente: caixa de texto
     * @return texto formatado
     */
	public static String formataCampo(JTextField componente) {
		return componente.getText().replace("-", "")
                .replace(" ", "").replace(".", "").replace(";", "")
                .replace("#", "");
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

    /**
     *
     * @param objetos: objetos a serem cadastrados no banco
     * @param path: diretório para salvamento
     */
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

    /**
     *
     * @param codigo: tipo de campo
     * @return campo gerado
     */
    public static JTextField geraField(String codigo) {
        JFormattedTextField previo;
        try {
            switch(codigo) {
                case "cpf": {
                    previo = new JFormattedTextField(new MaskFormatter("###.###.###-##"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "matricula": {
                    previo = new JFormattedTextField();
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "data": {
                    previo = new JFormattedTextField(new MaskFormatter("##/##/####"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "rg": {
                    previo = new JFormattedTextField(new MaskFormatter("UU-##########"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "agencia": {
                    previo = new JFormattedTextField(new MaskFormatter("####-#"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "conta": {
                    previo = new JFormattedTextField(new MaskFormatter("#####-#"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "numeroCasa": {
                    previo = new JFormattedTextField();
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                case "cep": {
                    previo = new JFormattedTextField(new MaskFormatter("#####-###"));
                    previo.setColumns(20);
                    previo.setMinimumSize(JanelaPrincipal.TAMANHO);
                } break;
                default: {
                    throw new Exception("Não foi possível criar o Campo.");
                }
            }
            return previo;
        } catch(Exception exc) {
            System.out.println("Não foi possível criar o campo.");
            return null;
        }
    }

    /**
     *
     * @return campo de texto padrão
     */
    public static JTextField geraField() {
        try {
            JTextField previo = new JTextField(20);
            previo.setMinimumSize(JanelaPrincipal.TAMANHO);
            return previo;
        } catch(Exception exc) {
            System.out.println("Não foi possível criar o campo.");
            return null;
        }
    }

    /**
     *
     * @param texto: texto do título
     * @return JLabel formatado com o título
     */
    public static JLabel geraTitulo(String texto) {
        JLabel previo = new JLabel(texto);
        previo.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(Color.BLACK),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)));
        return previo;
    }

    /**
     *
     * @param label: fica a esquerda
     * @param campo: fica a direita
     * @param alvo: JFrame
     * @param constantes: constantes do GridBagLayout
     */
    public static void geraCampoVertical(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridy++;
        constantes.gridx = 0;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    /**
     *
     * @param label: fica a esquerda
     * @param campo: fica a direita
     * @param alvo: JFrame
     * @param constantes: constantes do GridBagLayout
     */
    public static void geraCampoHorizontal(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridx++;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    /**
     *
     * @param label: fica a esquerda
     * @param campo: fica a direita
     * @param alvo: JFrame
     * @param constantes: constantes do GridBagLayout
     */
    public static void geraCampoCentral(Component label, Component campo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridy++;
        constantes.gridx = 1;
        alvo.add(label, constantes);
        constantes.gridx++;
        alvo.add(campo, constantes);
    }

    /**
     *
     * @param titulo: titulo a ser posicionado
     * @param alvo: JPanel onde vai ser posicionado
     * @param constantes: constantes do JPanel
     */
    public static void posicionaTitulo(Component titulo, JPanel alvo, GridBagConstraints constantes) {
        constantes.gridwidth = 4;
        constantes.gridy++;
        constantes.gridx = 0;
        constantes.insets = new Insets(20, 0, 20, 0);
        alvo.add(titulo, constantes);
        constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
        constantes.gridwidth = 1;
    }

    /**
     *
     * @param tabela: tabela a ser formatada
     * @param tamanho: quantidade de colunas da tabela
     */
    public static void formataEspacamentoTabela(JTable tabela, int tamanho) {
        for(int i = 0; i < tamanho; i++) {
            TableColumn coluna = tabela.getColumnModel().getColumn(i);
            String maior = (String)coluna.getHeaderValue();
            for(int j = 0; j < tabela.getRowCount(); j++) {
                if(maior.length() < ((String)tabela.getValueAt(j, i)).length()) {
                    maior = (String)tabela.getValueAt(j, i);
                }
            }
            coluna.setMinWidth(9*maior.length());
            DefaultTableCellRenderer renderizador = new DefaultTableCellRenderer();
            renderizador.setHorizontalAlignment(JLabel.CENTER);
            coluna.setCellRenderer(renderizador);
        }
    }

    /**
     *
     * @param escolha: true para poder editar, false caso contrário.
     * @param componentes: componentes do JPanel
     */
    public static void mudarVisualizacao(boolean escolha, Component[] componentes) {
	    for(Component i: componentes) {
	        if(i instanceof JTextField)
	            ((JTextField) i).setEditable(escolha);
	        else if(i instanceof JComboBox)
	            i.setEnabled(escolha);
        }
    }

}
