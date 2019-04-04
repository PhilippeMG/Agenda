package agenda.tarifa;

import java.time.LocalDateTime;

public class TarifaDomingosGratis extends Tarifa{

    private Tarifa tarifa;

    public TarifaDomingosGratis(Tarifa tarifa, int tipo){
        super(tipo);
        this.tarifa=tarifa;

    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+"con Tarifa Especial";
    }

    public  int getPrecio(LocalDateTime fecha){
        int money=tarifa.getPrecio();

        if (esDomingo(fecha)){
            System.out.println("Es domingo");
            money=super.getPrecio();
        }
        if (money<tarifa.getPrecio()){
            return money;
        }
        return tarifa.getPrecio();
    }
    public  boolean esDomingo(LocalDateTime fecha){
        if(fecha.getDayOfWeek().getValue()==7) return true;
        return false;
    }

}
