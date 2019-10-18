package juc;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;


public class CyclicBarrierDemo {

	// 模拟了100米赛跑，10名选手已经准备就绪，只等裁判一声令下。当所有人都到达终点时，比赛结束。
	public static void main(String[] args) throws InterruptedException {

		HashMap<String,String> result=new HashMap<>();
		// 开始的倒数锁 
		final CountDownLatch begin=new CountDownLatch(1) ;  
		// 结束的倒数锁  使用CyclicBarrier,在所有线程结束后启动统计线程
		final CyclicBarrier end=new CyclicBarrier(10, new Statistics(result));  
		
		// 十名选手 		
		for (int index = 0; index < 10; index++) {
			new Thread(new player1(begin,end,result),"player"+index).start();
		}  
		System.out.println("Game Start");  
		// begin减一，开始游戏
		begin.countDown();  
		// 等待end变为0，即所有选手到达终点		

	}
}
class player1 implements Runnable{
	// 开始的倒数锁 
	private  final CountDownLatch begin ;  
	// 结束的倒数锁 
	private final CyclicBarrier end;  
	//成绩记录
	HashMap<String,String> result;

	player1(CountDownLatch begin,CyclicBarrier end,HashMap<String,String> result){
		this.begin=begin;
		this.end=end;
		this.result=result;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {  
			// 如果当前计数为零，则此方法立即返回。
			// 等待
			begin.await();  
			Thread.sleep((long) (Math.random() * 10000));  
			result.put(Thread.currentThread().getName(), new Date().toString());
			System.out.println(Thread.currentThread().getName() + " arrived");  
			end.await();
		} catch (InterruptedException e) { 
			e.printStackTrace();          
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {  
			// 每个选手到达终点时，end就减一			
		}
	}
}
class Statistics implements Runnable{

	private HashMap<String,String> result;
	Statistics(HashMap<String,String> result){
		this.result=result;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Game Over\nbegin statistics:");
		Iterator it=result.keySet().iterator();
		while(it.hasNext()){
			String key=(String)it.next();
			String value=result.get(key);
			System.out.println(key+":"+value);
		}
	}
	
}
