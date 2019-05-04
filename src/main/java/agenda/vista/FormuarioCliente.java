package agenda.vista;

import agenda.controlador.GestionarCliente;
import agenda.modelo.excepciones.InvalidArguments;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormuarioCliente {
    JTextField nombre = new JTextField(20);
    JTextField dni = new JTextField(20);
    JTextField cp = new JTextField(20);
    JTextField provincia = new JTextField(20);
    JTextField poblacion = new JTextField(20);

    JTextField correo = new JTextField(20);
    JTextField apellido = new JTextField(20);
    JTextField tarifa = new JTextField(20);

    JButton bAñadir=new JButton("Añadir");
    public FormuarioCliente(){
        JFrame formulario=new JFrame("Añadir Cliente");//Creamos el JFrame
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/add-user.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        //Creamos una tabla de filas x columnas
        formulario.setLayout(new GridLayout(10,2));
        //nombre, nif, int CP, String provincia, String poblacion, correo, tarifa, apellidos
        Container contenedor = formulario.getContentPane();
       // contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));

        JPanel panel = new JPanel();//Este panel contiene las ofertas de Tarifa ChexBox.
        // Añadimos los elementos de rellenan por filas de izq a derecha
        panel.add(new Checkbox("Tardes a 5"));
        panel.add(new Checkbox("Domingos gratis"));

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

        contenedor.add(bAñadir);
        //ancho por altura
        formulario.setSize(600,400);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
        limpiarCampos();
        bAñadir.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
               try {
                   int CP= Integer.valueOf(String.valueOf(cp.getText()));
                   int preu= Integer.valueOf(String.valueOf(tarifa.getText()));

                   System.out.println("Añadiendo...");

                        new GestionarCliente().añadirCliente(nombre.getText(), dni.getText(), CP, provincia.getText(), poblacion.getText(), correo.getText(), apellido.getText(),preu);

                } catch (InvalidArguments invalidArguments) {
                   System.out.println("Error...");


                }
                limpiarCampos();
            }



        });


    }
    public void limpiarCampos(){
        nombre.setText("");
        dni.setText("");
        cp.setText("");
        provincia.setText("");
        poblacion.setText("");
        correo.setText("");
        apellido.setText(null);


    }

}
