package agenda.controlador;

import agenda.modelo.Modelo;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.tarifa.CrearTarifa;

public interface InterfaceControlador {
    void setModelo(Modelo modelo);
    void setFabricaClientes(CrearCliente fabrica);
    void setFabricaTarifas(CrearTarifa fabrica);
}
