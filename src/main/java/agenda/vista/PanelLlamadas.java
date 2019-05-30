package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class PanelLlamadas extends JPanel {
    private Controlador controlador;
    private Modelo modelo;
    private JButton bInsetarLlamada = new JButton("Insertar llamada");
    private JButton bListarLlamadas = new JButton("Listar llamadas de un DNI");
    private JButton bListarEntre2 = new JButton("Listar  entre dos fechas");
    private JButton bGuardar = new JButton("Guardar");
    private JFrame vista;
    private JTextArea areaDatos = new JTextArea(20, 10);
    private JTextField dni = new JTextField(10);

    public PanelLlamadas(Controlador controlador, Modelo modelo, JFrame vista) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.vista = vista;

        JPanel panel = new JPanel();
        panel.add(new JLabel("DNI del cliente: "));
        panel.add(dni);
        panel.add(bListarLlamadas);
        panel.add(bInsetarLlamada);

        panel.add(bListarEntre2);
        panel.add(bGuardar);

        Container contenedor = new Container();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        contenedor.add(panel);
        JScrollPane zonaDatos = new JScrollPane(areaDatos);
        zonaDatos.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        zonaDatos.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        areaDatos.setEditable(false);
        contenedor.add(zonaDatos);
        add(contenedor);
        bListarLlamadas.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                if (!dniIsEmpty()) {
                    try {
                        String datos = controlador.getLlamadasCliente(dni.getText());
                        rellenarInformacionLlamadas(datos);
                    } catch (ClientNotFound clientNotFound) {
                        new PopUp("Cliente no encontrado.", vista, true);

                    }
                } else {
                    new PopUp("EL DNI esta vacio.", vista, true);

                }

            }
        });
        bListarEntre2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioListarEntre2Fechas(controlador);
            }
        });
        bGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                controlador.guardarDatos();

            }
        });

        bInsetarLlamada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!dniIsEmpty()) {
                    new FormularioCrearLlamada(controlador, dni.getText());
                } else new PopUp("EL DNI esta vacio.", vista, true);

            }
        });
    }

    public boolean dniIsEmpty() {
        return (dni.getText().length() <= 0);
    }

    public void actualizarLlamadas() {
        if (!dniIsEmpty()) {
            try {
                String datos = controlador.getLlamadasCliente(dni.getText());
                rellenarInformacionLlamadas(datos);
            } catch (ClientNotFound clientNotFound) {

            }
        }
    }

    public void rellenarInformacionLlamadas(String datos) {
        areaDatos.setText("");
        areaDatos.append(datos);

    }
}
