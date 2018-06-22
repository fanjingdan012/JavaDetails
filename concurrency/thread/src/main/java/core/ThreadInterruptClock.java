package core;


import java.util.Date;
import java.util.concurrent.TimeUnit;
public class ThreadInterruptClock {
    public static void main(String[] args) {
        // 创建一个文件时间运行对象，并且将其放入一个线程对象中
        FileClock clock = new FileClock();
        Thread thread = new Thread(clock);

        // 开始线程
        thread.start();
        try {
            // 等待五秒
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // 中断线程
        thread.interrupt();
    }
}
class FileClock implements Runnable {
    //@Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.printf("%s\n", new Date());
            try {
                // 休眠一秒
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                // 当线程被中断时，释放或者关闭线程正在使用的资源。
                System.out.printf("The FileClock has been interrupted");
                return; // 发生异常就跳出
            }
        }
    }
}