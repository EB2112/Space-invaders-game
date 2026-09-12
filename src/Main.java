import javax.swing.*;
import java.awt.*;

public class Main {
    public static void main(String[] args) {


        JFrame window = new JFrame("Game");
        GamePanel g = new GamePanel();
        window.add(g);
        window.pack();
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setLocationRelativeTo(null);
        window.setVisible(true);






    }


}