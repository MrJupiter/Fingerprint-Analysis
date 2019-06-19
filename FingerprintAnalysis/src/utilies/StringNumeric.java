package utilies;

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
