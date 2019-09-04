package assistenciaestudantil;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class BolsaVigente {

    private Bolsa bolsa;
    private LocalDate dataInicio;
    private LocalDate dataFim;

    protected BolsaVigente(String codigo, Bolsa bolsa, LocalDate dataInicio, LocalDate dataFim) throws Exception{
        if(!(codigo == "gerenciador_bolsas"))
            throw new Exception("Tentativa ilegal de geração de bolsa interceptada.");
        else {
            this.bolsa = bolsa;
            this.dataInicio = dataInicio;
            this.dataFim = dataFim;
        }
    }

    public String toString() {
        DateTimeFormatter formatador = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return String.format("Bolsa: %s\nData Início: %s\nData Fim: %s\n", this.bolsa.getNome(),
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