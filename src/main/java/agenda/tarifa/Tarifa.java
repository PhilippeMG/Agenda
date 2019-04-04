package agenda.tarifa;

import java.io.Serializable;
import java.time.LocalDateTime;

public abstract class Tarifa  implements Serializable{
    private int precio;

    public Tarifa(int tarifa) {
        this.precio = tarifa;
    }


    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getPrecio(){return precio;}
    public int getPrecio(LocalDateTime fecha){return precio;}

    @Override
    public String toString() {
        return "Tarifa= " + precio;
    }
    public abstract String descripcion();

}
