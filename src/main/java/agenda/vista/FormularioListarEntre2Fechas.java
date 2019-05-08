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
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/info.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        JRadioButton rClientes=new JRadioButton("Clientes");
        JRadioButton rLlamadas=new JRadioButton("Llamadas");
        JRadioButton rFacturas=new JRadioButton("Facturas");
        JLabel info = new JLabel("Listar por :");
        JLabel lDNI= new JLabel("DNI:");
        JButton listar= new JButton("Listar");

        JTextField diaIn = new JTextField(3);
        JTextField mesIn = new JTextField(3);
        JTextField anyIn = new JTextField(3);

        JPanel fechaIn=new JPanel();
        fechaIn.add(new JLabel("Fecha inicio: "));
        fechaIn.add(new JLabel("Dia"));
        fechaIn.add(diaIn);
        fechaIn.add(new JLabel("Mes"));
        fechaIn.add(mesIn);
        fechaIn.add(new JLabel("Año"));

        fechaIn.add(anyIn);

        JTextField diaFi = new JTextField(3);
        JTextField mesFi = new JTextField(3);
        JTextField anyFi = new JTextField(3);

        JPanel fechaFi=new JPanel();
        fechaFi.add(new JLabel("Fecha final: "));

        fechaFi.add(new JLabel("Dia"));
        fechaFi.add(diaFi);
        fechaFi.add(new JLabel("Mes"));
        fechaFi.add(mesFi);
        fechaFi.add(new JLabel("Año"));
        fechaFi.add(anyFi);



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
        entradas.add(fechaFi);
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

        listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }



}
