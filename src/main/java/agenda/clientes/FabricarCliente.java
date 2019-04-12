package agenda.clientes;

import agenda.Direccion;
import agenda.tarifa.Tarifa;

public interface FabricarCliente {
    Cliente getCLienteParticular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos);
    Cliente getCLienteEmpresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa);
}
