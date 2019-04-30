package agenda.modelo.tarifa;

public interface FabricarTarifa {
    Tarifa getTarifaBasica(int precio);
    Tarifa getOfertaDomingos(Tarifa tarifa);
    Tarifa getOfertaTardes(Tarifa tarifa);
}
