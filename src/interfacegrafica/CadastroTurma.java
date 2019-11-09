package interfacegrafica;

import javax.swing.*;

import complementares.Utilitario;
import ensino.classecurso.GerenciadorCursos;
import ensino.secaodisciplina.Disciplina;
import ensino.secaodisciplina.DisciplinaAplicada;
import ensino.secaodisciplina.GerenciadorDisciplinas;
import pessoas.classealuno.Aluno;
import sistema.classes.ServidorArmazenamento;

import java.awt.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class CadastroTurma extends JPanel {

        protected PainelOpcoesTurma origem;
        protected JanelaPrincipal parent;

        protected GridBagConstraints constantes = new GridBagConstraints();
        protected JLabel disciplinaLabel = new JLabel("Disciplina (Código): ");
        protected JComboBox<String> disciplinaField = new JComboBox<String>(
                ServidorArmazenamento.gerenciadorDisciplinas.getCodigoDisciplinas());
        protected JLabel cpfProfessorLabel = new JLabel("CPF do professor: ");
        protected JTextField cpfProfessorField = Utilitario.geraField();
        protected JTextField nomeProfessor = Utilitario.geraField();
        protected JLabel dataInicioLabel = new JLabel("Data Início: ");
        protected JTextField dataInicioField = Utilitario.geraField("data");
        protected JLabel dataFimLabel = new JLabel("Data Fim: ");
        protected JTextField dataFimField = Utilitario.geraField("data");
        protected JLabel vagasDisponiveisLabel = new JLabel("Vagas Disponíveis: ");
        protected JTextField vagasDisponiveisField = Utilitario.geraField();
        protected JLabel semestreLabel = new JLabel("Semestre: ");
        protected JTextField semestreField = Utilitario.geraField();
        protected JLabel alunosLabel = new JLabel("Digite CPF alunos:");
        protected JTextField alunosField = Utilitario.geraField();
        protected JButton adicionarAlunoButton = new JButton("ADICIONAR ALUNO");
        protected JButton excluirAlunoButton = new JButton("EXCLUIR ALUNO");
        protected JLabel codigoLabel = new JLabel("Código Turma: ");
        protected JTextField codigoField = Utilitario.geraField();
        protected JTable alunosPesquisados = new JTable();
        protected JScrollPane alunosContainer = new JScrollPane(this.alunosPesquisados);
        protected ArrayList<Aluno> alunosAdicionados = new ArrayList<Aluno>();
        protected JButton botaoConfirma = new JButton("CONFIRMAR CADASTRO");
        protected JButton botaoVolta = new JButton("VOLTAR");
        protected JButton botaoExcluir = new JButton("EXCLUIR DISCIPLINA");
        protected AcaoCadastrarDisciplinaVigente acaoBotaoConfirma = new AcaoCadastrarDisciplinaVigente(this);

        public CadastroTurma(JanelaPrincipal parent, PainelOpcoesTurma origem) {

            this.parent = parent;
            this.origem = origem;

            this.setLayout(new GridBagLayout());
            this.alunosContainer.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
            this.alunosContainer.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
            this.alunosContainer.setMinimumSize(new Dimension(700, 200));
            this.alunosContainer.setPreferredSize(new Dimension(700, 200));
            this.alunosContainer.setBorder(BorderFactory.createEmptyBorder(10, 0, 0 , 0));
            this.alunosPesquisados.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);

            this.constantes.insets = JanelaPrincipal.ESPACAMENTO_PADRAO;
            this.nomeProfessor.setEditable(false);
            //this.botaoExcluir.addActionListener(new AcaoExcluirDisciplinaVigente(this.parent, this.codigoField));
            //this.botaoConfirma.addActionListener(this.acaoBotaoConfirma);
            this.botaoVolta.addActionListener(new TrocaTela(this, this.origem));
            this.botaoConfirma.addActionListener(acaoBotaoConfirma);
            this.adicionarAlunoButton.addActionListener(new AdicionarAlunoNaTabela(this.alunosPesquisados, this.alunosField,
                    this.codigoField, this.alunosAdicionados, this));
            this.excluirAlunoButton.addActionListener(new ExcluirAlunoDaTabela(this.alunosPesquisados,
                    this.alunosAdicionados, this, this.codigoField));
            this.alunosPesquisados.setModel(ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(
                    this.alunosAdicionados, null));
            Utilitario.formataEspacamentoTabela(this.alunosPesquisados, 5);

            Utilitario.geraCampoVertical(this.disciplinaLabel, this.disciplinaField, this, this.constantes);
            Utilitario.geraCampoHorizontal(this.vagasDisponiveisLabel, this.vagasDisponiveisField, this, this.constantes);
            this.constantes.gridy++;
            this.constantes.gridx = 0;
            this.add(this.cpfProfessorLabel, this.constantes);
            this.constantes.gridx++;
            this.cpfProfessorField.addKeyListener(new ProfessorFieldListener(this.cpfProfessorField, this.nomeProfessor));
            this.add(this.cpfProfessorField, this.constantes);
            this.constantes.gridx++;
            this.add(this.nomeProfessor, this.constantes);
            this.constantes.gridy++;
            Utilitario.geraCampoVertical(this.dataInicioLabel, this.dataInicioField, this, this.constantes);
            Utilitario.geraCampoHorizontal(this.dataFimLabel, this.dataFimField, this, this.constantes);
            Utilitario.geraCampoVertical(this.codigoLabel, this.codigoField, this, this.constantes);
            Utilitario.geraCampoHorizontal(this.semestreLabel, this.semestreField, this, this.constantes);
            this.constantes.gridy++;
            this.constantes.gridx = 0;
            this.add(this.alunosLabel, this.constantes);
            this.constantes.gridx++;
            this.add(this.alunosField, this.constantes);
            this.constantes.gridx++;
            this.add(this.adicionarAlunoButton, this.constantes);
            this.constantes.gridx++;
            this.add(this.excluirAlunoButton, this.constantes);

            this.constantes.gridy++;
            this.constantes.gridx = 0;
            this.constantes.gridwidth = 4;
            this.add(this.alunosContainer, this.constantes);
            this.constantes.gridwidth = 1;

            this.constantes.gridy++;
            this.constantes.gridx = 1;
            this.add(this.botaoVolta, this.constantes);
            this.constantes.gridx++;
            this.add(this.botaoExcluir, this.constantes);
            this.constantes.gridx++;
            this.add(this.botaoConfirma, this.constantes);

            this.setVisible(false);
        }

        public void setaCampos(String acao, DisciplinaAplicada disciplina) {
            DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            if(disciplina != null) {
                this.codigoField.setText(disciplina.getCodigoVigente());
                this.cpfProfessorField.setText(disciplina.getProfessor());
                this.dataInicioField.setText(formatador.format(disciplina.getDataInicio()));
                this.dataFimField.setText(formatador.format(disciplina.getDataFim()));
                this.vagasDisponiveisField.setText(Integer.toString(disciplina.getVagasDisponiveis()));
                this.semestreField.setText(Integer.toString(disciplina.getSemestre()));
                this.disciplinaField.setSelectedItem(disciplina.getCodigo());
                this.alunosAdicionados = disciplina.getArrayListAlunosMatriculados();
                this.alunosPesquisados.setModel(
                        ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(
                                this.alunosAdicionados, disciplina));
                if(acao.equals("view")) {
                    this.botaoConfirma.setVisible(false);
                    this.botaoExcluir.setVisible(false);
                }
                else if(acao.equals("change")) {
                    this.botaoConfirma.setText("ALTERAR DISCIPLINA");
                    this.acaoBotaoConfirma.setAcao("alterar");
                    this.acaoBotaoConfirma.codigoAnterior = disciplina.getCodigoVigente();
                    this.botaoExcluir.setVisible(true);
                    this.botaoConfirma.setVisible(true);
                }
            }
            else {
                this.codigoField.setText(null);
                this.cpfProfessorField.setText(null);
                this.disciplinaField.setSelectedItem(null);
                this.vagasDisponiveisField.setText(null);
                this.semestreField.setText(null);
                this.dataInicioField.setText(null);
                this.dataFimField.setText(null);
                this.alunosAdicionados.clear();
                this.alunosPesquisados.setModel(
                        ServidorArmazenamento.gerenciadorDisciplinas.getTableFromArray(
                                this.alunosAdicionados, disciplina));
                this.botaoConfirma.setVisible(true);
                this.botaoExcluir.setVisible(false);
            }
        }

    }
