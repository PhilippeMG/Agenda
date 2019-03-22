package agenda.menu;

import agenda.Factura;
import agenda.Fichero;
import agenda.Gestor;
import agenda.Llamada;
import agenda.clientes.Cliente;
import agenda.excepciones.ClientNotFound;
import agenda.excepciones.FacturaNotFound;

import java.time.LocalDate;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OpcionesMenu extends Gestor {

    public void menuPrincipal() throws Exception {
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

        } while (!opcion.name().equals("SALIR"));
    }

    public void subMenuFacturas() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuFacturas opcionFactura;
        do {

            System.out.println(Menu.OpcionesSubMenuFacturas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcionFactura = Menu.OpcionesSubMenuFacturas.getOpcion(valor);
            switch (opcionFactura) {
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

        } while (!opcionFactura.name().equals("SALIR"));
    }

    public void subMenuLlamadas() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuLlamadas opcionLlamada;
        do {

            System.out.println(Menu.OpcionesSubMenuLlamadas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcionLlamada = Menu.OpcionesSubMenuLlamadas.getOpcion(valor);
            switch (opcionLlamada) {
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

        } while (!opcionLlamada.name().equals("SALIR"));
    }

    public void subMenuCLientes() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuClientes opcionCliente;
        do {

            System.out.println(Menu.OpcionesSubMenuClientes.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();


            opcionCliente = Menu.OpcionesSubMenuClientes.getOpcion(valor);
            switch (opcionCliente) {
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
                    mostrarClientes(getClientes());
                    break;
                case CAMBIAR_TARIFA_CLIENTE:
                    opcionCambiarTarifa();
                    break;

                case SALIR:
                    escribirDatos();
                    break;
                default:
                    System.out.printf("Opcion no valida");
                    break;
            }

        } while (!opcionCliente.name().equals("SALIR"));
    }
//Metodos

    //Menu
    public void opcionInsertarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("0.-Particular");
        System.out.println("1.-Empresa");
        System.out.print("Tipo de cliente: ");
        int tipo = scanner.nextInt();
        nuevoCliente(tipo);
        //scanner.close();
    }

    public void opcionBorrarCliente() throws ClientNotFound {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente que quieres eliminar: ");
        String nif = scanner.next();
        borrarCliente(nif);
    }

    public void opcionCambiarTarifa() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.print("Nueva tarifa para el cliente: ");
        int tarifa = scanner.nextInt();
        cambiarTarifa(nif, tarifa);
    }

    public void opcionDatosClientes() throws ClientNotFound {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarCliente(nif);
    }

    public void opcionListarLlamadasCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente: ");

        String nif = scanner.next();
        mostrarLlamadas(nif);
    }

    public void opcionEmitirFacturaCLiente() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        System.out.printf(emitirFactura(nif, crearFecha(), crearFecha()).toString());
    }

    public void opcionInsertarLlamada() throws ClientNotFound {
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

    public void opcionRecuperarFactura() throws FacturaNotFound {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Codigo de la factura: ");
        int codigo = scanner.nextInt();
        mostrarFactura(codigo);
    }

    public void opcionFacturasCLiente() throws ClientNotFound {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        mostrarFacturas(nif);
    }

    public void opcionDevolverLlamadasEntreFechas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        if (getClientes().containsKey(nif)) {
            Cliente cliente = getClientes().get(nif);
            LinkedList<Llamada> llamadas = devolverEntreFechas(cliente.getLlamadas(), crearFecha(), crearFecha());
            System.out.printf(llamadas.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void opcionDevolverFacturasEntreFechas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        if (getClientes().containsKey(nif)) {
            Cliente cliente = getClientes().get(nif);

            Collection<Factura> myCollection = cliente.getFacturas().values();
            List<Factura> list = new LinkedList<>(myCollection);
            LinkedList<Factura> facturasEntreFechas = devolverEntreFechas(list, crearFecha(), crearFecha());
            System.out.printf(facturasEntreFechas.toString());
        } else {
            throw new IllegalArgumentException();
        }
    }


    public void opcionDevolverCLientesEntreFechas() {
        Collection<Cliente> myCollection = getClientes().values();
        List<Cliente> list = new LinkedList<>(myCollection);
        LinkedList<Cliente> clienteEntreFechas = devolverEntreFechas(list, crearFecha(), crearFecha());
        System.out.printf(clienteEntreFechas.toString());

    }

}
