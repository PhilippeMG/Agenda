import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaBasica;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TarifaTest {


    @Test
    public void cambiarTarifa() {
        Tarifa tarifa = new TarifaBasica(9);
        assertEquals(tarifa.getPrecio(), 9, 0);

    }
}
