
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class AnagramSolver {

	public static List<List<String>> perfectAnagrams = new ArrayList<List<String>>();
	public static List<String> currList = new ArrayList<String>();
	public static Map <String, LetterInventory> invMap = new TreeMap <String, LetterInventory>();

	public AnagramSolver(List<String> dictList) {


		for (int index = 0; index < dictList.size(); index++){
			LetterInventory currInv = new LetterInventory(dictList.get(index));
			invMap.put(dictList.get(index), currInv);

		}
	}
	
	
	public List<List<String>> getAnagrams(String targetWord, Integer maxWords){
					
		perfectAnagrams.clear();
		currList.clear();
		LetterInventory targetInv = new LetterInventory(targetWord); //Make a new letter inventory out of our target word
		recursive(invMap, targetInv, maxWords);

		removeDuplicates();
		Collections.sort(perfectAnagrams, new AnagramComparator());
		
		return perfectAnagrams;
	}
	
	
	

	private static void removeDuplicates(){
		boolean status;
		for (int currWord = 0; currWord < perfectAnagrams.size(); currWord++){
			for (int currElement = currWord + 1; currElement < perfectAnagrams.size(); currElement ++){
				status = true;
				if (perfectAnagrams.get(currWord).size() == perfectAnagrams.get(currElement).size()){
					for (int element = 0; element < perfectAnagrams.get(currWord).size(); element++){
						if (!perfectAnagrams.get(currElement).contains(perfectAnagrams.get(currWord).get(element))){
							status = false;
						}

					}	
					if (status == true){
						perfectAnagrams.remove(currElement);
						currElement--; //Since we removed an element, Set k = k-1, so we don't skip any anagram lists
					}
				}			
			}
		}
	}
	
	
	//Sort the list in alphabetical order
	public static class AnagramComparator implements Comparator<List<String>>
	{
		public int compare(List<String> a1, List<String> a2)
		{
			if(a1.size() < a2.size())
				return -1;
			if(a1.size() > a2.size())
				return 1;
			if(a1.size() == a2.size())
			{
				if(a1.equals(a2))
					return 0;
				else
				{
					for(int currNum = 0; currNum < a1.size(); currNum++)
					{
						if(a1.get(currNum).compareTo(a2.get(currNum)) != 0)
							return a1.get(currNum).compareTo(a2.get(currNum));
					}

				}

			}

			return -1;
		}
	}
	
		
	
	/* Make a much smaller map, which only contains keys who's ListInventory is a substring of our target word's ListInventory */	
	
	private static Map makeSmallerDict(Map <String, LetterInventory> inputMap, LetterInventory targetInv){

		Map <String, LetterInventory> returnMap = new TreeMap<String, LetterInventory>(); //Make a new map, which will be returned

		for (String key: inputMap.keySet()){
			if (targetInv.subtract(inputMap.get(key))!= null){
				returnMap.put(key, inputMap.get(key));
			}
		}

		return returnMap;

	}


	
	private static void recursive(Map <String, LetterInventory> inputMap, LetterInventory remainingInv, int maxWords){


		for (String key: inputMap.keySet()){


			currList.add(key.toString());

			/* Is the current key a sublist of the remaining characters? */
			if (remainingInv.subtract(inputMap.get(key)) == null){
				currList.remove(currList.size()-1);
			}

			else{

				/* Have we found a perfect anagram? */
				if (remainingInv.subtract(inputMap.get(key)).toString() == ""){

					ArrayList<String> addList = new ArrayList<String>();
					if (maxWords == 0 || currList.size() <= maxWords){
						for (int index = 0; index < currList.size(); index++){
							addList.add(currList.get(index));
						}
						perfectAnagrams.add(addList);
					}


				}

				/* We've found a word that, when combined with other words, may form an anagram, but we need additional words.  Call the method again */
				else{
					recursive(makeSmallerDict(inputMap, (remainingInv.subtract(inputMap.get(key)))), remainingInv.subtract(inputMap.get(key)), maxWords);
				}


				currList.remove(currList.size() -1);




			}
		}
	}

}
