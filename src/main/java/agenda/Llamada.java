package agenda;

import java.time.LocalDate;
import java.util.Date;
import java.util.LinkedList;

public class Llamada {
    int numDestino;
    int duracion;
    LocalDate fechaLlamada;

    public Llamada(int numDestino, int duracion, LocalDate fechaLlamada) {
        this.numDestino = numDestino;
        this.duracion = duracion;
        this.fechaLlamada = fechaLlamada;
    }

    @Override
    public String toString() {
        return "Llamada{" +
                "numDestino=" + numDestino +
                ", duracion=" + duracion +
                ", fechaLlamada=" + fechaLlamada +
                '}';
    }

    public void listadoLlamadas(Cliente cliente){
        LinkedList<Llamada> llamadas=cliente.llamadas;
        for( int i=0; i< llamadas.size();i++){
            llamadas.get(i).toString();
        }

    }
}
