package agenda.tarifa;

public class TarifaEspecial extends Tarifa{

    public TarifaEspecial(int tipo){
        super(tipo);
    }

    public String descripcion(){
        return "Tarifa Especial";
    }

}
