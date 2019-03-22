package agenda;

import agenda.clientes.Cliente;
import agenda.clientes.GetFecha;

import java.io.*;
import java.util.HashMap;

public class Fichero {
    //>>>Fichero<<<<
    /*public void escribirDatos(HashMap<String,Cliente> cliente, HashMap<Integer,Factura> factura) throws IOException {
        System.out.println("Escribir datos");

        System.out.println(cliente.toString());
        System.out.println(factura.toString());

        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(cliente);
        oos.close();
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(factura);
        oos.close();
    }

    public  HashMap<String,Cliente>  leerClientes() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosClientes.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<String, Cliente> mapa=(HashMap<String,Cliente>) ois.readObject();
        ois.close();
        System.out.println("leer clientes datos");

        System.out.print(mapa.toString());
        return mapa;
    }
    public  HashMap<Integer,Factura>  leerFecturas() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosFacturas.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        HashMap<Integer, Factura> mapa=(HashMap<Integer,Factura>) ois.readObject();
        ois.close();
        System.out.println("Leer facturas datos");

        System.out.println(mapa.toString());

        return mapa;
    }*/
    public  void escribirDatos() throws IOException {
        FileOutputStream fos = new FileOutputStream("datosClientes.bin");
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(new Gestor().getClientes());
        oos.close();
        fos = new FileOutputStream("datosFacturas.bin");
        oos = new ObjectOutputStream(fos);
        oos.writeObject(new Gestor().getFacturas());
        oos.close();
    }

    public void leerDatos() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("datosClientes.bin");
        ObjectInputStream ois = new ObjectInputStream(fis);
        new Gestor().setClientes((HashMap<String, Cliente>) ois.readObject());
        fis = new FileInputStream("datosFacturas.bin");
        ois = new ObjectInputStream(fis);
        new Gestor().setFacturas((HashMap<Integer, Factura>) ois.readObject());
        ois.close();
    }
}
