import java.awt.Dimension;
import javax.swing.JFrame;
public class YogiBearGUI {
    private JFrame frame;
    private GameEngine game;
    public YogiBearGUI() {
        frame = new JFrame("YogiBear");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        game = new GameEngine();
        frame.getContentPane().add(game);

        frame.setPreferredSize(new Dimension(800, 600));
        frame.setResizable(false);
        frame.pack();
        frame.setVisible(true);
    }
}