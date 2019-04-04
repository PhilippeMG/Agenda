package agenda;

import agenda.clientes.Cliente;
import agenda.clientes.GetFecha;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.LinkedList;

public class Llamada implements Serializable, GetFecha {
    int numDestino;
    double duracion;
    LocalDateTime fechaLlamada;

    public Llamada(int numDestino, double duracion, LocalDateTime fechaLlamada) {
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

    public void listadoLlamadas(Cliente cliente) {
        LinkedList<Llamada> llamadas = cliente.getLlamadas();
        for (int i = 0; i < llamadas.size(); i++) {
            llamadas.get(i).toString();
        }

    }

    public LocalDateTime getFecha() {
        return fechaLlamada;
    }

    public void setFechaLlamada(LocalDateTime fechaLlamada) {
        this.fechaLlamada = fechaLlamada;
    }
}
