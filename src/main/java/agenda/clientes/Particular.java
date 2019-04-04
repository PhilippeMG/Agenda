package agenda.clientes;

import agenda.Direccion;
import agenda.tarifa.Tarifa;

public class Particular extends Cliente {
    private String apellidos;

    public Particular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        super(nombre, nif, direccion, correo, tipoTarifa);
        this.apellidos = apellidos;
    }
    public String getApellidos() {
        return this.apellidos;
    }

    @Override
    public String toString() {
        return "Particular: " +
                "nombre='" + super.getNombre() + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", nif='" + super.getNif() + '\'' +
                ", Direccion: " + super.getDireccion() +
                ", correo='" + super.getCorreo() + '\'' +
                ", fechaDeAlta=" + getFechaDeAlta() +
                ", tipoTarifa=" + super.getTarifa().getPrecio();
    }
}
