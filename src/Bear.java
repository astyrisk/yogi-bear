import javax.swing.*;

public class Bear extends Charachter{
    private final int grav = 7;
    private int toggleFPS = 0;
    public Bear(int x, int y) {
        super(x, y, 80,80, new ImageIcon("data/yogi/standing.png").getImage(), 10, 15);
    }

    public void moveX(boolean direction, boolean collidesRight, boolean collidesLeft) {
        this.setImage(new ImageIcon("data/yogi/running/" + toggleRunning  +".png").getImage());
        if (direction) {
            if (collidesRight) return;
            if (!toggleDirection) x -= 50;
            this.setWidth(50);
            x += velx;
        }
        else {
            if (collidesLeft) return;
            if (toggleDirection) x += 50;
            this.setWidth(-50);
            x -= velx;
        }
        toggleDirection = direction;
        toggleRunning++;
        toggleRunning %= 3;
    }

    public void moveY(boolean collidesDown){
        this.setImage(new ImageIcon("data/yogi/jumping.png").getImage());
        y -= vely;
    }
    public void moveGrav(boolean collidesDown){
        if (toggleFPS == 0 && !collidesDown) {
            y += grav;
        }
        toggleFPS += 30;
        toggleFPS %= 600;
    }
    public void moveDown(boolean collidesDown) {
        if (toggleFPS == 0 && !collidesDown) {
            y += 8;
        }
        toggleFPS += 30;
        toggleFPS %= 600;
    }


    public boolean getToggleDirection() { return toggleDirection; }
    // jump handleing
}
