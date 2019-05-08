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
        panel.add(text);
        popup.setSize(250, 80);
        popup.add(panel, BorderLayout.CENTER);
        popup.setVisible(true);
    }

}
