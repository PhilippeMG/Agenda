package agenda.main;

import agenda.menu.OpcionesMenu;
import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaBasica;
import agenda.tarifa.TarifaDomingosGratis;
import agenda.tarifa.TarifaTardesGratis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Cargando datos...");

        // new OpcionesMenu().menuPrincipal();
        System.out.printf("Guardando datos...");
        LocalTime hora = LocalTime.now();
        LocalDate fecha = LocalDate.of(2019, 3, 30);
        LocalDateTime data = LocalDateTime.of(fecha, hora);
        Tarifa tarifa = new TarifaBasica(9);
        System.out.println("\nTarifa Basica + Domingo");
        Tarifa domingo = new TarifaDomingosGratis(tarifa, 0);

        System.out.println("Precio; " + domingo.getPrecio(data));

        System.out.println("tarifa Basica + Domingo + Tardes");
        Tarifa tarde = new TarifaTardesGratis(domingo, 1);
        System.out.println("Precio:"+tarde.getPrecio(data));

    }

}
