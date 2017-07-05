package com.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimpleThreadPool {

   /* public static void main(String[] args) {
//        ExecutorService executor = Executors.newFixedThreadPool(2);
        for (int i = 0; i < 2; i++) {
            Runnable worker = new WorkerThread("" + i);
            Thread t = new Thread(worker);
            t.start();
//            executor.execute(worker);
          }
//        executor.shutdown();
//        while (!executor.isTerminated()) {
//        }
        System.out.println("Finished all threads");
    }*/
    
    
    public static void main(String[] args) throws InterruptedException {
      ExecutorService executor = Executors.newFixedThreadPool(2);
      for (int i = 1; i < 3; i++) {
          Runnable worker = new WorkerThread("" + i);
          executor.execute(worker);
        }
      executor.shutdown();
      while (!executor.isTerminated()) {
      }
      System.out.println("Finished all threads");
  }
}
