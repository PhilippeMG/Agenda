package agenda.vista;

import agenda.controlador.Controlador;

import javax.swing.*;

public class FormularioListarEntre2Fechas {
    Controlador controlador;
    public FormularioListarEntre2Fechas(Controlador controlador) {
        this.controlador = controlador;
        JFrame formulario = new JFrame("Listar Entre Dos Fechas");//Creamos el JFrameJs
        JRadioButton rClientes=new JRadioButton("Clientes");
        JRadioButton rLlamadas=new JRadioButton("Llamadas");
        JRadioButton rFacturas=new JRadioButton("Facturas");
        JLabel info = new JLabel("Listar por :");
        ButtonGroup opciones= new ButtonGroup();
        opciones.add(rClientes);
        opciones.add(rLlamadas);
        opciones.add(rFacturas);
        JPanel contenedor= new JPanel();
        contenedor.add(info);
        contenedor.add(rClientes);
        contenedor.add(rFacturas);
        contenedor.add(rLlamadas);
        formulario.add(contenedor);
        formulario.pack();
        //   formulario.setSize(300, 150);//Definimos el tamaño
        formulario.setVisible(true);// hacemos la ventsana visibel
    }


}
