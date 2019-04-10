package agenda.tarifa;

import agenda.Llamada;

public abstract class Oferta extends Tarifa {
    Tarifa tarifa;
    public Oferta(Tarifa tarifa,int precio){
        super(precio);
        this.tarifa=tarifa;
    }

    public Oferta(int i) {
        super(i);
    }

    public abstract String descripcion();
}
