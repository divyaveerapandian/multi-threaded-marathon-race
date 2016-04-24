package race;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class TextDataSource implements DataSource {

	private ArrayList<Runner> inputRunnerDetailsList =new ArrayList<>();
	/**
	 * This method gets the file  name, checks for the existence of the file path.
	 * If path exists, reads data from the Text file and stores them in an ArrayList
	 * Else throws Exception.
	 * @param fileName name of the file from which data is to be read.
	 * @return ArrayList containing the details of each runner in a Runner object.
	 */
	@Override
	public ArrayList<Runner> read(String location) throws Exception {
		try{
			
		Path path =Paths.get("Resources/"+location);
		if(!Files.exists(path))
				throw new FileNotFoundException("ERROR : The File entered is not Found. Please enter a valid text file.") ;
		
		BufferedReader in = new BufferedReader(new FileReader(path.toFile()));
		String line=in.readLine();
		Runner runner;
		while(line!=null){
			String[] column = line.split("\t");
			runner = new Runner(column[0], Integer.parseInt(column[1]) , Integer.parseInt(column[2]) );
			inputRunnerDetailsList.add(runner);
			line=in.readLine();
		}
		in.close();
	}
	catch (ArrayIndexOutOfBoundsException e) {
		System.out.println("ERROR : Enter valid TEXT file or Check the Input and Try Again. ");
		throw e;
	}
	return inputRunnerDetailsList;
	}

}
