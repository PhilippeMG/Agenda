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
        formulario.setLayout(new GridLayout(6,2));
        //nombre, nif, direccion, correo, tarifa, apellidos
        Container contenedor = formulario.getContentPane();
       // contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));



        contenedor.add(new JLabel("Nombre:"));
        contenedor.add(nombre);
        contenedor.add(new JLabel("Apellido:"));
        contenedor.add(apellido);
        contenedor.add(new JLabel("DNI:"));
        contenedor.add(dni);
        contenedor.add(new JLabel("Dirección:"));
        contenedor.add(direccion);
        contenedor.add(new JLabel("Correo:"));
        contenedor.add(correo);

        contenedor.add(bAñadir);
        formulario.setSize(300,250);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
    }

}
