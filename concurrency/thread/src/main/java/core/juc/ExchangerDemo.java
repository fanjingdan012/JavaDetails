package core.juc;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Exchanger;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

public class ExchangerDemo {

    public static void main(String[] args) {
        Exchanger<List<Integer>> exchanger = new Exchanger<>();
        ThreadPoolExecutor executor=(ThreadPoolExecutor) Executors.newCachedThreadPool();
        executor.execute(new Producer(exchanger));
        executor.execute(new Producer(exchanger));
    }

}

class Producer extends Thread {
    List<Integer> list = new ArrayList<>();
    Exchanger<List<Integer>> exchanger = null;
    public Producer(Exchanger<List<Integer>> exchanger) {
        super();
        this.exchanger = exchanger;
    }
    @Override
    public void run() {
        Random rand = new Random();
        int num;
        num=rand.nextInt(10000);
        list.add(num);
        num=rand.nextInt(10000);
        list.add(num);
        num=rand.nextInt(10000);
        list.add(num);
        num=rand.nextInt(10000);
        list.add(num);
        num=rand.nextInt(10000);
        list.add(num);
        try {
            System.out.println(Thread.currentThread().getName()+":交换前：");
            print();
            list = exchanger.exchange(list);
            System.out.println(Thread.currentThread().getName()+":交换后：");
            print();
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    public void print(){
        for(Integer i:list){
            System.out.println(i);
        }
    }
}

