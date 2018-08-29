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
	 * Getter Method for the Calendar
	 */
	public Calendar getCalendar()	{
		return this.cal;
	}
}
