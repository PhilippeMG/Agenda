package agenda.modelo.clientes;

import agenda.modelo.Direccion;
import agenda.modelo.tarifa.Tarifa;

public interface FabricarCliente {
    Cliente getClienteParticular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos);
    Cliente getClienteEmpresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa);
}
