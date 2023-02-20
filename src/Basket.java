import javax.swing.*;
import java.awt.*;

public class Basket extends Sprite {
    public Basket(int x, int y) {
        super(x, y, 80, 60, new ImageIcon("data/items/basket.png").getImage());
    }

    public boolean collects(Sprite other) {
        Rectangle rect = new Rectangle(x, y, 5, 5);
        Rectangle otherRect = new Rectangle(other.x - 40, other.y, 80, other.height);
        return rect.intersects(otherRect);
    }

}
