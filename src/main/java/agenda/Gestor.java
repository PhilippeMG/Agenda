package agenda;

import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
import agenda.clientes.GetFecha;
import agenda.clientes.Particular;

import java.io.*;
import java.time.*;
import java.util.*;


public class Gestor implements Serializable {
     private HashMap<String, Cliente> clientes = new HashMap<>();
     private HashMap<Integer, Factura> facturas = new HashMap<>();

    public Gestor() {
        super();
    }

    //>>>Fichero<<<<
    public  void escribirDatos() throws IOException {
        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(clientes);
        oos.close();
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(facturas);
        oos.close();
    }

    public  void leerDatos() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosClientes.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        clientes = (HashMap<String, Cliente>) ois.readObject();
        fis = new FileInputStream("datosFacturas.bin");
        ois = new ObjectInputStream(fis);
        facturas = (HashMap<Integer, Factura>) ois.readObject();
        ois.close();
    }

    //>>>CLIENTE<<<<
    public  boolean insertarCliente(Cliente cliente) {
        if (!clientes.containsKey(cliente.getNif())) {
            clientes.put(cliente.getNif(), cliente);
            return true;
        } else {
            throw new IllegalArgumentException();

        }
    }

    public  boolean borrarCliente(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.remove(NIF);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public  void mostrarCliente(String NIF) {
        if (clientes.containsKey(NIF)) {
            System.out.println(clientes.get(NIF).toString()); //LLama a toString de cliente.
        } else {
            throw new IllegalArgumentException();
        }
    }

    public Cliente getCliente(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String dni = scanner.next();
        if (clientes.containsKey(dni)) {
           return clientes.get(dni);
        } else {
           return null;
        }
    }
    public  void mostrarClientes(HashMap<String, Cliente> clientesMap) {
        Iterator<Cliente> clientela = clientesMap.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()) {
            System.out.println(clientela.next().toString());
        }
    }


    //>>>LLAMADAS<<<<
    public  boolean insertarLlamada(String NIF, Llamada llamada) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).insertarLlamada(llamada);
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public  void mostrarLlamadas(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarLlamadas();
        }
    }
   public  <T extends GetFecha> LinkedList <T> devolverEntreFechas(List <T> lista, LocalDate inicio, LocalDate fin) {
        LinkedList<T> devolver = new LinkedList<>();

        for(T elemento: lista) {
            if ((elemento.getFecha().isAfter(inicio) || elemento.getFecha().equals(inicio)) &&
                    (elemento.getFecha().isBefore(fin) || elemento.getFecha().equals(fin))) {
                devolver.add(elemento);
            }
        }

        return devolver;
    }
    //>>>FACTURAS<<<<

    public  Factura emitirFactura(String NIF, LocalDate ini, LocalDate fin) {
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

    public  boolean mostrarFactura(int codigo) {
        if (facturas.containsKey(codigo)) {
            System.out.println(facturas.get(codigo).toString());
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public  boolean mostrarFacturas(String NIF) {
        if (clientes.containsKey(NIF)) {
            clientes.get(NIF).mostrarFacturas();
            return true;
        } else {
            throw new IllegalArgumentException();
        }
    }

    public  void cambiarTarifa(String NIF, int tarifa) {

        if (clientes.containsKey(NIF)) {
            Cliente client = clientes.get(NIF);
            client.cambiarTarifa(tarifa);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public  void nuevoCliente(int opcion) {
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

    public  LocalDate crearFecha() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Dia Mes Año: ");
        int dia = scanner.nextInt();
        int mes = scanner.nextInt();
        int año = scanner.nextInt();

        LocalDate fecha = LocalDate.of(año, mes, dia);

        return fecha;
    }

    //Menu
    public  void opcionInsertarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("0.-Particular");
        System.out.println("1.-Empresa");
        System.out.print("Tipo de cliente: ");
        int tipo = scanner.nextInt();
        nuevoCliente(tipo);
        //scanner.close();
    }

    public  void opcionBorrarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente que quieres eliminar: ");
        String nif = scanner.next();
        borrarCliente(nif);
    }

    public  void opcionCambiarTarifa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.print("Nueva tarifa para el cliente: ");
        int tarifa = scanner.nextInt();
        cambiarTarifa(nif, tarifa);
    }

    public  void opcionDatosClientes() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarCliente(nif);
    }

    public  void opcionListarLlamadasCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente: ");

        String nif = scanner.next();
        mostrarLlamadas(nif);
    }

    public  void opcionEmitirFacturaCLiente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.printf(emitirFactura(nif, crearFecha(), crearFecha()).toString());
    }

    public  void opcionInsertarLlamada() {
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

    public  void opcionRecuperarFactura() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Codigo de la factura: ");
        int codigo = scanner.nextInt();
        mostrarFactura(codigo);
    }

    public  void opcionFacturasCLiente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarFacturas(nif);
    }
    public  void opcionDevolverLlamadasEntreFechas(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        if (clientes.containsKey(nif)) {
            Cliente cliente =clientes.get(nif);
            LinkedList<Llamada> llamadas=devolverEntreFechas(cliente.getLlamadas(),crearFecha(),crearFecha());
            System.out.printf(llamadas.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }
    public  void opcionDevolverFacturasEntreFechas(){
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        if (clientes.containsKey(nif)) {
            Cliente cliente =clientes.get(nif);

            Collection<Factura> myCollection =cliente.getFacturas().values();
            List<Factura> list = new LinkedList<>(myCollection);
            LinkedList<Factura> facturasEntreFechas=devolverEntreFechas(list,crearFecha(),crearFecha());
            System.out.printf(facturasEntreFechas.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }
    public  void opcionDevolverCLientesEntreFechas(){
        Scanner scanner = new Scanner(System.in);



            Collection<Cliente> myCollection =clientes.values();
            List<Cliente> list = new LinkedList<>(myCollection);
            LinkedList<Cliente> clienteEntreFechas=devolverEntreFechas(list,crearFecha(),crearFecha());
            System.out.printf(clienteEntreFechas.toString());

    }
    public  void subMenuCLientes() throws IOException {
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
                    opcionDevolverCLientesEntreFechas();
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

    public  void subMenuLlamadas() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuLlamadas opcion;
        do {

            System.out.println(Menu.OpcionesSubMenuLlamadas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcion = Menu.OpcionesSubMenuLlamadas.getOpcion(valor);
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
                    escribirDatos();
                    break;
            }

        } while (opcion.name().equals("SALIR"));
    }

    public  void subMenuFacturas() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuFacturas opcion;
        do {

            System.out.println(Menu.OpcionesSubMenuFacturas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcion = Menu.OpcionesSubMenuFacturas.getOpcion(valor);
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
                case LISTAR_FACTURAS_ENTRE_FECHAS:
                    opcionDevolverFacturasEntreFechas();
                    break;
                case SALIR:
                    escribirDatos();
                    break;
            }

        } while (opcion.name().equals("SALIR"));
    }
    public  void menuPrincipal() throws IOException {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesMenuPrincipal opcion;
        do {

            System.out.println(Menu.OpcionesMenuPrincipal.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcion = Menu.OpcionesMenuPrincipal.getOpcion(valor);
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
                    escribirDatos();
                    break;
            }

        } while (opcion.name().equals("SALIR"));
    }


        public  void ejecutar() throws IOException, ClassNotFoundException {
        //leerDatos();
        menuPrincipal();

    }

}