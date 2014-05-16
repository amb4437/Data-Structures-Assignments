import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.ArrayList;

public class NameSurfer {

	// constants for menu choices
	public static final int SEARCH = 1;
	public static final int ONE_NAME = 2;
	public static final int APPEAR_ONCE = 3;
	public static final int APPEAR_ALWAYS = 4;
	public static final int MORE_POPULAR = 5;
	public static final int LESS_POPULAR = 6;
	public static final int BIGGEST_INCREASE = 7;
	
	public static final int QUIT = 8;
	
	// CS314 students, explain your menu option 7 here:
	
	//Option 7 will display the biggest increase in popularity in the history of the name.  For example,
	//if the name Andrew has moved up 100 spots on the top 1000 list between 1980 and 1990, and 100
	//is the highest it's ever risen in a single decade, option 7 will return 100.
	
	
	// CS314 students, Explain your interesting search / trend here:
	
	
/*	I was curious to see what kind of effects popular movies had on the popularity of names associated
	with the film.
	
	Titanic (1994):
	Jack: 1990: 113  2000: 47 (significant increase)
	Rose: 1990: 309  2000: 294 (slight increase)
	Celine: 1990: 757  2000: 665 (drastic increase)
	
	
    Forrest Gump (1994):
    Forrest: 1990: 355  2000: 710 (drastic decrease.. Probably because the character in the movie was mildly retarded)
    
    
    Raiders Of The Lost Ark(1981)
    Harrison: 1980: 436  1990: 231 (drastic increase)

    
    The Godfather (1972)
    Vito: 1970: 775  1980: Unranked.  (Maybe people didn't want to name their kid after a dead mob boss)
	
	I spent time searching other names and found that there was not a strong correlation between the names of
	characters in popular movies and that name's populartiy.  However, in popular movies that are very character
	orriented, like Titanic and Forrest Gump, which both contain memorable character names, the movie seems
	to have a strong influence on the popularity of the name in the following decade.
	
*/
	
	
	
	// CS314 students, add test code for NameRecord class here:
	
	//I used the following method and tests and ran it directly from the NameRecord classx.
	
//	public static void main (String[] args){
//	NameRecord test3 = new NameRecord ("Sean 0 0 160 0 0 0 0 0 0 0 0");
//	NameRecord test4 = new NameRecord ("Andrew 300 200 160 150 50 40 30 29 20 10 5");
//	NameRecord test1 = new NameRecord ("Aaron 0 192 191 190 180 170 169 36 32 31 30");
//    NameRecord test2 = new NameRecord ("Abbey 190 191 192 193 200 201 210 300 301 302 400");
//    
//    System.out.println(test1.getName());
//    System.out.println(test2.getName());
//    
//    System.out.println(test2.getRank(1));
//    System.out.println(test1.getRank(4));
//    
//    System.out.println(test1.allRanks());
//    System.out.println(test2.allRanks());
//    
//    System.out.println(test1.bestDecade());
//    System.out.println(test2.bestDecade());
//    
//    System.out.println(test1.oneDecade());
//    System.out.println(test3.oneDecade());
//    
//    System.out.println(test1.morePopular());
//    System.out.println(test2.morePopular());
//    
//    System.out.println(test4.biggestJump());	    
//    System.out.println(test1.biggestJump());
//    
//    System.out.println(test2.lessPopular());
//    System.out.println(test1.lessPopular());
//    
//}
	
	// main method. Driver for the whole program
	public static void main(String[] args) {
		try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch(Exception e) {
		    System.out.println("Unable to set look at feel to local settings. " +
		    		"Continuing with default Java look and feel.");
		}
	    try {
		    System.out.println("Opening GUI to choose file with names data...");
	        // next line for GUI
		    Scanner fileScanner = new Scanner(getFile());

		    // next line to skip GUI
		  //  Scanner fileScanner = new Scanner("names.txt");

		    Names n = new Names(fileScanner);
		    fileScanner.close();
		    int choice;
		    Scanner keyboard = new Scanner(System.in);
		    do {
				showMenu();
				choice = getChoice(keyboard);
				if( choice == SEARCH)
					search(n, keyboard);
				else if( choice == ONE_NAME )
					oneName(n, keyboard);
				else if( choice == APPEAR_ONCE )
					appearOnce(n);
				else if( choice == APPEAR_ALWAYS )
					appearAlways(n);
				else if (choice == MORE_POPULAR)
					morePopular(n);
				else if (choice == LESS_POPULAR)
					lessPopular(n);
				else if (choice == BIGGEST_INCREASE)
					biggestIncrease(n, keyboard);
				else
					System.out.println("\n\nGoodbye.");

			} while( choice != QUIT);
		}
		catch(FileNotFoundException e) {
			System.out.println("Problem reading the data file. Exiting the program." + e);
		}
	}

	// method that shows names that have appeared in ever decade
	// pre: n != null
	// post: print out names that have appeared in ever decade
	private static void appearAlways(Names n) {
		
		if(n == null) 
			throw new IllegalArgumentException("Violation of precondition: N = Null");

		ArrayList<String> always = n.rankedEveryDecade();
		if (!always.isEmpty()){
			System.out.println("\n" + "The following names appeared in every decade:" + "\n");
			for (int i = 0; i <always.size(); i++){
				System.out.println(always.get(i));
		}
		}
		else
			System.out.println("There are no names that appear in every decade");
	}

	// method that shows names that have appeared in only one decade
	// pre: n != null
	// post: print out names that have appeared in only one decade
	private static void appearOnce(Names n) {
		
		if(n == null) 
			throw new IllegalArgumentException("Violation of precondition: N = Null");

		ArrayList<String> once = n.rankedOnlyOneDecade();
		if (!once.isEmpty()){
			System.out.println("\n" + "The following names appeared in only one decade:" + "\n");
			for (int i = 0; i <once.size(); i++){
				System.out.println(once.get(i));
		}
		}
		else
			System.out.println("There are no names that appear in only one decade");
		
	}
	
	private static void morePopular (Names n){
		ArrayList<String> popular = n.alwaysMorePopular();
		if (!popular.isEmpty()){
			System.out.println("\n" + "The following names get more popular in every decade:" + "\n");
			for (int i = 0; i <popular.size(); i++){
				System.out.println(popular.get(i));
			}
			
		}
		else
			System.out.println("\n" + "There aren't any names in the database that get more popular every decade" + "\n");
	}
	
	private static void lessPopular (Names n){
		ArrayList<String> popularity = n.alwaysLessPopular();
		if (!popularity.isEmpty()){
			System.out.println("\n" + "The following names get less popular in every decade:" + "\n");
			for (int i = 0; i <popularity.size(); i++){
				System.out.println(popularity.get(i));
		}
	}
		else
			System.out.println("\n" + "There aren't any names in the database that get less popular every decade" + "\n");
	}
	
	private static void biggestIncrease (Names n, Scanner keyboard){
		
		System.out.println("Please enter a name to see it's biggest increase in popularity");
		String searchname = keyboard.next().toLowerCase();
		ArrayList<NameRecord> newname = n.getMatches(searchname);
		
		if (newname != null){
			System.out.println("The following names contain that substring");
			for (int i = 0; i < newname.size(); i ++){
				System.out.println(newname.get(i).getName() + "'s biggest increase in popularity was when it rose " + (newname.get(i).biggestJump()) + " spots on the list");
			}
		}
		else
			System.out.println("Sorry, that name was not found in the database");
	}

	// method that shows data for one name, or states that name has never been ranked
	// pre: n != null, keyboard != null and is connected to System.in
	// post: print out the data for n or a message that n has never been in the top 1000 for any decade
	private static void oneName(Names n, Scanner keyboard) {
		
		if(n == null || keyboard == null) 
			throw new IllegalArgumentException("Violation of precondition");

		System.out.println("Please enter a name");
		String searchname = keyboard.next().toLowerCase();
		NameRecord newname = n.findName(searchname);
		
		if (newname!= null){
			System.out.println(newname.allRanks());
			}
		else
			System.out.println("The name " + searchname + " has never been in the top 1000 for any decade");
		}

		

	// method that shows all names that contain a substring from the user
	// and the decade they were most popular in
	// pre: n != null, keyboard != null and is connected to System.in
	// post: show the correct data		
	private static void search(Names n, Scanner keyboard) {
		
		if(n == null || keyboard == null) 
			throw new IllegalArgumentException("Violation of precondition");
		
		System.out.println("Please enter a substring");
		String searchname = keyboard.next().toLowerCase();
		ArrayList<NameRecord> newname = n.getMatches(searchname);
		
		if (newname != null){
			System.out.println("The following names contain that substring");
			for (int i = 0; i < newname.size(); i ++){
				System.out.println(newname.get(i).getName() + " : most popular in " + (n.getYear() + (10 * newname.get(i).bestDecade())));
			}
		}
		else
			System.out.println("No names in the database contain that substring");

	}

	// get choice from the user
	// keyboard != null and is connected to System.in
	// return an int that is >= SEARCH and <= QUIT
	private static int getChoice(Scanner keyboard) {
		int choice = getInt(keyboard, "Enter choice: ");
		keyboard.nextLine();
		while( choice < SEARCH || choice > QUIT){
			System.out.println("\n" + choice + " is not a valid choice");
			choice = getInt(keyboard, "Enter choice: ");
			keyboard.nextLine();
		}
		return choice;
	}
	
	// ensure an int is entered from the keyboard
	// pre: s != null and is connected to System.in
    private static int getInt(Scanner s, String prompt) {
        System.out.print(prompt);
        while( !s.hasNextInt() ){
            s.next();
            System.out.println("That was not an int.");
            System.out.print(prompt);
        }
        return s.nextInt();
    }

    // show the user the menu
	private static void showMenu() {
		System.out.println("\nOptions:");
		System.out.println("Enter " + SEARCH + " to search for names.");
		System.out.println("Enter " + ONE_NAME + " to display data for one name.");
		System.out.println("Enter " + APPEAR_ONCE+ " to display all names that appear in only one decade.");
		System.out.println("Enter " + APPEAR_ALWAYS + " to display all names that appear in all decades.");
		System.out.println("Enter " + MORE_POPULAR + " to display all names that are more popular in every decade.");
		System.out.println("Enter " + LESS_POPULAR + " to display all names that are less popular in every decade.");
		System.out.println("Enter " + BIGGEST_INCREASE + " to display a name's biggest increase in popularity");
		// CS314 students fill in other options
		
		System.out.println("Enter " + QUIT + " to quit.\n");
	}

	/** Method to choose a file using a traditional window.
     * @return the file chosen by the user. Returns null if no file picked.
     */ 
    public static File getFile() {
        // create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        chooser.setDialogTitle("Select File With Baby Names Data.");
        int retval = chooser.showOpenDialog(null);
        File f =null;
        chooser.grabFocus();
        if (retval == JFileChooser.APPROVE_OPTION)
           f = chooser.getSelectedFile();
        return f;
    }
    
    

}
