package utilies;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Terminal {
	public static String executeCommand(String command) {
	    StringBuffer output = new StringBuffer();
	    Process p;
	    try {
	        p = Runtime.getRuntime().exec(command);
	        p.waitFor();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
	        String line = "";           
	        while ((line = reader.readLine())!= null) 
	            output.append(line + "\n");
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return output.toString();
	}
}
