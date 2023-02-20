import javax.swing.*;
import java.awt.*;

public class Mountain extends Sprite {
    public Mountain(int x, int y) {
        super(x, y, 84, 64, new ImageIcon("data/items/mountain.png").getImage());
    }
}
