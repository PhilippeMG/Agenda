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

public class PanelClientes extends JPanel {
    JTable tbl;
    JScrollPane panel;
    Controlador controlador;
    Modelo modelo;
    JTextArea areaDatosClientes = new JTextArea(20, 10);
    JTextField dniCliente;
    JFrame vista;

    public PanelClientes(Controlador controlador, Modelo modelo, JFrame vista) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.vista = vista;
        // JButton bListarFacturas = new JButton("Añadir Cliente");
        JButton bBuscarCliente = new JButton("Buscar Cliente");
        JButton bAñadirCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");
        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JButton bEditarCliente = new JButton("Editar Tarifa ");
        JButton bListarEntre2 = new JButton("Listar entre dos Fechas");

        JLabel jDNI = new JLabel("DNI del cliente: ");
        dniCliente = new JTextField(10);

        Vector datos = modelo.informacionClientes(modelo.getClientes());

        JScrollPane panel = new JScrollPane(areaDatosClientes);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rellenarInformacion(datos);

        bBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (!dniIsEmpty()) {

                    Vector infoCliente = null;
                    try {
                        infoCliente = controlador.devolverCliente(dniCliente.getText());
                        new FormularioBuscarCliente(controlador, infoCliente);

                    } catch (ClientNotFound clientNotFound) {
                        new PopUp("El cliente no existe", vista, true);
                    }

                } else {

                    new PopUp("DNI no introducido", vista, true);
                }
            }
        });


        bAñadirCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creando cliente...");

                new FormularioCrearCliente(controlador);
            }
        });
        bEditarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Editar cliente...");
                new FormularioEditarTarifa(controlador);
            }
        });
        bListarEntre2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioListarEntre2Fechas(controlador);
            }
        });

        bBorrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!dniIsEmpty()) {
                    try {
                        controlador.eliminarCliente(dniCliente.getText());
                        dniCliente.setText("");
                        Vector datos = modelo.informacionClientes(modelo.getClientes());
                        rellenarInformacion(datos);

                    } catch (ClientNotFound clientNotFound) {
                        new PopUp("Cliente no encontrado", vista, true);

                    }
                } else {
                    System.out.printf("Dni no introducido");
                    new PopUp("DNI no introducido", vista, true);
                }
            }

        });
        bSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Guardando Datos...");
                try {
                    controlador.guardarDatos();
                    //    actualizarTabla(tabla,datos);
                    Vector datos = modelo.informacionClientes(modelo.getClientes());

                    rellenarInformacion(datos);


                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });

        //Añadimos los elementos
        JPanel panelDNI = new JPanel();
        panelDNI.add(jDNI);
        panelDNI.add(dniCliente);

        Container contenedor = new Container();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JPanel panelOption = new JPanel();

        panelOption.add(panelDNI);
        panelOption.add(bBuscarCliente);
        panelOption.add(bAñadirCliente);
        panelOption.add(bBorrarCliente);
        panelOption.add(bEditarCliente);
        panelOption.add(bListarEntre2);
        panelOption.add(bSave);

        contenedor.add(panelOption);
        contenedor.add(panel);
        add(contenedor);

        areaDatosClientes.setForeground(Color.BLACK);
        areaDatosClientes.setEditable(false);


    }

    public void actualizarClientes() {
        Vector datos = modelo.informacionClientes(modelo.getClientes());
        rellenarInformacion(datos);
    }

    public void rellenarInformacion(Vector datos) {
        areaDatosClientes.setText("");
        areaDatosClientes.append("NIF\tNombre\tDirección\t\t\tCorreo\tTarifa\tFecha\n");

        for (int i = 0; i < datos.size(); i++) {
            Vector dades = (Vector) datos.get(i);
            areaDatosClientes.append(dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\t" + dades.get(3) + "\t" + dades.get(4) + "\t" + dades.get(5) + "\n");
        }

    }

    public boolean dniIsEmpty() {
        return (dniCliente.getText().length() <= 0);
    }

}