package agenda;

import agenda.clientes.Cliente;

import java.io.*;
import java.util.HashMap;

public class Fichero {
    //>>>Fichero<<<<
    public void escribirDatos() throws IOException {
        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(Gestor.getClientes());
        oos.close();
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(Gestor.getFacturas());
        oos.close();
    }

    public void leerDatos() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosClientes.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        Gestor.setClientes((HashMap<String, Cliente>) ois.readObject());
        fis = new FileInputStream("datosFacturas.bin");
        ois = new ObjectInputStream(fis);
        Gestor.setFacturas((HashMap<Integer, Factura>) ois.readObject());
        ois.close();
    }
}
