package agenda;

import agenda.clientes.Cliente;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.LinkedList;

public class Llamada implements Serializable {
    int numDestino;
    double duracion;
    LocalDate fechaLlamada;

    public Llamada(int numDestino, double duracion, LocalDate fechaLlamada) {
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
        LinkedList<Llamada> llamadas=cliente.getLlamadas();
        for( int i=0; i< llamadas.size();i++){
            llamadas.get(i).toString();
        }

    }

    public LocalDate getFecha() {
        return fechaLlamada;
    }

    public void setFechaLlamada(LocalDate fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }
}
