package agenda;

import java.util.Date;

public class Fecha {
    Date fecha;

    public Fecha(Date fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        int dia=fecha.getDate();
        int mes=fecha.getMonth()+1;
        int año=fecha.getYear()+1900;
        return dia+"/"+mes+"/"+año;
    }
}
