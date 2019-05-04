package agenda.vista;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLOutput;

public class PanelClientes  extends JPanel{
    //JButton button = new JButton("Añadir Cliente");

    public PanelClientes() {
      // JButton button = new JButton("Añadir Cliente");
        JButton button = new JButton("Añadir Cliente");
        JButton button2 = new JButton("Añadir Cliente 2");





        button.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creamdo cliente...");
                new FormuarioCliente();
            }
        });
        //Añadimos los elementos
        add(button);
        add(button2);
    }
}
