import agenda.modelo.Direccion;
import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.CrearCliente;
import agenda.modelo.clientes.FabricarCliente;
import agenda.modelo.clientes.Particular;
import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.FabricarTarifa;
import agenda.modelo.tarifa.TarifaBasica;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FactoriaClienteTest {
    public static Cliente cliente,cliente2;

    public static FabricarCliente creador=new CrearCliente();
    public static FabricarTarifa creaTarifas = new CrearTarifa();

    @BeforeClass
    public static void init() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        cliente = creador.getClienteEmpresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaBasica(15));
        cliente2 = creador.getClienteParticular("Philip", "0002", direccion1, "al375923@uji.es", creaTarifas.getTarifaBasica(15),"Pego");

    }

    public static void finish() {
        cliente = null;
        cliente2 = null;
        creador = null;
        creaTarifas = null;
    }

    @Test
    public void crearEmpresa() {
        assertEquals(cliente.getNombre(), "Marcos");
    }
    @Test
    public void crearEmpresa2() {
        assertEquals(cliente.getNombreCompleto(),"Marcos");
    }

    @Test
    public void crearParticular() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        Cliente client3 =new Particular("Philip", "0002", direccion1, "al375923@uji.es", creaTarifas.getTarifaBasica(15),"Pego");
        assertEquals(cliente2.getNombre(), client3.getNombre());
    }
    @Test
    public void crearParticular2() {
        assertEquals(cliente2.getNombreCompleto(),"Philip Pego");
    }
}
