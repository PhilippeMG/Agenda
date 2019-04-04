package agenda.tarifa;

public class TarifaTardesGratis extends Tarifa{
    private Tarifa tarifa;

    public TarifaTardesGratis(Tarifa tarifa , int tipo){
        super(tipo);
        this.tarifa=tarifa;
    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+"con Tarifa Especial";
    }

}
