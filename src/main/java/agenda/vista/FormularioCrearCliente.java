package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.InvalidArguments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.LinkedList;

public class FormularioCrearCliente {
    JTextField nombre = new JTextField(20);
    JTextField dni = new JTextField(20);
    JTextField cp = new JTextField(20);
    JTextField provincia = new JTextField(20);
    JTextField poblacion = new JTextField(20);

    JTextField correo = new JTextField(20);
    JTextField apellido = new JTextField(20);
    JTextField tarifa = new JTextField(20);
    String ofertaDomingos=null;
    String ofertaTardes=null;
    LinkedList <String> ofertas= new LinkedList<String>();
    JButton bInsetar = new JButton("Añadir");
    JCheckBox tardes=new JCheckBox("Tardes a 5");
    JCheckBox domingos=new JCheckBox("Domingos gratis");
    JFrame formulario;
    Controlador controlador;

    public FormularioCrearCliente(Controlador controlador) {
        this.controlador = controlador;
        formulario = new JFrame("Añadir Cliente");//Creamos el JFrame

        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        //Creamos una tabla de filas x columnas
        formulario.setLayout(new GridLayout(10, 2));
        //nombre, nif, int CP, String provincia, String poblacion, correo, tarifa, apellidos
        Container contenedor = formulario.getContentPane();
        // contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();//Este panel contiene las ofertas de Tarifa ChexBox.
        // Añadimos los elementos de rellenan por filas de izq a derecha
        panel.add(tardes);
        panel.add(domingos);

        contenedor.add(new JLabel("Nombre:"));
        contenedor.add(nombre);
        contenedor.add(new JLabel("Apellido:"));
        contenedor.add(apellido);
        contenedor.add(new JLabel("DNI:"));
        contenedor.add(dni);
        contenedor.add(new JLabel("CP:"));
        contenedor.add(cp);
        contenedor.add(new JLabel("Provincia:"));
        contenedor.add(provincia);
        contenedor.add(new JLabel("Población:"));
        contenedor.add(poblacion);
        contenedor.add(new JLabel("Correo:"));
        contenedor.add(correo);
        contenedor.add(new JLabel("Tarifa:"));
        contenedor.add(tarifa);
        contenedor.add(new JLabel("Ofertas:"));
        contenedor.add(panel);

        contenedor.add(bInsetar);
        formulario.pack();
        formulario.setVisible(true);
        limpiarCampos();

        bInsetar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            insetarCliente();
            }
        });
        tardes.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()) {//Preguntamos al evento
                    case ItemEvent.SELECTED:
                        ofertaTardes="Tardes";
                        break;
                    case ItemEvent.DESELECTED:
                        ofertaTardes=null;

                        break;
                }
            }
        });

        domingos.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                switch(e.getStateChange()) {//Preguntamos al evento
                    case ItemEvent.SELECTED:
                        ofertaDomingos="Domingos";

                        break;
                    case ItemEvent.DESELECTED:
                        ofertaDomingos=null;

                        break;
                }
            }
        });

    }

    public void limpiarCampos() {
        nombre.setText("");
        dni.setText("");
        cp.setText("");
        provincia.setText("");
        poblacion.setText("");
        correo.setText("");
        apellido.setText(null);
        tarifa.setText("");
        ofertaDomingos=null;
        ofertaTardes=null;
        ofertas.clear();

    }
    private void insetarCliente(){
        if(nombre.getText().length()>0 && dni.getText().length()>0 && cp.getText().length()>0 && provincia.getText().length()>0 && poblacion.getText().length()>0 && correo.getText().length()>0 && tarifa.getText().length()>0){
            try {
                ofertas.add(ofertaDomingos);
                ofertas.add(ofertaTardes);
                int CP = Integer.valueOf(String.valueOf(cp.getText()));
                int preu = Integer.valueOf(String.valueOf(tarifa.getText()));

                System.out.println("Añadiendo...");

                controlador.insertarCliente(nombre.getText(), dni.getText(), CP, provincia.getText(), poblacion.getText(), correo.getText(), apellido.getText(), preu,ofertas);

            } catch (InvalidArguments invalidArguments) {
                new PopUp("DNI duplicado...",formulario,true);

            }
            limpiarCampos();
        }else{
            new PopUp("Hay campos vacios...",formulario,true);
        }
    }


}
