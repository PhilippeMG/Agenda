package agenda.controlador;

import agenda.modelo.Direccion;
import agenda.modelo.Gestor;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.Tarifa;
import agenda.modelo.tarifa.TarifaBasica;

import java.util.LinkedList;

public class GestionarCliente {
    public GestionarCliente(){

    }

 public void añadirCliente(String nombre, String nif, int cp, String provincia, String poblacion, String correo, String apellidos, int precio, LinkedList<String> oferta) throws InvalidArguments {
     CrearCliente creadorCliente= new CrearCliente();
     Tarifa tarifa=TarifaCliente(precio,oferta);
     Direccion direccion= new Direccion(cp,provincia,poblacion);
     Cliente cliente;
     if(apellidos.length()==0){
         cliente= creadorCliente.getClienteEmpresa(nombre,nif,direccion,correo,tarifa);
     }else{
         cliente = creadorCliente.getClienteParticular(nombre,nif,direccion,correo,tarifa,apellidos);
     }
      Gestor gestor=new Gestor();
     gestor.insertarCliente(cliente);
     gestor.mostrarClientes(gestor.getClientes());
 }
 public Tarifa TarifaCliente(int precio,LinkedList<String> oferta){
     CrearTarifa creadorTarifa= new CrearTarifa();
     Tarifa tarifa=creadorTarifa.getTarifaBasica(precio);
     if (oferta.contains("Tardes")){
         tarifa=creadorTarifa.getOfertaTardes(tarifa);
     }
     if (oferta.contains("Domingos")){
        tarifa=creadorTarifa.getOfertaDomingos(tarifa);

     }
     return tarifa;



 }
}
