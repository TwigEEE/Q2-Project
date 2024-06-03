public class HashSet<E> {
  
    private Object[] hashArray;
    int size;

    public HashSet( ){
            hashArray = new Object[99999999];
            size = 0;
    }

    public boolean add(E obj) {
        
        int location = obj.hashCode();
       
        if( hashArray[location] == null){
            hashArray[location] = obj;
            size++;
            return true;
        }
        return false;
    }


    public boolean contains(E obj){
        if(hashArray[obj.hashCode()] != null){
            return true;
        }
        return false;
    }

    public boolean remove(E obj){
        if(hashArray[obj.hashCode()] != null){
            hashArray[obj.hashCode()] = null;
            size--;
            return true;
        }
        return false;
    }

    public int size(){
        return size;
    }
    public void clear(){
        hashArray = new Object[150];
        size = 0;
    }

    @SuppressWarnings("unchecked")
    public DLList<E> toDLList(){
        DLList<E> list = new DLList<E>();
        
        for (Object o : hashArray) {
            if (o != null) {
                list.add((E)o);
            }
        }
        return list;
    }

}
