package agenda.modelo;

import agenda.modelo.clientes.Cliente;
import agenda.modelo.clientes.GetFecha;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Llamada implements Serializable, GetFecha {
    private int numDestino;
    private double duracion;
    private LocalDateTime fechaLlamada;

    public Llamada(int numDestino, double duracion, LocalDateTime fechaLlamada) {
        this.numDestino = numDestino;
        this.duracion = duracion;
        this.fechaLlamada = fechaLlamada;
    }

    public double getDuracion() {
        return duracion;
    }

    public int getNumDestino() {
        return numDestino;
    }


    public LocalDateTime getFecha() {
        return fechaLlamada;
    }

    public void setFechaLlamada(LocalDateTime fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }

    public void listadoLlamadas(Cliente cliente) {
        LinkedList<Llamada> llamadas = cliente.getLlamadas();
        for (int i = 0; i < llamadas.size(); i++) {
            llamadas.get(i).toString();
        }

    }

    @Override
    public String toString() {
        return "Llamada{" +
                "numDestino=" + getNumDestino() +
                ", duracion=" + getDuracion()+
                ", fechaLlamada=" + getFecha() +
                '}';
    }
}