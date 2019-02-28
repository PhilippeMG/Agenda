package agenda;

import java.util.Date;

public class Factura {
    int tipoTarifa;
    int cod;
    Date emision;
    Date periodo;
    Double importe;

    public Factura(int tipoTarifa, int cod, Date emision, Date periodo, Double importe) {
        this.tipoTarifa = tipoTarifa;
        this.cod = cod;
        this.emision = emision;
        this.periodo = periodo;
        this.importe = importe;
    }
}
