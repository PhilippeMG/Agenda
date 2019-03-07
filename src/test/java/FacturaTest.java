package agenda.test.java.java;

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

    @Test
    public void getImporte() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        Cliente cliente = new Cliente("Marcos", "0001", direccion1, "al375909@uji.es", 1);
        Llamada llamada3 = new Llamada(654078311,0.9, LocalDate.of(2017, Month.MARCH, 1));
        cliente.añadirLlamada(llamada3);
        Factura factura = new Factura(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3));

        assertEquals(0.9,factura.importe(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3)),0);
    }
}