package m3.uf5.navegador;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/** Classe per a la lògica del navegador
 * @version 08/11/2020
 * @author Dídac Cumelles Cenzano
 */

public class Navegador {
    Pila pilaEnrere = new Pila();
    Pila pilaEndavant = new Pila();
    List<String> hist = new ArrayList<>();

    String actual;

    /** Constructor
     * @param home URL d'inici
     */
    public Navegador(String home) {
        this.actual = home;
        hist.add(home);
    }

    /** Sobreescriu la pàgina actual del navegador per la nova
     * @param novaURL Nova URL, es comprova si té http://
     */
    public void anarA(String novaURL) {
        //Comprovar si afegir http
        if (!novaURL.matches("^(https?)://.*$")){
            StringBuffer sb = new StringBuffer();
            sb.append("http://");
            sb.append(novaURL);
            novaURL=sb.toString();
            System.out.println(novaURL);
        }
        pilaEnrere.push(actual);
        actual = novaURL;
        hist.add(novaURL);
        if (!pilaEndavant.empty()) {
            pilaEndavant.removeAllElements();
        }
        System.out.println("Ara ets a: " + actual);
    }

    /** Sobreescriu la pàgina actual per la anterior a la pila
     * @throws PilaBuidaException si la pila d'enrere està buida
     */
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

    /** Sobreescriu la pàgina actual per la seguent a la pila
     * @throws PilaBuidaException si la pila de davant està buida
     */
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

    /** Imprimeix la llista historial
     */
    public void veureHistorial() {
        if (hist.isEmpty()) {
            System.out.println("Historial buit.");
        } else {
            System.out.println(hist.toString());
        }
    }

    /** Crea un hashmap a partir del historial i mostra quants cops s'han visitat
     */
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

    /** Per a comprovar la pàgina actual
     * @return Retorna el String de la pàgina actual
     */
    public String getActual() {
        return actual;
    }
}
