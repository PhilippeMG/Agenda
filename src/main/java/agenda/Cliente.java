package agenda;

import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

public class Cliente {
    String nombre;
    String nif;
    Direccion direccion;
    String correo;
    Date fechaDeAlta;
    int tipoTarifa;
    LinkedList <Factura> facturas =new LinkedList<>();
    LinkedList <Llamada> llamadas =new LinkedList<>();

    public Cliente(String nombre, String nif, Direccion direccion, String correo, int tipoTarifa) {
        this.nombre = nombre;
        this.nif = nif;
        this.direccion = direccion;
        this.correo = correo;
        this.fechaDeAlta = new Date();
        this.tipoTarifa = tipoTarifa;

    }
    public Date getFecha(){
        return this.fechaDeAlta;
    }

    @Override
    public String toString() {
        return "Cliente:" +
                "nombre='" + nombre + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion=" + direccion.toString() +
                ", correo='" + correo + '\'' +
                ", fechaDeAlta=" + fechaDeAlta +
                ", tipoTarifa=" + tipoTarifa ;
    }

}