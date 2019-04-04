
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
//import agenda.excepciones.ClientNotFound;
//import com.sun.security.ntlm.Client;
import agenda.tarifa.TarifaBasica;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;

import static org.junit.Assert.*;

public class FacturaTest {
    private static Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
    private static Cliente cliente = new Empresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaBasica(1));
    static LocalTime hora= LocalTime.now();
    static LocalDate fecha = LocalDate.of(2014, 3, 2);
   static LocalDateTime data= LocalDateTime.of(fecha,hora);

    static LocalDate fecha2 = LocalDate.of(2019, 3, 1);
    static LocalDateTime data2= LocalDateTime.of(fecha2,hora);
    private static Llamada llamada3 = new Llamada(654078311, 0.9,data2);
    private static Factura factura = new Factura(cliente,data,data2);


    @BeforeClass
    public static void init() {
        cliente.insertarLlamada(llamada3);
    }

    @Test
    public void getImporte() {
        assertEquals(0.9, factura.importe(cliente, data, data2), 0);
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