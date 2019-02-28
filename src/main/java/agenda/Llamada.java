package agenda;

import java.util.Date;

public class Llamada {
    int numDestino;
    Date hora;
    int duracion;
    Date fechaLlamada;

    public Llamada(int numDestino, Date hora, int duracion, Date fechaLlamada) {
        this.numDestino = numDestino;
        this.hora = hora;
        this.duracion = duracion;
        this.fechaLlamada = fechaLlamada;
    }
}
