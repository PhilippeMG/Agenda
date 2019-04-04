package agenda;

import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
import agenda.clientes.GetFecha;
import agenda.clientes.Particular;
import agenda.excepciones.ClientNotFound;
import agenda.excepciones.FacturaNotFound;
import agenda.excepciones.InvalidArguments;
import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaDomingosGratis;
import agenda.tarifa.TarifaTardesGratis;
import agenda.tarifa.TarifaBasica;

import java.io.*;
import java.time.*;
import java.util.*;


public class Gestor implements Serializable {
    private  HashMap<String, Cliente> clientes = new HashMap<>();
    private  HashMap<Integer, Factura> facturas = new HashMap<>();

    public Gestor() {
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

    //>>>CLIENTE<<<<
    public void insertarCliente(Cliente cliente) throws InvalidArguments {
        if (!clientes.containsKey(cliente.getNif())) {
            clientes.put(cliente.getNif(), cliente);
        } else {
            throw new InvalidArguments();

        }
    }

    public void borrarCliente(String NIF) throws ClientNotFound {
        if (clientes.containsKey(NIF)) {
            clientes.remove(NIF);
        } else {
            throw new ClientNotFound();
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

    public void mostrarClientes(HashMap<String, Cliente> clientesMap) {
        Iterator<Cliente> clientela = clientesMap.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()) {
            System.out.println(clientela.next().toString());
        }
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
        //TODO Marcos mira lo de las tarifas, Tarifa es abstract y hay dos que tenemos que usar
        int tipo=-1;
        while (tipo<0 ||tipo>1) {
            System.out.printf("Tipo de Tarifa:\n 0- Tarifa Especial o 1- Tarifa Un dia Gratis: ");
             tipo = scannerEntero.nextInt();
        }
        System.out.print("Tarifa: ");
        int tipoTarifa = scannerEntero.nextInt();
        Tarifa tarifas;
        Tarifa tarifa;

        if (tipo==1){
             tarifas = new TarifaBasica(tipoTarifa);
            tarifa = new TarifaTardesGratis(tarifas,tipoTarifa);


        }else{
             tarifas = new TarifaBasica(tipoTarifa);
            tarifa = new TarifaDomingosGratis(tarifas,tipoTarifa);


        }


        if (opcion == 0) {
            Cliente nuevo = new Particular(nombre, nif, direccion, correo, tarifa, apellidos);
            insertarCliente(nuevo);
        } else {
            Cliente nuevo = new Empresa(nombre, nif, direccion, correo, tarifa);
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
        //Fichero tando en el vector de Cliente como en el mapa de Gestor.
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
                System.out.println(e.getMessage());
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
        LocalTime hora= LocalTime.now();
        LocalDate fecha = LocalDate.of(año, mes, dia);
        LocalDateTime data= LocalDateTime.of(fecha,hora);

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
        oos.close();
    }



}