import java.awt.*;
import java.awt.image.BufferedImage;

public class Projectile extends Entity{

    GamePanel gp;
    int xHitbox, yHitbox;
    public Projectile(GamePanel gp){
        this.gp = gp;


    }


 public void set(int posX, int posY, boolean alive){
    this.posX = posX;
    this.posY = posY;
    this.alive = alive;
     xHitbox = posX + 16;
 }

 public void update(){
    this.posY -= 15;
    if (posY <= -gp.tileSize){
        alive = false;
    }

     yHitbox = posY + gp.tileSize;
 }
    public void draw(Graphics2D g2){
        BufferedImage image = sprite;
        g2.drawImage(image, posX, posY, gp.tileSize, gp.tileSize, null );
    }
}
