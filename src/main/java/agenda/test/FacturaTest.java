package agenda.test;

import agenda.Cliente;
import agenda.Direccion;
import agenda.Factura;
import agenda.Llamada;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class FacturaTest {
    private static Cliente cliente;
    private static Factura factura;


    @BeforeClass

    public static void init() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        cliente = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
        Llamada llamada3 = new Llamada(654078311,0.9, LocalDate.of(2017, Month.MARCH, 1));
        cliente.añadirLlamada(llamada3);
        Factura factura = new Factura(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3));

    }

    @AfterClass
    public static void finish() {
        cliente = null;
        factura=null;
    }



    @Test
    public void getImporte() {

        System.out.println(factura.toString());
        assertEquals(0.9,factura.importe(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3)),0);
    }
}