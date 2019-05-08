package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class FormularioListarEntre2Fechas {
    Controlador controlador;
    JTextArea areaDatos =new JTextArea(20,10);
    String opcionListar="";
    public FormularioListarEntre2Fechas(Controlador controlador) {
        this.controlador = controlador;
        JFrame formulario = new JFrame("Listar Entre Dos Fechas");//Creamos el JFrameJs
        JRadioButton rClientes=new JRadioButton("Clientes");
        JRadioButton rLlamadas=new JRadioButton("Llamadas");
        JRadioButton rFacturas=new JRadioButton("Facturas");
        JLabel info = new JLabel("Listar por :");
        JLabel lDNI= new JLabel("DNI:");
        JButton listar= new JButton("Listar");
        JLabel diaIn = new JLabel("Fecha inicio :");
        JLabel mesIn = new JLabel("Fecha inicio :");
        JLabel anyIn = new JLabel("Fecha inicio :");
        JPanel fechaIn=new JPanel();
        fechaIn.add(diaIn);
        fechaIn.add(mesIn);

        JTextField tFechaIn = new JTextField(10);

        JLabel fechaFin = new JLabel("Fecha fin :");
        JTextField tFechaFin = new JTextField(10);

        JTextField dni = new JTextField(7);
        JScrollPane panel = new JScrollPane(areaDatos);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ButtonGroup opciones= new ButtonGroup();
        opciones.add(rClientes);
        opciones.add(rLlamadas);
        opciones.add(rFacturas);
        JPanel contenedor= new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JPanel options= new JPanel();
        JPanel entradas= new JPanel();

        options.add(info);
        options.add(rClientes);
        options.add(rFacturas);
        options.add(rLlamadas);

        entradas.add(lDNI);
        entradas.add(dni);
        entradas.add(fechaIn);
        entradas.add(tFechaIn);
        entradas.add(fechaFin);
        entradas.add(tFechaFin);
        entradas.add(listar);

        dni.setEditable(false); //Con esto deshabilitamos la edicion del campo
        areaDatos.setEditable(false);

        contenedor.add(options);
        contenedor.add(entradas);

        contenedor.add(panel);
        formulario.add(contenedor);
        formulario.pack();
        //   formulario.setSize(300, 150);//Definimos el tamaño

        formulario.setVisible(true);// hacemos la ventsana visibel

        rClientes.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Clientes pulsado");
                opcionListar="Clientes";
            }
        });

        rLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Llamadas pulsado.");
                opcionListar="Llamadas";
            }
        });

        rFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Facturas pulsado.");
                opcionListar="Facturas";
            }
        });


    }



}
