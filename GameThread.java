public class GameThread implements Runnable {
        
    private Manager manager;
    private Game game;

    private ArrayList<Projectile> projectiles;
    private HashMap<Dragon, Dragon> dragonInfo;



    public GameThread(Manager manager, Game game) {
        this.manager = manager;
        this.game = game;
        projectiles = new ArrayList<Projectile>();

        dragonInfo = new HashMap<Dragon, Dragon>();
        dragonInfo.put(new Dragon("Mud Wyvern"), new Dragon("Mud Wyvern", "images/MudWyvern1.png", "images/fireball.png", 48, 15, 20));
        dragonInfo.put(new Dragon("Pygmy Wyvern"), new Dragon("Pygmy Wyvern", "images/PygmyWyvern2.png", "images/fireball.png", 48, 25, 15));
        dragonInfo.put(new Dragon("Viridian Drake"), new Dragon("Viridian Drake", "images/ViridianDrake4.png", "images/fireball.png", 48, 10, 25));


    }


    @Override
    public void run() {
        while (true) {

            try {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                ArrayList<Player> players = game.getPlayers();
                for (int i = 0; i < players.size(); i++) {
                    players.get(i).move(dragonInfo.get(new Dragon(players.get(i).getDragonName())).getSpeed());
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
                                
                                for (int a = 0; a < players.size(); a++) {
                                    if (players.get(a).getID() == projectiles.get(i).getID()) {
                                        players.get(j).takeDamage(dragonInfo.get(new Dragon(players.get(a).getDragonName())).getDamage());
                                        projectiles.remove(i);
                                        i--;
                                        break;
                                    }
                                }
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
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
}