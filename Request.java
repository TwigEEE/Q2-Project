import java.io.Serializable;

public class Request implements Serializable {
    
    public String type;
    private Object[] objects;

    public Request(String type) {
        this.type = type;
    }
    
    public Request(String type, Object[] objects) {
        this.type = type;
        this.objects = objects;
    }

    public String getType() {
        return type;
    }

    public Object[] getObjects() {
        return objects;
    }

}
