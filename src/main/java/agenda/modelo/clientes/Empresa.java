package agenda.modelo.clientes;

import agenda.modelo.Direccion;
import agenda.modelo.tarifa.Tarifa;

public class Empresa extends Cliente {
    public Empresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa) {
        super(nombre, nif, direccion, correo, tipoTarifa);
    }

    public Empresa() {
    }

    @Override
    public String getNombreCompleto() {
        return getNombre();    }

    @Override
    public String toString() {
        return "Empresa: " +
                "nombre='" + super.getNombre() + '\'' +
                ", nif='" + super.getNif() + '\'' +
                ", Direccion: [" + super.getDireccion() + ']' +
                ", correo='" + super.getCorreo() + '\'' +
                ", fechaDeAlta='" + getFechaDeAlta() + '\'' +
                ", infoTarifa='" + super.getTarifa().descripcion() + '\'';
    }

}
