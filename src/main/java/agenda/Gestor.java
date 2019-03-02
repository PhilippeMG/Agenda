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
    public void añadirCliente(Cliente cliente){
        if(!clientes.containsKey(cliente.nif)){
            clientes.put(cliente.nif,cliente);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void borrarCliente(String NIF){
        if(clientes.containsKey(NIF)){
            clientes.remove(NIF);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void mostrarCliente(String NIF){
        if(clientes.containsKey(NIF)){
            clientes.get(NIF).toString(); //LLama a toString de cliente.
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void mostrarClientes(){
        Iterator<Cliente> clientela = clientes.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()){
            System.out.println(clientela.next().toString());
        }
    }

    //>>>LLAMADAS<<<<
    public void añadirLlamada(String NIF, Llamada llamada){
        if(!clientes.containsKey(NIF)){
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
    public void añadirFactura(Factura factura) {
        if(!facturas.containsKey(factura.getCod())) {
            facturas.put(factura.getCod(), factura);
        }else{
            throw new IllegalArgumentException();
        }
    }
    public void emitirFactura(String NIF, LocalDate ini, LocalDate fin) {
        //Guardar tando en el vector de Cliente como en el mapa de Gestor.
        if (clientes.containsKey(NIF)) {
            Cliente cliente = clientes.get(NIF);
            Factura factura = new Factura(cliente, ini, fin);
            cliente.añadirFactura(factura);

            factura.toString();
        } else {
            throw new IllegalArgumentException();
        }
    }

    public static void main(String[] args) {
        System.out.println(OpcionesMenu.getMenu());
        Scanner scanner = new Scanner(System.in);
        System.out.print("Elije una opción: ");
        int valor = scanner.nextInt();
        OpcionesMenu opcion = OpcionesMenu.getOpcion(valor);
        switch (opcion){
            case AÑADIR_CLIENTE:
                System.out.println("0.-Particular");
                System.out.println("1.-Empresa");
                System.out.print("Tipo de cliente:");
                int cliente = scanner.nextInt();
                switch (cliente){
                    case 0:
                        break;
                    case 1:
                        break;
                }
                break;
            case BORRAR_CLIENTE:
                break;
            case CAMBIAR_TARIFA_CLIENTE:
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
        System.out.println(cliente1.toString());
        System.out.println("Añadiendo cliente...");
        gestor.añadirCliente(cliente1);
        gestor.añadirCliente(cliente2);

        System.out.println("CLIENTES AÑADIDOS");
        gestor.mostrarClientes();

        //Crear llamadas

        Llamada llamada = new Llamada(654078311,120,new Date());
        Llamada llamada2 = new Llamada(654078551,120,new Date());
        cliente1.llamadas.add(llamada);
        cliente1.llamadas.add(llamada2);
        Factura.calcularFactura(cliente1,new Date(),new Date(),facturas);
        Factura.calcularFactura(cliente1,new Date(),new Date(),facturas);

        System.out.println("Facturas Cliente 1");

        for(int i =0; i<cliente1.facturas.size();i++){
            LinkedList<Factura> fact=cliente1.facturas;
            System.out.println(fact.get(i).toString());
        }
*/


    }
}