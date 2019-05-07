package agenda.modelo.tarifa;

import agenda.modelo.Llamada;

public class TarifaBasica extends Tarifa{
    int precio;
    public TarifaBasica(int tipo){
        super(tipo);
        precio=tipo;

    }

    @Override
    public double calculaPrecio(Llamada llamada) {
        return getPrecio() * llamada.getDuracion();
    }

    @Override
    public int getPrecio(){
        return precio;
    }
    public String descripcion(){
        return "Tarifa Basica";
    }
    public  void setPrecio(int preu){
        precio=preu;
    }

    //@Override
    //public  int getPrecio(LocalDateTime fecha){
        //return super.getPrecio();
    //}

}
