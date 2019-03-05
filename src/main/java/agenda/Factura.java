package agenda;

import groovy.ui.SystemOutputInterceptor;

import java.time.LocalDate;
import java.time.Month;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Factura {
    int tipoTarifa;
    static int cod=0;
    LocalDate inicio;
    LocalDate fin;
    LocalDate emision;
    Double importe=0.0;


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

    public Double getImporte() {
        if (importe==null) return 0.0;
        return importe;
    }

    public Double importe(Cliente cliente, LocalDate inicio, LocalDate fin) {
        double importe=0; //€/min
        if (cliente==null || cliente.llamadas== null) return 0.0;
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
        return "Factura :" +
                "tipoTarifa=" + this.tipoTarifa +
                ", cod=" + this.cod +
                ", inicio=" + this.inicio +
                ", fin=" + this.fin +
                ", importe=" + this.importe;
    }

    public static void main(String[] args) {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        Cliente cliente = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
        Llamada llamada3 = new Llamada(654078311,0.9, LocalDate.of(2017, Month.MARCH, 1));
        cliente.añadirLlamada(llamada3);
        Factura factura = new Factura(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3));
        System.out.println("--Probando metodos--");
        System.out.println(factura.toString());
        System.out.println("--Probando getImporte--");
        System.out.println(factura.getImporte());
        System.out.println("--Probando Importe--");
        System.out.println(factura.getImporte());
        System.out.println("--Probando getCode--");
        System.out.println(factura.getCod());
    }
}
