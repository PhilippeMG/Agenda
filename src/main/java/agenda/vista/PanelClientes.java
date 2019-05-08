package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import javax.swing.event.TableModelEvent;
import javax.swing.table.TableModel;
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

    public PanelClientes(Controlador controlador, Modelo modelo) {
        this.controlador = controlador;
        this.modelo = modelo;

        // JButton button = new JButton("Añadir Cliente");
        JButton bAñadirCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");

        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JLabel borrar = new JLabel("DNI del cliente: ");
        JTextField dniCliente = new JTextField(10);
        JButton bEditarCliente = new JButton("Editar Tarifa ");

        JPanel panelBorrar = new JPanel();
        panelBorrar.add(borrar);
        panelBorrar.add(dniCliente);
        panelBorrar.add(bBorrarCliente);

        Vector cabecera = new Vector();
        cabecera.add("NIF");
        cabecera.add("Nombre");
        cabecera.add("Dirección");
        cabecera.add("Correo");
        cabecera.add("Tarifa");
        cabecera.add("Fecha");

        Vector datos = modelo.informacionClientes(modelo.getClientes());


        JTable tabla = new JTable(datos, cabecera);
        JScrollPane panel = new JScrollPane(tabla);

        bAñadirCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creamdo cliente...");
                new FormularioCliente(controlador);
            }
        });
        bEditarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Creando cliente...");
                new FormularioEditarTarifa(controlador);
            }
        });
        bBorrarCliente.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Borrando cliente...");
                if (dniCliente.getText().length() > 0) {
                    try {

                        controlador.eliminarCliente(dniCliente.getText());
                        dniCliente.setText("");

                    } catch (ClientNotFound clientNotFound) {
                        clientNotFound.printStackTrace();
                    }
                } else System.out.printf("Dni no introducido");
            }

        });
        bSave.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Guardando Datos...");
                try {
                    controlador.guardarDatos();
                //    actualizarTabla(tabla,datos);

                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        //Añadimos los elementos
        Container contenedor = new Container();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JPanel panelOption = new JPanel();

        panelOption.add(bAñadirCliente);
        panelOption.add(panelBorrar);
        panelOption.add(bEditarCliente);
        panelOption.add(bSave);
        contenedor.add(panelOption);
        contenedor.add(panel);
        add(contenedor);

    }
    public void actualizarTabla(JTable tabla,Vector datos){
        TableModelEvent evento= new TableModelEvent((TableModel) datos);
        tabla.tableChanged(evento);
    }

}
