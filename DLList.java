public class DLList<E> {
    
    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DLList() {
        head = new Node<E>(null);
        tail = new Node<E>(null);
        head.setNext(tail);
        tail.setPrev(head);
        size = 0;
    }


    public E get(int index) {
        Node<E> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next();
            }
            return current.get();
        }
        else {
            current = tail.prev();
            for (int i = size - 1; i > index; i--) {
                current = current.prev();
            }
            return current.get();
        }
    }

    public Node<E> getNode(int index) {
        Node<E> current;
        if (index <= size / 2) {
            current = head;
            for (int i = 0; i < index; i++) {
                current = current.next();
            }
            return current;
        }
        else {
            current = tail.prev();
            for (int i = size - 1; i > index; i--) {
                current = current.prev();
            }
            return current;
        }
    }

    public void add(E data) {
        Node<E> newNode = new Node<E>(data);
        if (size == 0) {
            newNode.setNext(tail);
            tail.setPrev(newNode);
            head = newNode;
            size++;
        }
        else {
            newNode.setNext(tail);
            newNode.setPrev(tail.prev());
            tail.prev().setNext(newNode);
            tail.setPrev(newNode);
            size++;
        }
    }

    public void add(int index, E data) {
        Node<E> newNode = new Node<E>(data);
        if (size == 0) {
            newNode.setNext(tail);
            tail.setPrev(newNode);
            head = newNode;
            size++;
        }
        else {
            Node<E> current = getNode(index);
            newNode.setPrev(current.prev());
            newNode.setNext(current);
            current.prev().setNext(newNode);
            current.setPrev(newNode);            
            size++;
        }
    }


    public void remove(E data) {
        if (head.get().equals(data)) {
            head = head.next();
            size--;
        }
        else {
            Node<E> current = head;
            while (current.next() != null) {
                if (current.get().equals(data)) {
                    current.prev().setNext(current.next());
                    current.next().setPrev(current.prev());
                    size--;
                }
                current = current.next();
            }
        }
    }

    public void remove(int index) {
        if (index == 0) {
            head = head.next();
            size--;
        }
        else {

            Node<E> current = getNode(index);
            current.prev().setNext(current.next());
            current.next().setPrev(current.prev());
            size--;
        }

    }


    public void set(int index, E data) {
        Node<E> newNode = new Node<E>(data);
        if (index == 0) {
            newNode.setNext(head.next());
            head.next().setPrev(newNode);
            head = newNode;

        }
        else {
            Node<E> current = getNode(index);
            newNode.setPrev(current.prev());
            newNode.setNext(current.next());
            newNode.prev().setNext(newNode);
            newNode.next().setPrev(newNode);
        }
    }
    

    public String toString() {
        Node<E> current = head;
        String output = "";
        while (current.next() != null) {
            output += current.get().toString() + "\n";
            current = current.next();
        }
        return output;
    }

    public int size() {
        return size;
    }
}



/* 

    public void add(int index, E data) {
        Node<E> newNode = new Node<E>(data);
        if (index == 0) {
            head.setPrev(newNode);
            newNode.setNext(head);
            head = newNode;
            size++;
        }
        else {
            Node<E> current = getNode(index);
            newNode.setNext(current);
            newNode.setPrev(current.prev());
            newNode.prev().setNext(newNode);
            current.setPrev(newNode);
            size++;
        }
    }

    public E get(int index) {
        return getNode(index).get();
    }

    public E remove(int index) {

        if (index == 0) {
            head = head.next();
            size--;
            return head.get();
        }

        Node<E> current = getNode(index);
        current.prev().setNext(current.next());
        current.next().setPrev(current.prev());
        size--;
        return current.get();
    }

    public boolean remove(E data) {
        Node<E> current = head;

        while (current.next() != null) {
            System.out.println(current.get().toString() + "   " + data.toString());
            if (current.get().equals(data)) {
                current.prev().setNext(current.next());
                current.next().setPrev(current.prev());
                size--;
                return true;
            }
            current = current.next();
        }
        
        return false;
    }

    public void set(int index, E data) {
        Node<E> current = getNode(index);
        Node<E> newNode = new Node<E>(data);
        newNode.setPrev(current.prev());
        newNode.setNext(current.next());
        newNode.prev().setNext(newNode);
        newNode.next().setPrev(newNode);
    }
    */



