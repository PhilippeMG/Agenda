package agenda.tarifa;

import java.time.LocalDateTime;

public class TarifaDomingosGratis extends Tarifa{

    private Tarifa tarifa;

    public TarifaDomingosGratis(Tarifa tarifa){
        super(0);
        this.tarifa=tarifa;

    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+" con Tarifa Domingos Gratis";
    }

    public  int getPrecio(LocalDateTime fecha){
        int money=tarifa.getPrecio(fecha);
        int precio=money;

        if (esDomingo(fecha)){
            money=super.getPrecio(fecha);
        }
        if (money<precio){
            return money;
        }
        return precio;
    }
    public  boolean esDomingo(LocalDateTime fecha){
        if(fecha.getDayOfWeek().getValue()==7) return true;
        return false;
    }

}
