package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.Vector;

public class FormularioListarEntre2Fechas {
    Controlador controlador;
    JTextArea areaDatos = new JTextArea(20, 10);
    String opcionListar = "";
    JTextField diaIn = new JTextField(3);
    JTextField mesIn = new JTextField(3);
    JTextField anyIn = new JTextField(3);
    JTextField diaFi = new JTextField(3);
    JTextField mesFi = new JTextField(3);
    JTextField anyFi = new JTextField(3);
    JTextField dni = new JTextField(7);
    public FormularioListarEntre2Fechas(Controlador controlador) {
        this.controlador = controlador;
        JFrame formulario = new JFrame("Listar Entre Dos Fechas");//Creamos el JFrameJs
        Image icono = Toolkit.getDefaultToolkit().getImage("src/media/info.png"); //Creamos una IMAGE
        formulario.setIconImage(icono); //Añadimos la IMAGE creada

        JRadioButton rClientes = new JRadioButton("Clientes");
        JRadioButton rLlamadas = new JRadioButton("Llamadas");
        JRadioButton rFacturas = new JRadioButton("Facturas");
        JLabel info = new JLabel("Listar por :");
        JLabel lDNI = new JLabel("DNI:");
        JButton listar = new JButton("Listar");


        JPanel fechaIn = new JPanel();
        fechaIn.add(new JLabel("Fecha inicio: "));
        fechaIn.add(new JLabel("Dia"));
        fechaIn.add(diaIn);
        fechaIn.add(new JLabel("Mes"));
        fechaIn.add(mesIn);
        fechaIn.add(new JLabel("Año"));

        fechaIn.add(anyIn);


        JPanel fechaFi = new JPanel();
        fechaFi.add(new JLabel("Fecha final: "));

        fechaFi.add(new JLabel("Dia"));
        fechaFi.add(diaFi);
        fechaFi.add(new JLabel("Mes"));
        fechaFi.add(mesFi);
        fechaFi.add(new JLabel("Año"));
        fechaFi.add(anyFi);



        JScrollPane panel = new JScrollPane(areaDatos);
        panel.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        panel.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        ButtonGroup opciones = new ButtonGroup();
        opciones.add(rClientes);
        opciones.add(rLlamadas);
        opciones.add(rFacturas);
        JPanel contenedor = new JPanel();
        contenedor.setLayout(new BoxLayout(contenedor, BoxLayout.PAGE_AXIS));
        JPanel options = new JPanel();
        JPanel entradas = new JPanel();

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
                opcionListar = "Clientes";
                dni.setEditable(false);
            }
        });

        rLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Llamadas pulsado.");
                opcionListar = "Llamadas";
                dni.setEditable(true);
            }
        });

        rFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("Facturas pulsado.");
                opcionListar = "Facturas";
                dni.setEditable(true);
            }
        });

        listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!camposVacios()) {
                    int[] inicio=new int[3];
                    inicio[0]=convertirAInt(diaIn);
                    inicio[1]=convertirAInt(mesIn);
                    inicio[2]=convertirAInt(anyIn);
                    Vector datos= new Vector();
                    int[] fin=new int[3];
                    fin[0]=convertirAInt(diaFi);
                    fin[1]=convertirAInt(mesFi);
                    fin[2]=convertirAInt(anyFi);
                    if(opcionListar.equals("Clientes")){
                    datos= controlador.opcionDevolverClientesEntreFechas(inicio,fin);
                    rellenarInformacionClientes(datos);
                    }
                    if(opcionListar.equals("Llamadas")){
                        try {
                            datos = controlador.opcionDevolverLlamadasEntreFechas(dni.getText(), inicio, fin);

                            rellenarInformacionLlamadas(datos);
                        }catch (ClientNotFound clientNotFound){
                            new PopUp("Cliente no encontrado",formulario,true);
                        }
                    }

                    if(opcionListar.equals("Facturas")){
                          try {
                            datos = controlador.opcionDevolverFacturaEntreFechas(dni.getText(), inicio, fin);

                            rellenarInformacionFacturas(datos);
                        }catch (ClientNotFound clientNotFound){
                            new PopUp("Cliente no encontrado",formulario,true);
                        }

                    }
                } else {
                    new PopUp("Hay campos vacios",formulario,true);
                }
            }
        });

    }

    public int convertirAInt(JTextField campo) {
        return Integer.valueOf(String.valueOf(campo.getText()));
    }

    public int tamanyCampo(JTextField campo) {
        return campo.getText().length();
    }

    public boolean camposVacios() {
        return (tamanyCampo(diaIn) <= 0 && tamanyCampo(diaFi) <= 0 && tamanyCampo(mesIn) <= 0 && tamanyCampo(mesFi) <= 0 && tamanyCampo(anyIn) <= 0 && tamanyCampo(anyFi) <= 0);
    }
    public void rellenarInformacionLlamadas(Vector datos){
        areaDatos.setText("");
        areaDatos.append("Num destino\t Duración\t Fecha de la llamada\n");

        for(int i=0; i<datos.size();i++){
            Vector dades=(Vector) datos.get(i);
            areaDatos.append(dades.get(0)+"\t"+dades.get(1)+"\t"+dades.get(2)+"\n");
        }

    }
    public void rellenarInformacionClientes(Vector datos){
        areaDatos.setText("");
        areaDatos.append("Codigo\t Tarifa\tFecha Inicio\tFecha Final\tImporte\n");

        for(int i=0; i<datos.size();i++){
            Vector dades=(Vector) datos.get(i);
            areaDatos.append(dades.get(0)+"\t"+dades.get(1)+"\t"+dades.get(2)+"\t"+dades.get(3)+"\t"+dades.get(4)+"\t"+dades.get(5)+"\n");
        }

    }
    public void rellenarInformacionFacturas(Vector datos){
        areaDatos.setText("");
        areaDatos.append("Codigo\t Tarifa\tFecha Inicio\t\t\tFecha Final\t\t\tImporte\n");

        for(int i=0; i<datos.size();i++){
            Vector dades=(Vector) datos.get(i);
            System.out.println(dades.toString());
            areaDatos.append(dades.get(0)+"\t"+dades.get(1)+"\t"+dades.get(2)+"\t\t"+dades.get(3)+"\t\t"+dades.get(4)+"\n");
        }

    }
}
