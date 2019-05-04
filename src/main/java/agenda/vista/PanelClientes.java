package agenda.vista;

import agenda.controlador.GestionarAgenda;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.sql.SQLOutput;

public class PanelClientes  extends JPanel{
    //JButton button = new JButton("Añadir Cliente");

    public PanelClientes() {
      // JButton button = new JButton("Añadir Cliente");
        JButton bAñadirCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");

        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JLabel borrar=new JLabel("DNI del cliente para borrar");
        JTextField dniCliente = new JTextField(20);

        JPanel panelBorrar= new JPanel();
        panelBorrar.add(borrar);
        panelBorrar.add(dniCliente);
        panelBorrar.add(bBorrarCliente);



        bAñadirCliente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creamdo cliente...");
                new FormuarioCliente();
            }
        });
        bBorrarCliente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Borrando cliente...");
                try {
                    new GestionarAgenda().eliminarCliente(dniCliente.getText());
                    dniCliente.setText("");
                } catch (ClientNotFound clientNotFound) {
                    clientNotFound.printStackTrace();
                }
            }
        });
        bSave.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Guardando Datos...");
                try {
                    new GestionarAgenda().guardarDatos();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

            }
        });
        //Añadimos los elementos
        add(bAñadirCliente);
        add(panelBorrar);
        add(bSave);
    }
}
