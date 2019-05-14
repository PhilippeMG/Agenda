package agenda.modelo;

import agenda.modelo.clientes.*;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.FacturaNotFound;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.tarifa.*;
import agenda.vista.Vista;

import java.io.*;
import java.time.*;
import java.util.*;


public class Modelo implements Serializable, InterfaceModelo {
    private HashMap<String, Cliente> clientes = new HashMap<>();
    private HashMap<Integer, Factura> facturas = new HashMap<>();
    Vista vista;

    public Modelo() {
        super();
    }

    public void setClientes(HashMap<String, Cliente> clientes) {
        this.clientes = clientes;
    }

    public void setFacturas(HashMap<Integer, Factura> facturas) {
        this.facturas = facturas;
    }

    public HashMap<String, Cliente> getClientes() {
        return clientes;
    }


    public HashMap<Integer, Factura> getFacturas() {
        return facturas;
    }
    public void setVista(Vista vista) {
        this.vista = vista;
    }

    //>>>CLIENTE<<<<
    public void insertarCliente(Cliente cliente) throws InvalidArguments {
        if (!clientes.containsKey(cliente.getNif())) {
            clientes.put(cliente.getNif(), cliente);

        } else {
            throw new InvalidArguments();

        }
        System.out.println("Actualizando...");
        vista.actualizar();
        System.out.println("Actualizado");

    }

    public void borrarCliente(String NIF) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            Cliente cliente= clientes.get(NIF);
            borrarFacturasDelCliente(cliente);
            clientes.remove(NIF);
            vista.actualizar();
        } else {
            throw new ClientNotFound();
        }
    }

    public  void borrarFacturasDelCliente(Cliente cliente){
        Collection<Factura> facturasCliente= cliente.getFacturas().values();
        for (Factura fact: facturasCliente){
            facturas.remove(fact.getCod());
        }
    }
    public void mostrarCliente(String NIF) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            System.out.println(clientes.get(NIF).toString()); //LLama a toString de cliente.
        } else {
            throw new ClientNotFound();
        }
    }

    public Cliente getCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String dni = scanner.next();
        if (clientes.containsKey(dni)) {
            return clientes.get(dni);
        } else {
            return null;
        }
    }

    public Cliente getCliente(String dni) throws ClientNotFound {

        if (clientes.containsKey(dni)) {
            return clientes.get(dni);
        } else {
            throw new ClientNotFound();
        }
    }

    public void mostrarClientes(HashMap<String, Cliente> clientesMap) {
        Collection<Cliente> clientela = clientesMap.values();
        if (clientela.isEmpty()) {
            System.out.println("No hay clientes");
        } else {
            Iterator<Cliente> iterClientela = clientela.iterator();//.entrySet().iterator();
            while (iterClientela.hasNext()) {
                System.out.println(iterClientela.next().toString());
            }
        }
    }

    public Vector informacionClientes(HashMap<String, Cliente> clientesMap) {
        Vector vector = new Vector();
        Collection<Cliente> clientela = clientesMap.values();
        if (clientela.isEmpty()) {
            System.out.println("No hay clientes");
        } else {
            Iterator<Cliente> iterClientela = clientela.iterator();//.entrySet().iterator();
            while (iterClientela.hasNext()) {
                vector.add(iterClientela.next().informacion());
                // System.out.println(iterClientela.next().toString());
            }
        }
        return vector;
    }

    public void nuevoCliente(int opcion) throws InvalidArguments {
        Scanner scanner = new Scanner(System.in);
        Scanner scannerEntero = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.print("Nombre del cliente: ");
        String nombre = scannerEntero.next();
        String apellidos = "";
        if (opcion == 0) { //Particular
            System.out.print("Apellidos del cliente: ");
            apellidos = scanner.next();
        }
        System.out.println("Dirección del cliente: ");
        System.out.print("\tCP: ");
        int CP = scannerEntero.nextInt();
        System.out.print("\tProvincia: ");
        String provincia = scanner.next();
        System.out.print("\tPoblación: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(CP, provincia, poblacion);

        System.out.print("Correo: ");
        String correo = scanner.next();
        int tipo = -1;
        while (tipo < 0 || tipo > 2) {
            System.out.printf("Tipo de Tarifa:\n 0- Tarifa Tardes o 1- Tarifa Domingo Gratis o 2- Ambas: ");
            tipo = scannerEntero.nextInt();
        }
        System.out.print("Tarifa: ");
        int tipoTarifa = scannerEntero.nextInt();
        FabricarTarifa creadorTarifas = new CrearTarifa();
        Tarifa tarifas = creadorTarifas.getTarifaBasica(tipoTarifa);

        Tarifa tarifa;

        if (tipo == 0) {
            tarifa = creadorTarifas.getOfertaTardes(tarifas);


        } else if (tipo == 1) {
            tarifa = creadorTarifas.getOfertaDomingos(tarifas);


        } else {
            Tarifa tarde = creadorTarifas.getOfertaTardes(tarifas);
            tarifa = creadorTarifas.getOfertaDomingos(tarde);


        }
        FabricarCliente creador = new CrearCliente();

        if (opcion == 0) {
            Cliente nuevo = creador.getClienteParticular(nombre, nif, direccion, correo, tarifa, apellidos);
            insertarCliente(nuevo);
        } else {
            Cliente nuevo = creador.getClienteEmpresa(nombre, nif, direccion, correo, tarifa);
            insertarCliente(nuevo);
        }
    }


    //>>>LLAMADAS<<<<
    public void insertarLlamada(String NIF, Llamada llamada) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).insertarLlamada(llamada);
        } else {
            throw new ClientNotFound();
        }
    }

    public void mostrarLlamadas(String NIF) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarLlamadas();
        } else {
            throw new ClientNotFound();
        }
    }

    public <T extends GetFecha> LinkedList<T> devolverEntreFechas(List<T> lista, LocalDateTime inicio, LocalDateTime fin) {
        LinkedList<T> devolver = new LinkedList<>();
        for (T elemento : lista) {
            if ((elemento.getFecha().isAfter(inicio) || elemento.getFecha().equals(inicio)) &&
                    (elemento.getFecha().isBefore(fin) || elemento.getFecha().equals(fin))) {
                devolver.add(elemento);
            }
        }
        return devolver;
    }

    //>>>FACTURAS<<<<
    public Factura emitirFactura(String NIF, LocalDateTime ini, LocalDateTime fin) throws Exception {
        if (clientes.containsKey(NIF)) {
            Cliente cliente = clientes.get(NIF);
            Factura factura = new Factura(cliente, ini, fin);
            cliente.insertarFactura(factura);
            try {
                if (!facturas.containsKey(factura.getCod())) {
                    facturas.put(factura.getCod(), factura);
                } else {
                    throw new FacturaNotFound();
                }
            } catch (FacturaNotFound e) {
                System.out.println("-" + e.getMessage());
            }
            return factura;
        } else {
            throw new ClientNotFound();
        }
    }

    public void mostrarFactura(int codigo) throws FacturaNotFound {
        if (facturas.containsKey(codigo)) {
            System.out.println(facturas.get(codigo).toString());
        } else {
            throw new FacturaNotFound();
        }
    }

    public Factura devolverFactura(int codigo) throws FacturaNotFound {
        Factura factura = null;
        if (facturas.containsKey(codigo)) {
            factura = facturas.get(codigo);
            System.out.println(facturas.get(codigo).toString());

        } else {
            throw new FacturaNotFound();
        }
        return factura;
    }

    public void mostrarFacturas(String NIF) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarFacturas();
        } else {
            throw new ClientNotFound();
        }
    }

    public void cambiarTarifa(String NIF, int tarifa) throws ClientNotFound {

        if (clientes.containsKey(NIF)) {
            Cliente client = clientes.get(NIF);
            client.cambiarTarifa(tarifa);
        } else {
            throw new ClientNotFound();
        }
    }

    public LocalDateTime crearFecha() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Dia Mes Año: ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int año = scanner.nextInt();
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.of(año, mes, dia);
        LocalDateTime data = LocalDateTime.of(fecha, hora);

        return data;
    }

    public LocalDateTime crearFecha(int dia, int mes, int any) {

        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.of(any, mes, dia);
        LocalDateTime data = LocalDateTime.of(fecha, hora);

        return data;
    }

    public void cargarDatos() throws ClassNotFoundException {
        //Clientes
        try {
            FileInputStream fis = new FileInputStream("datosClientes.bin");
            ObjectInputStream ois = new ObjectInputStream(fis);
            setClientes((HashMap<String, Cliente>) ois.readObject());
            //Facturas
            fis = new FileInputStream("datosFacturas.bin");
            ois = new ObjectInputStream(fis);
            setFacturas((HashMap<Integer, Factura>) ois.readObject());
            //Codigo Facturas
            fis = new FileInputStream("contadorFacturas.bin");
            ois = new ObjectInputStream(fis);
            Factura.setContCod((int) ois.readObject());
            ois.close();

        } catch (IOException e) {
            System.out.println("No hay datos guardados");

        }
    }

    public void guardarDatos() throws IOException {
        //Clientes
        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(getClientes());
        oos.close();
        //Facturas
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(getFacturas());
        //Codigo Facturas
        fos = new FileOutputStream("contadorFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject( Factura.getContCod());
        oos.close();
    }


}