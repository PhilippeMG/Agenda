package agenda.modelo.clientes;

import java.time.LocalDateTime;

public interface GetFecha {
    LocalDateTime getFecha();
    public default String ajusteFecha(LocalDateTime data){
        return (data.getDayOfMonth()+" "+data.getMonth()+" "+data.getYear());

    }
}
