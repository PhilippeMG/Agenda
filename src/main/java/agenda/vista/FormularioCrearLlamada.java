package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;
import java.awt.*;

public class FormularioCrearLlamada {

    JTextField diaIn = new JTextField(3);
    JTextField mesIn = new JTextField(3);
    JTextField anyIn = new JTextField(3);
    JTextField duracion = new JTextField(3);
    JTextField dni = new JTextField(5);

    JTextField numero = new JTextField(5);
    Controlador controlador;
    JButton bEmitir=new JButton("Crear llamada");

    public FormularioCrearLlamada(Controlador controlador) {
        this.controlador= controlador;
        JPanel fechaIn = new JPanel();
        //fechaIn.add(new JLabel("Fecha inicio: "));
        //fechaIn.add(new JLabel("Fecha inicial"));

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



        JFrame formulario = new JFrame("Crear llamada");//Creamos el JFrameJs
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-call.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        formulario.setLayout(new GridLayout(5, 2));
        Container contenedor = formulario.getContentPane();
        contenedor.add(new JLabel("DNI"));
        contenedor.add(dni);

        contenedor.add(new JLabel("Numero del telefono"));
        contenedor.add(numero);
        contenedor.add(new JLabel("Fecha inicial"));
        contenedor.add(fechaIn);
        contenedor.add(new JLabel("Duracion de la llamada"));
        contenedor.add(duracion);
        contenedor.add(bEmitir);
        formulario.pack();
        formulario.setVisible(true);


    }
}
