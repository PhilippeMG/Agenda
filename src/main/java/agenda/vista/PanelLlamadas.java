package agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelLlamadas extends JPanel {
    JButton button = new JButton("Click me!, Llamadas");

    public PanelLlamadas() {
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Boton Llamadas pulsado ");

               // JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
        add(button);
    }
}
