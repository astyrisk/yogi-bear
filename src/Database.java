/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;


public class Database {
    
    int maxScores;
    PreparedStatement insertStatement;
    PreparedStatement deleteStatement;
    Connection connection;
    private Statement stmt;

    /**
     *
     * @param maxScores
     * @throws SQLException
     * Initialize a Database object from a maximum score that can be recorded.
     */
    public Database(int maxScores) throws SQLException {
        this.maxScores = maxScores;
        
        String dbURL = "jdbc:mysql://localhost:3306/snake";
//        connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake","root","database@1");
//        String insertQuery = "INSERT INTO HIGHSCORES (TYPESTAMP, NAME, LEVEL, SCORE) VALUES (?, ?, ?, ?)";
//        insertStatement = connection.prepareStatement(insertQuery);
//        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
//        deleteStatement = connection.prepareStatement(deleteQuery);
        
        try {
            //Class.forName ("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/snake","root","database@1");
            stmt = connection.createStatement();
        } catch (SQLException ex) {
            dbURL += ";create=true";
            connection = DriverManager.getConnection(dbURL);
            stmt = connection.createStatement();
        }
        String insertQuery = "INSERT INTO HIGHSCORES (TIME,NAME, LEVEL, SCORE) VALUES (?, ?, ?, ?)";
        insertStatement = connection.prepareStatement(insertQuery);
        String deleteQuery = "DELETE FROM HIGHSCORES WHERE SCORE=?";
        deleteStatement = connection.prepareStatement(deleteQuery);
    }

    /**
     *
     * @return highScores
     * @throws SQLException
     * Get all scored HighScore's.
     */
    public ArrayList<HighScore> getHighScores() throws SQLException {
        String query = "SELECT * FROM HIGHSCORES";
        ArrayList<HighScore> highScores = new ArrayList<>();
        Statement stmt = connection.createStatement();
        ResultSet results = stmt.executeQuery(query);
        while (results.next()) {
            String name = results.getString("NAME");
            int score = results.getInt("SCORE");
            int level = results.getInt("LEVEL");
            int time = results.getInt("TIME");
            highScores.add(new HighScore(name, score, level,time));
        }
        sortHighScores(highScores);
        return highScores;
    }

    /**
     *
     * @param name
     * @param score
     * @throws SQLException
     * 
     * Put a new HighScore into the database.
     */
    public void putHighScore(String name, int score,int sec) throws SQLException {
        ArrayList<HighScore> highScores = getHighScores();
        if (highScores.size() < maxScores) {
            System.out.println("less");
            insertScore(name, score, /* game processor level*/ 5 ,sec);
            System.out.println("done");
        } else {
            System.out.println("gt");
            int leastScore = highScores.get(highScores.size() - 1).getScore();
            if (leastScore < score) {
                deleteScores(leastScore);
                insertScore(name, score, /* game processor level */ 4,sec);
            }
        }
    }

    /**
     * Sort the high scores in descending order.
     *
     * @param highScores
     */
    private void sortHighScores(ArrayList<HighScore> highScores) {
        Collections.sort(highScores, (HighScore t, HighScore t1) -> t1.getScore() - t.getScore());
    }

    
    /**
     * Inserts a new highScore into the database.
     */
    private void insertScore(String name, int score, int level,int sec) throws SQLException {
        //Timestamp ts = new Timestamp(System.currentTimeMillis());
        //insertStatement.setTimestamp(1, ts);
        System.out.println("in");
        insertStatement.setInt(1,sec);
        insertStatement.setString(2, name);
        insertStatement.setInt(3, level);
        insertStatement.setInt(4, score);
        insertStatement.executeUpdate();
        System.out.println("out");
    }

    /**
     * Deletes all the highScores with score.
     *
     * @param score
     */
    private void deleteScores(int score) throws SQLException {
        deleteStatement.setInt(1, score);
        deleteStatement.executeUpdate();
    }
    
    /**
     * @return a String array with the names of column headers.
     */
    public String[] getColumnNamesArray (){
        String[] columnNames = {"#","Time", "Name", "Level", "Score"};
        return columnNames;
    }
    
    /**
     * @return a String matrix with all the data in the database.
     * @throws SQLException
     */
    public String[][] getDataMatrix () throws SQLException{
        String[][] columnNames = new String[10][5];
        ArrayList<HighScore> highscores = getHighScores();
        int cnt = 0;
        for(HighScore hs : highscores){
            if(cnt == 10) break;
            columnNames[cnt][0] = String.valueOf(cnt+1);
            columnNames[cnt][1] = String.valueOf(hs.getTime());
            columnNames[cnt][2] = hs.getName();
            columnNames[cnt][3] = "" + hs.getLevel();
            columnNames[cnt][4] = "" + hs.getScore();
            
            cnt++;
        }
        while(cnt < 10){
            if(cnt == 11) break;
            columnNames[cnt][0] = String.valueOf(cnt+1);
            columnNames[cnt][4] = "";
            columnNames[cnt][1] = "";
            columnNames[cnt][2] = "";
            columnNames[cnt][3] = "";
            cnt++;
        }
        return columnNames;
    }
}