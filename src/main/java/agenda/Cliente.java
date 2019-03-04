package agenda;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Cliente {
    String nombre;
    String nif;
    Direccion direccion;
    String correo;
    LocalDate fechaDeAlta;
    int tipoTarifa;
    HashMap<Integer, Factura> facturas = new HashMap<>();
    LinkedList <Llamada> llamadas =new LinkedList<>();

    public Cliente(String nombre, String nif, Direccion direccion, String correo, int tipoTarifa) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaDeAlta = LocalDate.now();
        this.tipoTarifa = tipoTarifa;
    }

    public LocalDate getFecha(){
        return this.fechaDeAlta;
    }

    public String getNombre() {
        return nombre;
    }

    public String getNif() {
        return nif;
    }

    public LocalDate getFechaDeAlta() {
        return fechaDeAlta;
    }

    public int getTipoTarifa() {
        return tipoTarifa;
    }

    public void cambiarTarifa(int nuevaTarifa){
        this.tipoTarifa = nuevaTarifa;
    }

    public void añadirLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    public void añadirFactura(Factura factura) {
        facturas.put(factura.getCod(), factura);
    }


    public void mostrarLlamadas() {
        int contador=0;
        for (Llamada llamada : llamadas) {
            System.out.println(contador + ": " + llamada.toString());
            contador++;
        }
    }
    public void mostrarFacturas() {
        int contador=0;
        for (Factura factura : facturas.values()) {
            System.out.println(contador + ": " + factura.toString());
            contador++;
        }
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", Direccion: " + direccion.toString() +
                ", correo='" + correo + '\'' +
                ", fechaDeAlta=" + fechaDeAlta +
                ", tipoTarifa=" + tipoTarifa ;
    }
}