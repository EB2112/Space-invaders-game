import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class PlayerInput implements KeyListener {
    public boolean upPress, downPress, rightPress, leftPress, spacePress;

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();
        switch (code){
            case KeyEvent.VK_W-> upPress = true;
            case KeyEvent.VK_S -> downPress = true;
            case KeyEvent.VK_A -> leftPress= true;
            case KeyEvent.VK_D -> rightPress = true;
           case KeyEvent.VK_SPACE -> spacePress= true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        switch (e.getKeyCode()){
            case KeyEvent.VK_W -> upPress = false;
            case KeyEvent.VK_S -> downPress = false;
            case KeyEvent.VK_A -> leftPress= false;
            case KeyEvent.VK_D -> rightPress = false;
            case KeyEvent.VK_SPACE -> spacePress= false;
        }
    }
}
