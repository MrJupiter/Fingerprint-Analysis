package utilies;

/**
 * 
 * @author Abdelmalik GHOUBIR
 * @date 17 June 2019
 * 
 */

public class StringNumeric {
	public static boolean isNumeric(String str) { 
		try {  
			Double.parseDouble(str);  
		    return true;
		 } catch(NumberFormatException e){  
			 return false;  
		 }  
	}
}
