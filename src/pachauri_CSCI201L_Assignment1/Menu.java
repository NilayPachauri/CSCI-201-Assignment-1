/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.util.ArrayList;
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
	 * 
	 */
	private void addUser() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void removeUser() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void addEvent() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 */
	private void deleteEvent() {
		// TODO Auto-generated method stub
		
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
		
	}
}
