package agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class PanelClientes  extends JPanel{
    JButton button = new JButton("Click me!, Clientes");

    public PanelClientes() {
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Boton Clientes pulsado ");
               // JOptionPane.showMessageDialog(null, "You just clicked button");
            }
        });
        add(button);
    }
}
