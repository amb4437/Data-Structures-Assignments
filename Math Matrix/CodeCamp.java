package CodeCamp;

//CodeCamp.java - CS314 Assignment 1

/*  Student information for assignment:
* 
* replace <NAME> with your name.
*
*  On my honor, Andrew Baldwin, this programming assignment is my own work
*  and I have not provided this code to any other student.
*
*  Name:Andrew Baldwin
*  email address: amb4437@gmail.com
*  UTEID: amb4437	
*  Section 5 digit ID: 90804
*  Grader name:
*  Number of slip days used on this assignment: 2
*/

import java.util.Random;

public class CodeCamp {

/**
 * Determine the Hamming distance between two arrays of ints. 
 * Neither the parameter <tt>aList</tt> or
 * <tt>bList</tt> are altered as a result of this method.<br>
 * @param aList != null, aList.length == bList.length
 * @param bList != null, bList.length == aList.length
 * @return the Hamming Distance between the two arrays of ints.
 */    
public static int hammingDistance(int[] aList, int[] bList){
    // check preconditions
    if (aList == null || bList == null || aList.length != bList.length) 
        throw new IllegalArgumentException("Violation of precondition: " +
        		"hammingDistance. neither parameter may equal null, arrays" +
        		" must be equal length.");
    
    /*CS314 STUDENTS: INSERT YOUR CODE HERE*/

    int size = aList.length;
    int hammingsize = 0;
    for (int i=0; i<size; i++)
    {
     int k = aList[i];
     int j = bList[i];
      if (k !=j)
    	  hammingsize++;
    }   	
    
    return hammingsize; //must change
}


/**
 * Determine if one array of ints is a permutation of another.
 * Neither the parameter <tt>listA</tt> or 
 * the parameter <tt>listB</tt> are altered as a result of this method.<br>
 * @param listA != null
 * @param listB != null
 * @return <tt>true</tt> if listB is a permutation of listA, 
 * <tt>false</tt> otherwise
 * 
*/
public static boolean isPermutation(int[] listA, int[] listB) {
    // check preconditions
    if (listA == null || listB == null) 
        throw new IllegalArgumentException("Violation of precondition: " +
        		"isPermutation. neither parameter may equal null.");
    /*CS314 STUDENTS: INSERT YOUR CODE HERE*/
    
    int size = listA.length;
    int countA= 0;
    int countB = 0;
    boolean statement = true;
    for (int i=0; i<size; i++)
    {
     int newelement = listA[i];
     for (int j=0; j<size; j++)
     {
    	 if (listA[j] == newelement)
    			 countA++;
    	 
    		 if (listB[j] == newelement)
    			 countB++;
    	 
    		
    	 if (countA != countB)
    		 statement = false;
    	 else
    		 statement = true;
    		 
     }
     }
    
    if (listB == null || listA == null || listA.length != listB.length)
    	statement = false;
    return statement; //must change
}


/**
 * Determine the index of the String that 
 * has the largest number of vowels. 
 * Vowels are defined as <tt>'A', 'a', 'E', 'e', 'I', 'i', 'O', 'o', 
 * 'U', and 'u'</tt>.
 * The parameter <tt>list</tt> is not altered as a result of this method.
 * <p>pre: <tt>list != null</tt>, <tt>list.length > 0</tt>, there is an least 1 non null element in list
 * <p>post: return the index of the non-null element in list that has the 
 * largest number of characters that are vowels.
 * If there is a tie return the index closest to zero. 
 * The empty String, "", has zero vowels.
 * It is possible for the maximum number of vowels to be 0.<br>
 * @param list the array to check
 * @return the index of the non-null element in list that has 
 * the largest number of vowels.
 */
public static int mostVowels(String[] list) {
    // check preconditions
    if (list == null || list.length == 0 || !atLeastOneNonNull(list))  
        throw new IllegalArgumentException("Violation of precondition: " +
        		"mostVowels. parameter may not equal null and must contain " +
        		"at least one none null value.");
   

    // CS314 STUDENTS: ADD YOUR CODE HERE
    //Initial Values
    int size = list.length;
    int most = 0;
    int mostvowels = 0;
    int newcount = 0;
    
   // System.out.println(newstring);
   // System.out.println(newstring.charAt(j));
    
    for (int i= 0; i<size; i++)
    {
    	while (list[i] == null){
    		i++;
    		if (i == size)
    			break;
    	}
        if (i<size){
        String newstring = (list[i]);
        newstring = newstring.toLowerCase(); //convert it to lower case
    	int stringsize = newstring.length();       
        newcount = 0;
        
        for (int j=0; j<stringsize;) {
        	char element = newstring.charAt(j);
        	if (element == 'a' || element== 'e' || element== 'i' || element== 'o' || element== 'u'){
        		newcount ++;
        	    j++;
        	}
        	else{
        		j = j + 1;
        	}
        	if (newcount > most){
        		most = newcount;
        	    mostvowels = i;
        	}
        }
        	    
        }
    	
    }
   
    
    if (most == 0)
        for (int i= 0; i<size;)
        {
        	while (list[i] == null){
        		i++;
        		if (i == size)
        			break;
        	}
        	mostvowels = i;
        	break;
        }
    
    return mostvowels; //must change
}



/**
 * Perform an experiment simulating the birthday problem.
 * Pick random birthdays for the given number of people. 
 * Return the number of pairs of people that share the
 * same birthday.<br>
 * @param numPeople The number of people in the experiment.
 * This value must be > 0
 * @param numDaysInYear The number of days in the year for this experiement.
 * This value must be > 0
 * @return The number of pairs of people that share a birthday 
 * after running the simulation.
 */
public static int sharedBirthdays(int numPeople, int numDaysInYear) {
    // check preconditions
    if (numPeople <= 0 || numDaysInYear <= 0)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"sharedBirthdays. both parameters must be greater than 0. " +
                "numPeople: " + numPeople + 
                ", numDaysInYear: " + numDaysInYear);
    
    //CS314 STUDENTS: ADD YOUR CODE HERE
    

    //Code for the 50,000 experiments
    
    int numpairs = 0;
   // for (numPeople = 2; numPeople<101; numPeople++){
    	
    
    
    //create the list with everyone's birthday
    
   
    
    int ExpWithPairs = 0;
    int[] birthdays = new int[numPeople];
    
    //Controls the number of experiments to perform.  Set to 1 by default
    int numexperiments = 1; //Change to desired number of experiments
    for (int timestorun = 0; timestorun<numexperiments; timestorun++){
    	
    for (int i= 0; i<numPeople; i++)
    {
        Random r = new Random();
        int max = numDaysInYear;
        int x = r.nextInt(max);
        birthdays[i]= x;
    }
    
    for (int i= 0; i<numPeople; i++){
    	int currentbirthday = birthdays[i];
    	
    	for (int j= i+1; j<numPeople; j++){
    		if (birthdays[j]==currentbirthday)
    			numpairs++;
    	}
        //Code for 50,000 Experiments
    	
    }
    if (numpairs > 0)
    	ExpWithPairs++;
        //numpairs = 0;
    	
    
    
    //Code to conduct experiments
    //Will execute only if numexperiments is changed from the default of 1  
    int AveragePairs = 0;
	double percentage = 0;
//	if (numexperiments>1)
		
    	//AveragePairs = numpairs/numexperiments;      //Code for 1 mil experiments
    	//System.out.println(AveragePairs);
		
		
		//percentage = (ExpWithPairs * 100)/50000.0;
		//System.out.println("Num people:" + numPeople +  ", Number of experiments with one or more shared birthdays: " + ExpWithPairs + ", percentage:  " + percentage);
 //   }
	
    

   // return numpairs; //must change
    }
	return numpairs;
}

//}


/**
 * Determine if the chess board represented by board is a safe set up.
 * <p>pre: board != null, board.length > 0, board is a square matrix.
 * (In other words all rows in board have board.length columns.),
 * all elements of board == 'q' or '.'. 'q's represent queens, '.'s
 * represent open spaces.<br>
 * <p>post: return true if the configuration of board is safe,
 * that is no queen can attack any other queen on the board.
 * false otherwise.
 * the parameter <tt>board</tt> is not altered as a result of 
 * this method.<br>
 * @param board the chessboard
 * @return true if the configuration of board is safe,
 * that is no queen can attack any other queen on the board.
 * false otherwise.
*/
public static boolean queensAreSafe(char[][] board) {
    char[] validChars = {'q', '.'};
    // check preconditions
    if (board == null || board.length == 0 || !isSquare(board) 
            || !onlyContains(board, validChars))
        throw new IllegalArgumentException("Violation of precondition: " +
        		"queensAreSafe. The board may not be null, must be square, " +
        		"and may only contain 'q's and '.'s");        
            
  //CS314 STUDENTS: ADD YOUR CODE HERE
    
    boolean issafe= true;
    int squaresize = board.length;
    for (int i= 0; i<squaresize; i++){     //Rows
    	for (int j=0; j<squaresize; j++){  //Column
    		char Queencheck = board[i][j];
    		if (Queencheck == 'q'){

    			for (int newcol = (j + 1); newcol<squaresize; newcol++){  //Keep the row.  increment columns
    				if (board[i][newcol] == 'q'){
    					issafe=false;
    				}
    			}

    			for (int newrow =(i + 1); newrow <squaresize; newrow++){  //keep the column, increment rows
    				if (board[newrow][j] == 'q'){
    					issafe=false;
    				}
    			}

    			for (int toadd = 1; toadd+i<squaresize && toadd+j<squaresize; toadd++){
    				if (board[i + toadd][j + toadd] == 'q'){
    					issafe=false;
    				}
    			}

    			for (int toadd = 1; (i-toadd)>-1 && (j+toadd)<squaresize; toadd++){
    				if (board[i - toadd][j + toadd] == 'q'){
    					issafe=false;
    				}
    			}
    		}

    	}

    }
    return issafe; //must change
}


/**
 * Given a 2D array of ints return the value of the
 * most valuable contiguous sub rectangle in the 2D array.
 * The sub rectangle must be at least 1 by 1. 
 * <p>pre: <tt>mat != null, mat.length > 0, mat[0].length > 0,
 * mat</tt> is a rectangular matrix.
 * <p>post: return the value of the most valuable contigous sub rectangle
 * in <tt>city</tt>.<br>
 * @param city The 2D array of ints representing the value of
 * each block in a portion of a city.
 * @return return the value of the most valuable contigous sub rectangle
 * in <tt>city</tt>.
 */
public static int getValueOfMostValuablePlot(int[][] city) {
    // check preconditions
    if (city == null || city.length == 0 || city[0].length == 0 
            || !isRectangular(city) )
        throw new IllegalArgumentException("Violation of precondition: " +
        		"getValueOfMostValuablePlot. The parameter may not be null," +
        		" must value at least one row and at least" +
                " one column, and must be rectangular."); 
    

    //CS314 STUDENTS: ADD YOUR CODE HERE    
    
    
    
    ////////******Incomplete*********//////
    int maximum = -99999;
    int currentsum= 0;
    int row = 0;
    int runningsum = 0;
    for (int start=0; start<(city.length); start++){     // Row by row of the list
    	for (int currentelement =0; currentelement<(city[0].length); currentelement++){
    		for (int previouselement = 1; previouselement<(city[0].length); previouselement++){
    			runningsum = 0;
    			for (int newelement=currentelement; newelement<(previouselement); newelement++){ //Go through each element 1 by 1
    				runningsum = runningsum + city[start][newelement];
    				  currentsum = 0;
    				for (row= (start+1); row<(city.length); row++){  //Increment the rows
    					for (int newelement2 = currentelement; newelement2<(newelement+1); newelement2++){						
    						currentsum = (currentsum + city[row][newelement2]);
    						if (currentsum + runningsum > maximum){
    							maximum = (currentsum + runningsum);
    						}											
    						if (runningsum > maximum){		
    							maximum = runningsum;		
    						}		 
    					}
    				}
    			}
    		}
    	}
    }

    return maximum; //must change
}


// !!!!! ***** !!!!! ***** !!!!! ****** !!!!! ****** !!!!! ****** !!!!!!
// CS314 STUDENTS: Put your birthday problem experiment code here:

public static int sharedBirthdaysExperiment(int numPeople, int numDaysInYear) {
    // check preconditions
    if (numPeople <= 0 || numDaysInYear <= 0)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"sharedBirthdays. both parameters must be greater than 0. " +
                "numPeople: " + numPeople + 
                ", numDaysInYear: " + numDaysInYear);
    
    //CS314 STUDENTS: ADD YOUR CODE HERE
    

    //Code for the 50,000 experiments
    
    int numpairs = 0;
   // for (numPeople = 2; numPeople<101; numPeople++){
    	
    
    
    //create the list with everyone's birthday
    
   
    
    int ExpWithPairs = 0;
    int[] birthdays = new int[numPeople];
    
    //Controls the number of experiments to perform.  Set to 1 by default
    int numexperiments = 1; //Change to desired number of experiments
    for (int timestorun = 0; timestorun<numexperiments; timestorun++){
    	
    for (int i= 0; i<numPeople; i++)
    {
        Random r = new Random();
        int max = numDaysInYear;
        int x = r.nextInt(max);
        birthdays[i]= x;
    }
    
    for (int i= 0; i<numPeople; i++){
    	int currentbirthday = birthdays[i];
    	
    	for (int j= i+1; j<numPeople; j++){
    		if (birthdays[j]==currentbirthday)
    			numpairs++;
    	}
        //Code for 50,000 Experiments
    	
    }
    if (numpairs > 0)
    	ExpWithPairs++;
        //numpairs = 0;
    	
    
    
    //Code to conduct experiments
    //Will execute only if numexperiments is changed from the default of 1  
    int AveragePairs = 0;
	double percentage = 0;
//	if (numexperiments>1)
		
    	//AveragePairs = numpairs/numexperiments;      //Code for 1 mil experiments
    	//System.out.println(AveragePairs);
		
		
		//percentage = (ExpWithPairs * 100)/50000.0;
		//System.out.println("Num people:" + numPeople +  ", Number of experiments with one or more shared birthdays: " + ExpWithPairs + ", percentage:  " + percentage);
 //   }
	
    

   // return numpairs; //must change
    }
	return numpairs;
}

//}




// pre: list != null
// post: return true if at least one element in list is null
// otherwise return false.
private static boolean atLeastOneNonNull(String[] list) {
    // check precondition
    if(list == null)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"atLeastOneNonNull. parameter may not equal null.");
    
    boolean foundNonNull = false;
    int i = 0;
    while( !foundNonNull && i < list.length ){
        foundNonNull = list[i] != null;
        i++;
    }
    return foundNonNull;
}


/* pre: mat != null
post: return true if mat is a square matrix, false otherwise
 */
private static boolean isSquare(char[][] mat) {
    if(mat == null)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"isSquare. paremeter may not be null.");

    final int numRows = mat.length;
    int row = 0;
    boolean isSquare = true;
    while( isSquare && row < numRows ) {
        isSquare = ( mat[row] != null) && (mat[row].length == numRows);
        row++;
    }
    return isSquare;
}


/* pre: mat != null, valid != null
post: return true if all elements in mat are one of the characters in valid
 */
private static boolean onlyContains(char[][] mat, char[] valid) {
    // check preconditions
    if(mat == null || valid == null)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"onlyContains. paremeters may not be null.");
    
    int row = 0;
    boolean correct = true;
    while( correct && row < mat.length) {
        int col = 0;
        while(correct && col < mat[row].length) {
            correct = contains(valid, mat[row][col]);
            col++;
        }
        row++;
    }
    return correct;
}


/*  pre: list != null
    post: return true if c is in list
 */
private static boolean contains(char[] list, char tgtChar) {
    // check preconditions
    if(list == null)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"contains. paremeter may not be null.");

    boolean found = false;
    int index = 0;
    while( !found && index < list.length) {
        found = list[index] == tgtChar;
        index++;
    }
    return found;
}


 // pre: mat != null, mat.length > 0
 // post: return true if mat is rectangular
private static boolean isRectangular(int[][] mat) {
    // check preconditions
    if(mat == null || mat.length == 0)
        throw new IllegalArgumentException("Violation of precondition: " +
        		"isRectangular. paremeter may not be null and must contain" +
        		" at least one row.");

    boolean correct = mat[0] != null;
    int row = 0;
    while(correct && row < mat.length) {
        correct = (mat[row] != null) && (mat[row].length == mat[0].length);
        row++;
    }
    return correct;
}



// make constructor pirvate so no instances of CodeCamp can be created
private CodeCamp() {
    
}
}