/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

/**
 * @author Nilay Pachauri
 *
 */
public class Menu {

	private Calendar cal = null;
	private ArrayList<User> userList = null;
	private Scanner s = null;
	private String fileName = null;
	
	/**
	 * Constructor for the Menu
	 */
	public Menu()	{
		this.parseJSON();
		this.userList = (ArrayList<User>) cal.getUsers();
		s = new Scanner(System.in);
	}
	
	/**
	 * Parses JSON from a designated file in the specified format
	 * @return the Calendar object initialised from the JSON file
	 */
	private Calendar parseJSON()	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		boolean isJSON = false;
		
		while (!isJSON) {
			System.out.print("What is the name of the input file? ");
			
			try {
				this.fileName = br.readLine();
				cal = gson.fromJson(new BufferedReader(new FileReader(this.fileName)), Calendar.class);
				isJSON = true;
				
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
				
			} catch (IOException ioe) {
				System.out.println("That file could not be found.");
			}
		}
		
		return cal;
	}
	
	/**
	 * 
	 * @param num The number of the last available option
	 * @return A valid option the user selects
	 */
	private int checkOption(int num)	{
		int option = 0;
		
		try	{
			option = s.nextInt();
			s.nextLine(); // Removes the remaining characters in input
			if ((option < 1) || (option > num))
				throw new InputMismatchException();
		} catch	(InputMismatchException ime){
			System.out.println("That is not a valid option");
			option = 0;
		}
		
		return option;
	}
	
	
	/**
	 * Prompts the user for an option between 1 to 8:<br>
	 *  1) Display User's Calendar<br>
	 *  2) Add User<br>
	 *  3) Remove User<br>
	 *  4) Add Event<br>
	 *  5) Delete Event<br>
	 *  6) Sort Users<br>
	 *  7) Write File<br>
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
		
		return checkOption(8);
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
	
	/*
	 * Handles the option the user chose
	 */
	public void performOption(int option)	{
		
		switch (option)	{
		
			case 1:	{
				displayUsersCalendar();
				break;
			}
			
			case 2:	{
				addUser();
				break;
			}
			
			case 3:	{
				removeUser();
				break;
			}
			
			case 4:	{
				addEvent();
				break;
			}
			
			case 5:	{
				deleteEvent();
				break;
			}
			
			case 6:	{
				sortUsers();
				break;
			}
			
			case 7:	{
				writeFile();
				break;
			}
			
			case 8: {
				exit();
				break;
			}
			
			default:	{
				System.out.println("This should never occur. Congrats, you've broken the program");
				break;
			}
		}
	}

	/**
	 * Displays the User's Calendar from the JSON file
	 */
	private void displayUsersCalendar() {
		System.out.println();
		for (int i = 0; i < this.userList.size(); i++)	{
			System.out.println((i + 1) + ") " + this.userList.get(i).getName());
			for (int j = 0; j < this.userList.get(i).getEvents().size(); j++)	{
				System.out.println("\t" + ((char) ('a' + j)) + ". " + this.userList.get(i).getEvents().get(j));
			}
		}
	}

	/**
	 * Helper method which adds or deletes the user depending on the parameter
	 * @param toAdd adds the user to the list if true, deletes user if false
	 */
	private void userHelper(boolean toAdd)	{		
		boolean check = false;
		
		do {
			System.out.println();
			System.out.print("What is the user's name? ");
			String nameString = s.nextLine();
			try	{
				if (nameString.split(" ").length != 2)
					throw new ArrayIndexOutOfBoundsException();
				
				Name name = new Name(nameString.split(" ")[0], nameString.split(" ")[1]);
				
				if (toAdd)	{
					if (this.containsName(this.userList, name))
						throw new ArrayStoreException();
					else
						this.userList.add(new User(name, new ArrayList<Event>()));					
				}
				else
					this.userList.removeIf((User user) -> user.getName().equals(name));
				
				check = true;
			}	catch (ArrayIndexOutOfBoundsException aioobe)	{
				System.out.println("Invalid, must have a first and last name.");
				check = false;
			}	catch (ArrayStoreException ase)	{
				System.out.println("Invalid, this user already exists.");
				check = false;
			}
		} while (!check);
	}
	
	/**
	 * @param list User List to search for duplicate name
	 * @param name Name to find duplicate of in list
	 * @return whether a duplicate was found in the list
	 */
	private boolean containsName(ArrayList<User> list, Name name) {
		for (User u : list)
			if (u.getName().equals(name))
				return true;
		return false;
	}

	/**
	 * Add a user to the Calendar
	 */
	private void addUser() {
		this.userHelper(true);
	}

	/**
	 * Remove a user from the Calendar
	 */
	private void removeUser() {
		this.userHelper(false);
	}
	
	/**
	 * Helper method to access the list of users to see their events
	 * @param toAdd
	 * @return the user selected to modify the events
	 */
	private int eventHelper(boolean toAdd)	{
		for (int i = 0; i < this.userList.size(); i++)
			System.out.println((i + 1) + ") " + this.userList.get(i).getName());
		
		System.out.println();
		if (toAdd)
			System.out.println("To which user would you like to add an event?");
		else
			System.out.println("To which user would you like to delete an event?");
		
		int option = 0;
		while (option == 0)
			option = checkOption(this.userList.size());
		
		return option;
	}
	
	/**
	 * Add an event to the Calendar under an existing User
	 */
	private void addEvent() {
		// TODO Auto-generated method stub
		
		System.out.println();
		
		int option = eventHelper(true);
		
		boolean check = false;
		
		String title;
		String time;
		Date date = null;
		
		System.out.println();
		System.out.print("What is the title of the event? ");
		title = s.nextLine();
		
		do {
			System.out.println();
			System.out.print("What time is the event? ");
			time = s.nextLine();
			
			DateFormat sdf = new SimpleDateFormat("hh:mm a");
			try {
				sdf.parse(time);
				check = true;
			} catch (ParseException pe) {
				// Catch invalidly formatted date String
				System.out.println("That is not a valid option");
				check = false;
			}		
		} while (!check);

		do {
			try {
				System.out.println();
				System.out.print("What month? ");
				int month = s.nextInt();
				
				System.out.println();
				System.out.print("What day? ");
				int day = s.nextInt();
				
				System.out.println();
				System.out.print("What year? ");
				int year = s.nextInt();
				
				String monthString = Month.of(month).toString();
				
				date = new Date(monthString.charAt(0) + monthString.substring(1).toLowerCase(), day, year);
				check = true;
			} catch (InputMismatchException | DateTimeException e) {
				// TODO Auto-generated catch block
				System.out.println("That is not a valid option");
				check = false;
			}
		} while (!check);
		
		Event e = new Event(title, time, date);
		
		System.out.println();
		System.out.println("Added: " + e.toString() + " to " + this.userList.get(option - 1).getName().getFname() + " " + this.userList.get(option - 1).getName().getLname() + "'s calendar.");
		
		this.userList.get(option - 1).getEvents().add(e);
		Collections.sort(this.userList.get(option - 1).getEvents());
	}

	/**
	 * Delete an Event from the Calendar under an existing User
	 */
	private void deleteEvent() {
		
		System.out.println();
		
		int option = eventHelper(false);
		int size = this.userList.get(option - 1).getEvents().size();
		
		if (size > 0)	{
			for (int i = 0; i < size; i++)
				System.out.println((i + 1) + ") " + this.userList.get(option - 1).getEvents().get(i));
			
			System.out.println();
			System.out.print("Which event would you like to delete? ");
			int evOption = checkOption(size);
			
			System.out.println();
			System.out.println(this.userList.get(option - 1).getEvents().get(evOption - 1).getTitle() + " has been deleted");
			
			this.userList.get(option - 1).getEvents().remove(evOption - 1);
		} else	{
			System.out.println();
			System.out.println("Calendar is empty.");
		}
		
	}

	/**
	 * Sorts the users in alphabetical order
	 */
	private void sortUsers() {
		Collections.sort(this.userList);
	}

	/**
	 * Writes the updated calendar to the original file opened
	 */
	private void writeFile() {
		Gson gson = new Gson();
		try {
			gson.toJson(this.cal, new FileWriter(this.fileName));
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void exit() {
		// TODO Auto-generated method stub
		return;
	}
}
