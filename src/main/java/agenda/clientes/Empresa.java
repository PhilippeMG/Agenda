package agenda.clientes;

import agenda.Direccion;
import agenda.tarifa.Tarifa;

public class Empresa extends Cliente {
    public Empresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa) {
        super(nombre, nif, direccion, correo, tipoTarifa);
    }

    public Empresa() {
    }

    @Override
    public String toString() {
        return "Particular: " +
                "nombre='" + super.getNombre() + '\'' +
                ", nif='" + super.getNif() + '\'' +
                ", Direccion: [" + super.getDireccion() + ']' +
                ", correo='" + super.getCorreo() + '\'' +
                ", fechaDeAlta='" + getFechaDeAlta() + '\'' +
                ", infoTarifa='" + super.getTarifa().descripcion() + '\'';
    }

}
