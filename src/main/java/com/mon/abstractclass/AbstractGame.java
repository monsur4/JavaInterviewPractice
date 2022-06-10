package com.mon.abstractclass;

public abstract class AbstractGame {
    public static final int worldWideScore = 100;
    private int score = 5;
    protected int scored = 5;
    int scoreee = 5;

    public AbstractGame(){
        System.out.println("in AbstractGame constructor");
    }
    public String start(){
        try{
            int n = 9/0;
        }catch (ArithmeticException | IllegalArgumentException e){
            System.out.println("in catch with error => " + e.getMessage());
        }
            finally {
            System.out.println("in finally");
        }
        return "Game started";
    }

    public String endGame(){
        return "Game ended";
    }

    public abstract int run();

//    default int computeScore(int myScore){
//        return worldWideScore - myScore;
//    }
}
