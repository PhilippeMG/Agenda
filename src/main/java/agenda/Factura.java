package agenda;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;

public class Factura {
    int tipoTarifa;
    static  int cod=0;
    Date emision;
    Date periodo;
    Double importe;


    public Factura(int tipoTarifa, Date emision, Date periodo, Double importe) {
        this.tipoTarifa = tipoTarifa;
        this.cod = cod++;
        this.emision = emision;
        this.periodo = periodo;
        this.importe = importe;
    }
    @Override
    public String toString() {
        return "Factura{" +
                "tipoTarifa=" + tipoTarifa +
                ", cod=" + cod +
                ", emision=" + emision +
                ", periodo=" + periodo +
                ", importe=" + importe +
                '}';
    }
    public static  void calcularFactura(Cliente cliente, Date inicio, Date finalizacion,  HashMap<Integer, Factura> facturasMap){
        double valor=0;
        LinkedList llamadas=cliente.llamadas;
        LinkedList facturas=cliente.facturas;
        for(int i=0; i<llamadas.size();i++){
            Llamada llamada= (Llamada) llamadas.get(i);
            if(llamada.fechaLlamada.after(inicio) || llamada.fechaLlamada.before(finalizacion)){//MODIFICAR!!!
                valor+=llamada.duracion*cliente.tipoTarifa;
            }

        }
        //Como hacemos lo del periodo??
        Factura factura = new Factura(cliente.tipoTarifa,new Date(),inicio,valor);
        facturas.add(factura);
        facturasMap.put((int)factura.cod,factura);

    }
    public Factura buscarFactura(HashMap<String, Factura> facturas,String codigo){
       return facturas.get(codigo);
    }
    public LinkedList<Factura> recuperarFacturasCliente(Cliente cliente){
        return cliente.facturas;
    }
}
