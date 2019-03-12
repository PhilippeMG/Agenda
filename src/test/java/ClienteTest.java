
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.Empresa;
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
        cliente = new Empresa("Marcos", "0001", direccion1, "al375909@uji.es", new Tarifa(1));
    }

    @AfterClass


    public static void finish() {
        cliente = null;
    }
    @Test
    public void cambiarTarifa() {
        assertEquals(cliente.getTipoTarifa(), 1);

    }


}