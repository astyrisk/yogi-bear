/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

public class HighScore {
    private final String name;
    private final int score;
    private final int level;
    private final int time;
    public HighScore(String name, int score, int level, int time) {
        this.name = name;
        this.score = score;
        this.level = level;
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public int getScore() {
        return score;
    }
    
    public int getLevel() {
        return level;
    }
    
    public int getTime() {
        return time;
    }
    
    @Override
    public String toString() {
        return "HighScore{" + "name=" + name + ", score=" + score + '}';
    }
}
