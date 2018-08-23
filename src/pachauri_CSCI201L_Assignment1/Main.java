/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
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

		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Gson gson = new Gson();
		boolean isJSON = false;
		
		
		System.out.print("What is the name of the input file?");
		while (!isJSON) {
			try {
				String inputFileName = br.readLine();
				Users users = gson.fromJson(br, Users.class);
				isJSON = true;
			} catch (IOException ioe) {
				System.out.println("ioe: " + ioe.getMessage());
			} catch (JsonIOException jioe) {
				System.out.println("That file could not be found.");
			} catch (JsonSyntaxException jse) {
				System.out.println("That file is not a well-formed JSON file.");
			} 
		}
		
		
	}

}
