package race;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class DatabaseSource implements DataSource {
	private ArrayList<Runner> inputRunnerDetailsList =new ArrayList<>();

	/**
	 * This method gets a Connection to the database.
	 * If Connection is created, reads data from the table and stores them in an ArrayList.
	 * Then closes the Connection.
	 * @return ArrayList containing the details of each runner in a Runner object.
	 * @throws Exception If failed to  connect to the database .
	 */
	@Override
	public ArrayList<Runner> read(String location) throws Exception{
		Connection connectionObj = null;
		try{
			 connectionObj = getConnection(location);
			Runner runnerObj=null;
			Statement statement=connectionObj.createStatement();
			ResultSet rs= statement.executeQuery("SELECT * FROM RunnerDetails");
			while(rs.next()){
				runnerObj= new Runner(rs.getString("RunnerName"), Integer.parseInt(rs.getString("RunnersMoveIncrement")), 
						Integer.parseInt(rs.getString("RestPercentage")));
				inputRunnerDetailsList.add(runnerObj);
			}
			rs.close();
			statement.close();			
			
		}
		catch(SQLException e){
			
			e.printStackTrace();
			throw new SQLException("ERROR : No current connection. Try Again. Message: " + e.getMessage());
			
		}
		finally {
		   
			closeConnection(connectionObj);
		}

		
		return inputRunnerDetailsList;
	}
	
	/**
	 * This method using URL, username, password creates a connection with the database.
	 * @return Connection created with the database.
	 * @throws Exception  If failed to  connect to the database .
	 */
	private  Connection getConnection(String url) throws Exception {
			
		try {
				String dbDirectory="Resources";
				System.setProperty("derby.system.home", dbDirectory);
				
				return DriverManager.getConnection("jdbc:derby:RunnerDB","","");	
			} 
			catch (SQLException e) {				  
				throw new SQLException("ERROR : Failed to start your database. Check your Connection And Try Again.  Message: " + e.getMessage());
			}
			
		}
		
	/**
	 * This method closes the connection made with  the database.
	 * @return true if successfully closes the connection.
	 * @throws Exception If fail to close the connection.
	 */
	private  boolean closeConnection(Connection connectionObj) throws Exception {
			try{
				 
				if(connectionObj != null){
					connectionObj.close();
					
				}
				   if(connectionObj.isClosed()){
					   System.out.println("DB connection successfully closed");
				   }
				   
				   System.gc();
				   
			}
			catch(SQLException e){
					throw new SQLException("ERROR : Cannot close connection -as no connection is open . Check your Connection And Try Again. ");	
			}
			catch(Exception e){
				throw new Exception("ERROR : Check your Connection And Try Again. ");
			}
			return true;
		}
}
