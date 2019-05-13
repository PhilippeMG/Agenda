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
    Controlador controlador;
    Modelo modelo;
    JButton bInsetarLlamada = new JButton("Insertar llamada");
    JButton bListarLlamadas = new JButton("Listar llamadas de un DNI");
    JButton bListarEntre2 = new JButton("Listar  entre dos fechas");
    JButton bGuardar = new JButton("Guardar");
    JFrame vista;
    JTextArea areaDatos = new JTextArea(20, 10);
    JTextField dni = new JTextField(10);

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
        bListarLlamadas.addActionListener(new ActionListener(){

            public void actionPerformed(ActionEvent e){
                System.out.println("Listando llamadas...");
                if (!dniIsEmpty()) {
                    try {
                        Vector datos = controlador.getLlamadasCliente(dni.getText());
                        rellenarInformacionLlamadas(datos);
                    } catch (ClientNotFound clientNotFound) {
                        new PopUp("Cliente no encontrado.", vista, true);

                    }
                } else {
                    new PopUp("EL DNI esta vacio.", vista, true);

                }

               // JOptionPane.showMessageDialog(null, "You just clicked bListarFacturas");
            }
        });
        bListarEntre2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioListarEntre2Fechas(controlador);
            }
        });
        bGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                try {
                    controlador.guardarDatos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            }
        });

        bInsetarLlamada.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.print("Creando Llamada");
                new FormularioCrearLlamada(controlador);
            }
        });
    } public boolean dniIsEmpty() {
        return (dni.getText().length() <= 0);
    }
    public void rellenarInformacionLlamadas(Vector datos){
        areaDatos.setText("");
        areaDatos.append("Num destino\t Duración\t Fecha de la llamada\n");

        for(int i=0; i<datos.size();i++){
            Vector dades=(Vector) datos.get(i);
            areaDatos.append(dades.get(0)+"\t"+dades.get(1)+"\t"+dades.get(2)+"\n");
        }

    }
}
