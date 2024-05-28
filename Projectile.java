import java.io.Serializable;

public class Projectile implements Serializable {
    
    private int id;
    private double x;
    private double y;
    private double xMove;
    private double yMove;
    private int age;


    public Projectile(int id, int x, int y, Double xMove, Double yMove) {
        this.id = id;
        this.x = x;
        this.y = y;
        this.xMove = xMove;
        this.yMove = yMove;

        age = 0;
    }

    public Projectile(String request) {
        updateData(request);

        age = 0;
    }

    public void move() {
        x += xMove;
        y += yMove;
        age++;
    }

    public int getID() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }


    public boolean equals(Object o) {
        Projectile projectile = (Projectile)o;
        return id == projectile.getID();
    }

    public String toString() {
        String request = id + "," + x + "," + y + "," + xMove + "," + yMove + "!";
        return request;
    }

    public void updateData(String request) {
        this.id = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.x = Double.parseDouble(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.y = Double.parseDouble(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.xMove = Double.parseDouble(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.yMove = Double.parseDouble(request.substring(0, request.indexOf('!')));

    }
}
