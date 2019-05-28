package agenda.vista;

import javax.swing.*;

public abstract class Formulario {
    public  int tamanyCampo(JTextField campo) {
        return campo.getText().length();
    }
    public int convertirAInt(JTextField campo) {
        return Integer.valueOf(String.valueOf(campo.getText()));
    }


}
