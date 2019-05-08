package agenda.vista;

import javax.swing.*;
import java.awt.*;

public class PopUp {
    String info;

    PopUp(String info){
        this.info = info;
        JFrame popup = new JFrame("Error");
        JPanel panel = new JPanel();
        JLabel text = new JLabel(info);
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/cancel.png"); //Creamos una IMAGE
        popup.setIconImage(icono); //Añadimos la IMAGE creada

        panel.add(text);
        popup.setSize(250, 80);
        popup.add(panel, BorderLayout.CENTER);
        popup.setVisible(true);
    }

}
