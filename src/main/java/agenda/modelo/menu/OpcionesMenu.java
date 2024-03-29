package agenda.modelo.menu;

import agenda.modelo.Factura;
import agenda.modelo.Modelo;
import agenda.modelo.Llamada;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.FacturaNotFound;
import agenda.modelo.excepciones.InvalidArguments;
import agenda.modelo.excepciones.OptionNotFound;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class OpcionesMenu extends Modelo {

    //>>>MENU PRINCIPAL<<<<

    public void menuPrincipal() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesMenuPrincipal opcion = Menu.OpcionesMenuPrincipal.getOpcion(0);
        cargarDatos();

        do {

            System.out.println(Menu.OpcionesMenuPrincipal.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();

            try {
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
                        guardarDatos();
                        break;
                }
            } catch (OptionNotFound e) {
                System.out.println(e.getMessage().toUpperCase());

            }

        } while (!opcion.name().equals("SALIR"));
    }

    //>>>MENU FACTURAS<<<<
    public void subMenuFacturas() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuFacturas opcionFactura = Menu.OpcionesSubMenuFacturas.getOpcion(0);
        do {

            System.out.println(Menu.OpcionesSubMenuFacturas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();

            try {
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
                        guardarDatos();
                        break;

                }
            } catch (OptionNotFound e) {
                System.out.println(e.getMessage().toUpperCase());

            }
        } while (!opcionFactura.name().equals("SALIR"));
    }

    //>>>MENU LLAMADAS<<<<
    public void subMenuLlamadas() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuLlamadas opcionLlamada = Menu.OpcionesSubMenuLlamadas.getOpcion(0);
        do {

            System.out.println(Menu.OpcionesSubMenuLlamadas.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();

            try {
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
                        guardarDatos();
                        break;

                }
            } catch (OptionNotFound e) {
                System.out.println(e.getMessage().toUpperCase());

            }
        } while (!opcionLlamada.name().equals("SALIR"));
    }

    //>>>MENU CLIENTES<<<<
    public void subMenuCLientes() throws Exception {
        Scanner scannerMenu = new Scanner(System.in);
        Menu.OpcionesSubMenuClientes opcionCliente = Menu.OpcionesSubMenuClientes.getOpcion(0);
        do {

            System.out.println(Menu.OpcionesSubMenuClientes.getMenu());

            System.out.print("\nElije una opción: ");
            int valor = scannerMenu.nextInt();

            try {
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
                        guardarDatos();
                        break;

                }
            } catch (OptionNotFound e) {
                System.out.println(e.getMessage().toUpperCase());

            }
        } while (!opcionCliente.name().equals("SALIR"));
    }

    //METODOS DE LAS OPCIONES DEL MENU QUE LLAMAN A METODOS DE GESTOR MEDIANTE: extendeds Modelo
    //>>>OPCION CLIENTE<<<<
    public void opcionInsertarCliente() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("0.-Particular");
        System.out.println("1.-Empresa");
        System.out.print("Tipo de cliente: ");
        int tipo = scanner.nextInt();
        try {
            nuevoCliente(tipo);
        } catch (InvalidArguments e) {
            System.out.println("YA HAY UN CLIENTE CON EL MISMO DNI");
        }
    }

    public void opcionBorrarCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente que quieres eliminar: ");
        String nif = scanner.next();
        try {
            borrarCliente(nif);
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    public void opcionDatosClientes() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            mostrarCliente(nif);
        } catch (ClientNotFound e) {
            System.out.println("NO HAY CLIENTE CON NIF EXPECIFICADO");
        }
    }

    public void opcionDevolverCLientesEntreFechas() {
        Collection<Cliente> myCollection = getClientes().values();
        List<Cliente> list = new LinkedList<>(myCollection);
        LinkedList<Cliente> clienteEntreFechas = devolverEntreFechas(list, crearFecha(), crearFecha());
        if (clienteEntreFechas.isEmpty()) {
            System.out.println("NO HAY CLIENTES ENTRE ESAS FECHAS");
        } else {
            System.out.println(clienteEntreFechas.toString());
        }
    }

    public void opcionCambiarTarifa() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nueva tarifa para el cliente: ");
        int tarifa = scanner.nextInt();
        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            cambiarTarifa(nif, tarifa);
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    //>>>OPCION LLAMADAS<<<<
    public void opcionListarLlamadasCliente() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("NIF del cliente: ");

        String nif = scanner.next();
        try {
            mostrarLlamadas(nif);
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    public void opcionInsertarLlamada() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();

        System.out.print("Numero destino: ");
        int numDestino = scanner.nextInt();
        System.out.print("Dureción: ");
        double duracion = scanner.nextDouble();


        LocalDateTime fechaLlamada = crearFecha();
        Llamada llamada = new Llamada(numDestino, duracion, fechaLlamada);
        try {
            insertarLlamada(nif, llamada);
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    public void opcionDevolverLlamadasEntreFechas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            if (getClientes().containsKey(nif)) {
                Cliente cliente = getClientes().get(nif);
                LinkedList<Llamada> llamadas = devolverEntreFechas(cliente.getLlamadas(), crearFecha(), crearFecha());
                System.out.printf(llamadas.toString());
            } else {
                throw new ClientNotFound();
            }
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    //>>>OPCION FACTURA<<<<
    public void opcionEmitirFacturaCLiente() throws Exception {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            System.out.printf(emitirFactura(nif, crearFecha(), crearFecha()).toString());
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }

    }

    public void opcionRecuperarFactura() {
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.print("Codigo de la factura: ");
            int codigo = scanner.nextInt();
            mostrarFactura(codigo);
        } catch (FacturaNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    public void opcionFacturasCLiente() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            mostrarFacturas(nif);
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }

    public void opcionDevolverFacturasEntreFechas() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("NIF del cliente: ");
        String nif = scanner.next();
        try {
            if (getClientes().containsKey(nif)) {
                Cliente cliente = getClientes().get(nif);

                Collection<Factura> myCollection = cliente.getFacturas().values();
                List<Factura> list = new LinkedList<>(myCollection);
                LinkedList<Factura> facturasEntreFechas = devolverEntreFechas(list, crearFecha(), crearFecha());
                System.out.printf(facturasEntreFechas.toString());
            } else {
                throw new ClientNotFound();
            }
        } catch (ClientNotFound e) {
            System.out.println(e.getMessage().toUpperCase());
        }
    }
}
