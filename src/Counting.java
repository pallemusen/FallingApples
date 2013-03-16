/**
 * 
 * @author Pallemusen
 * @description This class is a thread, which can count seconds. It has the ability to return a time difference, 
 * pause- and resume its counting, and is of course to be ran as a Thread. 
 */

public class Counting implements Runnable {

	private long t0;
	private long t1; 
	private int seconds;
	private int deltaTime; 
	
	public int getSeconds() {
		return seconds;
	}
	
	public void resetCount() {
		deltaTime = 0; 
		t0 = System.currentTimeMillis();
	}
	
	// saves the difference in time
	public void isPaused() {
		deltaTime = seconds; 
	}
	
	// applies the saved difference in time so that it stays the same diff
	public void isUnpaused() {
		t1 = System.currentTimeMillis();
		t0 = t1 - (deltaTime*1000); 
	}
	
	@Override
	public void run() {	 
		while(true) {
			t1 = System.currentTimeMillis()+1000;
			seconds = (int)(t1-t0)/1000; 
			System.out.println("Elapsed: " + seconds + " seconds");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {}
		}	
	}
}
