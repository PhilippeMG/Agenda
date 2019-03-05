package agenda;

import com.sun.security.ntlm.Client;
import es.uji.www.GeneradorDatosINE;

import java.time.*;
import java.time.format.*;
import java.util.*;
import static agenda.Gestor.OpcionesMenu.SALIR;

public class Gestor {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<Integer, Factura> facturas = new HashMap<>();


    //>>>MENU<<<<
    public enum OpcionesMenu {

        AÑADIR_CLIENTE("Añadir cliente"),
        BORRAR_CLIENTE("Borrar cliente"),
        LISTAR_CLIENTES("Listar clientes"),

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

    public static void mostrarClientes() {
        Iterator<Cliente> clientela = clientes.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()) {
            System.out.println(clientela.next().toString());
        }
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
            if (!facturas.containsKey(factura.getCod())) {
                facturas.put(factura.getCod(), factura);
            } else {
                throw new IllegalArgumentException();
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

    public static void nuevoParticular() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");

        String nombre = scanner.nextLine();
        System.out.print("Apellidos del cliente: ");
        String apellidos = scanner.nextLine();

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        System.out.println("Dirección del cliente: ");
        System.out.print("CP: ");
        int CP = scanner.nextInt();
        System.out.print("Provincia: ");
        String provincia = scanner.next();

        System.out.print("Población: ");
        String poblacion = scanner.next();


        Direccion direccion = new Direccion(CP, provincia, poblacion);
        System.out.print("Correo: ");
        String correo = scanner.next();
        System.out.print("Tarifa: ");
        int tipoTarifa = scanner.nextInt();
        Cliente nuevo = new Particular(nombre, nif, direccion, correo, tipoTarifa, apellidos);
        añadirCliente(nuevo);
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

    public static void nuevoEmpresa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");

        String nombre = scanner.nextLine();

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        System.out.println("Dirección del cliente: ");
        System.out.print("CP: ");
        int CP = scanner.nextInt();
        System.out.print("Provincia: ");
        String provincia = scanner.next();

        System.out.print("Población: ");
        String poblacion = scanner.next();


        Direccion direccion = new Direccion(CP, provincia, poblacion);
        System.out.print("Correo: ");
        String correo = scanner.next();
        System.out.print("Tarifa: ");
        int tipoTarifa = scanner.nextInt();
        Cliente nuevo = new Empresa(nombre, nif, direccion, correo, tipoTarifa);
        añadirCliente(nuevo);

    }

    public static void main(String[] args) {
        boolean terminar = false;
        String nif;
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");

        Cliente cliente1 = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
        Cliente cliente2 = new Cliente("Philippe", "0002", direccion1, "al375923@uji.es", 1);
        añadirCliente(cliente1);
        añadirCliente(cliente2);

        do {
            System.out.println(OpcionesMenu.getMenu());
            Scanner scanner = new Scanner(System.in);
            System.out.print("Elije una opción: ");
            int valor = scanner.nextInt();
            OpcionesMenu opcion = OpcionesMenu.getOpcion(valor);
            switch (opcion) {
                case AÑADIR_CLIENTE:
                    System.out.println("0.-Particular");
                    System.out.println("1.-Empresa");
                    System.out.print("Tipo de cliente: ");
                    int cliente = scanner.nextInt();
                    switch (cliente) {
                        case 0:
                            nuevoParticular();
                            break;
                        case 1:
                            nuevoEmpresa();
                            break;
                    }
                    break;
                case BORRAR_CLIENTE:
                    System.out.print("NIF del cliente que quieres eliminar: ");
                    nif = scanner.next();
                    borrarCliente(nif);
                    break;

                case CAMBIAR_TARIFA_CLIENTE:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();
                    System.out.print("Nueva tarifa para el cliente: ");
                    int tarifa = scanner.nextInt();
                    cambiarTarifa(nif, tarifa);
                    break;
                case DATOS_CLIENTE:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();
                    mostrarCliente(nif);
                    break;
                case LISTAR_LLAMADAS_CLIENTE:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();
                    mostrarLlamadas(nif);
                    break;
                case EMITIR_FACTURA_CLIENTE:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();
                    System.out.printf(emitirFactura(nif, crearFecha(), crearFecha()).toString());
                    break;
                case LISTAR_CLIENTES:
                    mostrarClientes();
                    break;
                case AÑADIR_LLAMADA:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();

                    System.out.print("Numero destino: ");
                    int numDestino = scanner.nextInt();
                    System.out.print("Dureción: ");
                    double duracion = scanner.nextDouble();


                    LocalDate fechaLlamada = crearFecha();
                    Llamada llamada = new Llamada(numDestino, duracion, fechaLlamada);
                    añadirLlamada(nif, llamada);
                    break;
                case RECUPERAR_FACTURA:
                    System.out.print("Codigo de la factura: ");
                    int codigo = scanner.nextInt();
                    mostrarFactura(codigo);
                    break;
                case FACTURAS_CLIENTE:
                    System.out.print("NIF del cliente: ");
                    nif = scanner.next();
                    mostrarFacturas(nif);
                    break;
                case SALIR:
                    terminar = true;
                    break;
            }
        } while (terminar == false);

    }

}