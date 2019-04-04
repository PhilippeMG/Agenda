package agenda.tarifa;

import java.io.Serializable;
import java.util.Date;

public abstract class Tarifa  implements Serializable{
    private double tarifa;

    public Tarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    public double getTipoTarifa() {
        return tarifa;
    }

    public void setTarifa(int tarifa) {
        this.tarifa = tarifa;
    }

    @Override
    public String toString() {
        return "Tarifa= " + tarifa;
    }
    public abstract String descripcion();
}
