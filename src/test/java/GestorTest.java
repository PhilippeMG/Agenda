import agenda.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.Month;

import static org.junit.Assert.*;

public class GestorTest {
    private static Cliente cliente;
    private static Factura factura;
    private static Gestor gestor;


    @BeforeClass

    public static void init() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        cliente = new Empresa("Marcos", "0001", direccion1, "al375909@uji.es", new Tarifa(1));
        Llamada llamada3 = new Llamada(654078311,0.9, LocalDate.of(2017, Month.MARCH, 1));
        cliente.insertarLlamada(llamada3);
        Factura factura = new Factura(cliente,LocalDate.of(2017,Month.MARCH, 1),LocalDate.of(2019,Month.MARCH, 3));
        gestor.insertarCliente(cliente);
    }

    @AfterClass
    public static void finish() {
        cliente = null;
        factura=null;
    }
    @Test
    public void insertarCliente() {

            try{
                gestor.insertarCliente(cliente);

                fail("No debe llegar");
            }catch (IllegalArgumentException e){
                //System.out.println("Salto la excepcion");
            }
    }



}