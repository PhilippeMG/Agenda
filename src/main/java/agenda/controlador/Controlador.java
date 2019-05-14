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
import java.time.LocalDateTime;
import java.util.*;

public class Controlador implements InterfaceControlador {
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
       // modelo.mostrarClientes(modelo.getClientes());
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

    public void guardarDatos() throws IOException {
        modelo.guardarDatos();
        System.out.println("Datos guardados: ");
        //modelo.mostrarClientes(modelo.getClientes());
    }

    public void cargarDatos() throws ClassNotFoundException {
        modelo.cargarDatos();
        System.out.println("Datos cargados: ");
      //  modelo.mostrarClientes(modelo.getClientes());
    }

    public void modificarTarifa(String dni, int precio) throws ClientNotFound {
        modelo.cambiarTarifa(dni, precio);
    }

    public Vector opcionDevolverClientesEntreFechas(int[] fechaInicio, int[] fechaFinal) {
        Collection<Cliente> myCollection = modelo.getClientes().values();
        List<Cliente> list = new LinkedList<>(myCollection);
        Vector datos = new Vector();

        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        //System.out.println("inici:" + inici.toString());
        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);
        //System.out.println("fi:" + fi.toString());
        //System.out.println("claves: " + list.toString());


        LinkedList<Cliente> clienteEntreFechas = modelo.devolverEntreFechas(list, inici, fi);
        if (clienteEntreFechas.isEmpty()) {
            System.out.println("NO HAY CLIENTES ENTRE ESAS FECHAS");
        } else {
            for (Cliente client : clienteEntreFechas) {
                datos.add(client.informacion());
            }
            //System.out.println(clienteEntreFechas.toString());
        }
        return datos;
    }

    public Vector opcionDevolverLlamadasEntreFechas(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {
        Vector datos = new Vector();

        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);
        LinkedList<Llamada> llamadas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            llamadas = modelo.devolverEntreFechas(cliente.getLlamadas(), inici, fi);
            //System.out.printf(llamadas.toString());
        } else {
            throw new ClientNotFound();
        }


        if (llamadas.isEmpty()) {
            System.out.println("NO HAY LLAMADAS ENTRE ESAS FECHAS");
        } else {
            for (Llamada llamada : llamadas) {
                datos.add(llamada.informacion());
            }
            //System.out.println(llamadas.toString());
        }
        return datos;
    }

    public Vector opcionDevolverFacturaEntreFechas(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {
        Vector datos = new Vector();

        LocalDateTime inici = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fi = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);
        LinkedList<Factura> facturas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Factura> myCollection = cliente.getFacturas().values();
            List<Factura> list = new LinkedList<>(myCollection);
            facturas = modelo.devolverEntreFechas(list, inici, fi);
           // System.out.printf(facturas.toString());
        } else {
            throw new ClientNotFound();
        }


        if (facturas.isEmpty()) {
            System.out.println("NO HAY FACTURAS ENTRE ESAS FECHAS");
        } else {
            for (Factura fact : facturas) {
                datos.add(fact.informacion());
               // System.out.println(fact.informacion());
            }

        }
        return datos;
    }

    public Vector devolverCliente(String dni) throws ClientNotFound {
        Cliente cliente = modelo.getCliente(dni);

        Vector info = cliente.informacion();
        info.add(cliente.getNombreCompleto());

        //System.out.println(info);

        return info;


    }

    public Vector getFacturasCliente(String dni) throws ClientNotFound {
        Vector datos = new Vector();
        LinkedList<Factura> facturas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Factura> myCollection = cliente.getFacturas().values();
            facturas = new LinkedList<>(myCollection);

        } else {
            throw new ClientNotFound();
        }


        if (facturas.isEmpty()) {
            System.out.println("NO HAY FACTURAS ");
        } else {
            for (Factura fact : facturas) {
                datos.add(fact.informacion());
               // System.out.println(fact.informacion());
            }

        }
        return datos;
    }
    public  void insertarLlamada(String dni, int[] fecha, int duracio ,int numero) throws ClientNotFound {

        if (modelo.getClientes().containsKey(dni)) {
           LocalDateTime data= modelo.crearFecha(fecha[0],fecha[1],fecha[2]);
           modelo.insertarLlamada(dni,new Llamada(numero,duracio,data));
            System.out.println("Llamada insertada");


        } else {
            throw new ClientNotFound();
        }
    }
    public Vector getLlamadasCliente(String dni) throws ClientNotFound {
        Vector datos = new Vector();
        LinkedList<Llamada> llamadas;

        if (modelo.getClientes().containsKey(dni)) {
            Cliente cliente = modelo.getClientes().get(dni);
            Collection<Llamada> myCollection = cliente.getLlamadas();
            llamadas = new LinkedList<>(myCollection);

        } else {
            throw new ClientNotFound();
        }


        if (llamadas.isEmpty()) {
            System.out.println("NO HAY LLAMADAS");
        } else {
            for (Llamada llamada : llamadas) {
                datos.add(llamada.informacion());
                //System.out.println(llamada.informacion());
            }

        }
        return datos;
    }
    public void emitirFacturaCliente(String dni, int[] fechaInicio, int[] fechaFinal) throws ClientNotFound {

        LocalDateTime inicio = modelo.crearFecha(fechaInicio[0], fechaInicio[1], fechaInicio[2]);

        LocalDateTime fin = modelo.crearFecha(fechaFinal[0], fechaFinal[1], fechaFinal[2]);
        try {
            modelo.emitirFactura(dni,inicio,fin);

        }catch (Exception e){
            throw new ClientNotFound();
        }

    }
    public Vector devolverFactura(int codigo) throws FacturaNotFound {
        Factura fact=null;
        try {
             fact=modelo.devolverFactura(codigo);
        } catch (FacturaNotFound facturaNotFound) {
            throw new FacturaNotFound();
        }

        return fact.informacion();
    }

}
