package agenda;

import java.util.Date;
import java.util.LinkedList;

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

    @Override
    public String toString() {
        return "Llamada{" +
                "numDestino=" + numDestino +
                ", hora=" + hora +
                ", duracion=" + duracion +
                ", fechaLlamada=" + fechaLlamada +
                '}';
    }

    public void imprimirListado(LinkedList<Llamada> llamadas ){
        for( int i=0; i< llamadas.size();i++){
            llamadas.get(i).toString();
        }

    }
}
