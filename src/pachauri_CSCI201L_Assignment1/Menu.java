/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.BufferedWriter;
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
	private boolean fileChanged = false;
	
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
	 * Checks if the users input is between the valid options
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
		System.out.println("\t1) Display User's Calendar");
		System.out.println("\t2) Add User");
		System.out.println("\t3) Remove User");
		System.out.println("\t4) Add Event");
		System.out.println("\t5) Delete Event");
		System.out.println("\t6) Sort Users");
		System.out.println("\t7) Write File");
		System.out.println("\t8) Exit");
		System.out.println();
		System.out.print("What would you like to do? ");
		
		return this.checkOption(8);
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
			System.out.println("\t" + (i + 1) + ") " + this.userList.get(i).getName());
			for (int j = 0; j < this.userList.get(i).getEvents().size(); j++)	{
				System.out.println("\t\t" + ((char) ('a' + j)) + ". " + this.userList.get(i).getEvents().get(j));
			}
		}
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
		boolean check = false;
		
		do {
			System.out.println();
			System.out.print("What is the user's name? ");
			String nameString = s.nextLine();
			try	{
				if (nameString.indexOf(' ') == -1)
					throw new ArrayIndexOutOfBoundsException();
				
				Name name = new Name(nameString.substring(0, nameString.indexOf(' ')), nameString.substring(nameString.indexOf(' ') + 1));
				
				if (this.containsName(this.userList, name))
					throw new ArrayStoreException();
				else
					this.userList.add(new User(name, new ArrayList<Event>()));	
				
				check = true;
			}	catch (ArrayIndexOutOfBoundsException aioobe)	{
				System.out.println("Invalid, must have a first and last name.");
				check = false;
			}	catch (ArrayStoreException ase)	{
				System.out.println("Invalid, this user already exists.");
				check = false;
			}
		} while (!check);
		
		this.fileChanged = true;
	}

	/**
	 * Remove a user from the Calendar
	 */
	private void removeUser() {
		System.out.println();
		
		int option = userEventHelper(true, false);
		
		System.out.println();
		System.out.println(this.userList.get(option - 1).getName().getFname() + " " + this.userList.get(option - 1).getName().getLname() + " has been deleted.");
		
		this.userList.remove(option - 1);
		
		this.fileChanged = true;
	}
	
	/**
	 * Helper method to access the list of users to see their events
	 * @param user true if modifying users, false if modifying events
	 * @param toAdd true if adding, false if deleting
	 * @return the user selected to modify the events
	 */
	private int userEventHelper(boolean user, boolean toAdd)	{
		for (int i = 0; i < this.userList.size(); i++)
			System.out.println("\t" + (i + 1) + ") " + this.userList.get(i).getName());
		
		System.out.println();
		if (user)
			if (!toAdd)
				System.out.print("Which user would you like to delete? ");
		else
			if (toAdd)
				System.out.print("To which user would you like to add an event? ");
			else
				System.out.print("From which user would you like to delete an event? ");
		
		int option = 0;
		while (option == 0)
			option = this.checkOption(this.userList.size());
		
		return option;
	}
	
	/**
	 * Add an event to the Calendar under an existing User
	 */
	private void addEvent() {
		// TODO Auto-generated method stub
		
		System.out.println();
		
		int option = userEventHelper(false, true);
		
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
		
		this.fileChanged = true;
	}

	/**
	 * Delete an Event from the Calendar under an existing User
	 */
	private void deleteEvent() {
		
		System.out.println();
		
		int option = userEventHelper(false, false);
		int size = this.userList.get(option - 1).getEvents().size();
		
		if (size > 0)	{
			for (int i = 0; i < size; i++)
				System.out.println("\t" + (i + 1) + ") " + this.userList.get(option - 1).getEvents().get(i));
			
			System.out.println();
			System.out.print("Which event would you like to delete? ");
			int evOption = this.checkOption(size);
			
			System.out.println();
			System.out.println(this.userList.get(option - 1).getEvents().get(evOption - 1).getTitle() + " has been deleted");
			
			this.userList.get(option - 1).getEvents().remove(evOption - 1);
		} else	{
			System.out.println();
			System.out.println("Calendar is empty.");
		}
		
		this.fileChanged = true;
	}

	/**
	 * Sorts the users in alphabetical order
	 */
	private void sortUsers() {
		System.out.println();
		
		System.out.println("1) Ascending (A-Z)");
		System.out.println("2) Descending (Z-A)"); 
		
		System.out.println();
		
		System.out.print("How would you like to sort? ");
		int option = this.checkOption(2);
		
		if (option == 1)
			Collections.sort(this.userList);
		else
			Collections.sort(this.userList, Collections.reverseOrder());
		
		System.out.println();
		for (int i = 0; i < this.userList.size(); i++)
			System.out.println("\t" + (i + 1) + ") " + this.userList.get(i).getName());
		
		this.fileChanged = true;
	}

	/**
	 * Writes the updated calendar to the original file opened
	 */
	private void writeFile() {
		Gson gson = new Gson();
		
		try {
			String output = gson.toJson(this.cal);
			BufferedWriter bw = new BufferedWriter(new FileWriter(this.fileName));
			
			bw.write(output);
			bw.flush();
			bw.close();
			
			this.fileChanged = false;
		} catch (JsonIOException | IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Terminates the program<br><br>
	 * If the user has made changes, prompt to save the file<br>
	 * Otherwise, no prompt
	 */
	private void exit() {
		System.out.println();
		
		if (fileChanged)	{
			System.out.println("Changes have been made since the file was last saved.");
			System.out.println("\t1) Yes");
			System.out.println("\t2) No");
			System.out.print("Would you like to save the file before exiting? ");
			int option = this.checkOption(2);
			
			System.out.println();
			
			if (option == 1)	{
				this.writeFile();
				System.out.println("File was saved.");
			} else	{
				System.out.println("File was not saved");
			}
			
			System.out.println();
			System.out.println("Thank you for using my program");
		}
			
		return;
	}
}
