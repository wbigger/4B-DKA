package marconi.c4binf.orale;

import java.util.Date;

public class SimpleThread {
	
	static class ProgressBar {
		// this method should NOT be interrupted by other threads
		// to do this, we use the keyword "synchronized"
		synchronized void show() {
			for(int i=0;i<100;i++){
				Date date = new Date();
				System.out.println("Thread "+ Thread.currentThread().getName()+" is loading "+i*1+"% @"+date);
			}
		}
	}
	
	static class LoadVideoThread implements Runnable{
		ProgressBar progressBar;
		
		public LoadVideoThread(ProgressBar progressBar) {
			super();
			this.progressBar = progressBar;
		}
		
		public void run() {
			// use the global instance of the progress bar
			progressBar.show();
		}
	}

	public static void main(String[] args) {
		// Our application has a single, global progress bar
		// on the top of the screen
		ProgressBar pb = new ProgressBar();
		
		// pass the global progress bar as argument to the new threads
		Thread t1=new Thread(new LoadVideoThread(pb),"DKA");
		t1.setPriority(1);
		Thread t2=new Thread(new LoadVideoThread(pb),"DKA2");
		t2.setPriority(10);
        
		// launch the threads
		t1.start();
        t2.start();
        
        // as we can see running this program, the DKA progress is
        // _always_ executed first, whatever is its priority
        // this is because the block of code inside synchronized
        // cannot be interrupted!
        
        // NOTE: another way to synchronize block of code is as follow:
        // synchronized (this) {
        //    ...some code here...
        // }
	}

}
