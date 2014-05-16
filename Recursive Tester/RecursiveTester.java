/*
CS 314 Students. Put your answers to the questions from the
RecursiveTrace class here.
 
    <<What is the output when a(5) called?>>
  	5 performing recursive step
	4 performing recursive step
	3 performing recursive step
	2 performing recursive step
	1 performing recursive step
	0 at base case.

	<<What is the Big O of this method?>>
	O(N)

	<<What would occur if the method call a(-5) is made?>>
	Since n is less than 0, it would start at the base case


	<<What is the output when b(7) is called?>>
	7 performing recursive step
	5 performing recursive step
	3 performing recursive step
	1 performing recursive step
	-1 at base case.
	1 done with recursive step
	3 done with recursive step
	5 done with recursive step
	7 done with recursive step

	<<What is the output when c(7) is called?>>
	0
	0
	0
	0
	0
	0
	0
	0 at base case
	5
	8
	11
	14
	17
	20
	23


	<<What does d(16) return?>>
	9

	<<What is the Big O of this method?>>
	O(Log(N))

	<<What does e(4) return?>>
	77

	<<What is the Big O of this method?>>
	O(N^15) 
	
	Use the stopwatch class to record the time it takes for
	this method to complete as n goes from 1 to 30.
	Based on your answer what is the expected time to complete
    when n = 40?
    
    30 e of i elapsed itme: 11.68 seconds
    31 e of i elapsed time: 13.79 seconds
    32 e of i elapsed time: 42.81 seconds
    Estimated e of 40: ~6012.86 seconds
	
	

 */

import java.util.ArrayList;
import java.util.Collections;

public class RecursiveTester {
    public static void main(String[] args) {
    	Recursive r = new Recursive();


    	// try the Sierpinski triangle, uncomment when ready
    	r.drawTriangles(400, 100, 360);
    	//        
    	// try the Sierpinski Carpet, uncomment when ready
    	r.drawCarpet(729, 4);

    	// also try this
    	r.drawCarpet(729, 1);

    	studentTests(r);

    }
    
    // pre: r != null
    // post: run student test       
    private static void studentTests(Recursive r) {
        // CS314 students put your tests here
        String actualBinary = r.getBinary(314);
        String expectedBinary = "100111010";
        if( actualBinary.equals(expectedBinary) )
            System.out.println( "Test 1 passed. get binary.");
        else
            System.out.println( "Test 1 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        
        actualBinary = r.getBinary(21);
        expectedBinary = "10101";
        if( actualBinary.equals(expectedBinary) )
            System.out.println( "Test 2 passed. get binary.");
        else
            System.out.println( "Test 2 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);
        
        actualBinary = r.getBinary(10000);
        expectedBinary = "10011100010000";
        if( actualBinary.equals(expectedBinary) )
            System.out.println( "Test 3 passed. get binary.");
        else
            System.out.println( "Test 3 failed. get binary. expected: " + expectedBinary + ", actual: " + actualBinary);

        
        String actualRev = r.revString("University of Texas");
        String expectedRev = "saxeT fo ytisrevinU";
        if( actualRev.equals(expectedRev) )
            System.out.println( "Test 4 passed. reverse string.");
        else
            System.out.println( "Test 4 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);

        
        actualRev = r.revString("Internet");
        expectedRev = "tenretnI";
        if( actualRev.equals(expectedRev) )
            System.out.println( "Test 5 passed. reverse string.");
        else
            System.out.println( "Test 5 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
        
        actualRev = r.revString("Computer Science");
        expectedRev = "ecneicS retupmoC";
        if( actualRev.equals(expectedRev) )
            System.out.println( "Test 6 passed. reverse string.");
        else
            System.out.println( "Test 6 failed. reverse string. expected: " + expectedRev + ", actual: " + actualRev);
        
        
        int[] numsForDouble = {7, 14, 28, 32, 64, 100};
        int actualDouble = r.nextIsDouble(numsForDouble);
        int expectedDouble = 3;
        if(actualDouble == expectedDouble)
            System.out.println( "Test 7 passed. next is double.");
        else
            System.out.println( "Test 7 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble);     
        
        
        numsForDouble = new int[]{21, 42, 0, 19, 16, 8};
        actualDouble = r.nextIsDouble(numsForDouble);
        expectedDouble = 1;
        if(actualDouble == expectedDouble)
            System.out.println( "Test 8 passed. next is double.");
        else
            System.out.println( "Test 8 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble); 

        
        numsForDouble = new int[]{55, 110, 220, 440, 5};
        actualDouble = r.nextIsDouble(numsForDouble);
        expectedDouble = 3;
        if(actualDouble == expectedDouble)
            System.out.println( "Test 9 passed. next is double.");
        else
            System.out.println( "Test 9 failed. next is double. expected: " + expectedDouble + ", actual: " + actualDouble); 
        
        
        ArrayList<String> mnemonics = r.listMnemonics("1");
        ArrayList<String> expected = new ArrayList<String>();
        
        mnemonics = r.listMnemonics("88");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("TT");
        expected.add("TU");
        expected.add("TV");
        expected.add("UU");
        expected.add("UT");
        expected.add("UV");
        expected.add("VV");
        expected.add("VU");
        expected.add("VT");
        Collections.sort(expected);
        if( mnemonics.equals(expected))
            System.out.println( "Test 10 passed. Phone mnemonics.");
        else
            System.out.println( "Test 10 failed. Phone mnemonics.");

        mnemonics = r.listMnemonics("0000001");
        expected.clear();
        expected.add("0000001");
        if( mnemonics.equals(expected))
            System.out.println( "Test 11 passed. Phone mnemonics.");
        else
            System.out.println( "Test 11 failed. Phone mnemonics.");		
        
        mnemonics = r.listMnemonics("0155");
        Collections.sort(mnemonics);
        expected.clear();
        expected.add("01JJ");
        expected.add("01JK");
        expected.add("01JL");
        expected.add("01KK");
        expected.add("01KJ");
        expected.add("01KL");
        expected.add("01LL");
        expected.add("01LJ");
        expected.add("01LK");
        Collections.sort(expected);
        if( mnemonics.equals(expected))
            System.out.println( "Test 12 passed. Phone mnemonics.");
        else
            System.out.println( "Test 12 failed. Phone mnemonics.");

       int[][] world = new int[][]
                          {{100, 99, 200, 200, 200, 200, 200, 200, 200, 200},
        		{200, 98, 200, 200, 200, 200, 200, 200, 200, 200},
        		{200, 97, 96, 200, 200, 200, 200, 200, 200, 200},
        		{200, 200, 95, 200, 200, 200, 85, 84, 83, 200},
        		{200, 200, 94, 93, 92, 200, 86, 200, 82, 200},
        		{200, 150, 200, 200, 91, 200, 87, 200, 81, 200},
        		{200, 200, 200, 200, 90, 89, 88, 200, 80, 200},
        		{200, 150, 100, 200, 200, 200, 200, 200, 79, 200},
        		{200, 200, 200, 200, 200, 200, 200, 200, 78, 77},
        		{200, 200, 200, 200, 200, 200, 200, 200, 200, 76}};

        if( r.canFlowOffMap(world,3,1))
            System.out.println( "Test 13 passed. can flow off map.");
        else
            System.out.println( "Test 13 failed. can flow off map.");	

        if( !r.canFlowOffMap(world,5,1))
            System.out.println( "Test 14 passed. can flow off map.");
        else
            System.out.println( "Test 14 failed. can flow off map.");
        
        if( r.canFlowOffMap(world,2,2))
            System.out.println( "Test 15 passed. can flow off map.");
        else
            System.out.println( "Test 15 failed. can flow off map.");


        int[] abilities = {1, 2, 5, 7, 12, 20};
        if(r.minDifference(2, abilities) == 1)
            System.out.println( "Test 16 passed. min difference.");
        else
            System.out.println( "Test 16 failed. min difference.");	

        int[] abilities1 = {10, 10, 10, 10};
        if(r.minDifference(4, abilities1) == 0)
            System.out.println( "Test 17 passed. min difference.");
        else
            System.out.println( "Test 17 failed. min difference.");

        int[] abilities2 = {20, 21, 22, 23, 24};
        if(r.minDifference(5, abilities2)== 4)
            System.out.println( "Test 18 passed. min difference.");
        else
            System.out.println( "Test 18 failed. min difference.");		
        
        String puzzle1 = "900500203000073000405000900020408007050060030600702040002000604000320000801007005";
        int[][] board = stringToBoard(puzzle1);
        int[][] result = r.getSudokoSolution(board);
        String actualBoard = boardToString(result);
        String expectedBoard = "917546283268973451435281976129438567754169832683752149372815694596324718841697325";
        if(actualBoard.equals(expectedBoard))
            System.out.println( "Test 19 passed. sudoko solver.");
        else {
            System.out.println("Test 19 failed. sudoku solver:");
            System.out.println("Expected board:");
            printBoard(stringToBoard(expectedBoard));
            System.out.println();
            System.out.println("Actual board:");
            printBoard(result);
        }
        
        
        String puzzle4 = "000028070000300008008001004040000706080756040507000010900800600800009000020540000";
        assert puzzle4.length() == 81;
        board = stringToBoard(puzzle4);
        result = r.getSudokoSolution(board);
        actualBoard = boardToString(result);
        expectedBoard = "693428571471395268258671394349182756182756943567934812934817625815269437726543189";
        if(actualBoard.equals(expectedBoard))
            System.out.println( "Test 20 passed. sudoko solver.");
        else {
            System.out.println("Test 20 failed. sudoku solver:");
            System.out.println("Expected board:");
            printBoard(stringToBoard(expectedBoard));
            System.out.println();
            System.out.println("Actual board:");
            printBoard(result);
        }
        
        
        String puzzle5 = "750903006000000000000450003620090800015000230009010075300084000000000000900601057";
        assert puzzle5.length() == 81;
        board = stringToBoard(puzzle5);
        result = r.getSudokoSolution(board);
        actualBoard = boardToString(result);
        expectedBoard = "758923146243167598196458723627395814815746239439812675371584962564279381982631457";
        if(actualBoard.equals(expectedBoard))
            System.out.println( "Test 21 passed. sudoko solver.");
        else {
            System.out.println("Test 21 failed. sudoku solver:");
            System.out.println("Expected board:");
            printBoard(stringToBoard(expectedBoard));
            System.out.println();
            System.out.println("Actual board:");
            printBoard(result);
        }
    }

    private static String boardToString(int[][] board) {
        StringBuilder result = new StringBuilder(81);
        for(int r = 0; r < board.length; r++)
            for(int c = 0; c < board[r].length; c++)
                result.append(board[r][c]);
        return result.toString();
    }

    private static int[][] stringToBoard(String puzzle) {
        int[][] result = new int[Recursive.BOARD_SIZE][Recursive.BOARD_SIZE];
        int index = 0;
        for(int r = 0; r < result.length; r++)
            for(int c = 0; c < result.length; c++, index++)
                result[r][c] = puzzle.charAt(index) - '0';
        return result;
    }
    
    private static void printBoard(int[][] board) {
        for(int r = 0; r < board.length; r++) {
            for(int c = 0; c < board[r].length; c++) {
                System.out.print(board[r][c]);
                System.out.print(" ");
            }
            System.out.println();
        }
    }
}
