public class ArrayList<E> {

    private Object[] list;
    private int capacity;
    private int size;

    public ArrayList() {
        capacity = 10;
        size = 0;

        list = new Object[10];
    }

    public boolean add(E element) {
        try {
            list[size] = element;
            size++;

            if (size >= capacity) {
                capacity *= 2;
                Object[] tempList = list;
                list = new Object[capacity];
                for (int i = 0; i < size; i++) {
                    list[i] = tempList[i];
                }
            }

            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean add(int index, E element) {
        try {

            Object[] tempList = list;
            list = new Object[capacity];
            for (int i = 0; i < index; i++) {
                list[i] = tempList[i];
            }
            list[index] = element;
            for (int i = index + 1; i < size + 1; i++) {
                list[i] = tempList[i - 1];
            }
            size++;

            if (size >= capacity) {
                capacity *= 2;
                tempList = list;
                list = new Object[capacity];
                for (int i = 0; i < size; i++) {
                    list[i] = tempList[i];
                }
            }
            
            return true;
        }
        catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    public E get(int index) {
        return (E)(list[index]);
    }

    @SuppressWarnings("unchecked")
    public E remove(int index) {
        Object[] tempList = list;
        list = new Object[capacity];
        for (int i = 0; i < index; i++) {
            list[i] = tempList[i];
        }
        for (int i = index + 1; i < size; i++) {
            list[i - 1] = tempList[i];
        }
        size--;

        return (E)tempList[index];
        
    }

    public E remove(E e) {
        for (int i = 0; i < size; i++) {
            if (list[i].equals(e)) {
                return remove(i);
            }
        }
        return null;
    }

    public void set(int index, E obj) {
        list[index] = obj;
    }

    public String toString() {
        String output = "";
        for (int i = 0; i < size; i++) {
            output += list[i] + " ";
        }
        return output;
    }

    public int size() {
        return size;
    }

    public void clear() {
        list = new Object[capacity];
        size = 0;
    }
}