package m3.uf5.navegador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Navegador {
    Pila pilaEnrere = new Pila();
    Pila pilaEndavant = new Pila();
    List<String> hist = new ArrayList<>();

    String actual;

    public Navegador(String home) {
        this.actual = home;
        hist.add(home);
    }

    public void anarA(String novaURL) {
        pilaEnrere.push(actual);
        actual = novaURL;
        hist.add(novaURL);
        if (!pilaEndavant.empty()) {
            pilaEndavant.removeAllElements();
        }
        System.out.println("Ara ets a: " + actual);
    }

    public void enrere() throws PilaBuidaException {
        if (!pilaEnrere.empty()) {
            pilaEndavant.push(actual);
            actual = (String) pilaEnrere.pop();
            hist.add(actual);
            System.out.println("Enrere a: " + actual);
        } else {
            System.out.println("No hi ha anteriors.");
        }
    }

    public void endavant() throws PilaBuidaException {
        if (!pilaEndavant.empty()) {
            pilaEnrere.push(actual);
            actual = (String) pilaEndavant.pop();
            hist.add(actual);
            System.out.println("Endavant a: " + actual);
        } else {
            System.out.println("No hi ha següents.");
        }
    }

    public void veureHistorial() {
        if (hist.isEmpty()) {
            System.out.println("Historial buit.");
        } else {
            System.out.println(hist.toString());
        }
    }

    public void veureVisitades() {
        HashMap<String, Integer> hm = new HashMap();

        for (int i = 0; i < hist.size(); i++) {
            if (hm.containsKey(hist.get(i))) {
                int x = hm.get(hist.get(i));
                hm.put(hist.get(i), x+1);
            }else {
                hm.put(hist.get(i), 1);
            }
        }
        System.out.println(hm.entrySet().toString());
    }

    public String getActual() {
        return actual;
    }

    public void setActual(String actual) {
        this.actual = actual;
    }
}
