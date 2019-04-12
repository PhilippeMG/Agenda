package agenda.clientes;

import agenda.Direccion;
import agenda.tarifa.Tarifa;

public class crearCliente implements crearUsuario{

    public  crearCliente(){
        super();
    }
    @Override
    public Cliente getCLienteParticular(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa, String apellidos) {
        return new Particular(nombre, nif, direccion, correo, tipoTarifa,apellidos);
    }

    @Override
    public Cliente getCLienteEmpresa(String nombre, String nif, Direccion direccion, String correo, Tarifa tipoTarifa) {
        return new Empresa(nombre, nif, direccion, correo, tipoTarifa);
    }
}
