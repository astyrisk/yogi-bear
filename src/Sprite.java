import java.awt.Image;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Sprite {
    protected int x;
    protected int y;
    protected int width;
    protected int height;
    protected Image image;

    public Sprite(int x, int y, int width, int height, Image image) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.image = image;
    }

    public void draw(Graphics g) {
        g.drawImage(image, x, y, width, height, null);
    }


    public void setImage(Image image) { this.image = image; }
    public void setWidth(int width) { this.width = width; }

    public int getX() {return x;}
    public int getY() {return y;}
    public int getWidth() { return width; }
    public int getHeight() {return height; }



    //one unit away
    public boolean collides(Sprite other, boolean direction) {
        int otherWidth = other.width;
        int thisX= this.x;
        if (!direction) {
            otherWidth = - other.width;
            thisX = 30 + x;
        }
        Rectangle rect = new Rectangle(thisX, y, width, height);
        Rectangle otherRect = new Rectangle(other.x, other.y, otherWidth, other.height);
        return rect.intersects(otherRect);
    }
}
