import java.io.Serializable;
import java.util.ArrayList;

public class Game implements Serializable {
    
    private int id;
    public String status;
    private ArrayList<Player> players;
    private ArrayList<Projectile> projectiles;


    public Game(int id) {
        this.id = id;

        status = "open";
        players = new ArrayList<Player>();
        projectiles = new ArrayList<Projectile>();
    }



    public int getID() {
        return id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
  
    public int getNumPlayers() {
        return players.size();
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void addPlayer(Player player) {
        players.add(player);
    }

    public void removePlayer(Player player) {
        players.remove(player);
    }

    public void updatePlayer(Player player) {
        for (int i = 0; i < players.size(); i++) {
            if (player.equals(players.get(i))) {
                players.set(i, player);
                break;
            }
        }
    }

    public boolean isReadyToStart() {
        boolean ready = true;
        if (players.size() > 1) {
            for (int i = 0; i < players.size(); i++) {
                if (!players.get(i).isReady()) {
                    ready = false;
                }
            }
            return ready;
        }
        return false;
    }

    public boolean equals(Object o) {
        Game game = (Game)o;

        if (game.getID() == id) {
            return true;
        }
        return false;
    }

    public void addProjectile(Projectile projectile) {
        projectiles.add(projectile);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }


}
