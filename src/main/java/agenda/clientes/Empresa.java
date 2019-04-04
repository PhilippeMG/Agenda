package agenda.clientes;

import agenda.Direccion;
import agenda.tarifa.Tarifa;

public class Empresa extends Cliente {
    public Empresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa) {
        super(nombre, nif, direccion, correo, tipoTarifa);
    }

}
