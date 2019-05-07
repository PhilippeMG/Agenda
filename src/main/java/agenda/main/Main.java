package agenda.main;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.vista.Vista;


public class Main {
    public static void main(String[] args) throws Exception {
        Vista vista = new Vista();

        Controlador controlador= new Controlador();

        CrearTarifa fabricaTarifa= new CrearTarifa();
        CrearCliente fabricaCliente= new CrearCliente();

        controlador.setFabricaClientes(fabricaCliente);
        controlador.setFabricaTarifas(fabricaTarifa);
        controlador.setVista(vista);

        Modelo modelo=new Modelo();

        controlador.setModelo(modelo);



        vista.setControlador(controlador);
        vista.setModelo(modelo);


        modelo.setVista(vista);

        vista.run();
    }

}
