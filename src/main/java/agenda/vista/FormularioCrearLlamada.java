package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
                if(!camposVacios()) {
                    int[] inicio = new int[3];
                    inicio[0] = convertirAInt(diaIn);
                    inicio[1] = convertirAInt(mesIn);
                    inicio[2] = convertirAInt(anyIn);
                    try {
                        controlador.insertarLlamada(dni.getText(),inicio,convertirAInt(duracion),convertirAInt(numero));
                    } catch (ClientNotFound clientNotFound) {
                        new PopUp("El dni no existe",formulario,true);
                    }
                }else{
                    new PopUp("Hay campos vacios",formulario,true);
                }
            }
    });

    }
    public int tamanyCampo(JTextField campo) {
        return campo.getText().length();
    }

    public boolean camposVacios() {
        return (tamanyCampo(diaIn) <= 0 && tamanyCampo(dni) <= 0 && tamanyCampo(mesIn) <= 0 && tamanyCampo(numero) <= 0 && tamanyCampo(anyIn) <= 0 && tamanyCampo(duracion)<=0);
    }
    public int convertirAInt(JTextField campo) {
        return Integer.valueOf(String.valueOf(campo.getText()));
    }

}
