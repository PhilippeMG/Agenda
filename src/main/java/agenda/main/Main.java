package agenda.main;

import agenda.controlador.Controlador;
import agenda.modelo.Direccion;
import agenda.modelo.Llamada;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.menu.OpcionesMenu;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.vista.Vista;

import java.time.LocalDateTime;


public class Main {
    public static void main(String[] args) throws Exception {
        Modelo modelo = new Modelo();
        Vista vista = new Vista();
        Controlador controlador = new Controlador();

        CrearTarifa fabricaTarifa = new CrearTarifa();
        CrearCliente fabricaCliente = new CrearCliente();

        controlador.setFabricaClientes(fabricaCliente);
        controlador.setFabricaTarifas(fabricaTarifa);
        controlador.setVista(vista);
        controlador.setModelo(modelo);

        vista.setControlador(controlador);
        vista.setModelo(modelo);

        modelo.setVista(vista);

        vista.run();

    }

}
