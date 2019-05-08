package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;

public interface InterfaceVista {
    void setControlador(Controlador gestorAgenda);
    void setModelo(Modelo modelo);
}
