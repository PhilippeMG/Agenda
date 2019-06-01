package agenda.controlador;

import agenda.modelo.Direccion;
import agenda.modelo.Factura;
import agenda.modelo.Llamada;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.FacturaNotFound;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.Tarifa;
import agenda.vista.Vista;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.*;

public class Controlador implements InterfaceControlador {
    private Modelo modelo;
    private Vista vista;
    private CrearCliente creadorCliente;
    private CrearTarifa creadorTarifa;

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

    public void insertarCliente(String nombre, String nif, int cp, String provincia, String poblacion, String correo, String apellidos, int precio, LinkedList<String> oferta) throws InvalidArguments {
        Tarifa tarifa = TarifaCliente(precio, oferta);
        Direccion direccion = new Direccion(cp, provincia, poblacion);
        Cliente cliente;
        if (apellidos.length() == 0) {
            cliente = creadorCliente.getClienteEmpresa(nombre, nif, direccion, correo, tarifa);
        } else {
            cliente = creadorCliente.getClienteParticular(nombre, nif, direccion, correo, tarifa, apellidos);
        }

        modelo.insertarCliente(cliente);
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
        //modelo.mostrarClientes(modelo.getClientes());
    }

    public void guardarDatos() {
        try {
            modelo.guardarDatos();
        } catch (Exception IOException) {
            System.out.println("No se han podido guardar los datos. ");

        }
    }

    public void cargarDatos() {
        try {
            modelo.cargarDatos();
        } catch (Exception ClassNotFoundException) {
            System.out.println("Error al cargar los datos");

        }
    }

    public void modificarTarifa(String dni, int precio) throws ClientNotFound {
        modelo.cambiarTarifa(dni, precio);
    }

    public String opcionDevolverClientesEntreFechas(int[] fechaInicio, int[] fechaFinal) {
        Collection<Cliente> myCollection = modelo.getClientes().values();
        List<Cliente> list = new LinkedList<>(myCollection);
        Vector dades = new Vector();
        String datos = "";

        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);


        LinkedList<Cliente> clienteEntreFechas = modelo.devolverEntreFechas(list, inici, fi);
        if (clienteEntreFechas.isEmpty()) {
        } else {
            datos += "NIF\tNombre\tProvincia\tPoblación\tCP\tCorreo\tTarifa\tFecha de Alta\n";

            for (Cliente client : clienteEntreFechas) {
                dades = client.informacion();
                datos += (dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\t" + dades.get(3) + "\t" + dades.get(4) + "\t" + dades.get(5)  +"\t" + dades.get(6) +"\t" + dades.get(7)+ "\n");
            }
        }
        return datos;
    }

    public String opcionDevolverLlamadasEntreFechas(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {
        Vector dades = new Vector();
        String datos = "";
        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);

        LinkedList<Llamada> llamadas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            llamadas = modelo.devolverEntreFechas(cliente.getLlamadas(), inici, fi);
        } else {
            throw new ClientNotFound();
        }

        datos += "Num destino\t Duración\t Fecha de la llamada\n";

        if (llamadas.isEmpty()) {
        } else {
            for (Llamada llamada : llamadas) {
                dades = llamada.informacion();
                datos += (dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\n");


            }
        }
        return datos;
    }

    public String opcionDevolverFacturaEntreFechas(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {
        Vector dades = new Vector();
        String datos = "";
        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);

        LinkedList<Factura> facturas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Factura> myCollection = cliente.getFacturas().values();
            List<Factura> list = new LinkedList<>(myCollection);
            facturas = modelo.devolverEntreFechas(list, inici, fi);
        } else {
            throw new ClientNotFound();
        }

        datos += "Codigo\t Tarifa\tFecha Inicio\t\tFecha Final\t\tImporte\tFecha Emisón\n";

        if (facturas.isEmpty()) {
        } else {
            for (Factura fact : facturas) {
                dades = fact.informacion();
                datos += dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\t\t" + dades.get(3) + "\t\t" + dades.get(4) + "\t"+dades.get(5)+"\n";

            }

        }
        return datos;
    }

    public Vector devolverCliente(String dni) throws ClientNotFound {
        Cliente cliente = modelo.getCliente(dni);

        Vector info = cliente.informacion();
        info.add(cliente.getNombreCompleto());

        return info;


    }

    public String getFacturasCliente(String dni) throws ClientNotFound {
        Vector dades = new Vector();
        LinkedList<Factura> facturas;
        String datos = "";
        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Factura> myCollection = cliente.getFacturas().values();
            facturas = new LinkedList<>(myCollection);

        } else {
            throw new ClientNotFound();
        }

        datos += "Codigo\t Tarifa\tFecha Inicio\t\tFecha Final\t\tImporte\tFecha Emisón\n";

        if (!facturas.isEmpty()) {
            for (Factura fact : facturas) {
                dades = fact.informacion();
                datos += dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\t\t" + dades.get(3) + "\t\t" + dades.get(4) + "\t"+dades.get(5)+"\n";
            }

        }
        return datos;
    }

    public void insertarLlamada(String dni, int[] fecha, int duracio, int numero) throws ClientNotFound {

        if (modelo.getClientes().containsKey(dni)) {
            LocalDateTime data = modelo.crearFecha(fecha[0], fecha[1], fecha[2]);
            modelo.insertarLlamada(dni, new Llamada(numero, duracio, data));


        } else {
            throw new ClientNotFound();
        }
    }

    public String getLlamadasCliente(String dni) throws ClientNotFound {
        Vector dades = new Vector();
        String datos = "";
        LinkedList<Llamada> llamadas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Llamada> myCollection = cliente.getLlamadas();
            llamadas = new LinkedList<>(myCollection);

        } else {
            throw new ClientNotFound();
        }

        datos += "Num destino\t Duración\t Fecha de la llamada\n";
        if (!llamadas.isEmpty()) {

            for (Llamada llamada : llamadas) {
                // datos.add(llamada.informacion());
                dades = llamada.informacion();
                datos += (dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\n");
            }

        }
        return datos;
    }

    public void emitirFacturaCliente(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {

        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);


        try {
            modelo.emitirFactura(dni, inici, fi);

        } catch (Exception e) {
            throw new ClientNotFound();
        }

    }

    public Vector devolverFactura(int codigo) throws FacturaNotFound {
        Factura fact = null;
        try {
            fact = modelo.devolverFactura(codigo);
        } catch (FacturaNotFound facturaNotFound) {
            throw new FacturaNotFound();
        }

        return fact.informacion();
    }

}
