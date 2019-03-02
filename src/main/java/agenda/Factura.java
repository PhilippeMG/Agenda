package agenda;

import java.time.LocalDate;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Factura {
    int tipoTarifa;
    static int cod=0;
    LocalDate inicio;
    LocalDate fin;
    LocalDate emision;
    Double importe;


    public Factura(Cliente cliente, LocalDate inico, LocalDate fin) {

        this.tipoTarifa = cliente.tipoTarifa;
        this.cod += 1;
        this.inicio = inico;
        this.fin = fin;
        this.importe = importe(cliente, inicio, fin);
        this.emision = LocalDate.now();
    }

    public static int getCod() {
        return cod;
    }

    public Double importe(Cliente cliente, LocalDate inicio, LocalDate fin) {
        double importe=0; //€/min
        for (Llamada llamada : cliente.llamadas) {
            //si es igual a inicio o a fin o posterior a inicio o anteror a fin.
            if((llamada.fechaLlamada.equals(inicio)  || llamada.fechaLlamada.isAfter(inicio)) && (llamada.fechaLlamada.equals(fin) || llamada.fechaLlamada.isBefore(fin))){
                importe += cliente.tipoTarifa * llamada.duracion;
            }
        }
        return importe;
    }

    @Override
    public String toString() {
        return "Factura{" +
                "tipoTarifa=" + this.tipoTarifa +
                ", cod=" + this.cod +
                ", inicio=" + this.inicio +
                ", fin=" + this.fin +
                ", importe=" + this.importe +
                '}';
    }
}
