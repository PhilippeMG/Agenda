package agenda.excepciones;

public class ClientNotFound extends Exception {
    public ClientNotFound() {
        super("No existe cliente expecificado");
    }
}
