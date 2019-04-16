import agenda.Direccion;
import agenda.clientes.Cliente;
import agenda.clientes.CrearCliente;
import agenda.clientes.FabricarCliente;
import agenda.clientes.Particular;
import agenda.tarifa.CrearTarifa;
import agenda.tarifa.FabricarTarifa;
import agenda.tarifa.TarifaBasica;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class FactoriaClienteTest {
    public static Cliente cliente,cliente2,cliente3;

    public static FabricarCliente creador=new CrearCliente();
    public static FabricarTarifa creaTarifas = new CrearTarifa();

    @BeforeClass
    public static void init() {
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        cliente = creador.getClienteEmpresa("Marcos", "0001", direccion1, "al375909@uji.es", new TarifaBasica(15));
        cliente2 = creador.getClienteParticular("Philip", "0002", direccion1, "al375923@uji.es", creaTarifas.getTarifaBasica(15),"Pego");
        cliente3 = creador.getClienteParticular("Philip", "0002", direccion1, "al375923@uji.es", creaTarifas.getTarifaBasica(15),"Pego");

    }

    public static void finish() {
        cliente = null;
        cliente2 = null;
        creador = null;
        creaTarifas = null;
    }

    @Test
    public void crearEmpresa() {
        assertEquals(cliente.getNombre(), "Marcos", 0);
    }

    @Test
    public void crearParticular() {
        assertEquals(cliente2.toString(), cliente3.toString());
    }
}
