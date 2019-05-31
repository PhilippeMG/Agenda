package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioCrearLlamada extends   Formulario{

    private JTextField diaIn = new JTextField(3);
    private JTextField mesIn = new JTextField(3);
    private JTextField anyIn = new JTextField(3);
    private JTextField duracion = new JTextField(3);
    private   JTextField dni = new JTextField(5);

    private   JTextField numero = new JTextField(5);
    private   Controlador controlador;
    private   JButton bEmitir = new JButton("Crear llamada");
    private   String nif;
    private   JFrame formulario;

    public FormularioCrearLlamada(Controlador controlador, String text) {
        this.controlador = controlador;
        JPanel fechaIn = new JPanel();
        this.nif = text;

        dni.setText(text);
        dni.setEditable(false);
        fechaIn.add(new JLabel("Dia"));
        fechaIn.add(diaIn);
        fechaIn.add(new JLabel("Mes"));
        fechaIn.add(mesIn);
        fechaIn.add(new JLabel("Año"));

        fechaIn.add(anyIn);


        JPanel campoDuracion = new JPanel();
        // fechaFi.add(new JLabel("Fecha final: "));
        campoDuracion.add(new JLabel("Duracion de la llamada"));
        campoDuracion.add(duracion);


        formulario = new JFrame("Crear llamada");//Creamos el JFrameJs
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/mobile-phone.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        formulario.setLayout(new GridLayout(5, 2));
        Container contenedor = formulario.getContentPane();
        contenedor.add(new JLabel("DNI"));
        contenedor.add(dni);

        contenedor.add(new JLabel("Numero del telefono"));
        contenedor.add(numero);
        contenedor.add(new JLabel("Fecha de la llamda"));
        contenedor.add(fechaIn);
        contenedor.add(new JLabel("Duracion de la llamada"));
        contenedor.add(duracion);
        contenedor.add(bEmitir);
        formulario.pack();
        formulario.setVisible(true);

        bEmitir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                emitirLlamada();
            }
        });

    }


    private boolean camposVacios() {
        return (isEmpty(diaIn) || isEmpty(dni) || isEmpty(mesIn)  || isEmpty(numero) || isEmpty(anyIn)  || isEmpty(duracion) );
    }


    private void emitirLlamada() {
        if (!camposVacios()) {
            int[] inicio = new int[3];
            inicio[0] = convertirAInt(diaIn);
            inicio[1] = convertirAInt(mesIn);
            inicio[2] = convertirAInt(anyIn);
            try {
                controlador.insertarLlamada(nif, inicio, convertirAInt(duracion), convertirAInt(numero));
            } catch (ClientNotFound clientNotFound) {
                new PopUp("El dni no existe", formulario, true);
            }
        } else {
            new PopUp("Hay campos vacios", formulario, true);
        }

    }
}
