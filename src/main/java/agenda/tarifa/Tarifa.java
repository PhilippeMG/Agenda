package agenda.tarifa;

import java.io.Serializable;

public abstract class Tarifa  implements Serializable{
    private int precio;

    public Tarifa(int tarifa) {
        this.precio = tarifa;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Tarifa= " + precio;
    }
    public abstract String descripcion();
}
