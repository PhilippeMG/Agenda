package agenda.modelo.tarifa;

import agenda.modelo.Llamada;

import java.io.Serializable;

public abstract class Tarifa  implements Serializable{
    private int precio;

    public Tarifa(int tarifa) {
        this.precio = tarifa;
    }


    public void setPrecio(int precio) {
        this.precio = precio;
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
