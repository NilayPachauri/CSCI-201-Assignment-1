/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.Month;
import java.util.ArrayList;
import java.util.Collections;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * @author Nilay Pachauri
 *
 */
public class Menu {

	private ArrayList<User> userList = null;
	
	/*
	 * Constructor for the Menu
	 */
	public Menu(Calendar cal)	{
		
		this.userList = (ArrayList<User>) cal.getUsers();
	}
	
	/**
	 * 
	 * @param num The number of the last available option
	 * @return A valid option the user selects
	 */
	private int checkOption(int num)	{
		Scanner s = new Scanner(System.in);
		int option = 0;
		
		try	{
			option = s.nextInt();
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
		// TODO Auto-generated method stub
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

		Scanner scan = new Scanner(System.in);
		
		boolean check = false;
		
		do {
			System.out.println();
			System.out.print("What is the user's name? ");
			String nameString = scan.nextLine();
			try	{
				if (nameString.split(" ").length != 2)
					throw new ArrayIndexOutOfBoundsException();
				
				Name name = new Name(nameString.split(" ")[0], nameString.split(" ")[1]);
				
				if (toAdd)
					this.userList.add(new User(name, new ArrayList<Event>()));
				else
					this.userList.removeIf((User user) -> user.getName().equals(name));
				
				check = true;
			}	catch (ArrayIndexOutOfBoundsException aioobe)	{
				System.out.println("Invalid, must have a first and last name.");
				check = false;
			}
		} while (!check);
		
		scan.close();
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
	 * @return the user selected to modify the events
	 */
	private int eventHelper()	{
		for (int i = 0; i < this.userList.size(); i++)
			System.out.println((i + 1) + ") " + this.userList.get(i).getName());
		
		System.out.println();
		System.out.println("To which user would you like to add an event?");
		
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
		
		int option = eventHelper();
		
		Scanner scan = new Scanner(System.in);
		boolean check = false;
		
		String title;
		String time;
		Date date = null;
		
		System.out.println();
		System.out.print("What is the title of the event? ");
		title = scan.nextLine();
		
		do {
			System.out.println();
			System.out.print("What time is the event? ");
			time = scan.nextLine();
			
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
				int month = scan.nextInt();
				
				System.out.println();
				System.out.print("What day? ");
				int day = scan.nextInt();
				
				System.out.println();
				System.out.print("What year? ");
				int year = scan.nextInt();
				
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
		this.userList.get(option - 1).getEvents().add(e);
		Collections.sort(this.userList.get(option - 1).getEvents());
	}

	/**
	 * 
	 */
	private void deleteEvent() {
		// TODO Auto-generated method stub
		
		System.out.println();
		
		int option = eventHelper();
		int size = this.userList.get(option - 1).getEvents().size();
		
		if (size > 0)	{
			for (int i = 0; i < size; i++)
				System.out.println((i + 1) + ") " + this.userList.get(i).getEvents().get(i));
		} else	{
			System.out.println();
			System.out.println("Calendar is empty.");
		}
		
	}

	/**
	 * 
	 */
	private void sortUsers() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void writeFile() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void exit() {
		// TODO Auto-generated method stub
		return;
	}
}
