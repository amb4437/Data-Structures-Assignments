import java.util.ArrayList;
import java.util.List;

public class LetterInventory {
	
  private final int ALPHA_LENGTH= 26;
  private int[] letterInv;
  private int size = 0;
	
  
  // pre: String != null
  public LetterInventory(String inString){
	  
	  letterInv = new int[ALPHA_LENGTH];
	  String lowerString = inString.toLowerCase(); //Convert the input string to lower case
	  for (int index = 0; index < lowerString.length(); index++){
		  if( 'a' <= lowerString.charAt(index) && lowerString.charAt(index) <= 'z'){ //Make sure it's a valid English character
			  letterInv[lowerString.charAt(index) - 'a']++; //Increment the count at the correct LetterInventory index
			  size++;
		  }
	  }


  }
  
  
  //pre: Character is a valid English character
  //post: return the frequency of that character in the letter inventory
  public int get(char inChar){
	  
	  char lowerChar = Character.toLowerCase(inChar); //Convert the character to lower case

	  if (lowerChar < 'a' || lowerChar > 'z'){  //Make sure the character is a valid
		  throw new IllegalArgumentException("Violation of precondition: not a valid English character");
	  }
	  else{
		  return letterInv[lowerChar - 'a'];
	  }

  }
  
  public int size(){
	  return size;
  }
  
  public boolean isEmpty(){
	  return (size == 0);
  }
  
  public String toString(){
	  String output = "";

	  for (int index = 0; index < letterInv.length; index++){
		  if (letterInv[index] > 0){
			  for (int i = 0; i < letterInv[index]; i ++){
				  output+=(char) (index + 'a');;
			  }
		  }
	  }
	  return output;
  }
  
  public LetterInventory add(LetterInventory compareInv){
	String thisString = this.toString();
	thisString+= compareInv.toString();
	LetterInventory outputInventory = new LetterInventory(thisString);
	
	return outputInventory;
	  
  }
  
  
  public LetterInventory subtract(LetterInventory compareInv){
	  
	  LetterInventory outputInventory = new LetterInventory("");

	  for (int index = 0; index < ALPHA_LENGTH; index++){
		  int changeVal = (this.letterInv[index] - compareInv.letterInv[index]);
		  if (changeVal < 0){
			  return null;
		  }
		  else{
			  outputInventory.letterInv[index] = (this.letterInv[index] - compareInv.letterInv[index]); //Is this cool?..
		  }
	  }	  

	  LetterInventory newInv = new LetterInventory(outputInventory.toString());
	  return newInv;
  }
  
  public boolean equals(LetterInventory compareInv){
	  
	  String thisString = this.toString();
	  String inputString = compareInv.toString();
	  return thisString.equals(inputString);
  }
  
}