package agenda.tarifa;

public interface FabricarTarifa {
    Tarifa getTarifaBasica(int precio);
    Tarifa getTarifaDomingos(Tarifa tarifa);
    Tarifa getTarifaTardes(Tarifa tarifa);
}
