package agenda.tarifa;

public class TarifaDiaGratis extends Tarifa{


    public TarifaDiaGratis(int tipo){
        super(tipo);
    }
    public String descripcion(){
        return "Tarifa Dia Gratis";
    }

}
