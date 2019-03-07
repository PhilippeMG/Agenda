package agenda;

import java.io.Serializable;
import java.util.Date;

public class Tarifa implements Serializable {
    int tarifa;
    public Tarifa(int tarifa){
        this.tarifa=tarifa;
    }

    public int getTarifa() {
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
