package agenda.menu;

import agenda.excepciones.OptionNotFound;

public class Menu {
    //>>>MENU<<<<
    public enum OpcionesMenuPrincipal {

        GESTIONAR_CLIENTES("Gestionar clientes"),
        GESTIONAR_LLAMADAS("Gestionar llamadas"),
        GESTIONAR_FACTURAS("Gestionar facturas"),

        /*  INSERTAR_LLAMADA("Insertar llamada"),
          LISTAR_LLAMADAS_CLIENTE("Mostrar llamadas de un cliente"),
        EMITIR_FACTURA_CLIENTE("Emitir factura de un cliente"),
          FACTURAS_CLIENTE("Mostrar conjunto facturas de un cliente"),
          RECUPERAR_FACTURA("Mostrar Factura"),
        */  SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesMenuPrincipal(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesMenuPrincipal getOpcion(int opcion) throws OptionNotFound {
            if(opcion>= values().length) throw new OptionNotFound();

            return values()[opcion];
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static String getMenu() {
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for (OpcionesMenuPrincipal opcion : OpcionesMenuPrincipal.values()) {
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    //>>>SubMenuCLientes<<<<
    public enum OpcionesSubMenuClientes {

        INSERTAR_CLIENTE("Insertar cliente"),
        BORRAR_CLIENTE("Borrar cliente"),
        LISTAR_CLIENTES("Listar clientes"),
        LISTAR_CLIENTES_ENTRE_FECHAS("Listar clientes entre dos fechas"),
        DATOS_CLIENTE("Mostrar información cliente"),
        CAMBIAR_TARIFA_CLIENTE("Modificar tarifa cliente"),

        SALIR("Voler menú principal");

        private String descripcion;

        private OpcionesSubMenuClientes(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesSubMenuClientes getOpcion(int opcion) throws OptionNotFound {
            if(opcion>= values().length) throw new OptionNotFound();

            return values()[opcion];
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static String getMenu() {
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for (OpcionesSubMenuClientes opcion : OpcionesSubMenuClientes.values()) {
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    //>>>SubMenuLlamadas<<<<
    public enum OpcionesSubMenuLlamadas {

        INSERTAR_LLAMADA("Insertar llamada"),
        LISTAR_LLAMADAS_CLIENTE("Mostrar llamadas de un cliente"),
        LISTAR_LLAMADAS_ENTRE_FECHAS("Listar llamadas entre dos fechas"),


        SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesSubMenuLlamadas(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesSubMenuLlamadas getOpcion(int opcion) throws OptionNotFound {
            if(opcion>= values().length) throw new OptionNotFound();
            return values()[opcion];
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static String getMenu() {
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for (OpcionesSubMenuLlamadas opcion : OpcionesSubMenuLlamadas.values()) {
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }

    //>>>SubMenuFacturas<<<<
    public enum OpcionesSubMenuFacturas {

        EMITIR_FACTURA_CLIENTE("Emitir factura de un cliente"),
        FACTURAS_CLIENTE("Mostrar conjunto facturas de un cliente"),
        RECUPERAR_FACTURA("Mostrar Factura"),
        LISTAR_FACTURAS_ENTRE_FECHAS("Listar facturas entre dos fechas"),

        SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesSubMenuFacturas(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesSubMenuFacturas getOpcion(int opcion) throws OptionNotFound {
            if(opcion>= values().length) throw new OptionNotFound();

            return values()[opcion];
        }

        public String getDescripcion() {
            return descripcion;
        }

        public static String getMenu() {
            StringBuilder sb = new StringBuilder();
            System.out.println("¿Qué operación desea realizar?");
            for (OpcionesSubMenuFacturas opcion : OpcionesSubMenuFacturas.values()) {
                sb.append(opcion.ordinal());
                sb.append(".-");
                sb.append(opcion.getDescripcion());
                sb.append("\n");
            }
            return sb.toString();
        }
    }
}
