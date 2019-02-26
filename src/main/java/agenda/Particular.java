package agenda;

public class Particular extends Cliente{
    String apellidos;

    public Particular(String nombre, String nif, String apellidos) {
        super(nombre, nif);
        this.apellidos = apellidos;
    }
}
