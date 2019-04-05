package agenda.tarifa;

import java.time.LocalDateTime;

public class TarifaTardesA5 extends Tarifa{
    private Tarifa tarifa;

    public TarifaTardesA5(Tarifa tarifa){
        super(5);
        this.tarifa=tarifa;
    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+" con Tardes Gratis";
    }

    public  int getPrecio(LocalDateTime fecha){
        int money=tarifa.getPrecio();
        int precio=money;

        if (esTarde(fecha)){

            money= super.getPrecio(fecha);
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
