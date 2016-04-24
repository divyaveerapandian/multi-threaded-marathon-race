package race;
/**
 * This class declares and instantiates the class variables runner name, speed, rest percentage.
 * @author Divya Veerapandian
 *
 */
public class Runner {
	private String runnerName;
	private int runnerSpeed;
	private int restPercentage;

	/**
	 * This is the default constructor where the instance variables are initialised.
	 */
	public Runner() {
		runnerName="";
		runnerSpeed=0;
		restPercentage=0;
	}
	
	/**
	 * Constructor instaintiates the instance variable.
	 * @param name Name of the runner.
	 * @param speed Speed of the runner.
	 * @param percent Rest percentage of the runner.
	 */
	public Runner(String name, int speed, int percent) {
		runnerName=name;
		runnerSpeed=speed;
		restPercentage=percent;
	}
	
	/**
	 * This method gives the runner name.
	 * @return  Name of the runner.
	 */
	public String getRunnerName(){
		return runnerName;
	}
	
	/**
	 * This method sets the runner name.
	 * @param name  Name of the runner.
	 */
	public void setRunnerName(String name){
		this.runnerName=name;
	}
	
	/**
	 *  This method gives the runner speed.
	 * @return  Speed of the runner.
	 */
	public int getRunnerSpeed(){
		return runnerSpeed;
	}
	
	/**
	 *  This method sets the runner speed.
	 * @param speed  Speed of the runner.
	 */
	public void setRunnerSpeed(int speed){
		this.runnerSpeed=speed;
	}
	
	/**
	 * This method gets the rest percentage of the runner.
	 * @return Rest percentage of the runner.
	 */
	public int getRestPercentage(){
		return restPercentage;
	}
	
	/**
	 * This method sets the rest percentage of the runner.
	 * @param percent Rest percentage of the runner.
	 */
	public void setRestPercentage(int percent){
		this.restPercentage=percent;
	}

}
