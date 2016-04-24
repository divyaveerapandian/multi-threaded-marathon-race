package race;

import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class is for the User Interaction. In this class we read the user's data source choice and
 *  starts the thread according to in input from data sources.
 * @author Divya Veerapandian
 *
 */
public class MarathonApp {
	private static ArrayList<Thread> individualRunnerThread=null;
	private static ArrayList<Runner> inputRunnerDetailsList=null;
	static Scanner sc = new Scanner(System.in);

	
	/**
	 * This is the main method where the Application is started.
	 * @param args
	 */
	public static void main(String[] args) {
		startMarathon();
	}
	
	/**
	 * Provides the user with the menu to select the data sources : 
	 * 1. Derby database	 2. XML file	3. Text file	4. Default two runners	5. Exit .
	 * Baesd on the user's data source selection, this method calls the fetch methods to fetch datas from different datasources. 
	 * Starts the thread for each runner from the data.
	 * 
	 */
	public static void startMarathon() {
		individualRunnerThread = new ArrayList<>();
		inputRunnerDetailsList = new ArrayList<>();
			System.out.println("Welcome to the Marathon Race Runner Program");
			System.out.println("\nSelect your data source:\n" 
					+  "1. Derby database \n"
					+  "2. XML file  \n"
					+  "3. Text file \n"
					+  "4. Default two runners \n"
					+  "5. Exit \n"	);
			int dataSourceType =InputValidator.getIntegerInput("Enter your choice	:", sc) ;

			try{
				switch (dataSourceType) {
				case 1:{ 							
							buildRunnersAndStartRace(new DatabaseSource(),"jdbc:derby:RunnerDB");
							break;
							
				}
				case 2:{
							System.out.print("Enter XML file name : ");//RunnerDetails.xml
							String fileName=sc.next();
							buildRunnersAndStartRace(new XmlDataSource(),fileName);
							break;
				}
				case 3:{
							System.out.print("Enter text file name : ");//RunnerDetails.txt
							String fileName=sc.next();
							buildRunnersAndStartRace(new TextDataSource(),fileName);
							break;
				}
				case 4:{
							Runner runner1=new Runner("Tortoise", 10, 20);
							Runner runner2=new Runner("Hare", 100, 50);
							inputRunnerDetailsList.add(runner1);
							inputRunnerDetailsList.add(runner2);
							startThreads();
							break;
				}
				case 5: { 
							System.out.println("Thank you for using my Marathon Race Program.");
							System.exit(0);
							break;
				} 
				default: System.out.println("ERROR : Enter valid menu (1-5) . ");
				
				}
				
			}
			
			catch(Exception e) {
				System.out.println("ERROR: while trying to read the input. The exception message is: " + e.getMessage());
				startMarathon();
			}	
	}
/**
 * This method reads from any data source(text,xml or database) specificed at 
 * the location parameter and builds the thread object representing the runners.
 * Then it starts the race with the list of runners.
 * @param dataSource
 * @param location
 * @throws Exception
 */
	private static void buildRunnersAndStartRace(DataSource dataSource, String location) throws Exception {
		inputRunnerDetailsList=dataSource.read(location);
		if(inputRunnerDetailsList.size()>0)
			startThreads();
		else
			System.out.println("No participant have registered  for the Marathon. ");
	}
	
	/**
	 * This method creates the thread for each Runner in the Arraylist and starts them.
	 */
	private synchronized static void startThreads() {
		System.out.println("\nGet set...Go!");
		for(Runner runnerObj : inputRunnerDetailsList){
			Thread runnerthread =new ThreadRunner(runnerObj);
			individualRunnerThread.add(runnerthread);
			runnerthread.start();
		}
	}
	
	/**
	 * This method is called by the thread that finishes the race first.
	 * This method  declares the thread that calls it to be the winner and interrupt the other threads.
	 * And starts the marathon again.
	 * @param runnerName the name of the thread which calls this method.
	 */
	public synchronized static void finished(String runnerName){
		for(Thread runnerObj : individualRunnerThread){
			runnerObj.interrupt();
		}	
		System.out.println("\n" +runnerName + "  : I finished ! \nThe race is over! The "+runnerName + " is the winner.\n");
		for(Runner runnerObj : inputRunnerDetailsList){
			if(!runnerObj.getRunnerName().equalsIgnoreCase(runnerName))
				System.out.println(runnerObj.getRunnerName() + "  : You beat me fair and square.");
		}
		System.out.println("\n. . . . . . . . Press any key to continue . . . . . . . . .");
		if(sc.hasNext()){
			 sc.next();
			 startMarathon();
		}
		
	}
}
