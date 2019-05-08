package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class FormularioBuscarCliente {
    Controlador controlador;
    TextArea infoCliente = new TextArea(20,10);


    FormularioBuscarCliente(Controlador controlador){
        this.controlador = controlador;
        JFrame formulario = new JFrame("Buscar Cliente");
        Container contenedor = formulario.getContentPane();
        contenedor.add(infoCliente);

        formulario.setSize(800,400);
        formulario.setVisible(true);

    }

}
