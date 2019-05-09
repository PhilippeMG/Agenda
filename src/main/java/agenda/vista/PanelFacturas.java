package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Modelo;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.excepciones.FacturaNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Vector;

public class PanelFacturas extends JPanel {
    JButton bListarFacturas = new JButton("Listar facturas de un DNI");
    JButton bEmitirFacturas = new JButton("Emitir factura");
    JButton bBuscarFactura = new JButton("Buscar factura");
    JButton bGuardar = new JButton("Guardar");

    Controlador controlador;
    Modelo modelo;
    JTextArea areaDatos = new JTextArea(20, 10);
    JButton bListarEntre2 = new JButton("Listar entre dos Fechas");
    JTextField dniCliente;
    JTextField codFact;

    JFrame vista;

    public PanelFacturas(Controlador controlador, Modelo modelo, JFrame vista) {
        this.controlador = controlador;
        this.modelo = modelo;
        this.vista = vista;
        dniCliente = new JTextField(10);
        codFact = new JTextField(4);


        JPanel panel = new JPanel();
        panel.add(new JLabel("DNI del cliente: "));
        panel.add(dniCliente);
        panel.add(new JLabel("Codigo factura"));
        panel.add(codFact);
        panel.add(bListarFacturas);
        panel.add(bEmitirFacturas);
        panel.add(bBuscarFactura);
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

        bListarFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.out.println("Boton Listar  Facturas pulsado ");
                if (!dniIsEmpty()) {
                    try {
                        Vector datos = controlador.getFacturasCliente(dniCliente.getText());
                        rellenarInformacionFacturas(datos);
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
        bBuscarFactura.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!codFacturaIsEmpty()) {

                    try {
                        Vector datos = controlador.devolverFactura(convertirAInt(codFact));
                        new FormularioBuscarFactura(datos);

                    } catch (FacturaNotFound facturaNotFound) {
                        new PopUp("La factura no existe.", vista, true);
                    }

                } else {
                    new PopUp("El codigo factura esta vacio.", vista, true);

                }

            }
        });

        bEmitirFacturas.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new FormularioEmitirFactura(controlador);

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
    }

    public boolean dniIsEmpty() {
        return (dniCliente.getText().length() <= 0);
    }

    public boolean codFacturaIsEmpty() {
        return (codFact.getText().length() <= 0);
    }

    public void rellenarInformacionFacturas(Vector datos) {
        areaDatos.setText("");
        areaDatos.append("Codigo\t Tarifa\tFecha Inicio\t\t\tFecha Final\t\t\tImporte\n");

        for (int i = 0; i < datos.size(); i++) {
            Vector dades = (Vector) datos.get(i);
            System.out.println(dades.toString());
            areaDatos.append(dades.get(0) + "\t" + dades.get(1) + "\t" + dades.get(2) + "\t\t" + dades.get(3) + "\t\t" + dades.get(4) + "\n");
        }

    }
    public int convertirAInt(JTextField campo) {
        return Integer.valueOf(String.valueOf(campo.getText()));
    }

}
