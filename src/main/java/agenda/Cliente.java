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
    public void calcularFactura(Date inicio, Date finalizacion){
        double valor=0;
        for(int i=0; i<llamadas.size();i++){
            Llamada llamada=llamadas.get(i);
            if(llamada.fechaLlamada.compareTo(inicio)>=0 && llamada.fechaLlamada.compareTo(finalizacion)>=1){
                valor+=llamada.duracion*tipoTarifa;
            }

        }
        Factura factura = new Factura(tipoTarifa,2,new Date(),inicio,valor);
        facturas.add(factura);

    }
}