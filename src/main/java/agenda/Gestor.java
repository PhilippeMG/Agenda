package agenda;

import com.sun.security.ntlm.Client;
import es.uji.www.GeneradorDatosINE;
import java.time.*;
import java.time.format.*;

import java.util.*;

public class Gestor {
    static HashMap<String, Cliente> clientes = new HashMap<>();
    static HashMap<Integer, Factura> facturas = new HashMap<>();


    //>>>MENU<<<<
    public enum OpcionesMenu {
        AÑADIR_CLIENTE("Añadir cliente"),
        BORRAR_CLIENTE("Borrar cliente"),
        CAMBIAR_TARIFA_CLIENTE("Modificar tarifa cliente"),
        DATOS_CLIENTE("Mostrar información cliente"),
        LISTAR_CLIENTES("Listar clientes"),
        AÑADIR_LLAMADA("Añadir llamada"),
        LISTAR_LLAMADAS_CLIENTE("Mostrar llamadas de un cliente"),
        EMITIR_FACTURA_CLIENTE("Emitir factura de un cliente"),
        RECUPERAR_FACTURA("Mostrar Factura"),
        FACTURAS_CLIENTE("Mostrar conjunto facturas de un cliente");

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

        public static String getMenu(){
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for(OpcionesMenu opcion : OpcionesMenu.values()){
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    //>>>CLIENTE<<<<
    public static void añadirCliente(Cliente cliente){
        if(!clientes.containsKey(cliente.nif)){
            clientes.put(cliente.nif,cliente);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public static void borrarCliente(String NIF){
        if(clientes.containsKey(NIF)){
            clientes.remove(NIF);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarCliente(String NIF){
        if(clientes.containsKey(NIF)){
            clientes.get(NIF).toString(); //LLama a toString de cliente.
        }else{
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarClientes(){
        Iterator<Cliente> clientela = clientes.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()){
            System.out.println(clientela.next().toString());
        }
    }

    //>>>LLAMADAS<<<<
    public void añadirLlamada(String NIF, Llamada llamada){
        if(clientes.containsKey(NIF)){
            clientes.get(NIF).añadirLlamada(llamada);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void mostrarLlamadas(String NIF) {
        if(clientes.containsKey(NIF)){
            clientes.get(NIF).mostrarLlamadas();
        }
    }

    //>>>FACTURAS<<<<
        public Factura emitirFactura(String NIF, LocalDate ini, LocalDate fin) {
        //Guardar tando en el vector de Cliente como en el mapa de Gestor.
        if (clientes.containsKey(NIF)) {
            Cliente cliente = clientes.get(NIF);
            Factura factura = new Factura(cliente, ini, fin);
            cliente.añadirFactura(factura);
            if(!facturas.containsKey(factura.getCod())){
                facturas.put(factura.getCod(),factura);
            }else {
                throw new IllegalArgumentException();
            }
            return factura;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void mostrarFactura(int codigo) {
        if (facturas.containsKey(codigo)) {
            System.out.println(facturas.get(codigo).toString());
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void mostrarFacturas(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarFacturas();
        }else{
            throw new IllegalArgumentException();
        }
    }
    public static void cambiarTarifa(String NIF, int tarifa){

        if (clientes.containsKey(NIF)) {
            Cliente client = clientes.get(NIF);
            System.out.print("Nueva tarifa para el cliente: ");
            client.cambiarTarifa(tarifa);
        } else{
            throw new IllegalArgumentException();
        }
    }
    public static void nuevoParticular(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");

        String nombre= scanner.next();
        System.out.print("Apellidos del cliente: ");
        String apellidos= scanner.nextLine();

        nombre=nombre+" "+apellidos;
        System.out.print("NIF del cliente: ");
        String nif=scanner.next();

        System.out.println("Dirección del cliente: ");
        System.out.print("CP: ");
        int CP=scanner.nextInt();
        System.out.print("Provincia: ");
        String provincia=scanner.next();

        System.out.print("Población: ");
        String poblacion=scanner.next();


        Direccion direccion =new Direccion(CP,provincia,poblacion);
        System.out.print("Correo: ");
        String correo =scanner.next();
        System.out.print("Tarifa: ");
        int tipoTarifa=scanner.nextInt();
        Cliente nuevo = new Cliente(nombre, nif,direccion,correo,tipoTarifa);
        añadirCliente(nuevo);
    }
    public static void nuevoEmpresa(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nombre del cliente: ");

        String nombre= scanner.next();

        System.out.print("NIF del cliente: ");
        String nif=scanner.next();

        System.out.println("Dirección del cliente: ");
        System.out.print("CP: ");
        int CP=scanner.nextInt();
        System.out.print("Provincia: ");
        String provincia=scanner.next();

        System.out.print("Población: ");
        String poblacion=scanner.next();


        Direccion direccion =new Direccion(CP,provincia,poblacion);
        System.out.print("Correo: ");
        String correo =scanner.next();
        System.out.print("Tarifa: ");
        int tipoTarifa=scanner.nextInt();
        Cliente nuevo = new Empresa(nombre, nif,direccion,correo,tipoTarifa);
        añadirCliente(nuevo);

    }
        public static void main(String[] args) {
        System.out.println(OpcionesMenu.getMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elije una opción: ");
        int valor = scanner.nextInt();
        OpcionesMenu opcion = OpcionesMenu.getOpcion(valor);
        String nif;
        switch (opcion){
            case AÑADIR_CLIENTE:
                System.out.println("0.-Particular");
                System.out.println("1.-Empresa");
                System.out.print("Tipo de cliente:");
                int cliente = scanner.nextInt();
                switch (cliente){
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
                System.out.print("NIF del cliente que quieres eliminar: ");
                nif = scanner.next();
                System.out.print("Nueva tarifa para el cliente: ");
                int tarifa = scanner.nextInt();
                cambiarTarifa(nif,tarifa);
                break;
            case DATOS_CLIENTE:
                System.out.print("NIF del cliente que quieres eliminar: ");
                nif = scanner.next();
                mostrarCliente(nif);
                break;
            case EMITIR_FACTURA_CLIENTE:
                System.out.print("NIF del cliente que quieres eliminar: ");
                nif = scanner.next();
                mostrarFacturas(nif);
                break;
            case LISTAR_CLIENTES:
                mostrarClientes();
                break;

        }
/*      >>>POSIBLE COPY PASTE PARA TEST<<<

        Gestor gestor = new Gestor();
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        Cliente cliente1 = new Cliente("Marcos", "23325634T", direccion1, "al375909@uji.es", 1);
        Cliente cliente2 = new Cliente("Philippe", "53627507v", direccion1, "al375923@uji.es", 1);
        GeneradorDatosINE generador = new GeneradorDatosINE();
        String dni = generador.getNIF();
        System.out.println(dni);
        System.out.println("Cliente creado:");
        System.out.println("Añadiendo cliente...");
        gestor.añadirCliente(cliente1);
        gestor.añadirCliente(cliente2);
        System.out.println("CLIENTES AÑADIDOS");
        gestor.mostrarClientes();

        //Crear llamadas
        Llamada llamada = new Llamada(654078311,14,LocalDate.of(2019, Month.JANUARY, 3));
        Llamada llamada2 = new Llamada(654078311,1,LocalDate.of(2019, Month.FEBRUARY, 15));
        Llamada llamada3 = new Llamada(654078311,0.9,LocalDate.of(2019, Month.MARCH, 1));
        gestor.añadirLlamada(cliente1.getNif(),llamada);
        gestor.añadirLlamada(cliente1.getNif(),llamada2);
        gestor.añadirLlamada(cliente1.getNif(),llamada3);

        Llamada llamada4 = new Llamada(654078311,20.5,LocalDate.of(2019, Month.MARCH, 1));
        Llamada llamada5 = new Llamada(654078311,120,LocalDate.of(2019, Month.MARCH, 16));
        Llamada llamada6 = new Llamada(654078311,120,LocalDate.of(2019, Month.MARCH, 24));
        gestor.añadirLlamada(cliente2.getNif(),llamada4);
        gestor.añadirLlamada(cliente2.getNif(),llamada5);
        gestor.añadirLlamada(cliente2.getNif(),llamada6);

        System.out.println("Llamadas realizadas por: " + cliente1.nombre);
        gestor.mostrarLlamadas(cliente1.getNif());
        System.out.println("Llamadas realizadas por: " + cliente2.nombre);
        gestor.mostrarLlamadas(cliente2.getNif());

        System.out.println("Factura " + cliente1.getNombre());
        gestor.emitirFactura(cliente1.nif,LocalDate.of(2019, Month.JANUARY, 1), LocalDate.of(2019, Month.JANUARY, 31));
        gestor.emitirFactura(cliente1.nif,LocalDate.of(2019, Month.FEBRUARY, 1), LocalDate.of(2019, Month.FEBRUARY, 28));
        gestor.emitirFactura(cliente1.nif,LocalDate.of(2019, Month.MARCH, 1), LocalDate.of(2019, Month.MARCH, 31));
        //System.out.println(factura);
        System.out.println("HAY " + facturas.keySet().size() + " FACTURAS");
        gestor.mostrarFactura(1);
        gestor.mostrarFactura(2);
        gestor.mostrarFactura(3);

*/

    }

}