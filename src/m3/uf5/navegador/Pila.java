package m3.uf5.navegador;

import java.util.ArrayDeque;
import java.util.Iterator;

public class Pila<E> implements Iterable{
    private ArrayDeque<E> pila = new ArrayDeque<>();

    public boolean empty() {
        return pila.isEmpty();
    }

    public E peek() throws PilaBuidaException {
        return pila.peek();
    }

    public E pop() throws PilaBuidaException {
        if (pila.isEmpty()) {
            throw new PilaBuidaException();
        } else {
            return pila.pop();
        }
    }

    public void push(E e) {
        pila.push(e);
    }

    public void removeAllElements() {
        pila.clear();
    }

    @Override
    public Iterator iterator() {
        return pila.iterator();
    }
}

