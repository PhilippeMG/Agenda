package agenda.tarifa;

import agenda.Llamada;

import java.time.LocalDateTime;

public class OfertaBasica extends Oferta{
    public OfertaBasica(int tipo){
        super(tipo);

    }

    @Override
    public double calculaPrecio(Llamada llamada) {
        return getPrecio() * llamada.getDuracion();
    }

    @Override
    public String descripcion(){
        return "Tarifa Basica";
    }

    //@Override
    //public  int getPrecio(LocalDateTime fecha){
        //return super.getPrecio();
    //}

}
