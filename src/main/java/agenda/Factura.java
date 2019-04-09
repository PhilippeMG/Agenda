package agenda;


import agenda.clientes.Cliente;
import agenda.clientes.GetFecha;
import agenda.tarifa.Tarifa;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class Factura implements Serializable, GetFecha {
    Tarifa tipoTarifa;
    static int cod = 0;
    LocalDateTime inicio;
    LocalDateTime fin;
    LocalDateTime emision;
    Double importe = 0.0;


    public Factura(Cliente cliente, LocalDateTime inico, LocalDateTime fin) {

        this.tipoTarifa = cliente.getTarifa();
        this.cod += 1;
        this.inicio = inico;
        this.fin = fin;
        this.importe = importe(cliente, inicio, fin);
        this.emision = LocalDateTime.now();
    }

    public static int getCod() {
        return cod;
    }

    public Double getImporte() {
        if (importe == null) return 0.0;
        return importe;
    }

    public LocalDateTime getFecha() {
        return emision;
    }

    public Double importe(Cliente cliente, LocalDateTime inicio, LocalDateTime fin) {
        double importe = 0; //€/min
        if (cliente == null || cliente.getLlamadas() == null) return 0.0;
        for (Llamada llamada : cliente.getLlamadas()) {
            //si es igual a inicio o a fin o posterior a inicio o anteror a fin.
            if ((llamada.fechaLlamada.equals(inicio) || llamada.fechaLlamada.isAfter(inicio)) && (llamada.fechaLlamada.equals(fin) || llamada.fechaLlamada.isBefore(fin))) {
                importe += cliente.getTarifa().calculaPrecio(llamada);
            }
        }
        return importe;
    }

    @Override
    public String toString() {
        return "Factura :" +
                "Tarifa: " + this.tipoTarifa +
                ", cod: " + this.cod +
                ", inicio: " + this.inicio +
                ", fin: " + this.fin +
                ", importe: " + this.importe;
    }

}
