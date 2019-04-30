import agenda.modelo.tarifa.CrearTarifa;
import agenda.modelo.tarifa.FabricarTarifa;
import agenda.modelo.tarifa.Tarifa;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class FactoriaTarifaTest {
    private static Tarifa tarifa;
    private static FabricarTarifa creador = new CrearTarifa();

    @BeforeClass
    public static void init() {
        tarifa = creador.getTarifaBasica(15);
    }

    @AfterClass
    public static void finish() {
        tarifa = null;
        creador = null;
    }

    @Test
    public void precioTarifaBasica() {
        assertEquals(tarifa.getPrecio(), 15, 0);
    }

    @Test
    public void precioOfertaDomigos() {
        Tarifa domingos = creador.getOfertaDomingos(tarifa);
        assertEquals(domingos.getPrecio(), 0,0);
    }

    @Test
    public void precioOfertaTardes() {
        Tarifa tardes = creador.getOfertaTardes(tarifa);
        assertEquals(tardes.getPrecio(), 5,0);
    }

    @Test
    public void precioOfertaDomigosTardes() {
        Tarifa domingos = creador.getOfertaDomingos(tarifa);
        Tarifa tardesdomingos = creador.getOfertaTardes(domingos);
        assertEquals(tardesdomingos.getPrecio(), 5,0);
    }

}
