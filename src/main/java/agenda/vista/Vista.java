package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;

import javax.swing.*;
import java.awt.*;

public class Vista implements InterfaceVista {
    //Atributos
    Controlador controlador;
    Modelo modelo;
    JFrame ventana=new JFrame("Agenda");//Creamos el JFrame

    public void setControlador(Controlador gestorAgenda) {
        this.controlador = gestorAgenda;
    }

    public void run() throws ClassNotFoundException {
        controlador.cargarDatos();

        //Modificamos el icono de la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png"); //Creamos una IMAGE
        ventana.setIconImage(icono); //Añadimos la IMAGE creada
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Clentes", new PanelClientes(controlador, modelo, ventana));

        pestanyas.add("Facturas", new PanelFacturas(this.controlador, this.modelo));
        pestanyas.add("Llamadas", new PanelLlamadas(this.controlador, this.modelo));

        ventana.add(pestanyas);
        ventana.pack();

        // ventana.setSize(800,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
