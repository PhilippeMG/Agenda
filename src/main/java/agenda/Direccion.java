package agenda;

import java.io.Serializable;

public class Direccion implements Serializable {
    int CP;
    String provincia;
    String poblacion;

    public Direccion(int CP, String provincia, String poblacion) {
        this.CP = CP;
        this.provincia = provincia;
        this.poblacion = poblacion;
    }

    @Override
    public String toString() {
        return  "CP=" + CP +
                ", provincia='" + provincia + '\'' +
                ", poblacion='" + poblacion;
    }
}
