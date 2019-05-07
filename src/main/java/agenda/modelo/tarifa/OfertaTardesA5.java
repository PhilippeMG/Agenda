package agenda.modelo.tarifa;

import agenda.modelo.Llamada;

import java.time.LocalDateTime;

public class OfertaTardesA5 extends Oferta{
    private Tarifa tarifa;

    public OfertaTardesA5(Tarifa tarifa){
        super(5);
        this.tarifa=tarifa;
    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+" con Tardes a 5 cent";
    }
    public int getPrecio(){return tarifa.getPrecio();}
    public void setPrecio(int preu){ tarifa.setPrecio(preu);}

    public  double calculaPrecio(Llamada llamada){
        LocalDateTime fecha=llamada.getFecha();
        double money=tarifa.calculaPrecio(llamada);
        double precio=money;

        if (esTarde(fecha)){

            money=super.getPrecio()*llamada.getDuracion() ;
        }
        if (money<precio){
            return money;
        }
        return precio;
    }

    public  boolean esTarde(LocalDateTime fecha){
        if (fecha.getHour()>=17 && fecha.getHour()<=19){
            return true;
        }
        return false;
    }
}
