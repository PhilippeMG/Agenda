package agenda.vista;

import agenda.controlador.Controlador;
import agenda.modelo.excepciones.ClientNotFound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormularioListarEntre2Fechas extends Formulario {
    private Controlador controlador;
    private JTextArea areaDatos = new JTextArea(20, 10);
    private String opcionListar = "";
    private JTextField diaIn = new JTextField(3);
    private JTextField mesIn = new JTextField(3);
    private JTextField anyIn = new JTextField(3);
    private JTextField diaFi = new JTextField(3);
    private JTextField mesFi = new JTextField(3);
    private JTextField anyFi = new JTextField(3);
    private JTextField dni = new JTextField(7);

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
                opcionListar = "Clientes";
                dni.setEditable(false);
            }
        });

        rLlamadas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionListar = "Llamadas";
                dni.setEditable(true);
            }
        });

        rFacturas.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                opcionListar = "Facturas";
                dni.setEditable(true);
            }
        });

        listar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!camposVacios()) {
                    int[] inicio = new int[3];
                    inicio[0] = convertirAInt(diaIn);
                    inicio[1] = convertirAInt(mesIn);
                    inicio[2] = convertirAInt(anyIn);
                    String datos = "";
                    int[] fin = new int[3];
                    fin[0] = convertirAInt(diaFi);
                    fin[1] = convertirAInt(mesFi);
                    fin[2] = convertirAInt(anyFi);
                    if (opcionListar.equals("Clientes")) {
                        datos = controlador.opcionDevolverClientesEntreFechas(inicio, fin);
                        rellenarInformacion(datos);
                    }
                    if (opcionListar.equals("Llamadas")) {
                        try {
                            datos = controlador.opcionDevolverLlamadasEntreFechas(dni.getText(), inicio, fin);

                            rellenarInformacion(datos);
                        } catch (ClientNotFound clientNotFound) {
                            new PopUp("Cliente no encontrado", formulario, true);
                        }
                    }

                    if (opcionListar.equals("Facturas")) {
                        try {
                            datos = controlador.opcionDevolverFacturaEntreFechas(dni.getText(), inicio, fin);

                            rellenarInformacion(datos);
                        } catch (ClientNotFound clientNotFound) {
                            new PopUp("Cliente no encontrado", formulario, true);
                        }

                    }
                } else {
                    new PopUp("Hay campos vacios", formulario, true);
                }
            }
        });

    }


    public boolean camposVacios() {
        return (isEmpty(diaIn)&& isEmpty(diaFi)&& isEmpty(mesIn) && isEmpty(mesFi)&& isEmpty(anyIn)  && isEmpty(anyFi));
    }

    public void rellenarInformacion(String datos) {
        areaDatos.setText("");
        areaDatos.append(datos);


    }
}
