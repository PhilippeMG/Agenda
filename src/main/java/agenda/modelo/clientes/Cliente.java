package agenda.modelo.clientes;

import agenda.modelo.Direccion;
import agenda.modelo.Factura;
import agenda.modelo.Llamada;
import agenda.modelo.tarifa.Tarifa;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Vector;

public abstract class Cliente implements Serializable, GetFecha {
    private String nombre;
    private String nif;
    private Direccion direccion;
    private String correo;
    private LocalDateTime fechaDeAlta;
    private Tarifa tarifa;
    private HashMap<Integer, Factura> facturas = new HashMap<>();
    private LinkedList<Llamada> llamadas = new LinkedList<>();

    public Cliente(String nombre, String nif, Direccion direccion, String correo, Tarifa tarifa) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaDeAlta = LocalDateTime.now();
        this.tarifa = tarifa;
    }

    public Cliente() {
    }

    //>>>GETTERS<<<


    public HashMap<Integer, Factura> getFacturas() {
        return facturas;
    }

    public LinkedList<Llamada> getLlamadas() {
        return llamadas;
    }
    public abstract String getNombreCompleto();
    @Override
    public LocalDateTime getFecha() {
        return this.fechaDeAlta;

    }

    public String getNombre() {
        return nombre;
    }

    public String getNif() {
        return nif;
    }

    public LocalDateTime getFechaDeAlta() {
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
        tarifa.setPrecio(nuevaTarifa);
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
    public Vector informacion(){
        Vector vector = new Vector();
        vector.add(nif);
        vector.add(nombre);
        vector.add(direccion.getProvincia());
        vector.add(direccion.getPoblacion());
        vector.add(direccion.getCP());
        vector.add(correo);
        vector.add(tarifa.getPrecio());
        vector.add(ajusteFecha(fechaDeAlta));
        return vector;


    }
    @Override
    public abstract String toString() ;
}
