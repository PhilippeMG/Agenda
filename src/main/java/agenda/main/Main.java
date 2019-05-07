package agenda.main;

import agenda.controlador.GestionarAgenda;
import agenda.modelo.Gestor;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.menu.OpcionesMenu;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.vista.Ventana;


public class Main {
    public static void main(String[] args) throws Exception {
        Ventana vista = new Ventana();

        GestionarAgenda controlador= new GestionarAgenda();

        CrearTarifa fabricaTarifa= new CrearTarifa();
        CrearCliente fabricaCliente= new CrearCliente();

        controlador.setFabricaClientes(fabricaCliente);
        controlador.setFabricaTarifas(fabricaTarifa);
        Gestor modelo=new Gestor();

        controlador.setModelo(modelo);



        vista.setControlador(controlador);



        modelo.setVista(vista);

        vista.run();
    }

}
