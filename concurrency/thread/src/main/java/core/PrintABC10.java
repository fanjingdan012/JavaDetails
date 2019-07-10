package core;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class PrintABC10 {
    static Lock lock = new ReentrantLock();
    static Condition conditionA = lock.newCondition(); //Condition is on lock
    static Condition conditionB = lock.newCondition();
    static Condition conditionC = lock.newCondition();

    private static int flag = 0;
    public static void main(String[] args) {

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (flag != 0) {
                        try {
                            conditionA.await();
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }

                    System.out.println("A" + i);
                    flag = 1;
                    conditionB.signal(); //signal B
                } finally {
                    lock.unlock();
                }
            }

        }).start();

        new Thread(() -> {
            for (int i = 1; i <= 10; i++) {
                lock.lock();
                try {
                    while (flag != 1) {
                        try {
                            conditionB.await(); //await B signal
                        } catch (Exception e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                    }
                    System.out.println("B" + i);
                    flag = 2;
                    conditionC.signal(); //signal C(main)
                } finally {
                    lock.unlock();
                }
            }

        }).start();

        for (int i = 1; i <= 10; i++) {

            lock.lock();
            try {
                while (flag != 2) {
                    try {
                        conditionC.await();
                    } catch (Exception e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                System.out.println("C" + i);
                flag = 0;
                conditionA.signal();
            } finally {
                lock.unlock();
            }
        }
    }


}
