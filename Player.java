import java.io.Serializable;

public class Player implements Serializable {
    
    private int id;
    private String username;
    private Boolean ready;

    private int x;
    private int y;
    private int xMove;
    private int yMove;

    private int health;

    private String dragonName;
    private int currentURL;


    public Player(int id, String username, String dragonName) {
        this.id = id;
        this.username = username;
        this.dragonName = dragonName;

        ready = false;
        x = 0;
        y = 0;
        xMove = 0;
        yMove = 0;
        health = 100;

        currentURL = 1;

    }

    public Player(String request) {
        updateData(request);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getID() {
        return id;
    }

    public boolean isReady() {
        return ready;
    }

    public void setReady(boolean ready) {
        this.ready = ready;
    }

    public void setPos(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setXMove(int x) {
        this.xMove = x;
    }

    public void setYMove(int y) {
        this.yMove = y;
    }

    public void move(int speed) {
        if (x + xMove >= 0 && x + xMove <= 1160) {
            x += xMove * speed;
        }
        if (y + yMove >= 0 && y + yMove <= 1140) {
            y += yMove * speed;
        }
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public void animate() {
        currentURL += 1;
        if (currentURL > 4) {
            currentURL = 1;
        }
    }

    public String getGifURL() {
        return "images/" + dragonName + currentURL + ".png";
    }

    public String getDragonName() {
        return dragonName;
    }

    public void setDragonName(String dragonName) {
        this.dragonName = dragonName;
    }



    public boolean equals(Object o) {
        Player player = (Player)o;
        if (player.getID() == id) {
            return true;
        }
        return false;
    }

    public String toString() {
        String output = id + "," + username + "," + ready + "," + x + "," + y + "," + xMove + "," + yMove + "," + health + "," + dragonName + "," + currentURL + "!";
        return output;
    }

    public void updateData(String request) {
        this.id = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.username = request.substring(0, request.indexOf(','));
        request = request.substring(request.indexOf(',') + 1);
        this.ready = Boolean.parseBoolean(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.x = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.y = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.xMove = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.yMove = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.health = Integer.parseInt(request.substring(0, request.indexOf(',')));
        request = request.substring(request.indexOf(',') + 1);
        this.dragonName = request.substring(0, request.indexOf(','));
        request = request.substring(request.indexOf(',') + 1);
        this.currentURL = Integer.parseInt(request.substring(0, request.indexOf('!')));

    }

    public void reset() {
        x = 0;
        y = 0;
        xMove = 0;
        yMove = 0;
        health = 100;
    }


}
