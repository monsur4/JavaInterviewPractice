package com.mon.threading;

import com.mon.threading.locks.ExecutionQueue;

public class Test {
    public enum Status{
        WIN, LOSE, DRAW
    }

    public static int see(){
        try{
            System.out.println("trying return in try");
            System.exit(0);
            return 5/0;
        }catch (ArithmeticException e){
            System.out.println(e);
        }finally {
            System.out.println("Finally");
        }
        return 0;
    }
    public static void main(String[] args) {
        /*StaticBlock staticBlock = new StaticBlock();
        new StaticBlock();
        //System.out.println(staticBlock.j);
        String[] strings = new String[4];
        strings[0] = "first";
        strings[1] = "second";
        strings[2] = "third";
        strings[3] = "fourth";
        staticBlock.run(strings);
        staticBlock.run("fifth", "sixth");*/

        // calling a static variable from a class triggers the only static block. The static block always runs
        // --- System.out.println(StaticBlock.i);
        // calling the class constructor however triggers the static block, then the non-static block then the constructor
        // however, the static block is only ever triggered once. Even if the class is instantiated >1 times
        // the non-static block however is triggered every time the constructor is called and runs just before it

        /*StaticBlock2 staticBlock2 = new StaticBlock2(4);
        System.out.println(staticBlock2.k);

        GameImpl game = new GameImpl();
        Game.playGame();
        GameImpl.playGame();
        game.compareScore(45);

        String str= "    Hello World 😇     ggfgfgf";
        System.out.println(str.trim());

        int num = 15;
        System.out.println(num>>>2);
        int _num = 15;
        System.out.println(_num>>>1);
        int $num = 15;
        System.out.println($num>>>3);

        StaticBlock3 staticBlock3 = new StaticBlock3();

        AbstractGameImpl agl = new AbstractGameImpl();*/
        /*try {
            System.out.println(agl.calculateAsync().get() + " >>>>>>>>>>>>>>>>>");

        }catch (InterruptedException | ExecutionException e){
            System.out.println("Future execution failed " + e.getMessage());
        }finally {
            System.out.println("Future execution finish");
        }

        System.out.println(agl.sum());
        System.out.println(agl.start());

        Future<String> completableFuture = CompletableFuture.completedFuture("we here");
        try {
            System.out.println(completableFuture.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("Second Future execution failed " + e.getMessage());
        }

        CompletableFuture<String> completingFuture = CompletableFuture.supplyAsync(()-> "Hello Future");
        Future<String> future = completingFuture.thenApply(s -> s + " Againi");
        completingFuture.thenCompose(s -> CompletableFuture.supplyAsync(() -> s + " Second"));
        try {
            System.out.println(future.get());
            // future.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }*/

        /*String name = null;
        CompletableFuture<String> future = CompletableFuture.supplyAsync(()-> {
            if(name == null){
                throw new RuntimeException("name is null");
            }
            return "Hello Future, here we go!";
        }).handle((s, t) -> s != null? "error!!!!": t.getMessage());

        try {
            // future.get();
            System.out.println(future.get());
        } catch (InterruptedException | ExecutionException e) {
            System.out.println("error is " + e.getMessage());
        }*/

//        try{
//            System.out.println("trying return in try");
//            return agl.run();
//        }

        // int n = Test.see();
        // System.out.println(n);
        /*AbstractGameImpl agl = new AbstractGameImpl();
        StaticBlock2 sb2 = new StaticBlock2(8);
        if( sb2 instanceof StaticBlock){
            System.out.println("Alright");
        }
        if(agl.getClass().isAssignableFrom(AbstractGame.class)){

            System.out.println("Cool");
        }
        AbstractGame ag = (AbstractGame)agl;
        ag.endGame();

        MyThread myThread = new MyThread();
        myThread.start();*/

        /*Manipulator manipulator1 = new Manipulator();
        Manipulator manipulator2 = new Manipulator();
        RemoveManipulator removeManipulator = new RemoveManipulator(manipulator1);
        AddingManipulator addingManipulator = new AddingManipulator(manipulator2);
        System.out.println(Thread.currentThread().getName());
        Thread t1 = new Thread(()->removeManipulator.remove(3), "First thread");
        Thread t2 = new Thread(()->addingManipulator.add(3, 40), "Second thread");
        t1.start();
        t2.start();
        try {
            t1.join();
            t2.join();
        }catch (InterruptedException e){
            e.printStackTrace();
        }


        // Thread.yield() is a design decision. It tells the current thread to pause execution to allow other threads
        // of equal or greater priority to run
        System.out.println("continuing main thread");*/

        /*MySecondThread mySecondThread = new MySecondThread();
        Thread thread = new Thread(mySecondThread);
        thread.start();
        thread.interrupt();*/

//        ExecutionThread executionThread = new ExecutionThread();
//        new Thread(new Producer(executionThread)).start();
//        new Thread(new Producer(executionThread)).start();
//
//        new Thread(new Consumer(executionThread)).start();
//        new Thread(new Producer(executionThread)).start();

        ExecutionQueue queue = new ExecutionQueue();

        final Runnable producer = ()->{
            for(int i=1; i<=2000; i++){
                System.out.println("Starting: Adding "+ i+Thread.currentThread().getName()+ " to queue");
                queue.add(i);
                System.out.println("Ending: Adding "+ i+Thread.currentThread().getName()+ " to queue");
            }
        };

        final Runnable consumer = ()->{
            System.out.println("Starting: Removing from queue");
            while (true){
                 int n =queue.remove();
                 System.out.println("Ending: Removing "+ n+ Thread.currentThread().getName()+" from queue");
            }

        };

        new Thread(producer, " prod 1").start();
        new Thread(producer, " prod 2").start();

        new Thread(consumer," con 1").start();
        new Thread(consumer, " con 2").start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
