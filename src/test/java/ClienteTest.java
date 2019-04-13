
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.CrearCliente;
import agenda.clientes.FabricarCliente;
import agenda.tarifa.OfertaBasica;
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
        FabricarCliente creador=new CrearCliente();
        cliente = creador.getCLienteEmpresa("Marcos", "0001", direccion1, "al375909@uji.es", new OfertaBasica(1));
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