package agenda.vista;

import agenda.controlador.GestionarAgenda;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.InvalidArguments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class FormularioEditarTarifa {
    JTextField dni = new JTextField(10);

    JTextField tarifa = new JTextField(10);

    JButton bModificar = new JButton("Modificar");
    public FormularioEditarTarifa() {
        JFrame formulario = new JFrame("Editar Tarifa");//Creamos el JFrame
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        //Creamos una tabla de filas x columnas
        formulario.setLayout(new GridLayout(4, 2));
        //nombre, nif, int CP, String provincia, String poblacion, correo, tarifa, apellidos
        Container contenedor = formulario.getContentPane();
        // contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        // Añadimos los elementos de rellenan por filas de izq a derecha

        contenedor.add(new JLabel("DNI:"));
        contenedor.add(dni);
        contenedor.add(new JLabel("Tarifa:"));
        contenedor.add(tarifa);


        contenedor.add(bModificar);
        //ancho por altura
        formulario.setSize(300, 150);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
        limpiarCampos();
        bModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                    int preu = Integer.valueOf(String.valueOf(tarifa.getText()));

                    System.out.println("Modificando...");

                try {
                    new GestionarAgenda().modificarTarifa(dni.getText(),preu);
                    System.out.println("Modificado correctamente");

                } catch (ClientNotFound clientNotFound) {
                    clientNotFound.printStackTrace();
                }
                limpiarCampos();
            }
        });

    }

    public void limpiarCampos() {
        dni.setText("");
        tarifa.setText("");


    }

}
