package juc;

import net.HttpUtil;

import java.net.HttpCookie;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutorDemo {

    // 总的请求个数
    public static final int requestTotal = 20;

    // 同一时刻最大的并发线程的个数
    public static final int concurrentThreadNum = 4;

    public static void main(String[] args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        CountDownLatch countDownLatch = new CountDownLatch(requestTotal);
        Semaphore semaphore = new Semaphore(concurrentThreadNum);
        for (int i = 0; i< requestTotal; i++) {
            executorService.execute(new TaskRunner(semaphore ,countDownLatch,i));
        }
        countDownLatch.await();
        executorService.shutdown();

    }
}

class TaskRunner implements Runnable{
    Semaphore semaphore;
    CountDownLatch countDownLatch;
    int i;

    public TaskRunner(Semaphore semaphore, CountDownLatch countDownLatch, int i) {
        this.semaphore=semaphore;
        this.countDownLatch=countDownLatch;
        this.i=i;
    }

    @Override
    public void run() {
        try {
            semaphore.acquire();
            HttpUtil httpUtil = new HttpUtil();
            Map params = new HashMap<>();
            String username = 2+"";
            String password = 2+"";
            params.put("username",""+username);
            params.put("password",""+password);
            String text = httpUtil.sendRequest("http://localhost:8080/register", "Post", new HashMap<>(), params, HttpUtil.PARAMETER_TYPE_URLENCODED);
            System.out.println("send register"+username+"/"+password+":"+text);
            semaphore.release();
        } catch (Exception e) {
            System.out.println("exception"+ e);
            e.printStackTrace();
        }
        countDownLatch.countDown();

    }
}

