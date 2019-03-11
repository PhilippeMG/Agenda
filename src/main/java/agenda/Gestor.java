package agenda;

import com.sun.security.ntlm.Client;
import es.uji.www.GeneradorDatosINE;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;


public class Gestor implements Serializable {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<Integer, Factura> facturas = new HashMap<>();


    //>>>Fichero<<<<
    public static void escribirDatos() throws IOException {
        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(clientes);
        oos.close();
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(facturas);
        oos.close();
    }

    public static void leerDatos() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosClientes.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        clientes = (HashMap<String, Cliente>) ois.readObject();
        fis = new FileInputStream("datosFacturas.bin");
        ois = new ObjectInputStream(fis);
        facturas = (HashMap<Integer, Factura>) ois.readObject();
        ois.close();
    }

    //>>>CLIENTE<<<<
    public static boolean insertarCliente(Cliente cliente) {
        if (!clientes.containsKey(cliente.nif)) {
            clientes.put(cliente.nif, cliente);
            return true;
        } else {
            throw new IllegalArgumentException();

        }
    }

    public static boolean borrarCliente(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.remove(NIF);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarCliente(String NIF) {
        if (clientes.containsKey(NIF)) {
            System.out.println(clientes.get(NIF).toString()); //LLama a toString de cliente.
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarClientes(HashMap<String, Cliente> clientesMap) {
        Iterator<Cliente> clientela = clientesMap.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()) {
            System.out.println(clientela.next().toString());
        }
    }

    public static HashMap<String, Cliente> devolverClientesEntreFechas(HashMap<String, Cliente> clientesMap, LocalDate inicio, LocalDate fin) {
        Iterator<Cliente> conjuntos = clientesMap.values().iterator();//.entrySet().iterator();
        HashMap<String, Cliente> clientesEntreFechas = new HashMap<>();

        while (conjuntos.hasNext()) {
            Cliente client = conjuntos.next();
            if ((client.getFecha().isAfter(inicio) || client.getFecha().equals(inicio)) &&
                    (client.getFecha().isBefore(fin) || client.getFecha().equals(fin))) {

                clientesEntreFechas.put(client.getNif(), client);
            }


        }
        return clientesEntreFechas;

    }
    public static  LinkedList <Llamada> devolverLlamadasEntreFechas(Cliente cliente, LocalDate inicio, LocalDate fin) {
        LinkedList <Llamada> llamadaEntreFecha =new LinkedList<>();

        LinkedList <Llamada> llamadas= cliente.llamadas;
        Llamada llamada;
        for (int i =0; i<llamadas.size();i++){
            llamada=llamadas.get(i);
            if ((llamada.getFecha().isAfter(inicio) || llamada.getFecha().equals(inicio)) &&
                    (llamada.getFecha().isBefore(fin) || llamada.getFecha().equals(fin))) {
                llamadaEntreFecha.add(llamada);
            }

        }
        return llamadaEntreFecha;

    }
    //>>>LLAMADAS<<<<
    public static boolean insertarLlamada(String NIF, Llamada llamada) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).insertarLlamada(llamada);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarLlamadas(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarLlamadas();
        }
    }

    //>>>FACTURAS<<<<
    public static Factura emitirFactura(String NIF, LocalDate ini, LocalDate fin) {
        //Guardar tando en el vector de Cliente como en el mapa de Gestor.
        if (clientes.containsKey(NIF)) {
            Cliente cliente = clientes.get(NIF);
            Factura factura = new Factura(cliente, ini, fin);
            cliente.insertarFactura(factura);
            try {
                if (!facturas.containsKey(factura.getCod())) {
                    facturas.put(factura.getCod(), factura);
                } else {
                    throw new IllegalArgumentException();
                }
            } catch (IllegalArgumentException e) {
            }
            return factura;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean mostrarFactura(int codigo) {
        if (facturas.containsKey(codigo)) {
            System.out.println(facturas.get(codigo).toString());
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static boolean mostrarFacturas(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarFacturas();
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void cambiarTarifa(String NIF, int tarifa) {

        if (clientes.containsKey(NIF)) {
            Cliente client = clientes.get(NIF);
            client.cambiarTarifa(tarifa);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void nuevoCliente(int opcion) {
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
        System.out.print("CP: ");
        int CP = scannerEntero.nextInt();
        System.out.print("Provincia: ");
        String provincia = scanner.next();
        System.out.print("Población: ");
        String poblacion = scanner.next();
        Direccion direccion = new Direccion(CP, provincia, poblacion);

        System.out.print("Correo: ");
        String correo = scanner.next();
        System.out.print("Tarifa: ");
        int tipoTarifa = scannerEntero.nextInt();
        Tarifa tarifa = new Tarifa(tipoTarifa);

        if (opcion == 0) {
            Cliente nuevo = new Particular(nombre, nif, direccion, correo, tarifa, apellidos);
            insertarCliente(nuevo);
        } else {
            Cliente nuevo = new Empresa(nombre, nif, direccion, correo, tarifa);
            insertarCliente(nuevo);
        }
    }

    public static LocalDate crearFecha() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Dia Mes Año: ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int año = scanner.nextInt();

        LocalDate fecha = LocalDate.of(año, mes, dia);

        return fecha;
    }

    //Menu
    public static void opcionInsertarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("0.-Particular");
        System.out.println("1.-Empresa");
        System.out.print("Tipo de cliente: ");
        int tipo = scanner.nextInt();
        nuevoCliente(tipo);
        //scanner.close();
    }

    public static void opcionBorrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente que quieres eliminar: ");
        String nif = scanner.next();
        borrarCliente(nif);
    }

    public static void opcionCambiarTarifa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.print("Nueva tarifa para el cliente: ");
        int tarifa = scanner.nextInt();
        cambiarTarifa(nif, tarifa);
    }

    public static void opcionDatosClientes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarCliente(nif);
    }

    public static void opcionListarLlamadasCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarLlamadas(nif);
    }

    public static void opcionEmitirFacturaCLiente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.printf(emitirFactura(nif, crearFecha(), crearFecha()).toString());
    }

    public static void opcionInsertarLlamada() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        System.out.print("Numero destino: ");
        int numDestino = scanner.nextInt();
        System.out.print("Dureción: ");
        double duracion = scanner.nextDouble();


        LocalDate fechaLlamada = crearFecha();
        Llamada llamada = new Llamada(numDestino, duracion, fechaLlamada);
        insertarLlamada(nif, llamada);
    }

    public static void opcionRecuperarFactura() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Codigo de la factura: ");
        int codigo = scanner.nextInt();
        mostrarFactura(codigo);
    }

    public static void opcionFacturasCLiente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarFacturas(nif);
    }
    public static void opcionDevolverLlamadasEntreFechas(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        if (clientes.containsKey(nif)) {
            Cliente cliente =clientes.get(nif);
            LinkedList<Llamada> llamadas=devolverLlamadasEntreFechas(cliente,crearFecha(),crearFecha());
            System.out.printf(llamadas.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }
    public static void subMenuCLientes() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        boolean terminar = false;
        do {

            System.out.println(Menu.OpcionesSubMenuClientes.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            Menu.OpcionesSubMenuClientes opcion = Menu.OpcionesSubMenuClientes.getOpcion(valor);
            switch (opcion) {
                case INSERTAR_CLIENTE:
                    opcionInsertarCliente();
                    break;
                case BORRAR_CLIENTE:
                    opcionBorrarCliente();
                    break;

                case DATOS_CLIENTE:
                    opcionDatosClientes();
                    break;

                case LISTAR_CLIENTES_ENTRE_FECHAS:
                    HashMap<String, Cliente> listadoClientes = devolverClientesEntreFechas(clientes, crearFecha(), crearFecha());
                    mostrarClientes(listadoClientes);
                    break;

                case LISTAR_CLIENTES:
                    mostrarClientes(clientes);
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    opcionCambiarTarifa();
                    break;

                case SALIR:
                    terminar = true;
                    escribirDatos();
                    break;
                default:
                    System.out.printf("Opcion no valida");
                    break;
            }

        } while (terminar == false);
    }

    public static void subMenuLlamadas() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        boolean terminar = false;
        do {

            System.out.println(Menu.OpcionesSubMenuLlamadas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            Menu.OpcionesSubMenuLlamadas opcion = Menu.OpcionesSubMenuLlamadas.getOpcion(valor);
            switch (opcion) {
                case INSERTAR_LLAMADA:
                    opcionInsertarLlamada();
                    break;

                case LISTAR_LLAMADAS_CLIENTE:
                    opcionListarLlamadasCliente();
                    break;
                case LISTAR_LLAMADAS_ENTRE_FECHAS:
                    opcionDevolverLlamadasEntreFechas();
                    break;
                case SALIR:
                    terminar = true;
                    escribirDatos();
                    break;
            }

        } while (terminar == false);
    }

    public static void subMenuFacturas() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        boolean terminar = false;
        do {

            System.out.println(Menu.OpcionesSubMenuFacturas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            Menu.OpcionesSubMenuFacturas opcion = Menu.OpcionesSubMenuFacturas.getOpcion(valor);
            switch (opcion) {
                case EMITIR_FACTURA_CLIENTE:
                    opcionEmitirFacturaCLiente();
                    break;
                case RECUPERAR_FACTURA:
                    opcionRecuperarFactura();
                    break;
                case FACTURAS_CLIENTE:
                    opcionFacturasCLiente();
                    break;
                case SALIR:
                    terminar = true;
                    escribirDatos();
                    break;
            }

        } while (terminar == false);
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean terminar = false;
        leerDatos();
        Scanner scannerMenu = new Scanner(System.in);

        do {

            System.out.println(Menu.OpcionesMenuPrincipal.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            Menu.OpcionesMenuPrincipal opcion = Menu.OpcionesMenuPrincipal.getOpcion(valor);
            switch (opcion) {
                case GESTIONAR_CLIENTES:
                    subMenuCLientes();
                    break;
                case GESTIONAR_LLAMADAS:
                    subMenuLlamadas();
                    break;
                case GESTIONAR_FACTURAS:
                    subMenuFacturas();
                    break;
                case SALIR:
                    terminar = true;
                    escribirDatos();
                    break;
            }

        } while (terminar == false);

    }

}