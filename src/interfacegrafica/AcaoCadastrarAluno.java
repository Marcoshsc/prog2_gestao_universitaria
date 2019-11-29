package interfacegrafica;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.swing.JOptionPane;

import complementares.ContaBancaria;
import complementares.Endereco;
import complementares.Utilitario;
import ensino.classecurso.Curso;
import pessoas.classealuno.Aluno;
import pessoas.classealuno.GerenciadorAluno;
import sistema.classes.ServidorArmazenamento;


public class AcaoCadastrarAluno implements ActionListener {

    private CadastroAluno campos;
    private String acao;

    public AcaoCadastrarAluno(CadastroAluno campos) {
        this.campos = campos;
    }

    /**
     * @param acao the acao to set
     */
    public void setAcao(String acao) {
        this.acao = acao;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        Curso cursoSelecionado = ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome(
        (String)this.campos.cursoField.getSelectedItem());
        if(cursoSelecionado == null) {
            JOptionPane.showMessageDialog(this.campos.patern, "Curso inválido.");
        }
        else {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            boolean preencheuConta = true;
            boolean preencheuEndereco = true;
            String agenciaPrevia = Utilitario.formataCampo(this.campos.agenciaField);
            String contaPrevia = Utilitario.formataCampo(this.campos.contaField);
            String nomeTitularPrevio = this.campos.nomeTitularField.getText().replace("-", "");
            String cpfTitularPrevio = Utilitario.formataCampo(this.campos.cpfTitularField);
            if(agenciaPrevia.equals("") && contaPrevia.equals("") && nomeTitularPrevio.equals("") &&
            cpfTitularPrevio.equals("")) {
                preencheuConta = false;
            }
            String ruaPrevia = this.campos.ruaField.getText();
            String numeroPrevio = this.campos.numeroField.getText();
            String complementoPrevio = this.campos.complementoField.getText();
            String cepPrevio = Utilitario.formataCampo(this.campos.cepField);
            String bairroPrevio = this.campos.bairroField.getText().replace("-", "");
            String cidadePrevio = this.campos.cidadeField.getText().replace("-", "");
            String estadoPrevio = this.campos.estadoField.getText().replace("-", "");
            String paisPrevio = this.campos.paisField.getText().replace("-", "");
            if(Utilitario.formataCampo(this.campos.nomeField).equals("")) {
                this.campos.patern.erroPreenchimento("Campo NOME vazio.");
                return;
            }
            if(!Utilitario.validaCPF(Utilitario.formataCampo(this.campos.cpfField))) {
                this.campos.patern.erroPreenchimento("Digite um CPF válido!");
                return;
            }
            if(Utilitario.formataCampo(this.campos.identidadeField).equals("")) {
                this.campos.patern.erroPreenchimento("Digite um RG válido");
                return;
            }
            if(this.campos.sexoField.getSelectedItem() == null) {
                this.campos.patern.erroPreenchimento("Selecione um Sexo.");
                return;
            }
            try {
                LocalDate.parse(this.campos.dataNascimentoField.getText(), formatador);
            } catch(Exception exc) {
                this.campos.patern.erroPreenchimento("Data de Nascimento Inválida.");
                return;
            }
            try {
                LocalDate.parse(this.campos.dataIngressoField.getText(), formatador);
            } catch(Exception exc) {
                this.campos.patern.erroPreenchimento("Data de Ingresso Inválida.");
                return;
            }
            try {
                Integer.parseInt(Utilitario.formataCampo(this.campos.matriculaField));
            } catch(Exception exc) {
                this.campos.patern.erroPreenchimento("Digite apenas valores númericos no campo (Matrícula).");
                return;
            }
            try {
                Integer.parseInt(Utilitario.formataCampo(this.campos.identidadeField));
            } catch(Exception exc) {
                this.campos.patern.erroPreenchimento("Digite apenas valores númericos no campo (RG).");
                return;
            }
            if(ruaPrevia.equals("") && numeroPrevio.equals("") && complementoPrevio.equals("") && cepPrevio.equals("")
            && bairroPrevio.equals("") && cidadePrevio.equals("") && estadoPrevio.equals("") && paisPrevio.equals("")) {
                preencheuEndereco = false;
            }
            // aqui foi preenchido tudo
            if(preencheuEndereco && preencheuConta) {
                if(this.campos.bancoField.getSelectedItem() == null || agenciaPrevia.equals("") || contaPrevia.equals("") || 
                nomeTitularPrevio.equals("") || cpfTitularPrevio.equals("")) {
                    this.campos.patern.erroPreenchimento("Preencha campos vazios em (Conta Bancaria).");
                    return;
                }
                if(ruaPrevia.equals("") || numeroPrevio.equals("") || complementoPrevio.equals("") || cepPrevio.equals("")
                || bairroPrevio.equals("") || cidadePrevio.equals("") || estadoPrevio.equals("") || paisPrevio.equals("")) {
                    this.campos.patern.erroPreenchimento("Preencha campos vazios em (Endereco).");
                    return;
                }
                if(!Utilitario.validaCPF(Utilitario.formataCampo(this.campos.cpfTitularField))) {
                    this.campos.patern.erroPreenchimento("Digite um CPF do Titular válido! (Conta Bancaria)");
                    return;
                }
                try {
                    Integer.parseInt(numeroPrevio);
                } catch(Exception exc) {
                    this.campos.patern.erroPreenchimento("Apenas valores númericos em número(Endereço)");
                    return;
                }
                Aluno supostoExistente = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(Utilitario.formataCampo(this.campos.cpfField));
                if(this.acao.equals("cadastrar")) {
                    if(supostoExistente != null) {
                        this.campos.patern.erroPreenchimento("Aluno já cadastrado.");
                        return;
                    }
                    ServidorArmazenamento.gerenciadorAlunos.adiciona(new Aluno(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new Endereco(ruaPrevia, Integer.parseInt(numeroPrevio), complementoPrevio, cepPrevio, bairroPrevio,
                    cidadePrevio, estadoPrevio, paisPrevio), new ContaBancaria((String)this.campos.bancoField.getSelectedItem(), 
                    agenciaPrevia, contaPrevia, cpfTitularPrevio, nomeTitularPrevio), 
                    Utilitario.formataCampo(this.campos.matriculaField), LocalDate.parse(this.campos.dataIngressoField.getText(), 
                    formatador), ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem())));
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Cadastrado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
                else if(this.acao.equals("alterar")) {
                    supostoExistente.alterar(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new Endereco(ruaPrevia, Integer.parseInt(numeroPrevio), complementoPrevio, cepPrevio, bairroPrevio,
                    cidadePrevio, estadoPrevio, paisPrevio), new ContaBancaria((String)this.campos.bancoField.getSelectedItem(), 
                    agenciaPrevia, contaPrevia, cpfTitularPrevio, nomeTitularPrevio), 
                    Utilitario.formataCampo(this.campos.matriculaField), LocalDate.parse(this.campos.dataIngressoField.getText(), 
                    formatador), ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem()));
                    try {
                        GerenciadorAluno.atualizaBanco();
                    } catch(Exception exc) {
                        System.out.println(exc.getMessage());
                        exc.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Alterado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
            }
            // aqui ele preenche o basico + contabancaria;
            else if(preencheuConta) {
                if(this.campos.bancoField.getSelectedItem() == null || agenciaPrevia.equals("") || contaPrevia.equals("") || 
                nomeTitularPrevio.equals("") || cpfTitularPrevio.equals("")) {
                    this.campos.patern.erroPreenchimento("Preencha campos vazios em (Conta Bancaria).");
                    return;
                }
                if(!Utilitario.validaCPF(Utilitario.formataCampo(this.campos.cpfTitularField))) {
                    this.campos.patern.erroPreenchimento("Digite um CPF do Titular válido! (Conta Bancaria)");
                    return;
                }
                Aluno supostoExistente = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(Utilitario.formataCampo(this.campos.cpfField));
                if(this.acao.equals("cadastrar")) {
                    if(supostoExistente != null) {
                        this.campos.patern.erroPreenchimento("Aluno já cadastrado.");
                        return;
                    }
                    ServidorArmazenamento.gerenciadorAlunos.adiciona(new Aluno(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new ContaBancaria((String)this.campos.bancoField.getSelectedItem(), agenciaPrevia, 
                    contaPrevia, cpfTitularPrevio, nomeTitularPrevio), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem())));
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Cadastrado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
                else if(this.acao.equals("alterar")) {
                    if(supostoExistente == null) {
                        this.campos.patern.erroPreenchimento("Impossível alterar CPF de aluno, para fazê-lo, crie outro aluno.");
                        return;
                    }
                    supostoExistente.alterar(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new ContaBancaria((String)this.campos.bancoField.getSelectedItem(), agenciaPrevia, 
                    contaPrevia, cpfTitularPrevio, nomeTitularPrevio), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem()));
                    try {
                        GerenciadorAluno.atualizaBanco();
                    } catch(Exception exc) {
                        System.out.println(exc.getMessage());
                        exc.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Alterado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
            }
            // aqui foi preenchido o basico + endereco
            else if(preencheuEndereco) {
                if(ruaPrevia.equals("") || numeroPrevio.equals("") || complementoPrevio.equals("") || cepPrevio.equals("")
                || bairroPrevio.equals("") || cidadePrevio.equals("") || estadoPrevio.equals("") || paisPrevio.equals("")) {
                    this.campos.patern.erroPreenchimento("Preencha campos vazios em (Endereco).");
                    return;
                }
                try {
                    Integer.parseInt(numeroPrevio);
                } catch(Exception exc) {
                    this.campos.patern.erroPreenchimento("Apenas valores númericos em número(Endereço)");
                    return;
                }
                Aluno supostoExistente = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(
                    Utilitario.formataCampo(this.campos.cpfField));
                if(this.acao.equals("cadastrar")) {
                    if(supostoExistente != null) {
                        this.campos.patern.erroPreenchimento("Aluno já cadastrado.");
                        return;
                    }
                    ServidorArmazenamento.gerenciadorAlunos.adiciona(new Aluno(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new Endereco(ruaPrevia, Integer.parseInt(numeroPrevio), complementoPrevio, cepPrevio, bairroPrevio,
                    cidadePrevio, estadoPrevio, paisPrevio), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem())));
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Cadastrado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
                else if(this.acao.equals("alterar")) {
                    if(supostoExistente == null) {
                        this.campos.patern.erroPreenchimento("Impossível alterar CPF de aluno, para fazê-lo, crie outro aluno.");
                        return;
                    }
                    supostoExistente.alterar(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), new Endereco(ruaPrevia, Integer.parseInt(numeroPrevio), complementoPrevio, cepPrevio, bairroPrevio,
                    cidadePrevio, estadoPrevio, paisPrevio), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem()));
                    try {
                        GerenciadorAluno.atualizaBanco();
                    } catch(Exception exc) {
                        System.out.println(exc.getMessage());
                        exc.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Alterado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
            }
            // aqui só foi preenchido o basico mesmo
            else {
                Aluno supostoExistente = ServidorArmazenamento.gerenciadorAlunos.pesquisarAlunoCPF(
                    Utilitario.formataCampo(this.campos.cpfField));
                if(this.acao.equals("cadastrar")) {
                    if(supostoExistente != null) {
                        this.campos.patern.erroPreenchimento("Aluno já cadastrado.");
                        return;
                    }
                    ServidorArmazenamento.gerenciadorAlunos.adiciona(new Aluno(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem())));
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Cadastrado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
                else if(this.acao.equals("alterar")) {
                    if(supostoExistente == null) {
                        this.campos.patern.erroPreenchimento("Impossível alterar CPF de aluno, para fazê-lo, crie outro aluno.");
                        return;
                    }
                    supostoExistente.alterar(this.campos.nomeField.getText(),
                    Utilitario.formataCampo(this.campos.cpfField), Utilitario.formataCampo(this.campos.identidadeField),
                    (String)this.campos.sexoField.getSelectedItem(), LocalDate.parse(this.campos.dataNascimentoField.getText(),
                    formatador), Utilitario.formataCampo(this.campos.matriculaField), 
                    LocalDate.parse(this.campos.dataIngressoField.getText(), formatador), 
                    ServidorArmazenamento.gerenciadorCursos.pesquisaCursoNome((String)this.campos.cursoField.getSelectedItem()));
                    try {
                    GerenciadorAluno.atualizaBanco();
                    } catch(Exception exc) {
                        System.out.println(exc.getMessage());
                        exc.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(this.campos.patern, "Aluno Alterado com sucesso.", "CONFIRMAÇÃO", 
                    JOptionPane.INFORMATION_MESSAGE);
                    this.campos.setVisible(false);
                    this.campos.origem.setVisible(true);
                }
            }
        }
    }

}