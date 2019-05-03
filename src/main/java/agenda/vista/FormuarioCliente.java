package agenda.vista;

import javax.swing.*;
import java.awt.*;

public class FormuarioCliente {
    public FormuarioCliente(){
        JFrame formulario=new JFrame("Añadir Cliente");//Creamos el JFrame
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada
        Container contenedor = formulario.getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(new JLabel("Nombre del Usuario:"));
        contenedor.add( new JTextField(20));
        contenedor.add(new JButton("Añadir"));
        formulario.setSize(400,400);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
    }

}
