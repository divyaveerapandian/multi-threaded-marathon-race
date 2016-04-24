package race;

import java.util.InputMismatchException;
import java.util.Scanner;
/**
 * This class Validates the Input From the user.
 * @author Divya Veerapandian
 *
 */
public class InputValidator {
	/**
	 * This method prompts the user for the input and then gets the input and validates it.
	 * @param prompt String that prompts the user to give input.
	 * @param scan Scanner object using which the user input are read.
	 * @return Valid integer value provide by user.
	 */
	public static int getIntegerInput(String prompt,Scanner scan){
		boolean flag=false;
		int intval=0;
		while(flag==false){
		System.out.print(prompt);	
		try{
			intval=scan.nextInt();
			flag=true;
		}
		catch(InputMismatchException e){
			System.out.println("ERROR : Invalid integer value.Try Again");
			scan.nextLine();
		}
		}
		return intval;
	}
	
}
