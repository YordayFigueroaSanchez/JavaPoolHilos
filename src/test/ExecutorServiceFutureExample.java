package test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ExecutorServiceFutureExample {

   public static void main(String[] args) throws Exception {

       ExecutorService executor = Executors.newFixedThreadPool(10);
       Future<String> future = executor.submit(() -> doLongWork("hi with future 1"));
       System.out.println(future.get());

   }

   private static String doLongWork(String msg) {
       System.out.println("Running " + msg);
       try {
           Thread.sleep(1000l);
       } catch (InterruptedException e) {
           e.printStackTrace();
       }
       return "done " + msg;
   }

}