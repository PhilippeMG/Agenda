package agenda.excepciones;

public class FacturaNotFound extends Exception {
    public FacturaNotFound() {
        super("No existe la factura expecificada");
    }
}
