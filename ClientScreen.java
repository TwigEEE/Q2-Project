import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;
import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;
import java.io.*;
import java.net.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class ClientScreen extends JPanel implements ActionListener, MouseListener, KeyListener {
    
    private JTextField usernameField;
    private JButton playB;
    private JButton tutorialB;
    private JButton returnB;
    private JButton exitTutorialB;

    private JButton exitGameB;
    private JButton readyB;

    private JButton selectMud;
    private JButton selectPygmy;
    private JButton selectViridian;





    private PrintWriter out;
    private BufferedReader in;

    

    private Player player;
    private String currentScreen;
    private ArrayList<Player> players;
    private ArrayList<Projectile> projectiles;


    private HashMap<Dragon, Dragon> dragonInfo;


    public ClientScreen() {
        this.setLayout(null);

        currentScreen = "mainMenu";
        players = new ArrayList<Player>();   
        projectiles = new ArrayList<Projectile>();
        player = new Player(0, "", "MudWyvern");


        dragonInfo = new HashMap<Dragon, Dragon>();
        dragonInfo.put(new Dragon("Mud Wyvern"), new Dragon("Mud Wyvern", "images/MudWyvern1.png", "images/fireball.png", 48, 15, 20));
        dragonInfo.put(new Dragon("Pygmy Wyvern"), new Dragon("Pygmy Wyvern", "images/PygmyWyvern2.png", "images/fireball.png", 48, 25, 15));
        dragonInfo.put(new Dragon("Viridian Drake"), new Dragon("Viridian Drake", "images/ViridianDrake4.png", "images/fireball.png", 48, 10, 25));



        usernameField = new JTextField();
        usernameField.setBounds(624, 300, 482, 84);
        this.add(usernameField);

        playB = new JButton();
        playB.setBounds(114, 543, 416, 110);
        this.add(playB);
        playB.addActionListener(this);
        playB.setOpaque(false);
        playB.setContentAreaFilled(false);
        playB.setBorderPainted(false);
        playB.setFocusPainted(false);
        playB.setFocusable(false);

        tutorialB = new JButton();
        tutorialB.setBounds(634, 539, 416, 110);
        this.add(tutorialB);
        tutorialB.addActionListener(this);
        tutorialB.setOpaque(false);
        tutorialB.setContentAreaFilled(false);
        tutorialB.setBorderPainted(false);
        tutorialB.setFocusPainted(false);
        tutorialB.setFocusable(false);

        returnB = new JButton();
        returnB.setBounds(634, 539, 416, 110);
        this.add(returnB);
        returnB.addActionListener(this);
        returnB.setOpaque(false);
        returnB.setContentAreaFilled(false);
        returnB.setBorderPainted(false);
        returnB.setFocusPainted(false);
        returnB.setFocusable(false);
        returnB.setVisible(false);

        exitTutorialB = new JButton("BACK");
        exitTutorialB.setBounds(10, 750, 100, 50);
        this.add(exitTutorialB);
        exitTutorialB.addActionListener(this);
        exitTutorialB.setVisible(false);

        exitGameB = new JButton();
        exitGameB.setBounds(444, 675, 311, 101);
        this.add(exitGameB);
        exitGameB.addActionListener(this);
        exitGameB.setOpaque(false);
        exitGameB.setContentAreaFilled(false);
        exitGameB.setBorderPainted(false);
        exitGameB.setFocusPainted(false);
        exitGameB.setFocusable(false);
        exitGameB.setVisible(false);

        readyB = new JButton();
        readyB.setBounds(850, 675, 311, 101);
        this.add(readyB);
        readyB.addActionListener(this);
        readyB.setOpaque(false);
        readyB.setContentAreaFilled(false);
        readyB.setBorderPainted(false);
        readyB.setFocusPainted(false);
        readyB.setFocusable(false);
        readyB.setVisible(false);

        Icon icon = new ImageIcon("images/MudWyvern1.png");
        selectMud = new JButton(icon);
        selectMud.setBounds(120, 150, 100, 100);
        this.add(selectMud);
        selectMud.addActionListener(this);
        selectMud.setVisible(false);
        selectMud.setFocusable(false);

        icon = new ImageIcon("images/PygmyWyvern1.png");
        selectPygmy = new JButton(icon);
        selectPygmy.setBounds(120, 270, 100, 100);
        this.add(selectPygmy);
        selectPygmy.addActionListener(this);
        selectPygmy.setVisible(false);
        selectPygmy.setFocusable(false);

        icon = new ImageIcon("images/ViridianDrake1.png");
        selectViridian = new JButton(icon);
        selectViridian.setBounds(120, 390, 100, 100);
        this.add(selectViridian);
        selectViridian.addActionListener(this);
        selectViridian.setVisible(false);
        selectViridian.setFocusable(false);

        

        

        addMouseListener(this);
        addKeyListener(this);
        this.setFocusable(true);
    }

    public Dimension getPreferredSize() {
		return new Dimension(1200, 800);
	}

    public void paintComponent(Graphics g){
		super.paintComponent(g);
		

        if (currentScreen.equals("mainMenu")) {
            try {
                g.drawImage(ImageIO.read(new File("images/mainMenu.png")), 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

        } else if (currentScreen.equals("tutorial")) {
            try {
                g.drawImage(ImageIO.read(new File("images/tutorial.png")), 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (currentScreen.equals("lobby")) {
            try {
                g.drawImage(ImageIO.read(new File("images/lobby.png")), 0, 0, null);
            } catch (IOException e) {
                e.printStackTrace();
            }

            for (int i = 0; i < players.size(); i++) {
                g.setColor(new Color(0, 0, 0));
                g.fillRect(400 + 170 * i, 20, 150, 150);
                if (players.get(i).isReady()) {
                    g.setColor(new Color(68, 214, 44));
                } else {
                    g.setColor(new Color(210, 4, 45));
                }
                g.fillRect(405 + 170 * i, 25, 140, 140);
                g.setColor(new Color(0, 0, 0));
                g.setFont(new Font("SansSerif", Font.PLAIN, 24));
                g.drawString(players.get(i).getUsername(), 420 + 170 * i, 60);
                try {
                    g.drawImage(ImageIO.read(new File(players.get(i).getGifURL())), 440 + 170 * i, 80, null);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                
            }
        } else if (currentScreen.equals("game")) {

            g.setColor(new Color(0, 0, 0));
            g.setColor(new Color(20, 100, 150));
            g.fillRect(0, 0, 1200, 800);
            g.setColor(new Color(0, 0, 0));

            
            try {
                g.drawImage(ImageIO.read(new File("images/battleBackground.png")), -1*player.getX() + 560, player.getY() - 1200 + 360 + dragonInfo.get(new Dragon(player.getDragonName())).getSize(), null);
                g.drawImage(new ImageIcon(getClass().getResource(player.getGifURL())).getImage(), (int)(576 - 0.5 * dragonInfo.get(new Dragon(player.getDragonName())).getSize()), (int)(376 - 0.5 * dragonInfo.get(new Dragon(player.getDragonName())).getSize()), null);
                g.setColor(Color.RED);
                g.fillRect((int)(576 - 0.5 * dragonInfo.get(new Dragon(player.getDragonName())).getSize()), (int)(366 - 0.5 * dragonInfo.get(new Dragon(player.getDragonName())).getSize()), 50 * player.getHealth() / 100, 10);


                for (int i = 0; i < players.size(); i++) {
                    if (!players.get(i).equals(player)) {
                        g.drawImage(new ImageIcon(getClass().getResource(players.get(i).getGifURL())).getImage(), players.get(i).getX() - player.getX() + 576 - 24, player.getY() - players.get(i).getY() + 376 - 24, null);
                        g.setColor(Color.RED);
                        g.fillRect(players.get(i).getX() - player.getX() + 576 - 24, player.getY() - players.get(i).getY() + 366 - 24, 50 * players.get(i).getHealth() / 100, 10);

                    }
                }

                for (int i = 0; i < projectiles.size(); i++) {
                    try {
                        g.drawImage(ImageIO.read(new File("images/fireball.png")), projectiles.get(i).getX() - player.getX() + 576, player.getY() - projectiles.get(i).getY() + 376, null);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            
        } else if (currentScreen.equals("lost")) {
            try {
                g.drawImage(new ImageIcon(getClass().getResource("images/loseScreen.png")).getImage(), 0, 0, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (currentScreen.equals("won")) {
            try {
                g.drawImage(new ImageIcon(getClass().getResource("images/winScreen.png")).getImage(), 0, 0, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
       
	}




    public void actionPerformed(ActionEvent e) {
		if (e.getSource() == playB) {

            String username = usernameField.getText();
            out.println("reusername?" + username + "!");
            out.println("jg?");



            usernameField.setVisible(false);
            playB.setVisible(false);
            tutorialB.setVisible(false);
            exitGameB.setVisible(true);
            readyB.setVisible(true);
            selectMud.setVisible(true);
            selectPygmy.setVisible(true);
            selectViridian.setVisible(true);
            currentScreen = "lobby";

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == tutorialB) {
            usernameField.setVisible(false);
            playB.setVisible(false);
            tutorialB.setVisible(false);
            exitGameB.setVisible(false);
            readyB.setVisible(false);
            exitTutorialB.setVisible(true);
            currentScreen = "tutorial";

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == exitTutorialB) {
            usernameField.setVisible(true);
            playB.setVisible(true);
            tutorialB.setVisible(true);
            exitTutorialB.setVisible(false);
            currentScreen = "mainMenu";

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == returnB) {
            usernameField.setVisible(true);
            playB.setVisible(true);
            tutorialB.setVisible(true);
            exitGameB.setVisible(false);
            readyB.setVisible(false);
            returnB.setVisible(false);
            currentScreen = "mainMenu";

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == exitGameB) {
            
            out.println("lg?");

            usernameField.setVisible(true);
            playB.setVisible(true);
            tutorialB.setVisible(true);
            exitGameB.setVisible(false);
            readyB.setVisible(false);
            selectMud.setVisible(false);
            selectPygmy.setVisible(false);
            selectViridian.setVisible(false);
            currentScreen = "mainMenu";

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == readyB) {
            out.println("ready?");

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == selectMud) {
            player.setDragonName("MudWyvern");
            out.println("repl?" + player.toString());

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == selectPygmy) {
            player.setDragonName("PygmyWyvern");
            out.println("repl?" + player.toString());

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }

        if (e.getSource() == selectViridian) {
            player.setDragonName("ViridianDrake");
            out.println("repl?" + player.toString());

            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/button.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }
            repaint();
        }


    }




    public void mousePressed(MouseEvent e) {
        int x = e.getX();
        int y = e.getY();    

        if (currentScreen.equals("game")) {
            Double angle = Math.atan((double)(y - 376)/(x - 576));
            Double xMove = 0.0;
            Double yMove = 0.0;
            if (x - 576 >= 0) {
                xMove = Math.cos(angle) * 20;
                yMove = Math.sin(angle) * -20;
            } else {
                xMove = Math.cos(angle) * -20;
                yMove = Math.sin(angle) * 20;
            }

            out.println("crprojectile?" + player.getID() + "," + player.getX() + "," + player.getY() + "," + xMove + "," + yMove + "!");
     
            try {
                URL url = this.getClass().getClassLoader().getResource("sounds/shoot.wav");
                Clip clip = AudioSystem.getClip();
                clip.open(AudioSystem.getAudioInputStream(url));
                clip.start();
            } catch (Exception exc) {
                exc.printStackTrace(System.out);
            }

            
            repaint();
        }
    }
    public void mouseEntered(MouseEvent e) {}
    public void mouseReleased(MouseEvent e) {}
    public void mouseClicked(MouseEvent e) {}
    public void mouseExited(MouseEvent e) {}



    public void keyPressed(KeyEvent e) {
        if (currentScreen.equals("game")) {
            if (e.getKeyCode() == 87) {
                player.setYMove(1);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 68) {
                player.setXMove(1);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 83) {
                player.setYMove(-1);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 65) {
                player.setXMove(-1);
                out.println("repl?" + player.toString());
            }
        }
    }
    public void keyReleased(KeyEvent e) {
        if (currentScreen.equals("game")) {
            if (e.getKeyCode() == 87) {
                player.setYMove(0);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 68) {
                player.setXMove(0);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 83) {
                player.setYMove(0);
                out.println("repl?" + player.toString());
            }
            if (e.getKeyCode() == 65) {
                player.setXMove(0);
                out.println("repl?" + player.toString());
            }
        }
    }
    public void keyTyped(KeyEvent e){}





    public void connect() throws IOException {

        //String hostName = "10.210.105.181";
        String hostName = "localHost";
        int portNumber = 1023;
        Socket serverSocket = new Socket(hostName, portNumber);

        out = new PrintWriter(serverSocket.getOutputStream(), true);
        in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream()));

    
        while (true) {
            try {

                String request = in.readLine();
                
                String command = request.substring(0, request.indexOf('?'));
                request = request.substring(request.indexOf("?") + 1);

                if (command.equals("repl")) {
                    player.updateData(request);
                } else if (command.equals("repls")) {
                    players.clear();
                    while (!request.equals("!")) {
                        players.add(new Player(request.substring(0, request.indexOf("&&"))));
                        request = request.substring(request.indexOf("&&") + 2);
                    }
                } else if (command.equals("stg")) {
                    player.setPos((int)(Math.random() * 1160), (int)(Math.random() * 1140));
                    out.println("repl?" + player.toString());
                    selectMud.setVisible(false);
                    selectPygmy.setVisible(false);
                    selectViridian.setVisible(false);
                    currentScreen = "game";
                } else if (command.equals("reprojectiles")) {
                    projectiles.clear();
                    while (!request.equals("!")) {
                        projectiles.add(new Projectile(request.substring(0, request.indexOf("&&"))));
                        request = request.substring(request.indexOf("&&") + 2);
                    }
                } else if (command.equals("died")) {
                    returnB.setVisible(true);
                    playB.setVisible(true);
                    player.reset();
                    out.println("repl?" + player.toString());
                    currentScreen = "lost";
                } else if (command.equals("win")) {
                    returnB.setVisible(true);
                    playB.setVisible(true);
                    player.reset();
                    out.println("repl?" + player.toString());
                    currentScreen = "won";
                }


                repaint();
                
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


