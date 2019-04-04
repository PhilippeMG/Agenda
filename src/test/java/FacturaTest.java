
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
//import agenda.excepciones.ClientNotFound;
//import com.sun.security.ntlm.Client;
import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaEspecial;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class FacturaTest {
    private static Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
    private static Cliente cliente = new Empresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaEspecial(1));
    private static Llamada llamada3 = new Llamada(654078311, 0.9, LocalDate.of(2017, Month.MARCH, 1));
    private static Factura factura = new Factura(cliente, LocalDate.of(2017, Month.MARCH, 1), LocalDate.of(2019, Month.MARCH, 3));


    @BeforeClass
    public static void init() {
        cliente.insertarLlamada(llamada3);
    }

    @Test
    public void getImporte() {
        assertEquals(0.9, factura.importe(cliente, LocalDate.of(2017, Month.MARCH, 1), LocalDate.of(2019, Month.MARCH, 3)), 0);
    }
    /*
    @Test
    public void obtenerFactura() {
        try{

            fail("No debe llegar");

        }catch (ClientNotFound e){
            //System.out.println("Salto la excepcion");
        }
    }
*/
}