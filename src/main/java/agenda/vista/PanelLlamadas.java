package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLlamadas extends JPanel {
    Controlador controlador;
    Modelo modelo;
    JButton button = new JButton("Click me!, Llamadas");

    public PanelLlamadas(Controlador controlador, Modelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Boton Llamadas pulsado ");

               // JOptionPane.showMessageDialog(null, "You just clicked bListarFacturas");
            }
        });
        add(button);
    }
}
