import java.io.Serializable;

public class Dragon implements Serializable {
    
    private String name;
    private String imageURL;
    private String projectileURL;
    private int size;
    private int speed;
    private int damage;

    public Dragon(String name, String imageURL, String projectileURL, int size, int speed, int damage) {
        this.name = name;
        this.imageURL = imageURL;
        this.projectileURL = projectileURL;
        this.size = size;
        this.speed = speed;
        this.damage = damage;
    }

    public Dragon(String name) {
        this.name = name;
    }


    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getProjectileURL() {
        return projectileURL;
    }

    public int getSize() {
        return size;
    }

    public String toString() {
        return name;
    }

    public int getSpeed() {
        return speed;
    }

    public int getDamage() {
        return damage;
    }



    public int hashCode() {
        return (int)(name.charAt(0));
    }



}
