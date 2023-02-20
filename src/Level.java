import java.awt.*;
import java.io.*;
import javax.swing.ImageIcon;
import java.util.ArrayList;

/*
    - => empty
    # => floor
    @ => bear
    T => tree
    M => Mountain
    R => Ranger
 */

public class Level {
    private final int SPRITE_WIDTH = 80;
    private final int SPRITE_HEIGHT = 60;

    ArrayList<Basket> baskets;
    ArrayList<Floor> floors;
    ArrayList<Tree> trees;
    ArrayList<Mountain> mountains;
    ArrayList<Ranger> rangers;
    Bear bear;

    public Level(String levelPath) throws IOException {
        baskets = new ArrayList<>();
        floors = new ArrayList<>();
        trees = new ArrayList<>();
        mountains = new ArrayList<>();
        rangers = new ArrayList<>();

        BufferedReader br = new BufferedReader(new FileReader(levelPath));
        int y = 0;
        String line;
        while ((line = br.readLine()) != null) {
            System.out.println(line);
            int x = 0;
            for (char c : line.toCharArray()) {
                switch (c) {
                    case '!':
                        baskets.add(new Basket(x * SPRITE_WIDTH, y * SPRITE_HEIGHT));
                        break;
                    case '#':
                        floors.add(new Floor(x * SPRITE_WIDTH, y * SPRITE_HEIGHT));
                        break;
                    case 'T':
                        trees.add(new Tree(x * SPRITE_WIDTH, y * SPRITE_HEIGHT));
                        break;
                    case 'M':
                        mountains.add(new Mountain(x * SPRITE_WIDTH, y * SPRITE_HEIGHT));
                        break;
                    case 'R':
                        rangers.add(new Ranger(x * SPRITE_WIDTH, y * SPRITE_HEIGHT));
                        break;
                    case '@':
                        bear = new Bear(x * SPRITE_WIDTH, y * SPRITE_HEIGHT);
                        break;
                }
                x++;
            }
            y++;
        }
    }


    public int getBasketsNum() {
        return baskets.size();
    }
    public boolean isOver() {
        return baskets.isEmpty();
    }
    public Bear getBear() {
        return bear;
    }
    public void moveRangers() {
        for (Ranger ranger : rangers) {
            ranger.moveX();
        }
    }
    public boolean collidesRight() {
        for (Tree tree : trees) { if (tree.collides(bear, true) && bear.getX() < tree.getX()) return true; }
        for (Mountain mountain : mountains) { if (mountain.collides(bear,true) && bear.getX() < mountain.getX()) return true; }
        return false;
    }
    public boolean collidesLeft() {
        for (Tree tree : trees) { if (tree.collides(bear,false) && bear.getX() /*+ bear.getWidth()*/ > tree.getX()) return true; }
        for (Mountain mountain : mountains) { if (mountain.collides(bear, false) && bear.getX() /*+ bear.getWidth()*/ > mountain.getX()) return true; }
        return false;
    }
    public boolean collidesDown() {
        for (Floor floor : floors) { if (floor.collides(bear, bear.getToggleDirection()) && bear.getY() < floor.getY()) return true; }
        return false;
    }
    public boolean collects() {
        Basket collidedWith = null;
        for (Basket basket : baskets) {
            if (basket.collects(bear)) {
                collidedWith = basket;
                break;
            }
        }
        if (collidedWith != null)  {
            baskets.remove(collidedWith);
            return true;
        }
        return false;
    }
    public boolean sees() {
        for (Ranger ranger : rangers) {
            if (ranger.sees(bear)) return true;
        }
        return false;
    }

    public void draw(Graphics g) {
        for (Floor floor : floors) {
            floor.draw(g);
        }
        for (Basket basket : baskets) {
            basket.draw(g);
        }
        for (Tree tree : trees) {
            tree.draw(g);
        }
        for (Mountain mountain : mountains) {
            mountain.draw(g);
        }
        for (Ranger ranger : rangers) {
            ranger.draw(g);
        }
        bear.draw(g);
    }
}
