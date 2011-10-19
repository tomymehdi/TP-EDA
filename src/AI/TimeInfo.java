package AI;

public class TimeInfo {
	private final static int MAX_TIME_ITERATIONS=100;

	private int iterations;
	private long initialTime;
	private long maxTime;

	public TimeInfo(long maxTime){
		this.maxTime=maxTime;
		iterations=0;
		initialTime=System.currentTimeMillis();
	}

	public void update(){
		if(++iterations%MAX_TIME_ITERATIONS==0){
			if(System.currentTimeMillis()-initialTime>=maxTime){
				throw new TimeFinishedException();
			}
		}
	}

}
