import javax.swing.*;
import java.awt.*;

public class Ranger extends Charachter{
    private final int MAX_RANGE = 200;
    private int range = 0;
    private int toggleFPS = 0;
    private boolean direction = true;
    public Ranger(int x, int y) {
        super(x, y + 20, -60, 70, new ImageIcon("data/rangers/0.png").getImage(), 5, 0);
        toggleRunning = 1;
    }
    public void moveX() {
        if (toggleFPS == 0) {
            this.setImage(new ImageIcon("data/rangers/" + toggleRunning  +".png").getImage());
            if (toggleDirection) {
                if (!direction) x -= 60;
                setWidth(-60);
                x += velx;
                range += velx;
            } else {
                if (direction) x -= 60;
                setWidth(60);
                x -= velx;
                range -= velx;
            }
            range %= MAX_RANGE;
            if (range == 0) {
                toggleDirection = !toggleDirection;
            }
            direction = toggleDirection;
            toggleRunning++;
            toggleRunning %= 3;
        }
        toggleFPS += 30;
        toggleFPS %= 600;
    }

    public boolean sees(Sprite other) {
        Rectangle rect = new Rectangle(x, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, other.width + 40, other.height);
        return rect.intersects(otherRect);
    }
}
