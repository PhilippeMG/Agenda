package agenda.vista;

import agenda.controlador.GestionarAgenda;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class PanelClientes  extends JPanel{
    //JButton button = new JButton("Añadir Cliente");

    public PanelClientes() {
      // JButton button = new JButton("Añadir Cliente");
        JButton bAñadirCliente = new JButton("Añadir Cliente");
        JButton bSave = new JButton("Guardar");

        JButton bBorrarCliente = new JButton("Borrar Cliente ");
        JLabel borrar=new JLabel("DNI del cliente: ");
        JTextField dniCliente = new JTextField(10);
        JButton bEditarCliente = new JButton("Editar Tarifa ");

        JPanel panelBorrar= new JPanel();
        panelBorrar.add(borrar);
        panelBorrar.add(dniCliente);
        panelBorrar.add(bBorrarCliente);



        bAñadirCliente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creamdo cliente...");
                new FormularioCliente();
            }
        });
        bEditarCliente.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.out.println("Creando cliente...");
                new FormularioEditarTarifa();
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
        add(bEditarCliente);
        add(bSave);
    }
}
