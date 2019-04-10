package agenda.tarifa;

import agenda.Llamada;

import java.time.LocalDateTime;

public class TarifaDomingosGratis extends Oferta {
    private Tarifa tarifa;

    public TarifaDomingosGratis(Tarifa tarifa) {
        super(0);
        this.tarifa = tarifa;
    }


    @Override
    public String descripcion() {
        return tarifa.descripcion() + " con Tarifa Domingos Gratis";
    }

    public double calculaPrecio(Llamada llamada) {
        LocalDateTime fecha = llamada.getFecha();
        double money = tarifa.calculaPrecio(llamada);
        double precio = money;

        if (esDomingo(fecha)) {
            money = super.getPrecio() * llamada.getDuracion();
        }
        if (money < precio) {
            return money;
        }
        return precio;
    }

    public boolean esDomingo(LocalDateTime fecha) {
        if (fecha.getDayOfWeek().getValue() == 7) return true;
        return false;
    }
}
