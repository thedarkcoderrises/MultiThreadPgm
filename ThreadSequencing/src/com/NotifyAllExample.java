package com;

public class NotifyAllExample {

    int status=1;
    public static void main(String[] args) {

        NotifyAllExample notifyAllExample = new NotifyAllExample();

        A1 a=new A1(notifyAllExample);
        B1 b=new B1(notifyAllExample);
        C1 c=new C1(notifyAllExample);

        a.start();
        b.start();
        c.start();
    }
}

class A1 extends Thread{
    NotifyAllExample notifyAllExample;

    A1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {
    	System.out.println("A started");
        try{
            synchronized (notifyAllExample) {

                for (int i = 0; i < 10; i++) {

                	System.out.println("A checking status :"+notifyAllExample.status);
                    if(notifyAllExample.status==1){
                    	System.out.println("A ");
                        Thread.sleep(1000);
                        notifyAllExample.status = 2;
                        notifyAllExample.notifyAll();
                    }
                    System.out.println("A wait");
                    notifyAllExample.wait();// It will wait untill it is Notified by any other thread
                    System.out.println("Got Access Thread A");
                    
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 1 :"+e.getMessage());
        }

    }

}

class B1 extends Thread{

    NotifyAllExample notifyAllExample;

    B1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {
System.out.println("B started");
        try{
            synchronized (notifyAllExample) {

                for (int i = 0; i < 10; i++) {
                	System.out.println("B checking status :"+notifyAllExample.status);
                    if(notifyAllExample.status==2){

                        System.out.println("B ");
                        Thread.sleep(1000);
                        notifyAllExample.status = 3;
                        notifyAllExample.notifyAll();
                        
                    }
                    System.out.println("B wait");
                    notifyAllExample.wait();// It will wait untill it is Notified by any other thread
                    System.out.println("Got Access Thread B");
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 2 :"+e.getMessage());
        }

    }
}


class C1 extends Thread{

    NotifyAllExample notifyAllExample;

    C1(NotifyAllExample notifyAllExample){
        this.notifyAllExample = notifyAllExample;
    }

    @Override
    public void run() {
    	System.out.println("C started");
        try{
            synchronized (notifyAllExample) {

                for (int i = 0; i < 10; i++) {
                	System.out.println("C checking status :"+notifyAllExample.status);
                    if(notifyAllExample.status==3){
                    	System.out.println("C ");
                        Thread.sleep(1000);
                        notifyAllExample.status = 1;
                        notifyAllExample.notifyAll();
                        
                    }
                    System.out.println("C wait");
                    notifyAllExample.wait();
                    System.out.println("Got Access Thread C");
                    
                }

            }
        }catch (Exception e) {
            System.out.println("Exception 3 :"+e.getMessage());
        }

    }
}