package agenda.modelo;


import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.GetFecha;
import agenda.modelo.tarifa.Tarifa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Vector;

public class Factura implements Serializable, GetFecha {
    private Tarifa tipoTarifa;
    private static int cod = 0;
    private int codFact=0;
    private LocalDateTime inicio;
    private LocalDateTime fin;
    private LocalDateTime emision;
    private Double importe = 0.0;


    public Factura(Cliente cliente, LocalDateTime inico, LocalDateTime fin) {

        this.tipoTarifa = cliente.getTarifa();
        this.codFact=this.cod;
        this.cod+=1;
        this.inicio = inico;
        this.fin = fin;
        this.importe = importe(cliente, inicio, fin);
        this.emision = LocalDateTime.now();
    }

    public  int getCod() {
        return codFact;
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
            if ((llamada.getFecha().equals(inicio) || llamada.getFecha().isAfter(inicio)) && (llamada.getFecha().equals(fin) || llamada.getFecha().isBefore(fin))) {
                importe += cliente.getTarifa().calculaPrecio(llamada);
            }
        }
        return importe;
    }

    public static void setContCod(int cod) {
        Factura.cod = cod;
    }
    public static int getContCod() {
        return cod;
    }

    @Override
    public String toString() {
        return "Factura :" +
                "Tarifa: " + this.tipoTarifa +
                ", cod: " + getCod()+
                ", inicio: " + this.inicio +
                ", fin: " + this.fin +
                ", importe: " + this.importe;
    }
    public Vector informacion() {
        Vector vector = new Vector();
        vector.add(getCod());
        vector.add(tipoTarifa.getPrecio());
        vector.add(this.inicio);
        vector.add(this.fin);
        vector.add(getImporte());

        return vector;
    }
}
