package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;

public class FormularioListarEntre2Fechas {
    Controlador controlador;
    JTextArea areaDatos =new JTextArea(20,10);

    public FormularioListarEntre2Fechas(Controlador controlador) {
        this.controlador = controlador;
        JFrame formulario = new JFrame("Listar Entre Dos Fechas");//Creamos el JFrameJs
        JRadioButton rClientes=new JRadioButton("Clientes");
        JRadioButton rLlamadas=new JRadioButton("Llamadas");
        JRadioButton rFacturas=new JRadioButton("Facturas");
        JLabel info = new JLabel("Listar por :");
        JLabel lDNI= new JLabel("DNI:");

        JLabel fechaIn = new JLabel("Fecha inicio :");
        JTextField tFechaIn = new JTextField(10);

        JLabel fechaFin = new JLabel("Fecha fin :");
        JTextField tFechaFin = new JTextField(10);

        JTextField dni = new JTextField(10);
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
        dni.setEnabled(false); //Con esto deshabilitamos la edicion del campo
        areaDatos.setEnabled(false);



        contenedor.add(options);
        contenedor.add(entradas);

        contenedor.add(panel);
        formulario.add(contenedor);
        formulario.pack();
        //   formulario.setSize(300, 150);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
    }


}
