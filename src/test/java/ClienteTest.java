
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
import agenda.tarifa.TarifaBasica;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;

import static junit.framework.TestCase.fail;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;

public class ClienteTest {
    private static Cliente cliente;

    @BeforeClass

    public static void init() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        cliente = new Empresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaBasica(1));
    }

    @AfterClass


    public static void finish() {
        cliente = null;
    }

    @Test
    public void cambiarTarifa() {
        assertEquals(cliente.getTarifa().getPrecio(), 1.0, 0);

    }

}