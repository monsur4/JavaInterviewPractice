package com.mon.interfaces;

public interface Game {
    int highScore = 44;
    static final int previousHighScore = 43;
    public void start();
    public void restart();
    public int calculateScore(int myScore);
    public void endGame();
    public int calculateScore(int highScore, int myScore);

    // private method
    private String calculateProgress(int number){
        return "This is the end result " + String.valueOf(number);
    }

    // default method
    default int compareScore(int myScore){
        return calculateScore(highScore, myScore);
    }

    public static String playGame(){
        return "Starting game with the current highScore " + highScore;
    }
}
