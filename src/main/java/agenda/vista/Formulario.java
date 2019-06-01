package agenda.vista;

import javax.swing.*;

public abstract class Formulario {
    public int tamanyCampo(JTextField campo) {
        return campo.getText().length();
    }
    public int convertirAInt(JTextField campo) {
        return Integer.valueOf(String.valueOf(campo.getText()));
    }
    public boolean isEmpty(JTextField objeto) { return (tamanyCampo(objeto) <= 0);
    }
    public abstract boolean camposVacios();

}
