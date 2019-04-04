package agenda.tarifa;

import java.time.LocalDateTime;

public class TarifaTardesGratis extends Tarifa{
    private Tarifa tarifa;

    public TarifaTardesGratis(Tarifa tarifa , int tipo){
        super(tipo);
        this.tarifa=tarifa;
    }
    @Override
    public String descripcion(){
        return tarifa.descripcion()+"con Tarifa Especial";
    }
    public  int getPrecio(LocalDateTime fecha){
        int money=tarifa.getPrecio();
        System.out.println(fecha.toString());

        if (esTarde(fecha)){
            System.out.println("Es domingo");

            money= super.getPrecio();
        }
        if (money<tarifa.getPrecio()){
            return money;
        }
        return tarifa.getPrecio();
    }
    public  boolean esTarde(LocalDateTime fecha){
        if (fecha.getHour()>=17 && fecha.getHour()<=19){
            return true;
        }
        return false;
    }
}
