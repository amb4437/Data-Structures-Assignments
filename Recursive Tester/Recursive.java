//imports

import java.util.ArrayList;
import java.util.Arrays;
import java.awt.Graphics;
import java.awt.Color;

public class Recursive {

    /**
     * Problem 1: convert a base 10 int to binary recursively.<br> 
     *   pre: n >= 0<br>
     *   post: Returns a String that represents N in binary.
     *   All chars in returned String are '1's or '0's. Most significant digit is at position 0
     *   @param n the base 10 int to covnert to base 2
     */
    public String getBinary(int n) {
        if( n < 0) 
            throw new IllegalArgumentException("Failed precondition: getBinary. n must be >= 0. n: " + n);
   
	int remain;
	remain = n % 2;

	if(n / 2 == 0 && remain == 0)	/* Check for single values */
		return "0";

	if(n / 2 == 0 && remain == 1)
		return "1";

	else if(remain == 0)
		return getBinary(n / 2) + "0";
	
	else if(remain == 1)
		return getBinary(n / 2) + "1";

	else
		return "";
	}


    /**
     * Problem 2: reverse a String recursively.<br>
     *   pre: stringToRev != null<br>
     *   post: returns a String that is the reverse of stringToRev
     *   @param stringToRev the String to reverse.
     */
    public String revString(String stringToRev) {
        if( stringToRev == null)
            throw new IllegalArgumentException("Failed precondition: revString. parameter may not be null.");

	if(stringToRev.length() == 0)
		return "";
	else
		return String.valueOf(stringToRev.charAt(stringToRev.length()-1)) + revString(stringToRev.substring(0, stringToRev.length()-1));

    }


    /**
     * Problem 3: Returns the number of elements in data 
     * that are followed directly by value that is
     * double that element. 
     * pre: data != null
     * post: return the number of elements in data that are followed immidiately by double the value
     */
    public int nextIsDouble(int[] data) {

	return nextIsDoubleHelper(data, 0);
    }

    // CS314 students, add your nextIsDouble helper method here

	private int nextIsDoubleHelper(int[] array, int index)
	{
		if(index == array.length-1)
			return 0;	/* End recursion */

		if(array[index] * 2 == array[index+1])	/* Match found */
		{
			return 1 + nextIsDoubleHelper(array, index+1);
		}
		else
			return 0 + nextIsDoubleHelper(array, index+1);
	}




    /**  Problem 4: Find all combinations of mnemonics for the given number.<br>
     *   pre: number != null, all characters in number are digits<br>
     *   post: see tips section of assigment handout
     *   @param number The number to find mnemoics for
     */
    public ArrayList<String> listMnemonics(String number) {
        if( number == null ||  !allDigits(number))
            throw new IllegalArgumentException("Failed precondition: listMnemonics");

        ArrayList<String> result = new ArrayList<String>();
        recursiveMnemonics(result, "", number);
        return result;
    }


    /*
     * Helper method for listMnemonics
     * mnemonics stores completed mnemonics
     * mneominicSoFar is a paritl (possibly complete) mnemonic
     * digitsLeft are the digits that have not been used from the original number
     */
    private void recursiveMnemonics(ArrayList<String> mnemonics, 
            String mnemonicSoFar, String digitsLeft) {

	String letterChoices;
	String digitsEdited;
	String partialMnemonic;

	/* Add completely mnemonic to arraylist */
	if(digitsLeft.length() == 0)	/* No more digits left */
	{
		mnemonics.add(mnemonicSoFar);
		
		return;			/* End recursion */
	}

	/* Branch off for each possible letter in current digit */
	letterChoices = digitLetters(digitsLeft.charAt(0));
	digitsEdited = stripDigit(digitsLeft);	/* Strip the first character */

	for(int count = 0; count < letterChoices.length(); count++)
	{
		partialMnemonic = mnemonicSoFar + String.valueOf(letterChoices.charAt(count));

		recursiveMnemonics(mnemonics, partialMnemonic, digitsEdited);
	}
    }

	/* This simple helper function strips the first character
	 * of a string.
	 */
	private String stripDigit(String input)
	{
		String ret = "";

		if(input.length() <= 0)
			throw new IllegalArgumentException("requires a non-empty string");

		for(int count = 1; count < input.length(); count++)
			ret += String.valueOf(input.charAt(count));

		return ret;
	}
    
    // used by method digitLetters
    private static final String[] letters = {"0", "1", "ABC", "DEF", "GHI", "JKL", "MNO", "PQRS", "TUV", "WXYZ"};


    /* helper method for recursiveMnemonics
     * pre: ch is a digit '0' through '9'
     * post: return the characters associated with this digit on a phone keypad
     */
    private String digitLetters(char ch) {
        assert ('0' <= ch) && (ch <= '9') : "Failed precondition: digitLetters";
        int index = (int)(ch - '0');
        return letters[index];
    }


    /* helper method for listMnemonics
     * pre: s != null
     * post: reutrn true if every character in s is a digit ('0' through '9')
     * */
    private boolean allDigits(String s) {
        assert s != null : "Failed precondition: allDigits";
        boolean allDigits = true;
        int i = 0;
        while(i < s.length() && allDigits){
            allDigits = s.charAt(i) >= '0' && s.charAt(i) <= '9';
            i++;
        }
        return allDigits;
    }


    /**
     * Problem 5: Create a DrawingPanel and place Sierpinski triangles in it. 
     * The lower left corner shall be 20 pixels 
     * from the left edge of the window
     * and 20 pixels from the bottom of the window.
     * @param windowSize > 20
     * @param minSideLength > 4
     * @param startingSideLength > minSideLength
     */
    public void drawTriangles(int windowSize, 
            int minSideLength, int startingSideLength){

        DrawingPanel p = new DrawingPanel(windowSize, windowSize);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLUE);
        drawTriangles(minSideLength, startingSideLength, 20, windowSize - 20, g);
    }

    // helper method for drawTriangles
    private void drawTriangles(int minSideLength, double currentSideLength, 
            double x1, double y1, Graphics g)
	{
		if(currentSideLength <= minSideLength)
		{	/* Base Case */

			int leftx, lefty, rightx, righty, topx, topy;

			leftx = (int)x1;
			lefty = (int)y1;

			rightx = (int)(x1 + currentSideLength);
			righty = (int)y1;

			topx = (int)(x1 + (int)currentSideLength * 0.5);
			topy = (int)(y1 - Math.sqrt(3) * currentSideLength / 2);

			g.drawLine(leftx, lefty, rightx, righty);	/* Bottom */
			g.drawLine(leftx, lefty, topx, topy);		/* Left */
			g.drawLine(rightx, righty, topx, topy);		/* Right */

		}

		else
		{	/* Recursive Case */

			double x2, y2, x3, y3;

			x2 = x1 + currentSideLength * 0.25;
			y2 = y1 - Math.sqrt(3) * currentSideLength / 4;
			x3 = x1 + currentSideLength * 0.5;
			y3 = y1;

			/* Draw three smaller triangles */
			drawTriangles(minSideLength, currentSideLength / 2, x1, y1, g);
			drawTriangles(minSideLength, currentSideLength / 2, x2, y2, g);
			drawTriangles(minSideLength, currentSideLength / 2, x3, y3, g);
		}

	}


    /**
     * Problem 6: Draw a Sierpinski Carpet.
     * @param size the size in pixels of the window
     * @param limit the smallest size of a sqauer in the carpet.
     */
    public void drawCarpet(int size, int limit) {
        DrawingPanel p = new DrawingPanel(size, size);
        Graphics g = p.getGraphics();
        g.setColor(Color.BLACK);
        g.fillRect(0,0,size,size);
        g.setColor(Color.WHITE);
        drawSquares(g, size, limit, 0, 0);
    }


    /* Helper method for drawCarpet
     * Draw the individual squares of the carpet.
     * @param g The Graphics object to use to fill rectangles
     * @param size the size of the current square
     * @param limit the smallest allowable size of squares
     * @param x the x coordinate of the upper left corner of the current square
     * @param y the y coordinate of the upper left corner of the current square
     */
    private static void drawSquares(Graphics g, int size, int limit, double x, double y) {

	int section;

	if(size > limit)
	{
		section = size / 3;

		/* Divide current block into 9 squares */

		drawSquares(g, section, limit, x, y);					// One
		drawSquares(g, section, limit, x+section, y);				// Two
		drawSquares(g, section, limit, x+section*2, y);				// Three

		drawSquares(g, section, limit, x, y+section);				// Four
		g.fillRect((int)(x+section), (int)(y+section), section, section);	// Five -- WHITE
		drawSquares(g, section, limit, x+section*2, y+section);			// Six

		drawSquares(g, section, limit, x, y+section*2);				// Seven
		drawSquares(g, section, limit, x+section, y+section*2);			// Eight
		drawSquares(g, section, limit, x+section*2, y+section*2);		// Nine
	}


    }

    // size of Sudoko board
    /**
     * The size of the Sudoko board. Value will be a perfect square greater than 0.
     */
    public static final int BOARD_SIZE = 9; // must be perfect square
    
    /**
     * The size of a mini marix on the Sodoko board.
     */
    public static final int MINI_SIZE = (int)(Math.sqrt(BOARD_SIZE));


    /** 
     * Problem 7: Find a solution to a Sudoko puzzle. 
     * <br>pre: board != null, board is 9 by 9. 
     * <br>post: return a board that is the solved puzzle 
     * or a copy of the original board if there is no solution
     * @param startBoard The starting board. 
     * Empty values = 0, given values = 1 through 9 may not be changed
     */
    public int[][] getSudokoSolution(int[][] startBoard) {
        if(startBoard == null || startBoard.length != BOARD_SIZE || startBoard[0].length != BOARD_SIZE)
            throw new IllegalArgumentException("Violation of precondition in getSudo");

        // store solution in board so we don't change startBoard
        int[][] board = copyBoard(startBoard); 

	board = sudokoSolutionSubroutine(board, 0, 0);

	if(board == null)
		return startBoard;
	else
		return board;
    }


    // CS314 students, add your recursive sudoko solver method here
	private int[][] sudokoSolutionSubroutine(int[][] oldBoard, int vpos, int hpos)
	{
		/* Create board copy */
		int[][] board = copyBoard(oldBoard);
		int[][] ret;


		/* Check we have hit the end of the board */
		if(vpos == board.length-1 && hpos == board[0].length-1)
		{
			/* Run through the numbers */
			for(int count = 1; count <= 9; count++)
			{
				board[vpos][hpos] = count;

				/* Is the board valid? */
				if(digitsOkay(board, vpos, hpos) == true)
					return board;	/* Success */
			}

			return null;	/* Board is unsolvable */
		}


		/* Are we on an empty block? */
		if(board[vpos][hpos] == 0)
		{	/* We are on an empty block */

			/* Try all possible combinations on the current block */
			for(int count = 1; count <= 9; count++)
			{
				board[vpos][hpos] = count;

				/* Is this a valid combination? */
				if(digitsOkay(board, vpos, hpos) == true)
				{
					/* Are we at the end of a row? */
					if(hpos == board[0].length-1)
						ret = sudokoSolutionSubroutine(board, vpos+1, 0);	/* Next row */
					else
						ret = sudokoSolutionSubroutine(board, vpos, hpos+1);	/* Increment */

					/* Check if we found a valid chain */
					if(ret != null)
						return ret;
				}
			}

			/* No combination works, return null */

			return null;

		}

		else
		{	/* We are not on an empty block, skip this block */
			
			/* Are we at the end of a row? */
			if(hpos == board[0].length-1)
				ret = sudokoSolutionSubroutine(board, vpos+1, 0);	/* Next row */
			else
				ret = sudokoSolutionSubroutine(board, vpos, hpos+1);	/* Increment */

			/* Check if we found a valid chain */
			if(ret != null)
				return ret;
		}

		/* We should never actually get here */
		return null;
	}



    // helper method to make a copy of a sudoko.
    // CS314 students, you DO NOT need to call this method.
    private static int[][] copyBoard(int[][] startBoard) {
        int[][] result = new int[startBoard.length][];
        for(int r = 0; r < result.length; r++)
            result[r] = Arrays.copyOf(startBoard[r], startBoard[r].length);
        return result;
    }



    // Helper method check to ensure no digit other than zero
    // is repeated in a given row, column, or mini matrix.
    private static boolean digitsOkay(int[][] board, int row, int col) {
        return portionOkay(row, 0, 0, 1, board) &&
        portionOkay(0, col, 1, 0, board) &&
        miniMatrixOkay(row, col, board);

    }



    // helper method to see if no repeat digits (other than 0) in a row or column
    // CS314 students, you don't need to call this method directly
    private static boolean portionOkay(int rowStart, int colStart, int rowChange, int colChange, int[][] board) {
        // check digits in row or column. 
        // for all non zero digits update array of booleans. if digit appears twice then
        // used[digit] is set to true first time and we return false second time
        boolean[] used = new boolean[BOARD_SIZE + 1]; // don't use zero element in array

        // pretty GACKY, change to while loop?
        for(int i = 0, row = rowStart, col = colStart; i < BOARD_SIZE; i++, row += rowChange, col += colChange) {
            int digit = board[row][col];
            if(digit != 0){
                if(used[digit])
                    return false; // duplicate!!
                used[digit] = true; // mark as used
            }
        }
        return true; // no repeats found!
    }

    // helper to check that no digits other than 0 are
    // repeated in the mini matric cell row, col
    // is a part of.
    // CS314 students, you don't need to call this method directly
    private static boolean miniMatrixOkay(int row, 
            int col, int[][] board) {

        boolean[] used = new boolean[BOARD_SIZE + 1];

        // figure out upper left indices for mini matrix
        // we need to check

        // row 0,1,2 -> 0, row 3, 4, 5 -> 3, row 6, 7, 8 -> 6 
        // same logic for column
        row = (row / MINI_SIZE) * MINI_SIZE;
        col = (col / MINI_SIZE) * MINI_SIZE;

        for(int r = 0; r < 3; r++)
            for(int c = 0; c < 3; c++)  {
                int digit = board[row + r][col + c];
                if(digit != 0){
                    if(used[digit])
                        return false; // duplicate!!
                    used[digit] = true; // mark as used
                }
            }
        return true;
    }    


    /**
     * Problem 8: Determine if water at a given point 
     * on a map can flow off the map.
     * <br>pre: map != null, map.length > 0, 
     * map is a rectangular matrix, 0 <= row < map.length, 0 <= col < map[0].length
     * <br>post: return true if a drop of water starting at the location 
     * specified by row, column can reach the edge of the map, false otherwise.
     * @param map The elevations of a section of a map.
     * @param row The starting row of a drop of water.
     * @param col The starting column of a drop of water.
     */
    public boolean canFlowOffMap(int[][] map, int row, int col) {
        if( map == null || map.length == 0 || !isRectangular(map) || !inbounds(row, col, map))
            throw new IllegalArgumentException("Failed precondition: canFlowOffMap");
        
        /* Base Case */
        /* Check if the current row and column is along the edge */
        
    	if ( row == 0 || row == map.length-1 || col == 0 || col == map[0].length-1){
    		return true;
    	}
    	
    	/* Check if the surrounding rows and columns are less than the current row and column  */
    	if ( row+1 < map.length && map[row+1][col] >= map[row][col] 
    	                        && row-1 >= 0 && map[row-1][col] >= map[row][col] 
    	                        && col+1 < map[0].length && map[row][col+1] >= map[row][col] 
    	                        && col-1 >= 0 && map[row][col-1] >= map[row][col]){
    		return false;
    	}
    	
    	
    	/* Check if the above column is valid */
    	if ( (col-1) >= 0 && map[row][(col-1)] < map[row][col] ){
    		if(canFlowOffMap(map, row, (col-1))){      
    			return true;
    		}
    	}
    	
    	/* Check if the above row is valid */
    	if ( (row-1) >= 0 && map[(row-1)][col] < map[row][col] ){
    		if(canFlowOffMap(map, (row-1), col)){
    			return true;
    		}
    	}
    	
    	/* Check if the row below is valid */
    	if ( (row + 1) < map.length && map[(row + 1)][col] < map[row][col] ){
    		if(canFlowOffMap(map, (row + 1), col)){
    			return true;
    		}
    	}
    	/* Check if the column below is valid */
    	if ( (col+1) < map[0].length && map[row][(col+1)] < map[row][col] ){
    		if(canFlowOffMap(map, row, (col+1))){
    			return true;
    		}
    	}
    	return false;
    }

    /* helper method for canFlowOfMap - CS314 stdents you should not have to 
     * call this method,
     * pre: mat != null, 
     */
    private boolean inbounds(int r, int c, int[][] mat) {
        assert mat != null : "Failed precondition: inbounds";
        return r >= 0 && r < mat.length && mat[r] != null && c >= 0 && c < mat[r].length;
    }

    /* 
     * helper method for canFlowOfMap - CS314 stdents you should not have to 
     * call this method,
     * pre: mat != null, mat.length > 0
     * post: return true if mat is rectangular
     */
    private static boolean isRectangular(int[][] mat) {
        assert (mat != null) && (mat.length > 0) : "Violation of precondition: isRectangular";

        boolean correct = true;
        final int numCols = mat[0].length;
        int row = 0;
        while( correct && row < mat.length) {
            correct = (mat[row] != null) && (mat[row].length == numCols);
            row++;
        }
        return correct;
    }


    /**
     * Problem 9: Find the minimum difference possible between teams
     * based on ability scores. The number of teams may be greater than 2.
     * The goal is to minimize the difference between the team with the 
     * maximum total ability and the team with the minimum total ability.
     * <br> pre: numTeams >= 2, abilities != null, abilities.length >= numTeams
     * <br> post: return the minimum possible difference between the team
     * with the maximum total ability and the team with the minimum total
     * ability. 
     * @param numTeams the number of teams to form. 
     * Every team must have at least one member
     * @param abilities the ability scores of the people to distribute
     * The return value will be greater than or equal to 0.
     */
    public int minDifference(int numTeams, int[] abilities) {
	/* Create pool */
	ArrayList<Integer> pool = new ArrayList<Integer>();

	for(int count = 0; count < abilities.length; count++)
		pool.add(abilities[count]);

	/* Create teams */
	ArrayList<Integer> teams = new ArrayList<Integer>();

	for(int count = 0; count < numTeams; count++)
		teams.add(0);

        return fairTeamsRecursive(pool, teams, 1);
    }

	/* This subroutine performs a simple recursive function.
	 */
	private int fairTeamsRecursive(ArrayList<Integer> oldPool, ArrayList<Integer> oldTeams, int chain)
	{

		/* Base Case */
		if(oldPool.size() == 0)				/* The pool is empty */
		{
			if(validTeams(oldTeams) == true)		/* At least one person per team */
			{
				return teamDifference(oldTeams);	/* Return the minimum difference */
			}	

			else
			{	/* Invalid team */
				return Integer.MAX_VALUE;
			}
		}

		/* Recursive Case */

		ArrayList<Integer> pool = copyIntegers(oldPool);		/* Create a copy of the pool */

		int current = pool.get(0);	/* Remove a member from the pool */
		pool.remove(0);

		int lowest = Integer.MAX_VALUE;

		for(int count = 0; count < oldTeams.size(); count++)
		{	/* Cycle through all teams */

			ArrayList<Integer> teams = copyIntegers(oldTeams);	/* Create a copy of the teams */
			int newValue = teams.get(count) + current;

			teams.set(count, newValue);				/* Try adding the person to this team */

			int ret = fairTeamsRecursive(pool, teams, chain + 1);

			if(ret < lowest)
				lowest = ret;					/* If this combination creates the lowest chain, record it */
		}
	
		return lowest;	/* Return lowest chain */
	}

	/* This simple function checks to see if there is at
	 * least one person per team.
	 */
	private boolean validTeams(ArrayList<Integer> teams)
	{
		for(int count = 0; count < teams.size(); count++)
			if(teams.get(count) == 0)	/* Team does not have at least one member */
				return false;

		return true;

	}

	/* This simple function measures the difference between
	 * the highest and lowest team.
	 */
	private int teamDifference(ArrayList<Integer> teams)			// THIS IS GETTING THE WRONG RESULTS
	{
		int lowest = Integer.MAX_VALUE;
		int highest = Integer.MIN_VALUE;

		for(int count = 0; count < teams.size(); count++)
		{
			if(teams.get(count) > highest)
				highest = teams.get(count);

			if(teams.get(count) < lowest)
				lowest = teams.get(count);	/* Determine high and low */

		}

		return highest - lowest;
	}

	/* This simple routine performs a deep copy of an integer array.
	 * Yes, I could have made it generic.
	 */
	private ArrayList<Integer> copyIntegers(ArrayList<Integer> old)
	{
		ArrayList<Integer> newarray = new ArrayList<Integer>();

		for(int count = 0; count < old.size(); count++)
			newarray.add(old.get(count));

		return newarray;	/* Return copy */
	}
}
