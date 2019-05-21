package marconi.c4binf.orale;

import java.util.Date;

public class SimpleThread {
	static class LoadVideoThread implements Runnable{

		
		public void run() {
			for(int i=0;i<100;i++){
				//double f = 100.0/1000.0*0.9999999+1.01*0.99999*0.11111;
				Date date = new Date();
				System.out.println("Thread "+ Thread.currentThread().getName()+" is loading "+i*1+"% @"+date);
			}
			
		}
		
	}

	public static void main(String[] args) {
		Thread t1=new Thread(new LoadVideoThread(),"DKA");
		t1.setPriority(1);
		Thread t2=new Thread(new LoadVideoThread(),"DKA2");
		t2.setPriority(10);
        
		t1.start();
        t2.start();
	}

}
