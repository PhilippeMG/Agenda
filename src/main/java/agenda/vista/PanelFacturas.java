package agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFacturas extends JPanel {
    JButton button = new JButton("Click me!, Facturas");

    public PanelFacturas() {
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Boton Facturas pulsado ");

                //JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
        add(button);
    }
}
