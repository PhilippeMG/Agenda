package agenda.modelo.excepciones;

public class OptionNotFound extends Exception {
    public OptionNotFound(){
        super("Opcion no valida");
    }
}