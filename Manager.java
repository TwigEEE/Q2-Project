public class Manager {

    private HashMap<Integer, ServerThread> threads;
    private HashMap<Integer, Game> games;

    private int gameIDCount;
    private boolean updating;
    
    public Manager() {
        threads = new HashMap<Integer, ServerThread>();
        games = new HashMap<Integer, Game>();
        games.put(0, new Game(0));

        gameIDCount = 1;
        updating = false;
    }

    public void addServerThread(ServerThread serverThread) {
        threads.put(serverThread.getID(), serverThread);
    }

    public void removeServerThread(ServerThread serverThread) {
        threads.remove(serverThread.getID());
    }

    public Game getOpenGame() {

        DLList<Integer> keys = games.keySet().toDLList();
        for (int i = 0; i < keys.size(); i++) {
            if (games.get(keys.get(i)).getStatus().equals("open")) {
                return games.get(keys.get(i));
            }
        }
        return null;
    }

    public void queueUpdate(Game game) {
        if (!updating) {
            updating = true;
            updateGameInfo(game);
        }
    }

    public void updateGameInfo(Game game) {
        updating = true;
    
        ArrayList<Player> players = game.getPlayers();
        for (int i = 0; i < players.size(); i++) {
            threads.get(players.get(i).getID()).queueUpdate(game);
        }

        updating = false;
    }

    public void startGame(Game game) {
        game = games.get(game.getID());
        if (game.isReadyToStart()) {
            game.setStatus("playing");
            games.put(gameIDCount, new Game(gameIDCount));
            gameIDCount++;

            ArrayList<Player> players = game.getPlayers();
            for (int i = 0; i < players.size(); i++) {
                threads.get(players.get(i).getID()).startGame();
            }

            Thread thread = new Thread(new GameThread(this, game));
            thread.start();
        }

    }

    public void playerDied(Player player) {
        threads.get(player.getID()).playerDied();
    }

    public void playerWin(Player player) {
        threads.get(player.getID()).playerWin();
    }

   
}