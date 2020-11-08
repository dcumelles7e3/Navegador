package m3.uf5.navegador;

import java.util.ArrayDeque;
import java.util.Iterator;

/** Model de pila
 * @param <E> Els elements a guardar son les URL
 * @version 08/11/2020
 * @author Dídac Cumelles Cenzano
 */
public class Pila<E> implements Iterable{
    /** ArrayDeque per a afegir funcionalitats
     */
    private ArrayDeque<E> pila = new ArrayDeque<>();

    /** Comprova si la pila esta buida
     * @return boolean si esta o no buida
     */
    public boolean empty() {
        return pila.isEmpty();
    }

    /** Peek
     * @return Retorna el valor mes recent de la pila
     * @throws PilaBuidaException si la pila està buida
     */
    public E peek() throws PilaBuidaException {
        if (pila.isEmpty()) {
            throw new PilaBuidaException();
        } else {
            return pila.peek();
        }
    }

    /** Pop
     * @return Retorna el valor mes recent de la pila i l'esborra
     * @throws PilaBuidaException si la pila està buida
     */
    public E pop() throws PilaBuidaException {
        if (pila.isEmpty()) {
            throw new PilaBuidaException();
        } else {
            return pila.pop();
        }
    }

    /** Afegeix a la pila
     * @param e URL
     */
    public void push(E e) {
        pila.push(e);
    }

    /** Esborra tots els elements d'una pila
     */
    public void removeAllElements() {
        pila.clear();
    }

    /** Iterator de pila
     * @return iterator
     */
    @Override
    public Iterator iterator() {
        return pila.iterator();
    }
}

