package agenda;

public class Particular extends Cliente{
    String apellidos;

    public Particular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        super(nombre, nif, direccion, correo, tipoTarifa);
        this.apellidos = apellidos;
    }

    @Override
    public String toString() {
        return "Particular: " +
                  "nombre='" + nombre + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + nif + '\'' +
                ", direccion=" + direccion +
                ", correo='" + correo + '\'' +
                ", fechaDeAlta=" + fechaDeAlta +
                ", tipoTarifa=" + tipoTarifa.getTarifa() +
                '}';
    }
}
