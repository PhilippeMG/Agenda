package agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class PanelClientes  extends JPanel{
    JButton button = new JButton("Añadir Cliente");

    public PanelClientes() {
        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creamdo cliente...");
                new FormuarioCliente();
            }
        });
        add(button);
    }
}
