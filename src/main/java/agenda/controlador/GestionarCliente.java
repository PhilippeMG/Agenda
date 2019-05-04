package agenda.controlador;

import agenda.modelo.Direccion;
import agenda.modelo.Gestor;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.Tarifa;
import agenda.modelo.tarifa.TarifaBasica;

public class GestionarCliente {
    public GestionarCliente(){

    }

 public void añadirCliente(String nombre, String nif,int cp, String provincia, String poblacion, String correo, String apellidos,int precio) throws InvalidArguments {
     CrearCliente creadorCliente= new CrearCliente();
     CrearTarifa creadorTarifa= new CrearTarifa();
     Direccion direccion= new Direccion(cp,provincia,poblacion);
     Cliente cliente;
     if(apellidos.length()==0){
         cliente= creadorCliente.getClienteEmpresa(nombre,nif,direccion,correo,creadorTarifa.getTarifaBasica(precio));
     }else{
         cliente = creadorCliente.getClienteParticular(nombre,nif,direccion,correo,creadorTarifa.getTarifaBasica(precio),apellidos);
     }
      Gestor gestor=new Gestor();
     gestor.insertarCliente(cliente);
     gestor.mostrarClientes(gestor.getClientes());
 }
}
