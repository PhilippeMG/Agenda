package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFacturas extends JPanel {
    JButton button = new JButton();
    Controlador controlador;
    Modelo modelo;
    JTextArea areaDatos =new JTextArea(20,10);

    public PanelFacturas(Controlador controlador, Modelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;

        JPanel panel = new JPanel();
        panel.add(button);


        Container contenedor = new Container();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        contenedor.add(panel);
        contenedor.add(areaDatos);

        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Boton Facturas pulsado ");
            }
        });
        add(contenedor);
    }
}
