import java.util.Random;



/* CS314 Students. Put your experiment results and
 * answers to questions here.
 */

/**
 * A class to run tests on the MathMatrix class
 */
public class MathMatrixTester {

	/**
	 * main method that runs simple test on the MathMatrix class
	 *
	 * @param args not used
	 */
	public static void main(String[] args) {
		int[][] data1 = { {1, 2, 3},
				{2, 3, 4}};
		int[][] data2 = { {2, 1, 1},
				{2, 3, 1}};

		int[][] data3 = { {9, 8, 7},
				{6, 5, 4}};
		int[][] e1;

		//test 1.0, specify size and values constructor
		MathMatrix mat1 = new MathMatrix(3, 3, -4);
		e1 = new int[][] {{-4, -4, -4}, {-4, -4, -4}, {-4, -4, -4}};
		printTestResult( get2DArray(mat1), e1, "1.0", "Constructor with size and initial val specified.");

		// test 1.1, specify size and values constructor
		mat1 = new MathMatrix(2, 2, -2);
		e1 = new int[][] {{-2, -2}, {-2, -2}};
		printTestResult( get2DArray(mat1), e1, "1.1", "Constructor with size and initial val specified.");
		System.out.println("");


		//tests 2, int[][] constructor, deep copy
		mat1 = new MathMatrix( data1 );
		data1[0][0] = 400;
		// alter data1. mat1 should be unchanged if deep copy made
		e1 = new int[][] { {400, 2, 3}, {2, 3, 4} };
		printTestResult( data1, e1, "2.0", "constructor with one parameter of type int[][]");
		// data1 altered. mat1 should be unchanged if deep copy made
		mat1 = new MathMatrix( data3 );
		data3[0][1] = 1000;
		// alter data3. mat1 should be unchanged if deep copy made
		e1 = new int[][] { {9, 1000, 7}, {6, 5, 4} };
		printTestResult( data3, e1, "2.1", "constructor with one parameter of type int[][]");

		System.out.println("");

		//test 3, addition
		data1[0][0] = 1;
		mat1 = new MathMatrix(data1);
		MathMatrix mat2 = new MathMatrix(data3);
		MathMatrix mat3 = mat1.add(mat2);
		e1 = new int[][] { {10, 1002, 10}, {8, 8, 8} };
		printTestResult( get2DArray(mat3), e1, "3.0", "add method. Testing mat3 correct result.");

		data1[0][0] = 1;
		mat1 = new MathMatrix(data2);
		mat3 = new MathMatrix(data3);
		mat2 = mat1.add(mat3);
		e1 = new int[][] { {11, 1001, 8}, {8, 8, 5} };
		printTestResult( get2DArray(mat2), e1, "3.1", "add method. Testing mat3 correct result.");

		System.out.println("");


		data1[0][0] = 1;
		mat1 = new MathMatrix(data1);
		mat2 = new MathMatrix(data3);
		mat3 = mat1.subtract(mat2);
		e1 = new int[][] { {-8, -998, -4}, {-4, -2, 0} };
		printTestResult( get2DArray(mat3), e1, "4.0", "subtract method. Testing mat3 correct result.");

		data1[0][0] = 1;
		mat1 = new MathMatrix(data2);
		mat3 = new MathMatrix(data1);
		mat2 = mat1.subtract(mat3);
		e1 = new int[][] { {1, -1, -2}, {0, 0, -3} };
		printTestResult( get2DArray(mat2), e1, "4.1", "subtract method. Testing mat3 correct result.");

		System.out.println("");


		//test 4, multiplication       

		data2 = new int[][] { {2, 1}, {1, 3}, {1, 1} };
		mat2 = new MathMatrix(data2);
		mat1 = new MathMatrix(data1);
		mat3 = mat2.multiply(mat1);;
		e1 = new int[][] { {4, 7, 10}, {7, 11, 15}, {3, 5, 7} };
		printTestResult( get2DArray(mat3), e1, "5.0", "multiply method");

		data2 = new int[][] { {10, 11}, {3, 13}, {7, 11} };
		mat2 = new MathMatrix(data2);
		mat1 = new MathMatrix(data1);
		mat3 = mat2.multiply(mat1);
		e1 = new int[][] { {32, 53, 74}, {29, 45, 61}, {29, 47, 65} };
		printTestResult( get2DArray(mat3), e1, "5.1", "multiply method");

		System.out.println("");

		//test 5, toString()
		data1 = new int[][] {{9, 300, 212, 98000},
				{3200, 19, 22, 14},
				{10, 12, 9, 100}};
		mat1 = new MathMatrix(data1);
		String expected = "|     9   300   212 98000|\n|  3200    19    22    14|\n|    10    12     9   100|\n";
		if( mat1.toString().equals( expected ) )
			System.out.println("Test 6.0 tests the toString method.  The test passed");
		else
			System.out.println("Test 6.0 tests the toString method.  The test failed");

		data1 = new int[][] {{6, 200, 99, 12000},
				{1900, 88, 21, 13},
				{-9, -2, 7, -77}};
		mat1 = new MathMatrix(data1);
		expected = "|     6   200    99 12000|\n|  1900    88    21    13|\n|    -9    -2     7   -77|\n";
		if( mat1.toString().equals( expected ) )
			System.out.println("Test 6.1 tests the toString method.  The test passed");
		else
			System.out.println("Test 6.1 tests the toString method.  The test failed");


		System.out.println("");



		//test 6, upperTriangular
		data1 = new int[][] {{11, 22, 33, 0}, {0, 22, 19, 4}, {0, 0, 22, -100}, {0, 0, 0, -99}};
		mat1 = new MathMatrix(data1);
		if( mat1.isUpperTriangular())
			System.out.println("Test 7.0 tests the upperTriangular.  The test passed");
		else
			System.out.println("Test 7.0 tests the upperTriangular method.  The test failed");

		data1 = new int[][] {{99, 100, 2, 0}, {0, 33, 2, -2}, {0, 0, -100, -33}, {0, 0, 0, 9999}};
		mat1 = new MathMatrix(data1);
		if( mat1.isUpperTriangular())
			System.out.println("Test 7.1 tests the upperTriangular.  The test passed");
		else
			System.out.println("Test 7.1 tests the upperTriangular method.  The test failed");

		System.out.println("");


		//Test 7, get val
		mat1 = new MathMatrix( data1 );
		int value = mat1.getVal(0, 0);
		if( value ==99)
			System.out.println("Test 8.0 tests the getVal method.  The test passed");
		else
			System.out.println("Test 8.0 tests the getVal method.  The test failed");

		mat1 = new MathMatrix( data1 );
		value = mat1.getVal(3, 3);
		if( value ==9999)
			System.out.println("Test 8.1 tests the getVal method.  The test passed");
		else
			System.out.println("Test 8.1 tests the getVal method.  The test failed");

		System.out.println("");



		//Test 8, num rows
		mat1 = new MathMatrix( data1 );
		value = mat1.numRows();
		if( value ==4)
			System.out.println("Test 9.0 tests the numRows method.  The test passed");
		else
			System.out.println("Test 9.0 tests the numRows method.  The test failed");

		mat2 = new MathMatrix( data2 );
		value = mat1.numRows();
		if( value ==4)
			System.out.println("Test 9.1 tests the numRows method.  The test passed");
		else
			System.out.println("Test 9.1 tests the numRows method.  The test failed");

		System.out.println("");

		//Test 9, num cols
		mat1 = new MathMatrix( data1 );
		value = mat1.numCols();
		if( value ==4)
			System.out.println("Test 10.0 tests the numCols method.  The test passed");
		else
			System.out.println("Test 10.0 tests the numRows method.  The test failed");

		mat2 = new MathMatrix( data2 );
		value = mat1.numCols();
		if( value ==4)
			System.out.println("Test 10.1 tests the numCols method.  The test passed");
		else
			System.out.println("Test 10.1 tests the numCols method.  The test failed");

		System.out.println("");


		//Test 10, Change Element
		mat1 = new MathMatrix( data1 );
		mat1.changeElement(0, 0, 22);
		value = mat1.getVal(0,0);
		if( value ==22)
			System.out.println("Test 11.0 tests the Change Element method.  The test passed");
		else
			System.out.println("Test 11.0 tests the Change Element method.  The test failed");

		mat2 = new MathMatrix( data2 );
		mat2.changeElement(1, 1, 100);
		value = mat2.getVal(1, 1);
		if( value ==100)
			System.out.println("Test 11.1 tests the Change Element method.  The test passed");
		else
			System.out.println("Test 11.1 tests the Change Element method.  The test failed");


		System.out.println("");


		//Test 11,  Scale
		data2 = new int[][] { {2, 1}, {1, 3}, {1, 1} };
		mat2 = new MathMatrix(data2);
		e1 = new int[][]{ {4, 2}, {2, 6}, {2, 2} };
		mat2.scale(2);
		printTestResult( get2DArray(mat2), e1, "12.0", "Scale method");

		data2 = new int[][] { {100, 9}, {50, 100}, {20, 40} };
		mat2 = new MathMatrix(data2);
		e1 = new int[][]{ {300, 27}, {150, 300}, {60, 120} };
		mat2.scale(3);
		printTestResult( get2DArray(mat2), e1, "12.1", "Scale method");

		System.out.println("");

		//Test 12, transpose
		data2 = new int[][] { {1, 2}, {3, 4}, {5, 6} };
		mat2 = new MathMatrix(data2);
		e1 = new int[][]{ {1, 3, 5}, {2, 4, 6}};
		mat2= mat2.getTranspose();
		printTestResult( get2DArray(mat2), e1, "13.0", "Transpose method");


		data2 = new int[][] { {100, 200}, {-110, 400}, {21, 0} };
		mat2 = new MathMatrix(data2);
		e1 = new int[][]{ {100, -110, 21}, {200, 400, 0}};
		mat2= mat2.getTranspose();
		printTestResult( get2DArray(mat2), e1, "13.1", "Transpose method");


		System.out.println("");

		// CS314 Students. When ready delete the above tests 
		// and add your 24 tests here.


	}

	// method that sums elements of mat, may overflow int!
	// pre: mat != null
	private static int sumVals(MathMatrix mat) {
		if(mat == null)
			throw new IllegalArgumentException("mat may not be null");

		int result = 0;
		final int ROWS =  mat.numRows();
		final int COLS = mat.numCols();
		for(int r = 0; r < ROWS; r++)
			for(int c = 0; c < COLS; c++) 
				result += mat.getVal(r, c); // likely to overflow, but can still do simple check
		return result;
	}

	// create a matrix with random values
	// pre: rows > 0, cols > 0, randNumGen != null
	private static MathMatrix createMat(Random randNumGen, int rows,
			int cols, final int LIMIT) {

		if(randNumGen == null)
			throw new IllegalArgumentException("randomNumGen variable may no be null");
		else if(rows <= 0 || cols <= 0)
			throw new IllegalArgumentException("rows and columns must be greater than 0. " +
					"rows: " + rows + ", cols: " + cols);

		int[][] temp = new int[rows][cols];
		final int SUB = LIMIT / 4;
		for(int r = 0; r < rows; r++)
			for(int c = 0; c < cols; c++)
				temp[r][c] = randNumGen.nextInt(LIMIT) - SUB;

		return new MathMatrix(temp);
	}

	private static void printTestResult(int[][] data1, int[][] data2, String string, String testingWhat) {
		System.out.print( "Test number " + string + " tests the " + testingWhat +". The test ");
		String result = equals(data1, data2) ? "passed" : "failed";
		System.out.println( result );
	}

	// pre: m != null, m is at least 1 by 1 in size
	private static int[][] get2DArray(MathMatrix m) {
		//check precondition
		assert ( m != null ) && ( m.numRows() > 0 ) && ( m.numCols()> 0 )
		: "Violation of precondition: get2DArray";

		int[][] result = new int[m.numRows()][m.numCols()];
		for(int r = 0; r < result.length; r++)
		{   for(int c = 0; c < result[0].length; c++)
		{   result[r][c] = m.getVal(r,c);
		}
		}
		return result;
	}

	// pre: data1 != null, data2 != null, data1 and data2 are at least 1 by 1 matrices
	//      data1 and data2 are rectangular matrices
	// post: return true if data1 and data2 are the same size and all elements are
	//      the same
	private static boolean equals(int[][] data1, int[][] data2) {
		//check precondition
		if( ( data1 == null ) || ( data1.length == 0 )
				|| ( data1[0].length == 0 ) || !rectangularMatrix(data1)
				||  ( data2 == null ) || ( data2.length == 0 )
				|| ( data2[0].length == 0 ) || !rectangularMatrix(data2))
			throw new IllegalArgumentException( "Violation of precondition: equals check on 2d arrays of ints");

		boolean result = (data1.length == data2.length) && (data1[0].length == data2[0].length);
		int row = 0;
		while( result && row < data1.length ) {
			int col = 0;
			while( result && col < data1[0].length ) {
				result = (data1[row][col] == data2[row][col]);
				col++;
			}
			row++;
		}

		return result;
	}


	// method to ensure mat is rectangular
	// pre: mat != null, mat is at least 1 by 1
	private static boolean rectangularMatrix( int[][] mat ) {
		if(mat == null || mat.length == 0 || mat[0].length == 0)
			throw new IllegalArgumentException("Violation of precondition: "
					+ " Parameter mat may not be null" 
					+ " and must be at least 1 by 1");
		return MathMatrix.rectangularMatrix(mat);
	}
}

//Experiment code, results, and answers
//
//Add matrix code:
//	
//    Stopwatch s = new Stopwatch();
//
//    MathMatrix addedmatrix =null;
//    Random r = new Random();
//    MathMatrix expmatrix = createMat(r, 2200, 2200, 10); //Create matrix 1
//    MathMatrix expmatrix2 = createMat(r, 2200, 2200, 10); //Create matrix 2
//    
//    s.start();
//    for (int i=0; i<1000; i++){ 
//    	addedmatrix = expmatrix.add(expmatrix2); //Add them together
//    }
//    s.stop();
//    System.out.println(s.time()/1000);
//    
//    Time with matrix size 1100x1100: 0.013793645
//    Time with matrix size 2200x2200: 0.079882082

//Multiply matrix code:
//    Stopwatch s = new Stopwatch();
//
//    MathMatrix addedmatrix =null;
//    Random r = new Random();
//    MathMatrix expmatrix = createMat(r, 150, 150, 10);
//    MathMatrix expmatrix2 = createMat(r, 150, 150, 10);
//    
//    s.start();
//    for (int i=0; i<1000; i++){ 
//    	addedmatrix = expmatrix.multiply(expmatrix2);
//    }
//    s.stop();
//    System.out.println(s.time()/1000);
//    
//    Time with matrix size 150x150: 0.010962206
//    Time with matrix size 300x300: 0.139176283

//1.Based on the results of experiment 1, how long do you expect the add method to take if you doubled the dimension size of the MathMatrix objects again?
//-Around 0.4 seconds.
//
//2.What do you think the Big O of the add operation is given two N by N matrices? Does your timing data support this?
//-O(N^2).  The results are a little bit different from what I expected, but in general, yes the data supports this.
//
//3.Based on the results of experiment 2, how long do you expect the multiply method to take if you doubled the dimension size of the MathMatrix objects again?
//-Around 1.96 seconds.
//
//4.What do you think the Big O of the multiply operation is given two N by N matrices? Does your timing data support this?
//-O(N^3).  The timing data is strange, but the results are close to supporting this