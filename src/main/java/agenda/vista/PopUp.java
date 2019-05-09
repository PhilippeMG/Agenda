package agenda.vista;

import javax.swing.*;
import java.awt.*;

public class PopUp extends JDialog {
    String info;

    PopUp(String info,JFrame padre, boolean bloqueante) {
        super(padre, bloqueante);
        this.info = info;
        if(bloqueante) {
            JLabel text = new JLabel(info);
            JPanel panel = new JPanel();
            panel.add(text);

            add(panel,BorderLayout.CENTER);
            setBounds(750, 400, 250, 100);
            Image icono = Toolkit.getDefaultToolkit().getImage("src/media/cancel.png"); //Creamos una IMAGE
            setTitle("Error");
            setIconImage(icono);
            setVisible(true);

        }
       /* JFrame popup = new JFrame("Error");
        JPanel panel = new JPanel();
        JLabel text = new JLabel(info);
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/cancel.png"); //Creamos una IMAGE
        popup.setIconImage(icono); //Añadimos la IMAGE creada

        panel.add(text);
        popup.setSize(250, 80);
        popup.add(panel, BorderLayout.CENTER);
        popup.setVisible(true);*/
    }

}
