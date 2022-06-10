package com.mon.interfaces;

public class GameImpl implements Game{
    @Override
    public void start() {

    }

    @Override
    public void restart() {

    }

    @Override
    public int calculateScore(int myScore) {
return 0;
    }

    @Override
    public void endGame() {

    }

    @Override
    public int calculateScore(int highScore, int myScore) {
        return 0;
    }

    public static String playGame(){
        return "Starting game with the current highScore " + highScore;
    }

    /*// overriding the default method in interface
    @Override
    public int compareScore(int myScore) {
        return Game.super.compareScore(myScore);
    }*/
}
