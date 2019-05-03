package agenda.vista;

import javax.swing.*;
import java.awt.*;

class Ventana {
    public static void main(String[] args) {
        JFrame ventana=new JFrame("Agenda");//cCreamos el JFrame


        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png");
        ventana.setIconImage(icono);

        ventana.setSize(400,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Clentes", new PanelClientes());
        pestanyas.add("Facturas", new PanelFacturas());
        pestanyas.add("Llamadas", new PanelLlamadas());

        ventana.add(pestanyas);
        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


}
