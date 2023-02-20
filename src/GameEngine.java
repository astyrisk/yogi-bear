import java.awt.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Logger;
import java.io.IOException;

public class GameEngine extends JPanel {
    private final int FPS = 240;
    private int basketsNum;
    private int levelNum = 0;
    private Image background;
    private Level level;
    private boolean paused = false;
    private boolean collidesRight = false;
    private boolean collidesLeft = false;
    private boolean collidesDown = true;
    private Timer newFrameTimer;
    public Database database;
    public GameEngine() {
        super();
        background = new ImageIcon("data/background.png").getImage();
        this.getInputMap().put(KeyStroke.getKeyStroke("LEFT"), "pressed left");
        this.getInputMap().put(KeyStroke.getKeyStroke("RIGHT"), "pressed right");
        this.getInputMap().put(KeyStroke.getKeyStroke("UP"), "pressed up");
        this.getInputMap().put(KeyStroke.getKeyStroke("DOWN"), "pressed down");
        this.getInputMap().put(KeyStroke.getKeyStroke("ESCAPE"), "pressed escape");

        this.getActionMap().put("pressed right", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (paused) return;
                level.getBear().moveX(true, collidesRight, collidesLeft);
            }
        });
        this.getActionMap().put("pressed left", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (paused) return;
                level.getBear().moveX(false, collidesRight, collidesLeft);
            }
        });
        this.getActionMap().put("pressed up", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (paused) return;
                level.getBear().moveY(collidesDown);
            }
        });
        this.getActionMap().put("pressed down", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                if (paused) return;
                level.getBear().moveDown(collidesDown);
            }
        });
        this.getActionMap().put("pressed escape", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                paused = !paused;
            }
        });
        restart();
        newFrameTimer = new Timer(1000/ FPS, new NewFrameListener());
        newFrameTimer.start();
    }

    public void restart() {
        try {
            level = new Level("data/levels/" + levelNum + ".txt");
            basketsNum = level.getBasketsNum();
        } catch (IOException ex) {
            System.out.println("kek");
            Logger.getLogger(GameEngine.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        basketsNum = level.getBasketsNum();
    }
    public int getBasketNum() {
        return basketsNum;
    }
    public int getGameLevel() {
        return levelNum;
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(background, 0, 0 ,800, 600, null);
        level.draw(graphics);
    }

    class NewFrameListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (!paused) {
                level.moveRangers();
                if (level.collects()) {
                    basketsNum -= 1;
                }
                collidesRight = level.collidesRight();
                collidesLeft = level.collidesLeft();
                collidesDown = level.collidesDown();
                if (level.sees()) {
                    levelNum = 0;
                    restart();
                }
            }
            if (level.isOver()) {
                levelNum = (levelNum+1) % 10;
                restart();
            }
            level.getBear().moveGrav(collidesDown);
            repaint();
        }
    }
}
