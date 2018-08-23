/**
 * 
 */
package pachauri_CSCI201L_Assignment1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import com.google.gson.Gson;

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
		
		System.out.print("What is the name of the input file?");
		try {
			String inputFileName = br.readLine();
			gson.fromJson(br, Events.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}

}
