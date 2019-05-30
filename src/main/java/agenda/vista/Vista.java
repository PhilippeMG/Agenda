package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.Direccion;
import agenda.modelo.Llamada;
import agenda.modelo.Modelo;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.excepciones.ClientNotFound;
import agenda.modelo.tarifa.CrearTarifa;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Vista implements InterfaceVista {
    //Atributos
    private Controlador controlador;
    private Modelo modelo;
    private JFrame ventana = new JFrame("Agenda");//Creamos el JFrame
    private PanelClientes vistaClientes;
    private PanelFacturas vistaFacturas;
    private PanelLlamadas vistaLlamadas;

    public void setControlador(Controlador gestorAgenda) {
        this.controlador = gestorAgenda;
    }

    public void run() {
        controlador.cargarDatos();

        //Modificamos el icono de la ventana
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/icono.png"); //Creamos una IMAGE
        ventana.setIconImage(icono); //Añadimos la IMAGE creada
        JTabbedPane pestanyas = new JTabbedPane();
        vistaClientes = new PanelClientes(controlador, modelo, ventana);
        vistaFacturas = new PanelFacturas(controlador, modelo, ventana);
        vistaLlamadas = new PanelLlamadas(controlador, modelo, ventana);
        pestanyas.add("Clentes", vistaClientes);

        pestanyas.add("Facturas", vistaFacturas);
        pestanyas.add("Llamadas", vistaLlamadas);

        ventana.add(pestanyas);
        ventana.pack();

        // ventana.setSize(800,500);//Definimos el tamaño
        ventana.setVisible(true);// hacemos la ventsana visibel

        ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    public void actualizar() {

        vistaClientes.actualizarClientes();
        vistaLlamadas.actualizarLlamadas();
        vistaFacturas.actualizarFacturas();
    }


    public void setModelo(Modelo modelo) {
        this.modelo = modelo;
    }


}
