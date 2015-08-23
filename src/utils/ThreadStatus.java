package utils;

public class ThreadStatus {
	int availableThreads = 0;
	public ThreadStatus(){
		
	}
	public void setAvailableThreads(int availableThreads) {
		this.availableThreads = availableThreads;
	}
	public void releaseThread(){
		System.out.println("RRR");
		this.availableThreads -= 1;
	}
	public void addThread(){
		this.availableThreads ++;
	}
	public boolean goodToGo(int boundary){
		return this.availableThreads < boundary ;
	}
}
