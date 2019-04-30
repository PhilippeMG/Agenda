package agenda.modelo.clientes;

import agenda.modelo.Direccion;
import agenda.modelo.tarifa.Tarifa;

public class Particular extends Cliente {
    private String apellidos;

    public Particular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        super(nombre, nif, direccion, correo, tipoTarifa);
        this.apellidos = apellidos;
    }
    public Particular() {
        super();
    }

    @Override
    public String getNombreCompleto() {
        return getNombre()+" "+getApellidos();
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
                ", Direccion: [" + super.getDireccion() + ']' +
                ", correo='" + super.getCorreo() + '\'' +
                ", fechaDeAlta='" + getFechaDeAlta() + '\'' +
                ", infoTarifa='" + super.getTarifa().descripcion() + '\'';
    }
}
