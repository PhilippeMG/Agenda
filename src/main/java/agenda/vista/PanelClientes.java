package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class PanelClientes extends JPanel {

    private Controlador controlador;
    private Modelo modelo;
    private JTextArea areaDatosClientes = new JTextArea(20, 10);
    private JTextField dniCliente;
    private JFrame vista;

    public PanelClientes(Controlador controlador, Modelo modelo, JFrame vista) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.vista = vista;
        JButton bBuscarCliente = new JButton("Buscar Cliente");

        JButton bInsetarCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");
        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JButton bEditarCliente = new JButton("Editar Tarifa ");
        JButton bListarEntre2 = new JButton("Listar entre dos Fechas");

        JLabel jDNI = new JLabel("DNI del cliente: ");
        dniCliente = new JTextField(10);

        String datos = modelo.informacionClientes(modelo.getClientes());

        JScrollPane panel = new JScrollPane(areaDatosClientes);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        rellenarInformacion(datos);

        bBuscarCliente.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                buscarCliente();
            }
        });


        bInsetarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {

                new FormularioCrearCliente(controlador);
            }
        });
        bEditarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                editarCliente();
            }
        });
        bListarEntre2.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioListarEntre2Fechas(controlador);

            }
        });

        bBorrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                eliminarCliente();
            }

        });
        bSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                controlador.guardarDatos();
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
        panelOption.add(bInsetarCliente);
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
        String datos = modelo.informacionClientes(modelo.getClientes());
        rellenarInformacion(datos);
    }

    public void rellenarInformacion(String datos) {
        areaDatosClientes.setText("");
        areaDatosClientes.append(datos);

    }

    public boolean dniIsEmpty() {
        return (dniCliente.getText().length() <= 0);
    }

    private void eliminarCliente() {
        if (!dniIsEmpty()) {
            try {
                controlador.eliminarCliente(dniCliente.getText());
                dniCliente.setText("");

            } catch (ClientNotFound clientNotFound) {
                new PopUp("Cliente no encontrado", vista, true);

            }
        } else {
            System.out.printf("Dni no introducido");
            new PopUp("DNI no introducido", vista, true);
        }
    }

    private void editarCliente() {
        if (!dniIsEmpty()) {
            new FormularioEditarTarifa(controlador, dniCliente.getText());
        } else {
            new PopUp("DNI no introducido", vista, true);
        }
    }

    private void buscarCliente() {
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

}