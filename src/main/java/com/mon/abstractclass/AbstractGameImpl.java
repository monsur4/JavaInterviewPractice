package com.mon.abstractclass;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class AbstractGameImpl extends AbstractGame{

    @Override
    public int run(){
        return 25;
    }

    public AbstractGameImpl(){
        System.out.println("in AbstractGameImpl constructor");
    }

    public strictfp double sum(){
        double num1 = 10e+10;
        double num2 = 6e+4;

        return num1 + num2;
    }

    public Future<String> calculateAsync() throws InterruptedException{
        CompletableFuture<String> completableFuture = new CompletableFuture<>();
        Executors.newSingleThreadExecutor().submit(() ->{
            Thread.sleep(5000);
            completableFuture.complete("Future Complete");
            return null;
        });
        return completableFuture;
    }
}
