import agenda.Direccion;
import agenda.clientes.Empresa;
import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaBasica;
import agenda.tarifa.TarifaDomingosGratis;
import agenda.tarifa.TarifaTardesA5;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import static org.junit.Assert.assertEquals;

public class TarifaTest {
    private static LocalDateTime data,data2,data3;
    private static Tarifa tarifa;
    @BeforeClass

    public static void init() {
        LocalTime hora = LocalTime.of(19,56,23);

        LocalDate fecha = LocalDate.of(2019, 3, 31);
        data = LocalDateTime.of(fecha, hora);

        LocalTime hora2 = LocalTime.of(12,56,23);
        LocalDate fecha2 = LocalDate.of(2019, 3, 30);
        data2 = LocalDateTime.of(fecha2, hora2);

        LocalDate fecha3= LocalDate.of(2019, 3, 30);
        data3=LocalDateTime.of(fecha3,hora);
        tarifa = new TarifaBasica(9);
    }

    @AfterClass


    public static void finish() {
        data = data2=data3=null;
        tarifa=null;
    }


    @Test
    public void tarifaDomingos() {

        Tarifa domingo = new TarifaDomingosGratis(tarifa);
        assertEquals(domingo.getPrecio(data),0,0);
    }
    @Test
    public void tarifaTardes() {

        Tarifa tardes = new TarifaTardesA5(tarifa);
        assertEquals(tardes.getPrecio(data),5,0);

    }
    @Test
    public void tarifaNoTardes() {

        Tarifa noTardes = new TarifaTardesA5(tarifa);
        assertEquals(noTardes.getPrecio(data2),9,0);

    }
    @Test
    public void tarifaTardesNoDomingoSi(){
        Tarifa domingo = new TarifaDomingosGratis(tarifa);
        Tarifa todo = new TarifaTardesA5(domingo);
        assertEquals(todo.getPrecio(data2),0,0);
    }
    @Test
    public void tarifaDomingoNoTardesSi(){
        Tarifa tardes = new TarifaTardesA5(tarifa);
        Tarifa todo = new TarifaDomingosGratis(tardes);
        assertEquals(todo.getPrecio(data3),5,0);
    }
    @Test
    public void tarifaDomingoSiTardesSi(){
        Tarifa tardes = new TarifaTardesA5(tarifa);
        Tarifa todo = new TarifaDomingosGratis(tardes);
        assertEquals(todo.getPrecio(data),0,0);
    }
    @Test
    public void tarifaDomingoNoTardesNo(){
        Tarifa tardes = new TarifaTardesA5(tarifa);
        Tarifa todo = new TarifaDomingosGratis(tardes);
        assertEquals(todo.getPrecio(data2),9,0);
    }
}
