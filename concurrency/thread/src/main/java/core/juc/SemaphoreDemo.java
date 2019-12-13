package core.juc;

import java.util.Date;
import java.util.concurrent.Semaphore;

//打印任务队列，共享数据
class PrintQueue{
    private Semaphore semaphore;
    PrintQueue(int num){
        semaphore=new Semaphore(num);
    }
    public void printJob(){
        try {
            //获取
            semaphore.acquire();
            System.out.printf("%s printJob  time %s \n",Thread.currentThread().getName(),new Date());
            Thread.sleep(3000);
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
        }finally{
            //释放
            semaphore.release();
        }
    }
}
//模拟打印机
class PrintMachine implements Runnable{
    private PrintQueue printQueue;
    PrintMachine(PrintQueue printQueue){
        this.printQueue=printQueue;
    }
    @Override
    public void run() {
        // TODO Auto-generated method stub
        printQueue.printJob();
    }

}
public class SemaphoreDemo {
    public static void main(String[] arg){
        PrintQueue printQueue=new PrintQueue(2);
        //创建5个线程访问共享数据
        for(int i=0;i<5;i++){
            new Thread(new PrintMachine(printQueue)).start();
        }
    }
}
