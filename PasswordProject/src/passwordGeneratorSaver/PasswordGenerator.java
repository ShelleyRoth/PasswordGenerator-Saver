package forLoops;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class PasswordGenerator {
	
	
	// PRIVATE INSTANCE VARIABLES (Also known as fields)
	public static String[] characterList = new String[96];
	private ArrayList<SavedPassword> savedPasswords = new ArrayList<SavedPassword>();

	
	// CONSTRUCTOR
	public PasswordGenerator(String fileName) throws FileNotFoundException  {
		txtToArray(fileName);
	}
		
	
	// METHODS
	public static void txtToArray(String fileName) throws FileNotFoundException {
		File file = new File(fileName);
		Scanner sc = new Scanner(file);
		
		for(int i = 0; i < characterList.length; i++) {
			
			if(sc.hasNextLine()) {
				characterList[i] = sc.nextLine();
			}
			
		}
		
		sc.close();
		
	
	}
	
	
	public ArrayList<String> randomizedCharacterList() {
		ArrayList<String> randomArr = new ArrayList<String>(96);
		
		//copy everything from characterList to randomArr

		for(int i = 0; i < characterList.length; i++) {
			randomArr.add(characterList[i]);
		}
		
		// randomizes
		Collections.shuffle(randomArr);
				
		
		return randomArr;
	}
	
	public void askPasswordLength() throws FileNotFoundException {
		Scanner sc = new Scanner(System.in);
		
		System.out.println("How many characters would you like to be in the password? \nEnter a number between 1 and 96");
		int passLength = sc.nextInt();
		if(passLength > 0 && passLength <= characterList.length) {
		
		makePassword(passLength);
		
		}
	}
	
	
	public String makePassword(int length) throws FileNotFoundException{
		
		ArrayList<String> newCharacterList = randomizedCharacterList();
		
//		System.out.println(" randomized list " + newCharacterList);
		
		String retString = "";
		
		for(int i = length-1; i >= 0  ; i--) {
			retString += newCharacterList.get(i);
		}
		System.out.println("Your Password " + retString);
		
		savePassword(retString);
		
		return retString;
	}
	
	
	public void savePassword(String password) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);
				
		System.out.println("\nWould you like to save the password? \n\nType \"Yes\" or \"No\" \n");
		if(sc.hasNextLine()) {
			String yesOrNo = sc.nextLine();
			
			if(yesOrNo.equals("Yes") || yesOrNo.equals("yes")) {
				savePassword(password, true);
			} else {
				System.out.println("\nOkay, password not saved.");
			}
		} else {
			System.out.println("\nPassword not saved!");
		}
		
		sc.close();
		
	}
	
	public void savePassword(String password, boolean toSave) throws FileNotFoundException {
		
		Scanner sc = new Scanner(System.in);

		
		System.out.println("\nEnter password title (for locating purposes)\n");

		if(sc.hasNextLine()) {
			String passwordTitle = sc.nextLine();
			String passwordCharacters = password;
			
			savedPasswords.add(new SavedPassword(passwordTitle, passwordCharacters));
			savePasswordsPermanently();
			
			System.out.println("\nPassword saved!");
		} else {
			System.out.println("\nPassword not saved!");
		}
		
		if(toMakeAnotherPassword() == true) {
			askPasswordLength();
			}
		}
		
	
	
	public static boolean toMakeAnotherPassword() {
		Boolean retVal = false;
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\nWould you like to make another password \n\nType \"Yes\" or \"No\" \n");
		
		if(sc.hasNextLine()) {
			String yesOrNo = sc.nextLine();
			
			if(yesOrNo.equals("Yes") || yesOrNo.equals("yes")) {
				retVal = true;
				
			}
		}
		
		return retVal;
	}
	
	public void showSavedPasswords() {
		for(int i = 0; i < savedPasswords.size(); i++) {
			
			System.out.println(savedPasswords.get(i).getPasswordandTitle());
		}
	}
	
	public void savePasswordsPermanently() throws FileNotFoundException {
		
		PrintWriter outputStream = new PrintWriter(new FileOutputStream(new File("AllPasswords.txt"),true));
		
		//take the last saved password
		SavedPassword lastPassword = savedPasswords.get(savedPasswords.size()-1);
		
		outputStream.append(lastPassword.getPasswordandTitle() + "\n\n");
		outputStream.flush(); // you could also do outputStream.close() but then you wouldn't be able to keep writing
	
	}
	
	
	

	public static void main(String[] args) throws FileNotFoundException {
		PasswordGenerator myPasswordGenerator = new PasswordGenerator("AllKeyboardCharacters.txt");
		myPasswordGenerator.askPasswordLength();
		


	}


}
