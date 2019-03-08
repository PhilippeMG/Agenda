package agenda;

public class Menu {
    //>>>MENU<<<<
    public enum OpcionesMenuPrincipal {

        GESTIONAR_CLIENTES("Gestionar clientes"),
        CAMBIAR_TARIFA_CLIENTE("Modificar tarifa cliente"),
        INSERTAR_LLAMADA("Insertar llamada"),
        LISTAR_LLAMADAS_CLIENTE("Mostrar llamadas de un cliente"),
        EMITIR_FACTURA_CLIENTE("Emitir factura de un cliente"),
        FACTURAS_CLIENTE("Mostrar conjunto facturas de un cliente"),
        RECUPERAR_FACTURA("Mostrar Factura"),
        SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesMenuPrincipal(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesMenuPrincipal getOpcion(int opcion) {
            if (opcion >= values().length || opcion < 0){
                System.out.printf("Opcion no valida");
                return values()[11];
            }
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
    public enum OpcionesSubMenuClientes {

        INSERTAR_CLIENTE("Insertar cliente"),
        BORRAR_CLIENTE("Borrar cliente"),
        LISTAR_CLIENTES("Listar clientes"),
        LISTAR_CLIENTES_ENTRE_FECHAS("Listar clientes entre dos fechas"),
        DATOS_CLIENTE("Mostrar información cliente"),
        SALIR("Salir del Menú");

        private String descripcion;

        private OpcionesSubMenuClientes(String descripcion) {
            this.descripcion = descripcion;
        }

        public static OpcionesSubMenuClientes getOpcion(int opcion) {
            if (opcion >= values().length || opcion < 0){
                System.out.printf("Opcion no valida");
                return values()[11];
            }
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

}
