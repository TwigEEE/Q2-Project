import java.io.IOException;
import java.util.ArrayList;

public class GameThread implements Runnable {
        
    private Manager manager;
    private Game game;

    private ArrayList<Projectile> projectiles;


    public GameThread(Manager manager, Game game) {
        this.manager = manager;
        this.game = game;
        projectiles = new ArrayList<Projectile>();
    }


    @Override
    public void run() {
        int counter = 0;
        while (true) {
            counter++;
            System.out.println("GameTHREAD: " + counter);

            try {
                Thread.sleep(150);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ArrayList<Player> players = game.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                players.get(i).move();
                players.get(i).animate();
            }

            projectiles = game.getProjectiles();
            for (int i = 0; i < projectiles.size(); i++) {
                projectiles.get(i).move();
            }


            for (int i = 0; i < projectiles.size(); i++) {
                for (int j = 0; j < players.size(); j++) {
                    if (!(projectiles.get(i).getID() == players.get(j).getID())) {
                        if (Math.sqrt(Math.pow(players.get(j).getX() - projectiles.get(i).getX(), 2) + Math.pow(players.get(j).getY() - projectiles.get(i).getY(), 2)) < 30) {
                            projectiles.remove(i);
                            i--;
                            players.get(j).takeDamage(50);
                            if (players.get(j).getHealth() <= 0) {
                                manager.playerDied(players.get(j));
                                game.removePlayer(players.get(j));
                                break;
                            }
                        }
                    }
                }
            }


            if (game.getPlayers().size() == 1) {
                manager.playerWin(game.getPlayers().get(0));
                break;
            } else if (game.getPlayers().size() < 1) {
                break;
            }


            manager.queueUpdate(game);

        }
    }
}