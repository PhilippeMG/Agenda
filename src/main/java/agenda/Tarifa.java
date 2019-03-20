package agenda;

import java.io.Serializable;
import java.util.Date;

public class Tarifa implements Serializable {
    double tarifa;

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
}
