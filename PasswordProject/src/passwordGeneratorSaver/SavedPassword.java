package forLoops;

import java.util.ArrayList;

public class SavedPassword {
	
	private String title;
	private String password;
	private ArrayList<String> passwordAndTitle = new ArrayList<String>(2);


	
	public SavedPassword(String title, String password) {
		this.title = title;
		this.password = password;	
		
		passwordAndTitle.add(0, this.title);
		passwordAndTitle.add(1, this.password);
		
		

	}
	
	
	public String getPasswordandTitle() {
		String retString = "";
		
		retString += title + ": ";
		retString += password;
		
		return retString;
			
	}
	
	
	
	public static void main(String[] args) {
		
	}
	

}
