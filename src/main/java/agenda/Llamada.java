package agenda;

import java.util.Date;
import java.util.LinkedList;

public class Llamada {
    int numDestino;
    int duracion;
    Date fechaLlamada;

    public Llamada(int numDestino, int duracion, Date fechaLlamada) {
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
