package agenda;

public class Particular extends Cliente{
    String apellidos;

    public Particular(String nombre, String nif, Direccion direccion, String correo, int tipoTarifa, String apellidos) {
        super(nombre, nif, direccion, correo, tipoTarifa);
        this.apellidos = apellidos;
    }
}
