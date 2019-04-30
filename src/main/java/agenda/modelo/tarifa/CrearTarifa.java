package agenda.modelo.tarifa;

public class CrearTarifa implements FabricarTarifa {
    @Override
    public Tarifa getTarifaBasica(int precio) {
        return new TarifaBasica(precio);
    }

    @Override
    public Tarifa getOfertaDomingos(Tarifa tarifa) {
        return new OfertaDomingosGratis(tarifa);
    }

    @Override
    public Tarifa getOfertaTardes(Tarifa tarifa) {
        return new OfertaTardesA5(tarifa);
    }
}
