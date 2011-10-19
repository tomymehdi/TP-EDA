package AI;

public class TimeFinishedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TimeFinishedException(){
		super();
	}
	
	public TimeFinishedException(String s){
		super(s);
	}
}
