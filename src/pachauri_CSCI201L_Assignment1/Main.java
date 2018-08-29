/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

/**
 * @author Nilay Pachauri
 *
 */
public class Main {

	/**
	 * @param args The command line arguments
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Users users = parseJSON();
		
		int option = 0;
		while (option == 0)
			option = getOption();
	}
	
	private static Users parseJSON()	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		Users users = null;
		boolean isJSON = false;
		
		while (!isJSON) {
			System.out.print("What is the name of the input file? ");
			
			try {
				String inputFileName = br.readLine();
				users = gson.fromJson(new BufferedReader(new FileReader(inputFileName)), Users.class);
				isJSON = true;
				
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
				
			} catch (IOException ioe) {
				System.out.println("That file could not be found.");
			}
		}
		
		return users;
	}
	
	private static int getOption()	{
		System.out.println();
		System.out.println("\t 1) Display User's Calendar");
		System.out.println("\t 2) Add User");
		System.out.println("\t 3) Remove User");
		System.out.println("\t 4) Add Event");
		System.out.println("\t 5) Delete Event");
		System.out.println("\t 6) Sort Users");
		System.out.println("\t 7) Write File");
		System.out.println("\t 8) Exit");
		System.out.println();
		System.out.print("What would you like to do? ");
		
		Scanner s = new Scanner(System.in);
		int option = 0;
		
		try	{
			option = s.nextInt();
			if ((option < 1) || (option > 8))
				throw new InputMismatchException();
		} catch	(InputMismatchException ime){
			System.out.println("That is not a valid option");
		}
		
		return option;	
	}

}
