package agenda;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Gestor {
    HashMap<String, Cliente> clientes = new HashMap<>();
    HashMap<String, Factura> facturas = new HashMap<>();

    //>>>CLIENTE<<<<
    public void añadirCliente(Cliente cliente){
        if(!clientes.containsKey(cliente.nif)){
            clientes.put(cliente.nif,cliente);
        }else{
            throw new IllegalArgumentException();
        }
    }

    public void borrarCliente(Cliente cliente){
        clientes.remove(cliente.nif);
    }

    public void cambiarTarifa(Cliente cliente, int nuevaTarifa){
        cliente.tipoTarifa = nuevaTarifa;
    }

    public void mostrarClientes(){
        Iterator<Cliente> clientela = clientes.values().iterator();//.entrySet().iterator();
        while (clientela.hasNext()){
            System.out.println(clientela.next().toString());
        }
    }

    public static void main(String[] args) {
        System.out.println("¿Qué operación desea realizar?");
        Gestor gestor = new Gestor();
        Direccion direccion1 = new Direccion(1234, "Valencia", "Burjassot");
        Cliente cliente1 = new Cliente("Marcos", "23325634T", direccion1, "al375909@uji.es", 1);
        Cliente cliente2 = new Cliente("Philippe", "53627507v", direccion1, "al375923@uji.es", 1);

        System.out.println("Cliente creado:");
        System.out.println(cliente1.toString());
        System.out.println("Añadiendo cliente...");
        gestor.añadirCliente(cliente1);
        gestor.añadirCliente(cliente2);

        System.out.println("CLIENTES AÑADIDOS");
        gestor.mostrarClientes();


    }
}