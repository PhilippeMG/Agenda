package agenda.modelo.tarifa;

import agenda.modelo.Llamada;

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
        return "Tarifa Basica";
    }

    //@Override
    //public  int getPrecio(LocalDateTime fecha){
        //return super.getPrecio();
    //}

}
