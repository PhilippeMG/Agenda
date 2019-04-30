package agenda.modelo.clientes;

import agenda.modelo.Direccion;
import agenda.modelo.tarifa.Tarifa;

public class CrearCliente implements FabricarCliente {

    public CrearCliente(){
        super();
    }
    @Override
    public Cliente getClienteParticular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        return new Particular(nombre, nif, direccion, correo, tipoTarifa,apellidos);
    }

    @Override
    public Cliente getClienteEmpresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa) {
        return new Empresa(nombre, nif, direccion, correo, tipoTarifa);
    }
}
