package ensino.classecurso;

import java.util.ArrayList;

import contratos.ClassesGeral;
import ensino.secaodisciplina.Disciplina;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

public class Curso implements ClassesGeral {

    private String codigo;
    private String nome;
    private int tempoConclusao;
    private int cargaHoraria;
    private ArrayList<Disciplina> disciplinasRelacionadas = new ArrayList<Disciplina>();

    /**
     *
     * @param codigo: codigo curso
     * @param nome: nome curso
     * @param tempoConclusao: tempo de conclusão curso
     * @param cargaHoraria: carga horária do curso
     */
    public Curso(String codigo, String nome, int tempoConclusao, int cargaHoraria) {
        this.codigo = codigo;
        this.nome = nome;
        this.tempoConclusao = tempoConclusao;
        this.cargaHoraria = cargaHoraria;
    }

    /**
     *
     * @param nome: nome curso
     * @param tempoConclusao: tempo de conclusão curso
     * @param cargaHoraria: carga horária do curso
     */
    public void alterar(String nome, int tempoConclusao, int cargaHoraria) {
        this.nome = nome;
        this.tempoConclusao = tempoConclusao;
        this.cargaHoraria = cargaHoraria;
    }

    /**
     *
     * @return string para armazenamento do curso no banco de dados txt
     */
    public String getStorageString() {
        return String.format("%s-%s-%d-%d", this.codigo, this.nome, this.tempoConclusao, this.cargaHoraria);
    }

    /**
     *
     * @param texto: string com informações de um curso
     * @return curso relativo às informações recebidas
     */
    public static Curso fromStorageString(String texto) {
        String[] campos = texto.split("-");
        return new Curso(campos[0], campos[1], Integer.parseInt(campos[2]), Integer.parseInt(campos[3]));
    }

    /**
     *
     * @return relatório escrito sobre o curso.
     */
    @Override
    public String toString() {
        return String.format("\nCódigo: %s\nNome: %s\nTempo conclusão(Semestres): %d\nCarga Horária: %d\n", this.codigo,
        this.nome, this.tempoConclusao, this.cargaHoraria);
    }

    /**
     *
     * @return vetor de informações básicas do curso.
     */
    public String[] getInfoBasicasArray() {
        String[] infos = new String[4];
        infos[0] = this.codigo;
        infos[1] = this.nome;
        infos[2] = Integer.toString(this.cargaHoraria);
        infos[3] = Integer.toString(this.tempoConclusao);
        return infos;
    }

    /**
     * @return the disciplinasRelacionadas
     */
    public ArrayList<Disciplina> getDisciplinasRelacionadas() {
        return disciplinasRelacionadas;
    }

    /**
     * @return the cargaHoraria
     */
    public int getCargaHoraria() {
        return cargaHoraria;
    }

    /**
     * @return the codigo
     */
    public String getCodigo() {
        return codigo;
    }

    /**
     * @return the nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return the tempoConclusao
     */
    public int getTempoConclusao() {
        return tempoConclusao;
    }

    /**
     *
     * @return TableModel com disciplinas relacionadas ao curso.
     */
    public TableModel getDisciplinasRelacionadasTable() {
        String[] header = {
                "Codigo", "Nome", "Carga Horária", "Maximo Faltas"
        };
        String[][] data;
        if(this.disciplinasRelacionadas.size() > 0) {
            data = new String[this.disciplinasRelacionadas.size()][4];
            for(int i = 0; i < this.disciplinasRelacionadas.size(); i++) {
                data[i] = this.disciplinasRelacionadas.get(i).getInfoBasicasArray();
            }
        }
        else {
            data = null;
        }
        return new DefaultTableModel(data, header) {

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }

        };
    }

    /**
     *
     * @param disciplina: disciplina a ser adicionada
     */
    public void adicionaDisciplina(Disciplina disciplina) {
        this.disciplinasRelacionadas.add(disciplina);
    }

}