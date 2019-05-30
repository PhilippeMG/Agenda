package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

public class FormularioCrearFactura extends Formulario {
    private JTextField diaIn = new JTextField(3);
    private JTextField mesIn = new JTextField(3);
    private JTextField anyIn = new JTextField(3);
    private JTextField diaFi = new JTextField(3);
    private JTextField mesFi = new JTextField(3);
    private JTextField anyFi = new JTextField(3);
    private JTextField dni = new JTextField(5);
    private Controlador controlador;
    private JButton bEmitir = new JButton("Emitir factura");
    private String nif;
    private JFrame formulario;

    public FormularioCrearFactura(Controlador controlador, String text) {
        this.controlador = controlador;
        dni.setText(text);
        dni.setEditable(false);
        this.nif = text;
        JPanel fechaIn = new JPanel();
        fechaIn.add(new JLabel("Dia"));
        fechaIn.add(diaIn);
        fechaIn.add(new JLabel("Mes"));
        fechaIn.add(mesIn);
        fechaIn.add(new JLabel("Año"));

        fechaIn.add(anyIn);


        JPanel fechaFi = new JPanel();

        fechaFi.add(new JLabel("Dia"));
        fechaFi.add(diaFi);
        fechaFi.add(new JLabel("Mes"));
        fechaFi.add(mesFi);
        fechaFi.add(new JLabel("Año"));
        fechaFi.add(anyFi);

        JPanel camposDNI = new JPanel();
        camposDNI.add(new JLabel("DNI"));
        camposDNI.add(dni);

        formulario = new JFrame("Emitir factura");//Creamos el JFrameJs
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/factura.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        formulario.setLayout(new GridLayout(4, 1));
        Container contenedor = formulario.getContentPane();

        contenedor.add(new JLabel("DNI"));
        contenedor.add(dni);
        contenedor.add(new JLabel("Fecha inicial"));
        contenedor.add(fechaIn);
        contenedor.add(new JLabel("Fecha final"));
        contenedor.add(fechaFi);
        contenedor.add(bEmitir);
        formulario.pack();
        formulario.setVisible(true);

        bEmitir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emitirFactura();

            }
        });
    }

    private void emitirFactura() {
        if (!camposVacios()) {
            int[] inicio = new int[3];
            inicio[0] = convertirAInt(diaIn);
            inicio[1] = convertirAInt(mesIn);
            inicio[2] = convertirAInt(anyIn);
            int[] fin = new int[3];
            fin[0] = convertirAInt(diaFi);
            fin[1] = convertirAInt(mesFi);
            fin[2] = convertirAInt(anyFi);
            try {
                controlador.emitirFacturaCliente(nif, inicio, fin);

            } catch (ClientNotFound clientNotFound) {
                new PopUp("No existe el cliente.", formulario, true);
            }
        } else {
            new PopUp("Hay campos vacios.", formulario, true);

        }
    }


    public boolean camposVacios() {
        return (tamanyCampo(diaIn) <= 0 && tamanyCampo(diaFi) <= 0 && tamanyCampo(mesIn) <= 0 && tamanyCampo(mesFi) <= 0 && tamanyCampo(anyIn) <= 0 && tamanyCampo(anyFi) <= 0);
    }


}

