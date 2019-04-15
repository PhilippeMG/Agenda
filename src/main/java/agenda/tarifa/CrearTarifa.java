package agenda.tarifa;

public class CrearTarifa implements FabricarTarifa {
    @Override
    public Tarifa getTarifaBasica(int precio) {
        return new TarifaBasica(precio);
    }

    @Override
    public Tarifa getTarifaDomingos(Tarifa tarifa) {
        return new OfertaDomingosGratis(tarifa);
    }

    @Override
    public Tarifa getTarifaTardes(Tarifa tarifa) {
        return new OfertaTardesA5(tarifa);
    }
}
