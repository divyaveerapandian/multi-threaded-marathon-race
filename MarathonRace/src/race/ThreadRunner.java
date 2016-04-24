package race;
/**
 * This class inherits from Thread class and Overrides the run method of the Thread class.
 * 
 * @author Divya Veerapandian 
 *
 */
public class ThreadRunner extends Thread {
	Runner runner ;
	public ThreadRunner(Runner participant) {
		runner=participant;
	}
	
	/**
	 * The run method consist of a loop that repeats until the runner has reached 1000 meters.
	 *  Each time through the loop, the thread decide whether it should run or rest based on a random number and the rest percentage.
	 *  If this random number indicates that the runner should run, the class should add the speed value.
	 *   The run method should sleep for 100 milliseconds on each repetition of the loop.
	 *   Finally calls the finished method of the main Application if it reaches the 1000 metres.
	 *   Stops if it is interrupted.
	 */
	@Override
	public void run() {
		int flag=0;
		int random=(int) (Math.random()*100);
		for(int i=runner.getRunnerSpeed() ; i < 1000+runner.getRunnerSpeed() ; i=i+runner.getRunnerSpeed()){
			if(!interrupted()){
				if(random > runner.getRestPercentage()){
					if(i>1000){
						System.out.println(runner.getRunnerName() + " : " + 1000);
					break;
					}
					else
						System.out.println(runner.getRunnerName() + " : " + i);
				}
				else{
					try {
						Thread.sleep(100);
					} 
					catch (InterruptedException e) {
						flag=1;
						break;
					}
				}
			}
			else{
				flag=1;
				break;
			}
		}
		if(flag==0)
			MarathonApp.finished(runner.getRunnerName());
	}

}