import javax.swing.*;

public class GameOver {
    public GameOver(GameEngine game) {
        String PlayerName = JOptionPane.showInputDialog(game, "GAME OVER\n Enter your name");
        int score = game.getBasketNum();
        int gameLevel = game.getGameLevel();
    }
}
