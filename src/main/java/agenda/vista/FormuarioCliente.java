package agenda.vista;

import javax.swing.*;
import java.awt.*;

public class FormuarioCliente {
    public FormuarioCliente(){
        JFrame formulario=new JFrame("Añadir Cliente");//Creamos el JFrame
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada
        JTextField nombre = new JTextField(20);
        JTextField dni = new JTextField(20);
        JTextField direccion = new JTextField(20);
        JTextField correo = new JTextField(20);
        JTextField apellido = new JTextField(20);

        JButton bAñadir=new JButton("Añadir");
        //nombre, nif, direccion, correo, tarifa, apellidos
        Container contenedor = formulario.getContentPane();
        contenedor.setLayout(new FlowLayout());
        contenedor.add(new JLabel("Nombre:"),BorderLayout.CENTER);
        contenedor.add(nombre,BorderLayout.EAST);
        contenedor.add(new JLabel("Apellido:"),BorderLayout.CENTER);
        contenedor.add(apellido,BorderLayout.EAST);
        contenedor.add(new JLabel("DNI:"),BorderLayout.CENTER);
        contenedor.add(dni,BorderLayout.EAST);
        contenedor.add(new JLabel("Dirección:"),BorderLayout.CENTER);
        contenedor.add(direccion,BorderLayout.EAST);
        contenedor.add(new JLabel("Correo:"),BorderLayout.CENTER);
        contenedor.add(correo,BorderLayout.EAST);
        contenedor.add(bAñadir,BorderLayout.SOUTH);
        formulario.setSize(400,400);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
    }

}
