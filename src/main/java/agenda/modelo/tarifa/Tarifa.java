package agenda.modelo.tarifa;

import agenda.modelo.Llamada;

import java.io.Serializable;

public abstract class Tarifa  implements Serializable{
    private int precio;

    public Tarifa(int preu) {
        this.precio = preu;
    }


    public void setPrecio(int preu) {
        this.precio = preu;
    }
    public int getPrecio(){return precio;}
    //public int getPrecio(LocalDateTime fecha){return precio;}
    public abstract double calculaPrecio(Llamada llamada);//{return precio;}

    @Override
    public String toString() {
        return  descripcion();
    }
    public abstract String descripcion();
}
