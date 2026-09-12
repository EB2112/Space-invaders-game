import javax.imageio.IIOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

public class GamePanel extends JPanel implements Runnable{

    //screen settings
    final int originalTileSize = 16;
    final int scale = 3; //scaling for bigger monitors will create 48x48 tiles

    final int tileSize = originalTileSize * scale;
    final int maxScreenCols =12;
    final int maxScreenRows = 18;
    final int screenWidth = tileSize * maxScreenCols; // 576px
    final int screenHeight = tileSize * maxScreenRows; // 864px
    final int FPS = 30; //30 ticks per second

    private int backgroundScrollOffset;
    private BufferedImage background;
    //default starting pos

//     Image ship = ImageIO.read(new File("pixelart/Spaceship.png"));


    PlayerInput playerInput = new PlayerInput();
    Thread gameThread;
    Player player = new Player( this, playerInput);


    public ArrayList<Projectile> projectiles = new ArrayList<>();
    public ArrayList<Enemy> enemies = new ArrayList<>();

    public GamePanel(){
        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(playerInput);
        this.setFocusable(true);
        startThread();
        try {
            background = ImageIO.read(new File("res/starfield_background.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }


public void startThread(){
        gameThread = new Thread(this);
        gameThread.start();

}
    @Override
    public void run() {
        double updateInterval = 1000000000/FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;
        int addEnemy = 0;

            while (gameThread != null){
                currentTime = System.nanoTime();

                delta += (currentTime - lastTime) / updateInterval; // adds up to draw interval

                lastTime = currentTime;
                if(delta >= 1){
                    checkCollision();
                    update();
                    repaint();
                    delta--;
                    addEnemy++;
                }
                if(addEnemy >= 60){
                    Enemy enemy = new Enemy(this);
                    enemies.add(enemy);
                    addEnemy = 0;
                }





            }


    }

    public void update(){
        backgroundScrollOffset++;
        if(backgroundScrollOffset >= 900){
            backgroundScrollOffset= 0;
            System.out.println("offset reset");
        }
    player.update();
    for (int i = 0; i < enemies.size(); i++){
        if (enemies.get(i).isAlive){
            enemies.get(i).update();
        }
        if(!enemies.get(i).isAlive){
            enemies.remove(i);
        }
    }
    for(int i = 0; i < projectiles.size(); i++){
        if(projectiles.get(i).alive == true){
            projectiles.get(i).update();
        }
        if(projectiles.get(i).alive == false){
            projectiles.remove(i);
        }

    }
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(background, 0, backgroundScrollOffset, 600, 900, null);
        g2.drawImage(background, 0, backgroundScrollOffset - 900, 600, 900, null);
        player.draw(g2);
        for (Enemy e : enemies){
            e.draw(g2);
        }
        if(projectiles.size() > 0){
            for(Projectile p : projectiles){

                p.draw(g2);
            }
        }

        g2.dispose();
    }
    public void checkCollision(){
        for(int i = 0; i< projectiles.size(); i++){
            for (int j = 0; j < enemies.size(); j++){
                if(projectiles.get(i).posX <= enemies.get(j).xHitbox && projectiles.get(i).posY <= enemies.get(j).yHitbox &&
                        projectiles.get(i).xHitbox >= enemies.get(j).posX && projectiles.get(i).yHitbox >= enemies.get(j).posY){
                    projectiles.get(i).alive = false;
                    enemies.get(j).isAlive = false;
                    break;


                }

            }
        }
    }
}
