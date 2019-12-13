package core.juc;

import java.util.concurrent.atomic.AtomicInteger;


public class AtomicIntegerDemo {

    private static AtomicInteger count = new AtomicInteger(0);

    public static void main(String[] args) {

        new Thread(() -> {

            while (count.get() != 0) ;
            count.incrementAndGet();
            System.out.println(1);
        }).start();

        new Thread(() -> {
            while (count.get() != 1) ;
            count.incrementAndGet();
            System.out.println(2);

        }).start();


        while (count.get() != 2) ;
        count.incrementAndGet();
        System.out.println(3);

    }
}
