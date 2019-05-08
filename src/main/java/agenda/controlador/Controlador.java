package agenda.controlador;

import agenda.modelo.Direccion;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.Tarifa;
import agenda.vista.Vista;

import java.io.IOException;
import java.util.LinkedList;

public class Controlador implements InterfaceControlador{
    Modelo modelo;
    Vista vista;
    CrearCliente creadorCliente;
    CrearTarifa creadorTarifa;

    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }

    public void setVista(Vista vista) {
        this.vista = vista;
    }

    public void setFabricaClientes(CrearCliente fabrica) {
        creadorCliente = fabrica;
    }

    public void setFabricaTarifas(CrearTarifa fabrica) {
        creadorTarifa = fabrica;
    }

    public void añadirCliente(String nombre, String nif, int cp, String provincia, String poblacion, String correo, String apellidos, int precio, LinkedList<String> oferta) throws InvalidArguments {
        Tarifa tarifa = TarifaCliente(precio, oferta);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente;
        if (apellidos.length() == 0) {
            cliente = creadorCliente.getClienteEmpresa(nombre, nif, direccion, correo, tarifa);
        } else {
            cliente = creadorCliente.getClienteParticular(nombre, nif, direccion, correo, tarifa, apellidos);
        }

        modelo.insertarCliente(cliente);
        modelo.mostrarClientes(modelo.getClientes());
    }

    public Tarifa TarifaCliente(int precio, LinkedList<String> oferta) {
        Tarifa tarifa = creadorTarifa.getTarifaBasica(precio);
        if (oferta.contains("Tardes")) {
            tarifa = creadorTarifa.getOfertaTardes(tarifa);
        }
        if (oferta.contains("Domingos")) {
            tarifa = creadorTarifa.getOfertaDomingos(tarifa);

        }
        return tarifa;
    }

    public void eliminarCliente(String DNI) throws ClientNotFound {
        modelo.borrarCliente(DNI);
        modelo.mostrarClientes(modelo.getClientes());
    }

    public void guardarDatos() throws IOException {
        modelo.guardarDatos();
        System.out.println("Datos guardados: ");
        modelo.mostrarClientes(modelo.getClientes());
    }

    public void cargarDatos() throws ClassNotFoundException {
        modelo.cargarDatos();
        System.out.println("Datos cargados: ");
        modelo.mostrarClientes(modelo.getClientes());
    }

    public void modificarTarifa(String dni, int precio) throws ClientNotFound {
        modelo.cambiarTarifa(dni, precio);
    }

}
