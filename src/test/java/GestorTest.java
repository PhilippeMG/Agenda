import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.CrearCliente;
import agenda.clientes.FabricarCliente;
import agenda.excepciones.ClientNotFound;
import agenda.excepciones.FacturaNotFound;
import agenda.excepciones.InvalidArguments;
import agenda.tarifa.TarifaBasica;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.*;

public class GestorTest {
    private static Cliente cliente;
    // private static Factura factura;
    private static Gestor gestor = new Gestor();
    private static  FabricarCliente creador;


    @BeforeClass
    public static void init() throws InvalidArguments {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
          creador=new CrearCliente();
         cliente = creador.getClienteEmpresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaBasica(1));

        LocalTime hora= LocalTime.now();
        LocalDate fecha = LocalDate.of(2017, 4, 1);
        LocalDateTime data= LocalDateTime.of(fecha,hora);

        Llamada llamada3 = new Llamada(654078311, 0.9,data);
        cliente.insertarLlamada(llamada3);
        //Factura factura = new Factura(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3));
        gestor.insertarCliente(cliente);
    }

    @AfterClass
    public static void finish() {
        cliente = null;
        //factura=null;
    }

    @Test
    public void insertarCliente() {
        try {
            gestor.insertarCliente(cliente);
            fail("No debe llegar");

        } catch (InvalidArguments e) {
            //System.out.println("Salto la excepcion");
        }
    }

    @Test
    public void obtenerFactura() {
        try {
            gestor.mostrarFacturas("058393");
            fail("No debe llegar");

        } catch (ClientNotFound e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void obtenerFacturaCodigo() {
        try {
            gestor.mostrarFactura(19392921);
            fail("No debe llegar");

        } catch (FacturaNotFound e) {
            System.out.println(e.getMessage());
        }
    }


}