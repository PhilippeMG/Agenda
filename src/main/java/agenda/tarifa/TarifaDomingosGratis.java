package agenda.tarifa;

public class TarifaDomingosGratis extends Tarifa{

    private Tarifa tarifa;

    public TarifaDomingosGratis(Tarifa tarifa, int tipo){
        super(tipo);
        this.tarifa=tarifa;

    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+"con Tarifa Especial";
    }
}
