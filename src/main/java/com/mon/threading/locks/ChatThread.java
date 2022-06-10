package com.mon.threading.locks;

class Chat{
    boolean flag = false;

    public synchronized void Question(String msg){
        while(flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = true;
        System.out.println(msg);
        notify();
    }

    public synchronized void Answer(String msg){
        while(!flag){
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        flag = false;
        System.out.println(msg);
         notify();
    }
}
class FirstPerson implements Runnable{
    Chat chat;
    String[] messages;

    public FirstPerson(Chat chat) {
        this.chat = chat;
        this.messages = new String[]{"Hi", "How are you doing?", "I'm okay too. Thank you."};
    }

    public void run(){
        for(int i=0; i<messages.length; i++){
            chat.Question(messages[i]);
        }
    }
}

class SecondPerson implements Runnable{
    Chat chat;
    String[] messages;

    public SecondPerson(Chat chat){
        this.chat = chat;
        this.messages = new String[]{"Hi", "I'm doing fine. How are you too?", "Great."};
    }

    public void run(){
        for (int i = 0; i<messages.length; i++){
            chat.Answer(messages[i]);
        }
    }
}
public class ChatThread {
    public static void main(String[] args) {
        Chat chat = new Chat();
        FirstPerson firstPerson = new FirstPerson(chat);
        SecondPerson secondPerson = new SecondPerson(chat);

        Thread t1 = new Thread(firstPerson);
        t1.start();

        Thread t2 = new Thread(secondPerson);
        t2.start();
    }

}
