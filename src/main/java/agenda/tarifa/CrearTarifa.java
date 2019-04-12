package agenda.tarifa;

public class CrearTarifa implements FabricarTarifa {
    @Override
    public Tarifa getTarifaBasica(int precio) {
        return new TarifaBasica(precio);
    }

    @Override
    public Tarifa getTarifaDomingos(Tarifa tarifa) {
        return new TarifaDomingosGratis(tarifa);
    }

    @Override
    public Tarifa getTarifaTardes(Tarifa tarifa) {
        return new TarifaTardesA5(tarifa);
    }
}
