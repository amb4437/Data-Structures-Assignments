
/**
 * A class that models systems of linear equations (Math Matrices)
 * as used in linear algebra.
 *
 * @version Skeleton file for students
 */
public class MathMatrix
{

	private int [][] mathmatrix;
	// instance variables
	/*CS314 STUDENTS: ADD YOUR CODE HERE*/

	/**
	 * create a MathMatrix with cells equal to the values in mat.
	 * A "deep" copy of mat is made.
	 * Changes to mat after this constructor do not affect this
	 * Matrix and changes to this MathMatrix do not affect mat
	 * @param  mat  mat !=null, mat.length > 0, mat[0].length > 0,
	 * mat is a rectangular matrix
	 */
	public MathMatrix(int[][] mat) {
		// check the precondition. rectangularMatrix is a private method at end of Matrix class
		if((mat == null) || (mat.length == 0) || (mat[0].length == 0)
				|| !rectangularMatrix(mat)) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"int[][] Matrix constructor");

		mathmatrix = new int[mat.length][mat[0].length];
		for (int i= 0; i < mathmatrix.length; i++)  // Increment the row
			for (int j =0 ; j< mathmatrix[i].length; j++) //Increment the column
				mathmatrix[i][j]= mat[i][j]; //Add each element to the mathmatrix

		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
	}


	/**
	 * create a MathMatrix of the specified size with all cells set to the intialValue.
	 * <br>pre: numRows > 0, numCols > 0
	 * <br>post: create a matrix with numRows rows and numCols columns. 
	 * All elements of this matrix equal initialVal.
	 * In other words after this method has been called getVal(r,c) = initialVal 
	 * for all valid r and c.
	 * @param numRows numRows > 0
	 * @param numCols numCols > 0
	 * @param initialVal all cells of this Matrix are set to initialVal
	 */
	public MathMatrix(int numRows, int numCols, int initialVal) {
		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		if((numRows<=0) || (numCols<=0)) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"numRows <=0 or numCols <= 0");


		mathmatrix = new int[numRows][numCols];
		for (int i= 0; i < mathmatrix.length; i++)  // Incremenent the row
			for (int j =0 ; j< mathmatrix[i].length; j++) //Increment the column
				mathmatrix[i][j]= initialVal; //Set each element equal to initialVal


	}


	/**
	 * change the value of one of the cells in this MathMatrix.
	 * <br>pre: 0 <= row < numRows(), 0 <= col < numCols()
	 * <br>post: getVal(row, col) = newValue
	 * @param row 0 <= row < numRows()
	 * @param col 0 <= col < numCols()
	 */
	public void changeElement(int row, int col, int newValue) {
		if((row < 0) || (col < 0) || (row > numRows()) || (col > numCols() ))
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");


		mathmatrix[row][col] = newValue;
		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
	}


	/**
	 * Get the number of rows.
	 * @return the number of rows in this MathMatrix
	 */
	public int numRows() {
		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		return mathmatrix.length; // alter as necessary
	}


	/**
	 * Get the number of columns.
	 * @return the number of columns in this MathMatrix
	 */
	public int numCols() {
		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		return mathmatrix[0].length; // alter as necessary
	}


	/**
	 * get the value of a cell in this MathMatrix.
	 * <br>pre: row  0 <= row < numRows(), col  0 <= col < numCols()
	 * @param  row  0 <= row < numRows()
	 * @param  col  0 <= col < numCols()
	 * @return the value at the specified position
	 */
	public int getVal(int row, int col) {
		if((row < 0) || (col < 0) || (row > numRows()) || (col > numCols())) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");

		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		return mathmatrix[row][col]; // alter as necessary
	}


	/**
	 * implements MathMatrix addition, (this MathMatrix) + rightHandSide.
	 * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
	 * <br>post: This method does not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
	 * @return a new MathMatrix that is the result of adding this Matrix to rightHandSide.
	 * The number of rows in the returned Matrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned Matrix is equal to the number of columns in this MathMatrix.
	 */
	public MathMatrix add(MathMatrix rightHandSide) {

		if(numCols() != rightHandSide.numCols() || (numRows() != rightHandSide.numRows())) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");

		MathMatrix newmatrix = new MathMatrix(mathmatrix.length, mathmatrix[0].length, 0);


		for (int i = 0; i < mathmatrix.length; i++){
			for (int j = 0; j <mathmatrix[i].length; j++){
				newmatrix.changeElement(i, j, (mathmatrix[i][j] + rightHandSide.getVal(i, j)));
			}
		}


		/*CS314 STUDENTS: ADD YOUR CODE HERE*/

		return newmatrix; // alter as necessary

	}
	/**
	 * implements MathMatrix subtraction, (this MathMatrix) - rightHandSide.
	 * <br>pre: rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
	 * <br>post: This method does not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.numRows() = numRows(), rightHandSide.numCols() = numCols()
	 * @return a new MathMatrix that is the result of subtracting rightHandSide from this MathMatrix.
	 * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned MathMatrix is equal to the number of columns in this MathMatrix.
	 */
	public MathMatrix subtract(MathMatrix rightHandSide) {

		if(numCols() != rightHandSide.numCols() || (numRows() != rightHandSide.numRows())) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");

		MathMatrix newmatrix = new MathMatrix(mathmatrix.length, mathmatrix[0].length, 0);


		for (int i = 0; i < mathmatrix.length; i++){
			for (int j = 0; j <mathmatrix[i].length; j++){
				newmatrix.changeElement(i, j, (mathmatrix[i][j] -  rightHandSide.getVal(i, j)));
			}
		}
		return newmatrix; // alter as necessary
	}


	/**
	 * implements matrix multiplication, (this MathMatrix) * rightHandSide.
	 * <br>pre: rightHandSide.numRows() = numCols()
	 * <br>post: This method should not alter the calling object or rightHandSide
	 * @param rightHandSide rightHandSide.numRows() = numCols()
	 * @return a new MathMatrix that is the result of multiplying this MathMatrix and rightHandSide.
	 * The number of rows in the returned MathMatrix is equal to the number of rows in this MathMatrix.
	 * The number of columns in the returned MathMatrix is equal to the number of columns in rightHandSide.
	 */
	public MathMatrix multiply(MathMatrix rightHandSide) {

		if((numCols() != rightHandSide.numRows())) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");

		MathMatrix newmatrix = new MathMatrix(mathmatrix.length, rightHandSide.numCols(), 0);


		for (int i = 0; i < mathmatrix.length; i++){
			for (int j = 0; j <rightHandSide.numCols(); j++){
				int value = 0;
				for (int k= 0; k < mathmatrix[0].length; k++){
					value = value + (mathmatrix[i][k] * rightHandSide.getVal(k, j));
					newmatrix.changeElement(i, j, value);
				}
			}
		}
		return newmatrix; // alter as necessary
	}


	/**
	 * Multiply all elements of this MathMatrix by factor.
	 * <br>pre: none
	 * <br>post: all elements in this matrix have been multiplied by factor. 
	 * In other words after this method has been called getVal(r,c) = old getVal(r, c) * factor
	 * for all valid r and c.
	 * @param factor the value to multiply every cell in this Matrix by.
	 */
	public void scale(int factor) {

		for (int i = 0; i < mathmatrix.length; i++){
			for (int j = 0; j <mathmatrix[0].length; j++){
				int value = (mathmatrix[i][j] * factor);
				mathmatrix[i][j]=value;
			}
		}
	}


	/**
	 * accessor: get a transpose of this MathMatrix. 
	 * This Matrix is not changed.
	 * <br>pre: none
	 * @return a transpose of this MathMatrix
	 */
	public MathMatrix getTranspose() {
		MathMatrix newmatrix = new MathMatrix(mathmatrix[0].length, mathmatrix.length, 0);


		for (int i = 0; i < mathmatrix.length; i++){
			for (int j = 0; j <mathmatrix[0].length; j++){
				newmatrix.changeElement(j, i, mathmatrix[i][j]);
			}
		}
		return newmatrix; // alter as necessary
	}


	/**
	 * override equals.
	 * @return true if rightHandSide is the same size as this MathMatrix and all values in the
	 * two MathMatrix objects are the same, false otherwise
	 */
	public boolean equals(Object rightHandSide) {
		/* CS314 Students. The following is standard equals
		 * method code. We will learn about it in a few weeks.
		 */
		boolean result = true;
		if( rightHandSide != null && this.getClass() == rightHandSide.getClass()){
			// rightHandSide is a non null MathMatrix
			MathMatrix otherMatrix = (MathMatrix)rightHandSide;

			// cs314 students: determine if otherMatrix is equal
			// to this MathMatrix and set result to true if they are.

			/*CS314 STUDENTS: ADD YOUR CODE HERE*/

			if (mathmatrix.length != otherMatrix.numRows())
				result = false;
			if (mathmatrix[0].length != otherMatrix.numCols())
				result = false;


			for (int i = 0; i < mathmatrix.length; i++){
				for (int j = 0; j <mathmatrix[0].length; j++){
					if (mathmatrix[i][j] != otherMatrix.getVal(i, j))
						result = false;

				}
			}
		}
		return result;
	}


	/**
	 * override toString.
	 * @return a String with all elements of this MathMatrix. 
	 * Each row is on a seperate line.
	 * Spacing based on longest element in this Matrix.
	 * Each row stats and ends with a vertical bar: '|'
	 */
	public String toString(){

		String blankspaces = " ";
		String newstring = "";
		int numspaces = 0;
		for (int i = 0; i< mathmatrix.length; i++){ // go row by row
			for (int j= 0; j< mathmatrix[i].length; j++){ //go col by col   			
				int currentlength = (("" + mathmatrix[i][j]).length()); //convert to string so we can use length()
				if (currentlength > numspaces) //determine the number of spaces needed based on which element
					numspaces = currentlength; //has the largest length

			}
		}

		for (int i = 0; i< mathmatrix.length; i++){
			newstring +=("|");
			for (int j= 0; j< mathmatrix[i].length; j++){
				blankspaces = " ";
				for (int addspaces = 0; addspaces < (numspaces - ("" + mathmatrix[i][j]).length()) ; addspaces++){  //Determine the number of spaces needed
					blankspaces += (" ");
				}
				newstring = (newstring + blankspaces + mathmatrix[i][j]); //add the determined amount of spaces
				if (j == mathmatrix[i].length-1) //If it's the last column in the row, add a | and new line character
					newstring += ("|" + "\n");
			}
		}

		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		return newstring; // alter as necessary
	}

	/**
	 * Return true if this MathMatrix is upper triangular. To
	 * be upper triangular all elements below the main 
	 * diagonal must be 0.<br>
	 * pre: this is a square matrix. numRows() == numCols()  
	 * @return <tt>true</tt> if this MathMatrix is upper triangular,
	 * <tt>false</tt> otherwise. 
	 */
	public boolean isUpperTriangular() {

		if(numRows() != numCols()) 
			throw new IllegalArgumentException("Violation of precondition: " +
					"Number of rows or columns");
		boolean condition = true;
		/*CS314 STUDENTS: ADD YOUR CODE HERE*/
		for (int i = 1; i<mathmatrix.length; i++){
			for (int j = 0; j<i; j++){
				if (mathmatrix[i][j] != 0)
					condition = false;
			}
		}

		return condition; // alter as necessary
	}

	// method to ensure mat is rectangular
	// pre: mat != null
	public static boolean rectangularMatrix(int[][] mat) {
		if(mat == null)
			throw new IllegalArgumentException("Violation of precondition: "
					+ " Parameter mat may not be null");
		boolean isRectangular = true;
		int row = 1;
		final int COLUMNS = mat[0].length;

		while( isRectangular && row < mat.length ) {
			isRectangular = ( mat[row].length == COLUMNS );
			row++;
		}

		return isRectangular;
	}
}