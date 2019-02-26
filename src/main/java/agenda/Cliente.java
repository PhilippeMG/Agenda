package agenda;

import java.util.Arrays;
import java.util.Date;

public class Cliente {
    String nombre;
    String nif;
    Direccion direccion;
    String correo;
    Date fechaDeAlta;
    int tipoTarifa;
    Factura[] facturas;

    public Cliente(String nombre, String nif) {
        this.nombre = nombre;
        this.nif = nif;
    }

    public Cliente(String nombre, String nif, Direccion direccion, String correo, Date fechaDeAlta, int tipoTarifa, Factura[] facturas) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaDeAlta = new Date();
        this.tipoTarifa = tipoTarifa;
        this.facturas = null;
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                '}';
    }

    public static void main (String [ ] args) {

        Cliente cliente = new Cliente("Pepe", "2332X");
        System.out.printf(cliente.toString());
    }
}