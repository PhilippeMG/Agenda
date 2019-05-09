package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Direccion;
import agenda.modelo.Llamada;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.tarifa.CrearTarifa;

import javax.swing.*;
import java.awt.*;

public class Vista implements InterfaceVista {
    //Atributos
    Controlador controlador;
    Modelo modelo;
    JFrame ventana=new JFrame("Agenda");//Creamos el JFrame

    public void setControlador(Controlador gestorAgenda) {
        this.controlador = gestorAgenda;
    }

    public void run() throws ClassNotFoundException {
        controlador.cargarDatos();
        CrearCliente creadorCliente= new CrearCliente();
        CrearTarifa creadorTarifa= new CrearTarifa();
        try {
            modelo.insertarCliente(creadorCliente.getClienteEmpresa("Mercadona", "1234", new Direccion(3, "Madrid", "Madrid"), "email", creadorTarifa.getTarifaBasica(9)));
            modelo.insertarLlamada("1234", new Llamada(65432176, 4, modelo.crearFecha(1, 1, 2010)));
            modelo.emitirFactura("1234",modelo.crearFecha(1,1,1),modelo.crearFecha(1,1,2020));
        }catch (Exception e){
            System.out.printf("Error");
        }
        //Modificamos el icono de la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png"); //Creamos una IMAGE
        ventana.setIconImage(icono); //Añadimos la IMAGE creada
        JTabbedPane pestanyas = new JTabbedPane();
        pestanyas.add("Clentes", new PanelClientes(controlador, modelo, ventana));

        pestanyas.add("Facturas", new PanelFacturas(this.controlador, this.modelo));
        pestanyas.add("Llamadas", new PanelLlamadas(this.controlador, this.modelo));

        ventana.add(pestanyas);
        ventana.pack();

        // ventana.setSize(800,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
