package agenda.tarifa;

import agenda.Llamada;

import java.time.LocalDateTime;

public class TarifaBasica extends Tarifa{
    public TarifaBasica(int tipo){
        super(tipo);

    }

    @Override
    public double calculaPrecio(Llamada llamada) {
        return getPrecio() * llamada.getDuracion();
    }

    @Override
    public String descripcion(){
        return "Tarifa Normal";
    }

    //@Override
    //public  int getPrecio(LocalDateTime fecha){
        //return super.getPrecio();
    //}

}
