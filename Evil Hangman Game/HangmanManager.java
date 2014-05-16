// add imports as necessary

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;


public class HangmanManager {

	private static ArrayList<Character> guessList;
	private static int guessCount;
	private static String currentPattern;
	private static String secretWord;
	private static ArrayList<String> dictList;
	private static int wordLength;
	private static int gameDifficulty;
	private static List<String> originalDict;
	private static String hardkey;
	private static String mediumkey;
	private static Integer guessNumber;
	// instance vars

	public HangmanManager(List<String> words) {

		dictList = new ArrayList<String>();
		originalDict= words;
		dictList.clear();
		for (int i = 0; i < words.size(); i ++){
			if (words.get(i).length() == wordLength)
				dictList.add(words.get(i));
		}

	}


	// pre: none
	// post: return the number of words in the original Dictionary with the given length
	public int numWords(int length) {
		int count = 0;
		for (int i = 0; i < originalDict.size(); i++){
			if (originalDict.get(i).toString().length() == length)
				count++;
		}
		return count; 
	}


	// pre: numWords(wordLen) > 0, numGuesses >= 1, diff = HangmanMain.EASY, HangmanMain.MEDIUM, or HangmanMain.HARD
	public void prepForRound(int wordLen, int numGuesses, int diff) {

		//ADD PRECONDITION CHECKER

		wordLength = wordLen;
		dictList.clear();
		for (int i = 0; i < originalDict.size(); i ++){
			dictList.add( originalDict.get(i));  //dict list has all the right values
		}

		
		guessNumber = 0;
		gameDifficulty = diff;
		guessCount = numGuesses;
		currentPattern = "";
		for (int i = 0; i < wordLen; i ++){
			currentPattern += "-";
		}
		guessList = new ArrayList<Character>();
		setSecretWord(dictList);
				

	}


	// pre: none
	// return the number of words that are still posibilities
	public int numWordsCurrent() {
		
		int count = 0;
		for (int i = 0; i < dictList.size(); i++){
			if (dictList.get(i).toString().length() == wordLength)
				count++;
		}
		return count; 
	}


	// pre: none
	// return number of wrong guesses left in this game
	public int getGuessesLeft() {
		return guessCount;
	}


	// pre: none
	// post: return a String version of the guesses made so far
	public String getGuessesMade() {
		Collections.sort(guessList);  //Test this to make sure we dont need a new variable
		return guessList.toString();
	}

	public static String setSecretWord(ArrayList<String> dictionary){
		Random newrandom = new Random();
		int m = newrandom.nextInt(dictionary.size());
		secretWord = dictionary.get(m);
		return null;

	}


	// pre: none
	// post: return true if guess has already been used, false otherwise
	public boolean alreadyGuessed(char guess) {
		return guessList.contains(guess);
	}


	// get the current pattern. (In other words the revealed characters and '-'s
	// for characters not yet revealed.
	public String getPattern() {
		return currentPattern;
	}

	public static String makePattern(String word){
		String newPattern = "";
		for (int i = 0; i < word.length(); i++){
			if (guessList.contains(word.charAt(i))){
				newPattern+= word.charAt(i);
			}
			else
				newPattern+= "-";
		}

		currentPattern = newPattern;
		return newPattern;


	}
	
	public static Map<String, Integer> arrayToInt(TreeMap<String, ArrayList<String>> arrayMap){
        
		Map<String, Integer> intMap = new TreeMap<String, Integer>();
		for (String key : arrayMap.keySet()){
		  intMap.put(key, arrayMap.get(key).size());
		}
		
		return intMap;
		
	}


	// pre: !alreadyGuessed(ch)
	// post: return a tree map with the resulting patterns and the number of
	// words in each of the new patterns.
	// the return value is for testing and debugging purposes
	
	public TreeMap<String, Integer> makeGuess(char guess) {
 
		String temp = "";
		guessList.add(guess);
		TreeMap<String, ArrayList<String>> wordMap = new TreeMap<String, ArrayList<String>>();

		for (int listscanner = 0; listscanner < dictList.size(); listscanner++){   		
			ArrayList<String> toAdd = new ArrayList<String>();

			if (dictList.get(listscanner).toString().length() == wordLength){

				if (dictList.get(listscanner).toString().contains(String.valueOf(guess))){ //Check if word contains guess
					temp = makePattern(dictList.get(listscanner).toString()); //Convert the word to a pattern

					if (wordMap.containsKey(temp)){
						toAdd.add(dictList.get(listscanner).toString());
						wordMap.get(temp).addAll(toAdd);
					}
					else{
						toAdd.add(dictList.get(listscanner).toString());
						wordMap.put(temp, toAdd);
					}
				}
				else{
					temp = makePattern(dictList.get(listscanner).toString()); //Convert the word to a pattern

					if (wordMap.containsKey(temp)){
						toAdd.add(dictList.get(listscanner).toString());
						wordMap.get(temp).addAll(toAdd);
					}
					else{
						toAdd.add(dictList.get(listscanner).toString());
						wordMap.put(temp, toAdd);
					}
				}
			}

		}		
		
		dictList.clear();
		
		String bestkey = "";
		guessNumber +=1;
		
		bestkey = findBestKey(wordMap);			
		
		ArrayList<String> mapString = (wordMap.get(bestkey));
		for (int index = 0; index< mapString.size(); index++ ){
			dictList.add(mapString.get(index));
		}
	
		//Only change the remaining guesses if the letter was not in the secret word
		setSecretWord(dictList);  
		String containsCheck = Character.valueOf(guess).toString();
		if (!secretWord.contains(containsCheck))
			guessCount = (guessCount - 1);

		return (TreeMap<String, Integer>) arrayToInt(wordMap);
		
	}


	// pre: numWordsCurrent() > 0
	// return the secret word the manager picked 
	// if there is more than one word left, pick one at random
	public String getSecretWord() {
		return secretWord;
	}
	
	
	//pre: wordMap ! = null
	//return the key with the highest number of values
	public static String findHardestKey(TreeMap<String, ArrayList<String>> wordMap){
		hardkey = "";
		int maxcount = 0;
		for (String key : wordMap.keySet()){
			if ((wordMap.get(key).size()) == maxcount){
				hardkey = leastRevealed(hardkey, wordMap.get(key).toString());
			}
			if ((wordMap.get(key).size()) > maxcount){ //NEED TO DETERMINE WHICH HAS LEAST
				maxcount = wordMap.get(key).size();   //AMOUNT OF CHARACTERS REVEALED
				hardkey = key;					
				currentPattern = makePattern(hardkey);
			}
		}
		return hardkey;
	}
	
	//Method to determine which key to choose if they have the same number of wards
	public static String leastRevealed(String currentKey, String oldKey){
	  String toReturn = "";
	  currentKey = makePattern(currentKey);
	  oldKey = makePattern(oldKey);
      int curKeycount = 0;
      int oldKeycount = 0;
      Character e = "-".charAt(0);
      for (int i = 0; i < currentKey.length(); i++){
    	  if (currentKey.charAt(i) == e)
    		  curKeycount++;
    	  if (oldKey.charAt(i)== e)
    		  oldKeycount++;
    		  
      }
      
      if (curKeycount == oldKeycount){
    	  if (currentKey.compareTo(oldKey) > 0)
    		  toReturn = currentKey;
    	  else
    		  toReturn = oldKey;
    	  
      }
      
      else if (curKeycount < oldKeycount)
    	  toReturn = currentKey;
      
      else
    	  toReturn = oldKey;
      
      return toReturn;
		
	}
	
	public static String findMediumKey(TreeMap<String, ArrayList<String>> wordMap){
		mediumkey = "";
		int maxcount = 0;
		for (String key : wordMap.keySet()){
			if ((wordMap.size() == 1))
				mediumkey = key;
			if ((wordMap.get(key).size()) == maxcount){
				mediumkey = leastRevealed(mediumkey, wordMap.get(key).toString());
			}
			else if ((wordMap.get(key).size()) > maxcount){ //NEED TO DETERMINE WHICH HAS LEAST
				if (key != hardkey){
				maxcount = wordMap.get(key).size();   //AMOUNT OF CHARACTERS REVEALED
				mediumkey = key;
				}
				currentPattern = makePattern(mediumkey);
			}
		}
		return mediumkey;
	}
	
	public static String findBestKey(TreeMap<String, ArrayList<String>> wordMap){
		String bestkey = "";
		if (gameDifficulty == 1){
			if (guessNumber % 2 == 0){
				System.out.println(guessNumber);
				findHardestKey(wordMap);
				bestkey = findMediumKey(wordMap);
			}
			else
				bestkey = findHardestKey(wordMap);
		}

		//If the gameDifficulty is set to 2 (medium)
		if (gameDifficulty == 2){
			if (guessNumber % 4 == 0){
				findHardestKey(wordMap);
				bestkey = findMediumKey(wordMap); //Call the findHardestKey method to get the key with the most values
			}
			else
				bestkey = findHardestKey(wordMap);
		}

		//If the gameDifficulty is set to 3 (the hardest)
		if (gameDifficulty == 3){
			bestkey = findHardestKey(wordMap);
		}
		return bestkey;
	}


}
