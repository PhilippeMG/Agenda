package agenda.modelo.main;

import agenda.modelo.menu.OpcionesMenu;


public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Cargando datos...");

        new OpcionesMenu().menuPrincipal();
        System.out.printf("Guardando datos...");


    }

}
