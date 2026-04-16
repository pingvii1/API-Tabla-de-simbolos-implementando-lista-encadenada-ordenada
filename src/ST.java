
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.ArrayList;
import java.util.List;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author pingvii
 * @param <Key>
 */
public class ST<Key extends Comparable<? super Key>, Value> implements Iterable<Key> {

    private NodeST first;
    private int n; // Tamaño de la tabla

    private class NodeST {

        private Key key;
        private Value value;
        private NodeST next;

        public NodeST(Key key, Value value, NodeST next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public ST() {
        first = null;
        n = 0;
    }

    // --- Metodos Basicos ---
    public boolean isEmpty() {
        return n == 0;
    }

    public int size() {
        return n;
    }

    public boolean contains(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La llave es null");
        }
        return get(key) != null;
    }

    public Value get(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La llave es null");
        }
        for (NodeST x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                return x.value;
            }
            // Optimizacion: si la llave actual es mayor, no esta (porque esta ordenada)
            if (x.key.compareTo(key) > 0) {
                break;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        if (key == null) {
            throw new IllegalArgumentException("La llave es null");
        }
        if (val == null) {
            delete(key);
            return;
        }

        // Caso: Reemplazar valor si la llave ya existe
        for (NodeST x = first; x != null; x = x.next) {
            if (key.equals(x.key)) {
                x.value = val;
                return;
            }
        }

        // Insertar manteniendo el orden
        first = put(first, key, val);
        n++;
    }

    private NodeST put(NodeST x, Key key, Value val) {
        if (x == null || key.compareTo(x.key) < 0) {
            return new NodeST(key, val, x);
        }
        x.next = put(x.next, key, val);
        return x;
    }

    public void delete(Key key) {
        if (key == null) {
            throw new IllegalArgumentException("La llave es null");
        }
        first = delete(first, key);
    }

    private NodeST delete(NodeST x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            n--;
            return x.next;
        }
        x.next = delete(x.next, key);
        return x;
    }

    // --- Métodos de Orden (API de la imagen) ---
    public Key min() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tabla vacia");
        }
        return first.key;
    }

    public Key max() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tabla vacia");
        }
        NodeST x = first;
        while (x.next != null) {
            x = x.next;
        }
        return x.key;
    }

    public void deleteMin() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tabla vacia");
        }
        first = first.next;
        n--;
    }

    public void deleteMax() {
        if (isEmpty()) {
            throw new NoSuchElementException("Tabla vacia");
        }
        if (first.next == null) {
            first = null;
        } else {
            NodeST x = first;
            while (x.next.next != null) {
                x = x.next;
            }
            x.next = null;
        }
        n--;
    }

    public Key floor(Key key) {
        Key best = null;
        for (NodeST x = first; x != null; x = x.next) {
            int cmp = key.compareTo(x.key);
            if (cmp == 0) {
                return x.key;
            }
            if (cmp < 0) {
                break;
            }
            best = x.key;
        }
        return best;
    }

    public Key ceiling(Key key) {
        for (NodeST x = first; x != null; x = x.next) {
            if (x.key.compareTo(key) >= 0) {
                return x.key;
            }
        }
        return null;
    }

    public int rank(Key key) {
        int count = 0;
        for (NodeST x = first; x != null && x.key.compareTo(key) < 0; x = x.next) {
            count++;
        }
        return count;
    }

    public Key select(int k) {
        if (k < 0 || k >= size()) {
            return null;
        }
        NodeST x = first;
        for (int i = 0; i < k; i++) {
            x = x.next;
        }
        return x.key;
    }

    public int size(Key lo, Key hi) {
        if (lo.compareTo(hi) > 0) {
            return 0;
        }
        int count = 0;
        for (NodeST x = first; x != null; x = x.next) {
            if (x.key.compareTo(lo) >= 0 && x.key.compareTo(hi) <= 0) {
                count++;
            }
            if (x.key.compareTo(hi) > 0) {
                break;
            }
        }
        return count;
    }

    // --- Iteradores ---
    public Iterable<Key> keys() {
        return keys(min(), max());
    }

    public Iterable<Key> keys(Key lo, Key hi) {
        List<Key> list = new ArrayList<>();
        for (NodeST x = first; x != null; x = x.next) {
            int cmpLo = x.key.compareTo(lo);
            int cmpHi = x.key.compareTo(hi);
            if (cmpLo >= 0 && cmpHi <= 0) {
                list.add(x.key);
            }
            if (cmpHi > 0) {
                break;
            }
        }
        return list;
    }

    @Override
    public Iterator<Key> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Key> {

        private NodeST current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Key next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            Key key = current.key;
            current = current.next;
            return key;
        }
    }
}
