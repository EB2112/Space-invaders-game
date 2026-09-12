import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class Bullet extends Projectile {
    GamePanel gp;

    public Bullet(GamePanel gp) {
        super(gp);
        this.gp = gp;
        speed = 15;
        alive = false;
        getImage();
    }
    public void getImage()  {
        try {
            sprite=  ImageIO.read(new File("res/laser_bullet.png"));
        }catch (IOException e){
          e.printStackTrace();
        }

    }

}
