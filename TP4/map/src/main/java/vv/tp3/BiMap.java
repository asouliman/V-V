package vv.tp3;

/**
 * Created by Simon on 30/09/14.
 */
public class BiMap<K,V> extends SimpleMap<K,V> {

    public BiMap() {
        super();
    }

    public K getByValue(Object value) {
        int index = values.indexOf(value);
        return (index < 0) ? null : keys.get(index);
    }

    public K removeValue(Object value) {
        if (containsValue(value)) {
            int index = values.indexOf((V) value);
            values.remove(index);
            return keys.remove(index);
        }
        return null;
    }

    @Override
    public V put(K key, V value) {
        int iKey = keys.indexOf(key);
        int iValue = values.indexOf(value);

        if (iKey < 0 && iValue < 0) {
            keys.add(key);
            values.add(value);
        }
        else if (iKey < 0 && iValue >= 0) {
            keys.set(iValue, key);
        }
        else if (iKey >= 0 && iValue < 0) {
            values.set(iKey, value);
        }
        else {
            keys.remove(iKey);
            values.remove(iKey);
            if (iKey != iValue) {
                keys.remove(iValue);
                values.remove(iValue);
            }
            keys.add(key);
            values.add(value);
        }

        return value;
    }

}