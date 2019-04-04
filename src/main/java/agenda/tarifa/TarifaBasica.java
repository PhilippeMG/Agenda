package agenda.tarifa;

public class TarifaBasica extends Tarifa{
    public TarifaBasica(int tipo){
        super(tipo);

    }
    @Override
    public String descripcion(){
        return "Tarifa Normal";
    }

    @Override
    public  int getPrecio(){
        return super.getPrecio();
    }

}
