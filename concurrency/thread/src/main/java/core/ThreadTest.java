package core;

import java.util.ArrayList;
import java.util.List;

public class ThreadTest {
    // gets 81429
    public static void main(String[] args) {  
        final Count count = new Count();
        Runnable runnable = new Runnable() {  
            public void run() {  
                for (int i = 0; i < 10000; i++) {  
                    count.increment();  
                }  
            }  
        };  
        List<Thread> threads = new ArrayList<Thread>(10);
        for (int i = 0; i < 10; i++) {  
            Thread thread = new Thread(runnable);  
            threads.add(thread);  
            thread.start();  
        }  
        while (true) {  
            if (allThreadTerminated(threads)) {// 所有线程运行结束  
                System.out.println(count.get());  
                break;  
            }  
        }  
    }  
    private static boolean allThreadTerminated(List<Thread> threads) {
        for (Thread thread : threads) {  
            if (thread.isAlive()) {  
                return false;  
            }  
        }  
        return true;  
    }  
}  