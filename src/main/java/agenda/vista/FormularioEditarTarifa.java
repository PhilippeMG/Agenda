package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class FormularioEditarTarifa {
    JTextField cDni = new JTextField(5);
    JTextField tarifa = new JTextField(10);
    JButton bModificar = new JButton("Modificar");
    JFrame formulario;
    Controlador controlador;
    String dni;

    public FormularioEditarTarifa(Controlador controlador, String text) {
        this.controlador = controlador;
        this.dni = text;
        formulario = new JFrame("Editar Tarifa");//Creamos el JFrame
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        formulario.setLayout(new GridLayout(4, 2));
        Container contenedor = formulario.getContentPane();
        cDni.setText(text);
        cDni.setEditable(false);
        contenedor.add(new JLabel("DNI:"));
        contenedor.add(cDni);
        contenedor.add(new JLabel("Tarifa:"));
        contenedor.add(tarifa);
        contenedor.add(bModificar);

        formulario.pack();
        formulario.setVisible(true);

        bModificar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                modificar();
                formulario.setVisible(false); //ceramos la ventana automaticamente

            }
        });

    }

    public void limpiarCampos() {
        tarifa.setText("");
    }

    private void modificar() {
        if (tarifa.getText().length() > 0) {
            int precio = Integer.valueOf(String.valueOf(tarifa.getText()));
            try {
                controlador.modificarTarifa(dni, precio);

            } catch (ClientNotFound clientNotFound) {
                new PopUp("Cliente no existente", formulario, true);
            }
            limpiarCampos();
        } else {
            new PopUp("Campos vacios", formulario, true);
        }
    }


}
