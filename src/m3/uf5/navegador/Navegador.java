package m3.uf5.navegador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Navegador {
    Pila pila = new Pila();
    Pila pilaEndavant = new Pila();
    List hist = new ArrayList<>();
    HashMap<String, Integer> hm = new HashMap();

    public void anarA(String novaURL){
        pila.push(novaURL);
        hist.add(novaURL);
    }

    public void enrere(){
            try {
                pilaEndavant.push(pila.pop());
                hist.add(pila.peek());
                System.out.println("enrere a: "+pila.peek());
            }catch (PilaBuidaException pb){}
    }

    public void endavant(){
            try {
                pila.push(pilaEndavant.pop());
                hist.add(pila.peek());
                System.out.println("endavant a: "+pila.peek());
            }catch (PilaBuidaException pb){}
    }
    public void veureHistorial(){
        System.out.println(hist.toString());
    }
    public void veureVisitades() {
        int contador = 1;
        for (int i = 0; i<hist.size(); i++){
            hm.put(hist.get(i).toString(),contador);
            if (hm.containsKey(hist.get(i).toString())){
                System.out.println(hist.get(i).toString());
                contador++;
            }

            hm.replace(hist.get(i).toString(), contador, contador+1);
        }
        System.out.println(hm.entrySet());
    }


}
