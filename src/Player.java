import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Player extends Entity {

    GamePanel gp;
    PlayerInput pi;
    private boolean alreadyFired;
    public Player(GamePanel gp, PlayerInput pi) {

        this.gp = gp;
        this.pi = pi;
        setDefaultValues();
        getImage();
    }
    public void setDefaultValues(){
        posX = gp.screenWidth / 2 - (gp.tileSize / 2);
        posY = (int) (gp.screenHeight * 0.9);
        speed = 10;

    }
    public void getImage(){
    try {
    sprite = ImageIO.read(new File("res/Spaceship.png"));
    }
    catch (IOException e){
        e.printStackTrace();
    }
}
    public void update(){
        if (pi.upPress){
            if(posY > 0){
                posY -= speed;
            }


        }
        if (pi.downPress){
            if(posY < gp.screenHeight - gp.tileSize){
                posY += speed;
            }


        }
        if (pi.rightPress){
            if(posX < gp.screenWidth - gp.tileSize){
                posX += speed;
            }


        }
        if (pi.leftPress){
            if(posX > 0) {
                posX -= speed;
            }
        }
        if (pi.spacePress && alreadyFired != true){
            shoot();
            alreadyFired = true;
        }
        if(!pi.spacePress){
            alreadyFired = false;
        }

    }

    public void shoot(){
        Bullet bullet = new Bullet(gp);
        bullet.set(this.posX, this.posY -10, true);

        gp.projectiles.add(bullet);




    }
    public void draw(Graphics2D g2){

        BufferedImage image = sprite;
        g2.drawImage(image, posX, posY, gp.tileSize, gp.tileSize, null );


    }

}
