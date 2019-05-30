package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

public class FormularioBuscarFactura {
    private JTextField cod = new JTextField(10);
    private JTextField fIn = new JTextField(30);
    private JTextField fFin = new JTextField(30);
    private JTextField preu = new JTextField(10);
    private JTextField tarifa = new JTextField(10);
    private Controlador controlador;
    private Vector dades;

    public FormularioBuscarFactura(Vector dades) {
        this.dades = dades;
        JFrame formulario = new JFrame("Factura encontrada");
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/factura.png");
        formulario.setIconImage(icono);
        //System.out.println(dades.toString());
        formulario.setLayout(new GridLayout(5, 1));
        Container contenedor = formulario.getContentPane();
        contenedor.add(new JLabel("Codigo factura"));
        contenedor.add(cod);
        contenedor.add(new JLabel("Tarifa"));
        contenedor.add(tarifa);
        tarifa.setEditable(false);
        tarifa.setText(dades.get(1).toString());
        cod.setEditable(false);
        cod.setText(dades.get(0).toString());
        contenedor.add(new JLabel("Fecha inicial"));
        contenedor.add(fIn);
        fIn.setEditable(false);
        fIn.setText(dades.get(2).toString());

        contenedor.add(new JLabel("Fecha final"));
        contenedor.add(fFin);
        fFin.setEditable(false);
        fFin.setText(dades.get(3).toString());
        contenedor.add(new JLabel("Precio"));
        contenedor.add(preu);
        preu.setEditable(false);
        preu.setText(dades.get(4).toString());
        formulario.pack();
        formulario.setVisible(true);
    }

}
