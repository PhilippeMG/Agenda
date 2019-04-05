package agenda.main;

import agenda.menu.OpcionesMenu;
import agenda.tarifa.Tarifa;
import agenda.tarifa.TarifaBasica;
import agenda.tarifa.TarifaDomingosGratis;
import agenda.tarifa.TarifaTardesA5;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Cargando datos...");

        new OpcionesMenu().menuPrincipal();
        System.out.printf("Guardando datos...");


    }

}
