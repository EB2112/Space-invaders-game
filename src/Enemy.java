import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

public class Enemy extends Entity{
    Random r = new Random();
    GamePanel gp;
    public boolean isAlive = true;
    private int jump = 0;
    public Enemy(GamePanel gp){
        this.gp = gp;
        setDefaultValues();
        getImage();

    }
    public void setDefaultValues(){
//        posX = gp.screenWidth / 12 - (gp.tileSize / 2);
        int col = (r.nextInt(gp.maxScreenCols) + 1) * gp.tileSize;
//        posX = (col >= gp.screenWidth / 2) ? (col - (gp.tileSize)) : (col + (gp.tileSize / 2));
        posX = (col == 48 ? 0 : col);
        posY = (int) (gp.screenHeight * 0.1);
        xHitbox = posX + gp.tileSize;

        speed = 3;
    }
    public void getImage(){
        try {
            sprite = ImageIO.read(new File("res/alien.png"));
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    public void draw(Graphics2D g2){
        BufferedImage image = sprite;
        g2.drawImage(image, posX, posY, gp.tileSize, gp.tileSize, null);

    }
    public void update(){
        jump++;
        if (jump >= 30){
            posY = posY + gp.tileSize * 2;
            yHitbox = posY + gp.tileSize;
            jump = 0;
            if(yHitbox >= gp.screenHeight){
                this.isAlive = false;
            }
        }
    }
}
