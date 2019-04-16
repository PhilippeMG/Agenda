
import agenda.*;
import agenda.clientes.Cliente;
import agenda.clientes.CrearCliente;
import agenda.clientes.FabricarCliente;
import agenda.tarifa.CrearTarifa;
import agenda.tarifa.FabricarTarifa;
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
        FabricarCliente creador = new CrearCliente();
        FabricarTarifa creadorTarifa=new CrearTarifa();
        cliente = creador.getClienteEmpresa("Marcos", "0001", direccion1, "al375909@uji.es",  creadorTarifa.getTarifaBasica(1));
    }

    @AfterClass


    public static void finish() {
        cliente = null;
    }

    @Test
    public void cambiarTarifa() {
        assertEquals(cliente.getTarifa().getPrecio(), 1.0, 0);

    }

    @Test
    public void obtenerInformación() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        assertEquals(cliente.getNombre(), "Marcos");
        assertEquals(cliente.getDireccion().toString(), direccion1.toString());
        assertEquals(cliente.getNif(), 1,0001);
        assertEquals(cliente.getCorreo(), "al375909@uji.es");


    }

}