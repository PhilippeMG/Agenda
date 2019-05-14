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
    PanelClientes vistaClientes;
    PanelFacturas vistaFacturas;
    PanelLlamadas vistaLlamadas;
    public void setControlador(Controlador gestorAgenda) {
        this.controlador = gestorAgenda;
    }

    public void run() throws ClassNotFoundException {
        controlador.cargarDatos();

        //Modificamos el icono de la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png"); //Creamos una IMAGE
        ventana.setIconImage(icono); //Añadimos la IMAGE creada
        JTabbedPane pestanyas = new JTabbedPane();
         vistaClientes=new PanelClientes(controlador, modelo, ventana);
         vistaFacturas=new PanelFacturas(controlador, modelo,ventana);
         vistaLlamadas=new PanelLlamadas(controlador,modelo,ventana);
        pestanyas.add("Clentes", vistaClientes);

        pestanyas.add("Facturas", vistaFacturas);
        pestanyas.add("Llamadas", vistaLlamadas);

        ventana.add(pestanyas);
        ventana.pack();

        // ventana.setSize(800,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

   public void actualizar(){
        vistaClientes.actualizarClientes();
   }


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }
}
