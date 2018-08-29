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
public class Init {
	
	private Calendar cal = null;

	/*
	 * Constructor to Initialise Calendar Data and Prompt for option
	 */
	public Init()	{
		
		this.cal = parseJSON();
	}
	
	/*
	 * Parses JSON from a designated file in the specified format
	 */
	private Calendar parseJSON()	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		boolean isJSON = false;
		
		while (!isJSON) {
			System.out.print("What is the name of the input file? ");
			
			try {
				String inputFileName = br.readLine();
				cal = gson.fromJson(new BufferedReader(new FileReader(inputFileName)), Calendar.class);
				isJSON = true;
				
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
				
			} catch (IOException ioe) {
				System.out.println("That file could not be found.");
			}
		}
		
		return cal;
	}
	
	/*
	 * Prompts the user for an option between 1 to 8:
	 *  1) Display User's Calendar
	 *  2) Add User
	 *  3) Remove User
	 *  4) Add Event
	 *  5) Delete Event
	 *  6) Sort Users
	 *  7) Write File
	 *  8) Exit
	 */
	private int getOptionHelper()	{
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
			option = 0;
		}
		
		return option;	
	}
	
	/*
	 * Getter Method for the Calendar
	 */
	public Calendar getCalendar()	{
		return this.cal;
	}
	
	/*
	 * Prompts the user for the option to pick
	 */
	public int getOption()	{
		int option = 0;
		while (option == 0)
			option = getOptionHelper();
		return option;
	}
}
