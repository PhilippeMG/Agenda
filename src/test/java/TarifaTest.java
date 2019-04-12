import agenda.Direccion;
import agenda.Llamada;
import agenda.clientes.Empresa;
import agenda.tarifa.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TarifaTest {
    private static LocalDateTime data, data2, data3,data4;
    private static Tarifa tarifa;
    private static Llamada llamada1,llamada2, llamada3,llamada4;
    private static FabricarTarifa creador=new CrearTarifa();

    @BeforeClass

    public static void init() {
        LocalTime hora = LocalTime.of(19, 56, 23);

        LocalDate fecha = LocalDate.of(2019, 3, 31);
        data = LocalDateTime.of(fecha, hora);

        LocalTime hora2 = LocalTime.of(12, 56, 23);
        LocalDate fecha2 = LocalDate.of(2019, 3, 30);
        data2 = LocalDateTime.of(fecha2, hora2);

        LocalDate fecha3 = LocalDate.of(2019, 3, 30);
        data3 = LocalDateTime.of(fecha3, hora);
        data4 = LocalDateTime.of(fecha3, hora2);

        tarifa = creador.getTarifaBasica(9);
         llamada1 =new Llamada(1,10,data);
         llamada2=new Llamada(1,10,data2);
         llamada3 =new Llamada(1,10,data3);
        llamada4 =new Llamada(1,10,data4);

    }


    @AfterClass
    public static void finish() {
        data = data2=data3=null;
        tarifa=null;
    }


    @Test
    public void tarifaDomingos() {

        Tarifa domingo = creador.getTarifaDomingos(tarifa);
        assertEquals(domingo.calculaPrecio(llamada1),0,0);
    }
   @Test
    public void tarifaTardes() {

        Tarifa tardes = creador.getTarifaTardes(tarifa);
        assertEquals(tardes.calculaPrecio(llamada4),90,0);

    }
    @Test
    public void tarifaNoTardes() {

        Tarifa noTardes = creador.getTarifaTardes(tarifa);
        assertEquals(noTardes.calculaPrecio(llamada2),90,0);

    }
    @Test
    public void tarifaTardesNoDomingoSi(){
        Tarifa domingo = creador.getTarifaDomingos(tarifa);
        Tarifa todo = creador.getTarifaTardes(domingo);
        assertEquals(todo.calculaPrecio(llamada1),0,0);
    }
    @Test
    public void tarifaDomingoNoTardesSi(){
        Tarifa tardes = creador.getTarifaTardes(tarifa);
        Tarifa todo = creador.getTarifaDomingos(tardes);
        assertEquals(todo.calculaPrecio(llamada3),50,0);
    }
    @Test
    public void tarifaDomingoSiTardesSi(){
        Tarifa tardes = creador.getTarifaTardes(tarifa);
        Tarifa todo = creador.getTarifaDomingos(tardes);
        assertEquals(todo.calculaPrecio(llamada1),0,0);
    }
    @Test
    public void tarifaDomingoNoTardesNo(){
        Tarifa tardes = creador.getTarifaTardes(tarifa);
        Tarifa todo = creador.getTarifaDomingos(tardes);
        assertEquals(todo.calculaPrecio(llamada2),90,0);
    }
}
