/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

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

		Init init = new Init();
		Menu menu = new Menu(init.getCalendar());
		int option = 0;
		
		do	{
			option = menu.getOption();
			menu.performOption(option);
		} while(option != 8);
	}
	
}
