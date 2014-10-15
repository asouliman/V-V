package vv.tp3;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.*;

public class SimpleMap<K,V> implements Map<K,V> {

    protected ArrayList<K> keys;
    protected ArrayList<V> values;

    public SimpleMap() {
        keys = new ArrayList<K>();
        values = new ArrayList<V>();
    }

    @Override
    public int size() {
        return keys.size();
    }

    @Override
    public boolean isEmpty() {
        return keys.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return keys.contains(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return values.contains(value);
    }

    @Override
    public V get(Object key) {
        int index = keys.indexOf(key);

        return index == -1 ? null : values.get(index);
    }

    @Override
    public V put(K key, V value) {
        // Check if the key exists
        for (int i = 0; i < keys.size(); i++) {
            K k = keys.get(i);

            // The key does exist
            if (key.equals(k)) {
                values.set(i, value);
                return value;
            }
        }

        // Else add the new value
        keys.add(key);
        values.add(value);

        return null;
    }

    @Override
    public V remove(Object key) {
        int i = keys.indexOf(key);

        if (i == -1)
            return null;

        V value = values.get(i);
        keys.remove(i);
        values.remove(i);

        return value;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        Iterator it = m.keySet().iterator();

        while (it.hasNext()) {
            K k = (K)it.next();
            V v = m.get(k);

            put(k, v);
        }
    }

    @Override
    public void clear() {
        keys.clear();
        values.clear();
    }

    @Override
    public Set<K> keySet() {
        return new TreeSet(keys);
    }

    @Override
    public Collection<V> values() {
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        throw new NotImplementedException();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleMap simpleMap = (SimpleMap) o;

        /*
        if (keys == null || simpleMap.keys == null || !keys.equals(simpleMap.keys))
            return false;

        if (values == null || simpleMap.values == null || !values.equals(simpleMap.values))
            return false;
        */

        if (keys != null ? !keys.equals(simpleMap.keys) : simpleMap.keys != null) return false;
        if (values != null ? !values.equals(simpleMap.values) : simpleMap.values != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = keys != null ? keys.hashCode() : 0;
        result = 31 * result + (values != null ? values.hashCode() : 0);
        return result;
    }
}
