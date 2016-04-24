package race;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

public class XmlDataSource implements DataSource{

	private ArrayList<Runner> inputRunnerDetailsList = new ArrayList<>();

	/**
	 * This method gets the file  name, checks for the existence of the file path.
	 * If path exists, reads data from the XML file and stores them in an ArrayList
	 * Else throws Exception.
	 * @param fileName name of the file from which data is to be read.
	 * @return ArrayList containing the details of each runner in a Runner object.
	 */
	@Override
	public ArrayList<Runner> read(String location) throws Exception {

		XMLInputFactory inputFactory= XMLInputFactory.newFactory();

			Path path =Paths.get("Resources/"+location);
			if(!Files.exists(path))
				throw new FileNotFoundException("ERROR : The File entered is not Found. Please enter a valid XML file.") ;
		
			FileReader fileWriter=new FileReader(path.toFile());
			XMLStreamReader reader=inputFactory.createXMLStreamReader(fileWriter);
			Runner r=null;
			while(reader.hasNext()){
				int eventType=reader.getEventType();
				switch (eventType) {
							case XMLStreamConstants.START_ELEMENT:
							{
								String elementName=reader.getLocalName();
								if(elementName.equalsIgnoreCase("Runner"))
								{
									r=new Runner();
									r.setRunnerName(reader.getAttributeValue(0));
								}
								if(elementName.equalsIgnoreCase("RunnersMoveIncrement"))
								{
									r.setRunnerSpeed(Integer.parseInt(reader.getElementText()));
								}
								if(elementName.equalsIgnoreCase("RestPercentage"))
								{
									r.setRestPercentage(Integer.parseInt(reader.getElementText()));
								}
								break;
							}
							case XMLStreamConstants.END_ELEMENT :
							{ 
								String elementName=reader.getLocalName();
								if(elementName.equalsIgnoreCase("Runner")){
									inputRunnerDetailsList .add(r);
								}
								break;
							}
							default:
								break;
				}
				
				reader.next();
			}
		return inputRunnerDetailsList;
	}

}
