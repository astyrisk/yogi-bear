import java.awt.*;

public class Charachter extends Sprite {
    protected double velx = 0;
    protected double vely = 0;
    protected int toggleRunning = 0;
    protected boolean toggleDirection = true;
    public Charachter(int x, int y, int width, int height, Image image, double velx, double vely) {
        super(x, y, width, height, image);
        this.velx = velx;
        this.vely = vely;
    }
}
