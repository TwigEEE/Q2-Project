import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.io.BufferedReader;
import java.io.EOFException;

import java.util.ArrayList;


public class ServerThread implements Runnable {
    private Socket clientSocket;
    private PrintWriter out;
    private BufferedReader in;
    private Manager manager;
    
    private Player player;
    private Game currentGame;

    private boolean updating;


    public ServerThread(Socket clientSocket, Manager manager, int id) {
        this.clientSocket = clientSocket;
        this.manager = manager;
        this.player = new Player(id, "");

        currentGame = null;
        updating = false;
    }

    public int getID() {
        return player.getID();
    }

    public void startGame() {
        out.println("stg?");
    }

    public void queueUpdate(Game game) {
        if (!updating) {
            updating = true;
            updateGameInfo(game);
        }
    }

    public void updateGameInfo(Game game) {
        updating = true;
        currentGame = game;
        ArrayList<Player> players = game.getPlayers();
        String request = "repls?";
        for (int i = 0; i < players.size(); i++) {
            request += players.get(i).toString() + "&&";
        }
        request += "!";

        out.println(request);
        out.println("repl?" + player.toString());


        ArrayList<Projectile> projectiles = game.getProjectiles();
        request = "reprojectiles?";
        for (int i = 0; i < projectiles.size(); i++) {
            request += projectiles.get(i).toString() + "&&";
        }
        request += "!";
        
        out.println(request);


        updating = false;
    }


    public void playerDied() {
        out.println("died?");
    }

    public void playerWin() {
        out.println("win?");
    }




    @Override
    public void run() {
        
        System.out.println(Thread.currentThread().getName() + ": connection opened.");

        try {
            out = new PrintWriter(clientSocket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        } 


        while (true) {
            try {
                String request = in.readLine();
                if (request != null) {
                        
                    String command = request.substring(0, request.indexOf('?'));
                    request = request.substring(request.indexOf("?") + 1);

                    if (command.equals("reusername")) {
                        player.setUsername(request.substring(0, request.indexOf("!")));
                        out.println("repl?" + player.toString());
                    } else if (command.equals("jg")) {
                        player.setReady(false);
                        currentGame = manager.getOpenGame();
                        currentGame.addPlayer(player);
                        manager.queueUpdate(currentGame);
                    } else if (command.equals("lg")) {
                        currentGame.removePlayer(player);
                        manager.queueUpdate(currentGame);
                        currentGame = null;
                    } else if (command.equals("ready")) {
                        player.setReady(true);
                        currentGame.updatePlayer(player);
                        manager.queueUpdate(currentGame);
                        manager.startGame(currentGame);
                    } else if (command.equals("repl")) {
                        player.updateData(request);
                        if (currentGame != null) {
                            currentGame.updatePlayer(player);
                            manager.queueUpdate(currentGame);
                        }
                    } else if (command.equals("crprojectile")) {
                        currentGame.addProjectile(new Projectile(request));
                    }
                }
              
                    
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}