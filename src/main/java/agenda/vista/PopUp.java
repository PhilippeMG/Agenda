package agenda.vista;

import javax.swing.*;
import java.awt.*;

public class PopUp extends JDialog {
    private String info;

    PopUp(String info, JFrame padre, boolean bloqueante) {
        super(padre, bloqueante);
        this.info = info;
        if (bloqueante) {
            JLabel text = new JLabel(info);
            JPanel panel = new JPanel();
            panel.add(text);

            add(panel, BorderLayout.CENTER);
            setBounds(750, 400, 250, 100);
            Image icono = Toolkit.getDefaultToolkit().getImage("src/media/cancel.png"); //Creamos una IMAGE
            setTitle("Error");
            setIconImage(icono);
            setVisible(true);

        }

    }

}
