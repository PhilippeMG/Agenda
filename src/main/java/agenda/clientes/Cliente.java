package agenda.clientes;

import agenda.Direccion;
import agenda.Factura;
import agenda.Llamada;
import agenda.Tarifa;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public abstract class Cliente implements Serializable, GetFecha {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correo;
    private LocalDate fechaDeAlta;
    private Tarifa tarifa;
    private HashMap<Integer, Factura> facturas = new HashMap<>();
    private LinkedList<Llamada> llamadas = new LinkedList<>();

    public Cliente(String nombre, String nif, Direccion direccion, String correo, Tarifa tarifa) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaDeAlta = LocalDate.now();
        this.tarifa = tarifa;
    }

    //>>>GETTERS<<<
    public HashMap<Integer, Factura> getFacturas() {
        return facturas;
    }

    public LinkedList<Llamada> getLlamadas() {
        return llamadas;
    }

    @Override
    public LocalDate getFecha() {
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

    public Tarifa getTarifa() {
        return tarifa;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public String getCorreo() {
        return correo;
    }

    //>>>METODOS<<
    public void cambiarTarifa(int nuevaTarifa) {
        tarifa.setTarifa(nuevaTarifa);
    }

    public void insertarLlamada(Llamada llamada) {
        llamadas.add(llamada);
    }

    public void insertarFactura(Factura factura) {
        facturas.put(factura.getCod(), factura);
    }

    public void mostrarLlamadas() {
        int contador = 0;
        for (Llamada llamada : llamadas) {
            System.out.println(contador + ": " + llamada.toString());
            contador++;
        }
    }

    public void mostrarFacturas() {
        int contador = 0;
        for (Factura factura : facturas.values()) {
            System.out.println(contador + ": " + factura.toString());
            contador++;
        }
    }

    @Override
    public String toString() {
        return "Cliente: " +
                "nombre= '" + nombre + '\'' +
                ", nif= '" + nif + '\'' +
                ", Direccion: " + direccion.toString() +
                ", correo= '" + correo + '\'' +
                ", fechaDeAlta= " + fechaDeAlta +
                ", tipoTarifa= " + tarifa.getTipoTarifa();
    }
}
