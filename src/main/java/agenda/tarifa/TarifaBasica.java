package agenda.tarifa;

import java.time.LocalDateTime;

public class TarifaBasica extends Tarifa{
    public TarifaBasica(int tipo){
        super(tipo);

    }
    @Override
    public String descripcion(){
        return "Tarifa Normal";
    }

    @Override
    public  int getPrecio(LocalDateTime fecha){
        return super.getPrecio();
    }

}
