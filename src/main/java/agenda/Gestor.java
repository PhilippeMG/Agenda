package agenda;

import com.sun.security.ntlm.Client;
import es.uji.www.GeneradorDatosINE;

import java.io.*;
import java.time.*;
import java.time.format.*;
import java.util.*;

import static agenda.Gestor.OpcionesMenu.SALIR;

public class Gestor implements Serializable {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<Integer, Factura> facturas = new HashMap<>();
    private static FileOutputStream FileOutputStreamfos;


    //>>>MENU<<<<
    public enum OpcionesMenu {

        AÑADIR_CLIENTE("Añadir cliente"),
        BORRAR_CLIENTE("Borrar cliente"),
        LISTAR_CLIENTES("Listar clientes"),
        LISTAR_CLIENTES_ENTRE_FECHAS("Listar clientes entre dos fechas"),
        DATOS_CLIENTE("Mostrar información cliente"),
        CAMBIAR_TARIFA_CLIENTE("Modificar tarifa cliente"),
        AÑADIR_LLAMADA("Añadir llamada"),
        LISTAR_LLAMADAS_CLIENTE("Mostrar llamadas de un cliente"),
        EMITIR_FACTURA_CLIENTE("Emitir factura de un cliente"),
        FACTURAS_CLIENTE("Mostrar conjunto facturas de un cliente"),
        RECUPERAR_FACTURA("Mostrar Factura"),
        SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesMenu(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesMenu getOpcion(int opcion) {
            if (opcion >= values().length || opcion < 0) return values()[11];
            return values()[opcion];
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static String getMenu() {
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for (OpcionesMenu opcion : OpcionesMenu.values()) {
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

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
    public static boolean añadirCliente(Cliente cliente) {
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

    //>>>LLAMADAS<<<<
    public static boolean añadirLlamada(String NIF, Llamada llamada) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).añadirLlamada(llamada);
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
            cliente.añadirFactura(factura);
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
        Tarifa tarifa= new Tarifa(tipoTarifa);

        if (opcion == 0) {
            Cliente nuevo = new Particular(nombre, nif, direccion, correo, tarifa, apellidos);
            añadirCliente(nuevo);
        } else {
            Cliente nuevo = new Empresa(nombre, nif, direccion, correo, tarifa);
            añadirCliente(nuevo);
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
    public static void opcionAñadirCliente() {
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

    public static void opcionAñadirLlamada() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        System.out.print("Numero destino: ");
        int numDestino = scanner.nextInt();
        System.out.print("Dureción: ");
        double duracion = scanner.nextDouble();


        LocalDate fechaLlamada = crearFecha();
        Llamada llamada = new Llamada(numDestino, duracion, fechaLlamada);
        añadirLlamada(nif, llamada);
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

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        boolean terminar = false;

        //Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");

       /* Cliente cliente1 = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
        Cliente cliente2 = new Cliente("Philippe", "0002", direccion1, "al375923@uji.es", 1);
        añadirCliente(cliente1);
        añadirCliente(cliente2);*/
        leerDatos();
        Scanner scannerMenu = new Scanner(System.in);

        do {

            System.out.println(OpcionesMenu.getMenu());

            System.out.print("\nElije una opción: ");
            int  valor = scannerMenu.nextInt();


            OpcionesMenu opcion = OpcionesMenu.getOpcion(valor);
            switch (opcion) {
                case AÑADIR_CLIENTE:
                    System.out.println("0.-Particular");
                    System.out.println("1.-Empresa");
                    System.out.print("Tipo de cliente: ");
                    int tipo = scannerMenu.nextInt();
                    nuevoCliente(tipo);
                    break;
                case BORRAR_CLIENTE:
                    opcionBorrarCliente();
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    opcionCambiarTarifa();
                    break;
                case DATOS_CLIENTE:
                    opcionDatosClientes();
                    break;
                case LISTAR_LLAMADAS_CLIENTE:
                    opcionListarLlamadasCliente();
                    break;
                case LISTAR_CLIENTES_ENTRE_FECHAS:
                    HashMap<String, Cliente> listadoClientes = devolverClientesEntreFechas(clientes, crearFecha(), crearFecha());
                    mostrarClientes(listadoClientes);
                    break;
                case EMITIR_FACTURA_CLIENTE:
                    opcionEmitirFacturaCLiente();
                    break;
                case LISTAR_CLIENTES:
                    mostrarClientes(clientes);
                    break;
                case AÑADIR_LLAMADA:
                    opcionAñadirLlamada();
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
        //  scanner.close();
    }

}