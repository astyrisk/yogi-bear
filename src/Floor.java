import javax.swing.*;
import java.awt.*;

public class Floor extends Sprite {
    public Floor(int x, int y) {
        super(x, y, 80, 60, new ImageIcon("data/items/floor2.png").getImage());
    }
}
