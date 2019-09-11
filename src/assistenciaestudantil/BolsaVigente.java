package assistenciaestudantil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BolsaVigente extends Bolsa {

    private LocalDate dataInicio;
    private LocalDate dataFim;

    protected BolsaVigente(Bolsa bolsa, LocalDate dataInicio, LocalDate dataFim) {
        super(bolsa.getCodigo(), bolsa.getNome(), bolsa.getValor());
        this.dataInicio = dataInicio;
        this.dataFim = dataFim;
    }

    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("\n%s\nData Início: %s\nData Fim: %s\n", super.toString(),
        formatador.format(dataInicio), formatador.format(dataFim));
    }

    /**
     * @return the dataInicio
     */
    public LocalDate getDataInicio() {
        return dataInicio;
    }

    /**
     * @return the dataFim
     */
    public LocalDate getDataFim() {
        return dataFim;
    }

}