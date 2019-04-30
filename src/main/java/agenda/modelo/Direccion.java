package agenda.modelo;

import java.io.Serializable;

public class Direccion implements Serializable {
    private int CP;
    private String provincia;
    private String poblacion;

    public Direccion(int CP, String provincia, String poblacion) {
        this.CP = CP;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    public int getCP() {
        return CP;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getPoblacion() {
        return poblacion;
    }

    @Override
    public String toString() {
        return "CP='" + CP + '\'' +
                ", provincia='" + provincia + '\'' +
                ", poblacion='" + poblacion + "'";
    }
}
