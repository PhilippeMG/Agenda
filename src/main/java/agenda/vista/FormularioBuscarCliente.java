package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FormularioBuscarCliente {
    Controlador controlador;


    JTextField nombre = new JTextField(20);
    JTextField dni = new JTextField(20);
    JTextField direccion = new JTextField(40);

    JTextField correo = new JTextField(20);
    JTextField tarifa = new JTextField(20);


    FormularioBuscarCliente(Controlador controlador, Vector infoCliente){
        this.controlador = controlador;
        JFrame formulario = new JFrame("Buscar Cliente");
        formulario.setLayout(new GridLayout(5,2));
        Container contenedor = formulario.getContentPane();

        contenedor.add(new JLabel("Nombre completo:"));
        nombre.setText(infoCliente.get(6).toString());
        nombre.setEditable(false);
        contenedor.add(nombre);

        contenedor.add(new JLabel("DNI:"));
        dni.setText(infoCliente.get(0).toString());
        dni.setEditable(false);
        contenedor.add(dni);

        contenedor.add(new JLabel("Direccion:"));
        direccion.setText(infoCliente.get(2).toString());
        direccion.setEditable(false);
        contenedor.add(direccion);

        contenedor.add(new JLabel("Correo:"));
        correo.setText(infoCliente.get(3).toString());
        correo.setEditable(false);
        contenedor.add(correo);

        contenedor.add(new JLabel("Tarifa:"));
        tarifa.setText(infoCliente.get(4).toString());
        tarifa.setEditable(false);
        contenedor.add(tarifa);

        formulario.pack();
        formulario.setVisible(true);// hacemos la ventsana visibel




    }



}
