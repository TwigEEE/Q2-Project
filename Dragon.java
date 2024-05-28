import java.io.Serializable;
import java.util.ArrayList;

public class Dragon implements Serializable {
    
    private String name;
    private String imageURL;
    private int x;
    private int y;

    public Dragon(String name, String imageURL) {
        this.name = name;
        this.imageURL = imageURL;
    }


    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String toString() {
        return name;
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

    public void step(ArrayList<Dragon> enemies) {
        Dragon closestEnemy = enemies.get(0);
        double closestDistance = Math.sqrt(Math.pow(((double)x - closestEnemy.getX()), 2.0) + Math.pow(((double)y - closestEnemy.getY()), 2.0));
        for (int i = 0; i < enemies.size(); i++) {
            if (Math.sqrt(Math.pow(((double)x - enemies.get(i).getX()), 2.0) + Math.pow(((double)y - enemies.get(i).getY()), 2.0)) < closestDistance) {
                closestEnemy = enemies.get(i);
                closestDistance = Math.sqrt(Math.pow(((double)x - enemies.get(i).getX()), 2.0) + Math.pow(((double)y - enemies.get(i).getY()), 2.0));
            }
        }

        if (x > closestEnemy.getX()) {
            if (y > closestEnemy.getY()) {
                x -= 3;
                y -= 3;
            }
            else if (y < closestEnemy.getY()) {
                x -= 3;
                y += 3;
            }
            else {
                x -= 5;
            }
        } else if (x < closestEnemy.getX()) {
            if (y > closestEnemy.getY()) {
                x += 3;
                y -= 3;
            }
            else if (y < closestEnemy.getY()) {
                x += 3;
                y += 3;
            }
            else {
                x += 5;
            }
        } else {
            if (y < closestEnemy.getY()) {
                y += 5;
            }
            else if (y > closestEnemy.getY()) {
                y -= 5;
            }
        }
    }

}
