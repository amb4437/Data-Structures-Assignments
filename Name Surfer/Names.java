import java.io.File;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFileChooser;


public class Names {

	
	private ArrayList<NameRecord> namelist;
	int linenum= 0;
	public static int year = 0;

	public Names (Scanner fileScanner){
		
		
		String line;		    
		int decades = 0;
		namelist = new ArrayList<NameRecord>();
		while(fileScanner.hasNextLine() ){
		    line = fileScanner.nextLine();
		    linenum++;
		    NameRecord record = new NameRecord(line);
			if (linenum == 1){
			    year = Integer.parseInt(record.getName());
			    namelist.add(record);
			}
			if (linenum == 2){		 
		        decades = Integer.parseInt(record.getName());
			}
		    	
		    String[] lengthtest = (record.allRanks().split(" "));
	    	if (lengthtest.length == decades){  //Need to change the 11 to a variable
	    		namelist.add(record);
	    	}

		    /* create a NameRecord object based on line and add it to the ArrayList of NameRecord objects */
		}
		
	}
	
	public NameRecord findName(String searchname){
		
		searchname = searchname.toLowerCase();
		NameRecord namereturn = null;
		boolean newterm = false;
		for (int i = 0; i < namelist.size(); i++){
			newterm = (namelist.get(i).getName().toLowerCase().matches(searchname));
			if (newterm == true)
				namereturn = namelist.get(i);
				
		}
		return namereturn;
	}
	
	public ArrayList<NameRecord> getMatches(String partialName){ // NEEDS TESTING
		ArrayList<NameRecord> partial = new ArrayList<NameRecord>();
		String newname = partialName.toLowerCase();
		for (int i = 0; i < namelist.size(); i++){
			 if ((namelist.get(i).getName().toLowerCase().contains(newname))){
				 partial.add(namelist.get(i));
			 }
		}
		if (!partial.isEmpty())
			return partial;
		else
			return null;
	}
	
	public int getYear(){
		return year;
	}
	
	public ArrayList<String> rankedEveryDecade(){ //might have to change it so i starts at 2 so it doesnt print out the year and num decades
		ArrayList<String> everydecade = new ArrayList<String>();
		for (int i = 2; i < namelist.size(); i++){
				 if (namelist.get(i).everyDecade()){
				   everydecade.add(namelist.get(i).getName());
				 }
		}
		return everydecade;
	}
	
	public ArrayList<String> rankedOnlyOneDecade(){ //might have to change it so i starts at 2 so it doesnt print out the year and num decades
		ArrayList<String> onlyonedecade = new ArrayList<String>();
		for (int i = 2; i < namelist.size(); i++){
				 if (namelist.get(i).oneDecade()){
				   onlyonedecade.add(namelist.get(i).getName());
				 }
		}
		return onlyonedecade;
	}
	
	public ArrayList<String> alwaysMorePopular(){ //might have to change it so i starts at 2 so it doesnt print out the year and num decades
		ArrayList<String> morepopular = new ArrayList<String>();
		for (int i = 2; i < namelist.size(); i++){
				 if (namelist.get(i).morePopular()){
				   morepopular.add(namelist.get(i).getName());
				 }
		}
		return morepopular;
	}
	
	public ArrayList<String> alwaysLessPopular(){ //might have to change it so i starts at 2 so it doesnt print out the year and num decades
		ArrayList<String> lesspopular = new ArrayList<String>();
		for (int i = 2; i < namelist.size(); i++){
				 if (namelist.get(i).lessPopular()){
				   lesspopular.add(namelist.get(i).getName());
				 }
		}
		return lesspopular;
	}
	
}
