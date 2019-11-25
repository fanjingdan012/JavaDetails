package core.future;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Test {
    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        Task task = new Task();
        Future<Integer> result = executor.submit(task);
        executor.shutdown();
         
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
         
        System.out.println("MainThread running"+System.currentTimeMillis());
         
        try {
            System.out.println("task运行结果"+result.get()+System.currentTimeMillis());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
         
        System.out.println("All tasks finished"+System.currentTimeMillis());
    }
}
class Task implements Callable<Integer> {
    public Integer call() throws Exception {
        System.out.println("CallableThread-Task Running"+System.currentTimeMillis());
        Thread.sleep(1000);
        int sum = 0;
      for (int i = 0; i < 100; i++) {
        sum += i;
      }
        return sum;
    }
}