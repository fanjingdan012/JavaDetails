package core;

import java.util.concurrent.TimeUnit;

public class ThreadInterruptCalc {
    public static void main(String[] args) {

        Thread task = new PrimeGenerator();
        task.start(); // 启动质数生成线程
        try {
            TimeUnit.SECONDS.sleep(5); // 主线程休眠5s
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        task.interrupt(); // 质数生成线程中断
    }
}
class PrimeGenerator extends Thread {
    @Override
    public void run() {
        long number = 1L;

        while (true) {
            // 对每个数字，计算它是不是一个质数，如果是的话就打印到控制台。
            if (isPrime(number)) {
                System.out.printf("Number %d is Prime\n", number);
            }

            // 当被中断时，输出一条消息，并且退出方法
            if (isInterrupted()) {
                System.out.printf("The Prime Generator has been Interrupted\n");
                return;
            }
            number++;
        }
    }

    /**
     * 判断一个数是否是质数
     *
     * @param number 待判断的数
     * @return true是质数，false不是质数
     */
    private boolean isPrime(long number) {
        if (number <= 2) {
            return true;
        }

        for (long i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }
}
