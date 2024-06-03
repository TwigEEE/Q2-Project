public class HashMap<K, V> {

    private Object[] hashArray;
    private int size;
    public HashSet<K> keySet;

    public HashMap() {
        hashArray = new Object[500];
        size = 0;
        keySet = new HashSet<K>();
    }

    public boolean put(K key, V value) {
        if (keySet.add(key)) {
            hashArray[key.hashCode()] = value;
            size++;
            return true;
        }
        else {
            return false;
        }
    }
    
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        return ((V)hashArray[key.hashCode()]);
    }

    @SuppressWarnings("unchecked")
    public V remove(Object key) {
        if (keySet.contains((K)(key))) {
            keySet.remove((K)(key));
            Object value = hashArray[key.hashCode()];
            hashArray[key.hashCode()] = null;
            size--;
            return (V)(value);
        }
        else {
            return null;
        }
    }

    public int size() {
        return size;
    }
    
    public HashSet<K> keySet() {
        return keySet;
    }
}