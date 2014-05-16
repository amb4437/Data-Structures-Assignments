import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class AnagramFinderTester {

    private static final String testCaseFileName = "testCaseAnagrams.txt";
    private static final String dictionaryFileName = "d3.txt";

    public static void main(String[] args) {

//        // CS314 Students add your LetterInventory tests here.
    	
	   
        Object expected;
        Object actual;
          	
    	LetterInventory testInv1 = new LetterInventory("dcba");
        expected = "abcd";
        actual = testInv1.toString();
        showTestResults(expected, actual, 1, " toString method.  OWN TEST");
        
    	testInv1 = new LetterInventory("abcd");
        expected = "abcd";
        actual = testInv1.toString();
        showTestResults(expected, actual, 2, " toString method.  OWN TEST");
        
    	testInv1 = new LetterInventory("abcccd");
        expected = 3;
        actual = testInv1.get('c');
        showTestResults(expected, actual, 3, " get method.  OWN TEST");

        expected = 0;
        actual = testInv1.get('z');
        showTestResults(expected, actual, 4, " get method.  OWN TEST");
        
        expected = false;
        actual = testInv1.isEmpty();
        showTestResults(expected, actual, 5, " isEmpty on non empty LetterInventory");
        
        testInv1 = new LetterInventory("");
        expected = true;
        actual = testInv1.isEmpty();
        showTestResults(expected, actual, 6, " isEmpty on empty LetterInventory");
        
        expected = 0;
        actual = testInv1.size();
        showTestResults(expected, actual, 7, " size method on empty LetterInventory");
        
        testInv1 = new LetterInventory("Hello!!**!!");
        expected = 5;
        actual = testInv1.size();
        showTestResults(expected, actual, 8, " size method on LetterInventory");
        
        LetterInventory testInv2 = new LetterInventory("Hello");
        expected = "eehhlllloo";
        LetterInventory testInv3 = testInv1.add(testInv2);
        actual = testInv3.toString();
        showTestResults(expected, actual, 9, "test add method");
        
        testInv2 = new LetterInventory("");
        expected = "ehllo";
        testInv3 = testInv1.add(testInv2);
        actual = testInv3.toString();
        showTestResults(expected, actual, 10, "test add method");
        
        testInv2 = new LetterInventory("Hello");
        testInv1 = new LetterInventory("hlle");
        expected = "o";
        actual = testInv2.subtract(testInv1).toString();
        showTestResults(expected, actual, 11, "test subtract method");
        
        testInv2 = new LetterInventory("AAAAAAA!!!!!!!!!!!");
        testInv1 = new LetterInventory("test");
        expected = null;
        actual = testInv2.subtract(testInv1);
        showTestResults(expected, actual, 12, "test subtract method");
        
        expected = true;
        testInv2 = new LetterInventory("!!!!!!!!!!!A&^&^%&^(");
        testInv1 = new LetterInventory("$#%^&*(@#$%^A%$^&*");
        actual = testInv2.equals(testInv1);
        showTestResults(expected, actual, 13, "test equals method");
        
        expected = true;
        testInv2 = new LetterInventory("cOmPuTeR sCiEnCe");
        testInv1 = new LetterInventory("Computer science");
        actual = testInv2.equals(testInv1);
        showTestResults(expected, actual, 14, "test equals method");
        
        testInv1 = new LetterInventory("Computer science");
        expected = "ccceeeimnoprstu";
        actual = testInv1.toString();
        showTestResults(expected, actual, 15, "test LetterInventory constructor");
        
        testInv1 = new LetterInventory("");
        expected = "";
        actual = testInv1.toString();
        showTestResults(expected, actual, 16, "test LetterInventory constructor");
//
//        // tests on the anagram solver itself
//
        boolean displayAnagrams = getChoiceToDisplayAnagrams();
        AnagramSolver solver = new AnagramSolver(AnagramMain.readWords(dictionaryFileName));
        runAnagramTests(solver, displayAnagrams);
    }

    private static boolean getChoiceToDisplayAnagrams() {
        Scanner console = new Scanner(System.in);
        System.out.print("Enter y or Y to display anagrams during tests: ");
        String response = console.nextLine();
        return response.length() > 0 && response.toLowerCase().charAt(0) == 'y';
    }

    public static boolean showTestResults(Object expected, Object actual, int testNum, String featureTested) {
        System.out.println("Test Number " + testNum + " testing " + featureTested);
        System.out.println("Expected result: " + expected);
        System.out.println("Actual result: " + actual);
        boolean passed = (actual == null && expected == null) || actual.equals(expected);
        if(passed)
            System.out.println("Passed test " + testNum);
        else
            System.out.println("!!! FAILED TEST !!! " + testNum);
        System.out.println();
        return passed;
    }

    /**
     * Method to run tests on Anagram solver itself.
     * pre: the files d5.txt and testCaseAnagrams.txt are in the local directory
     * 
     * assumed format for file is
     * <NUM_TESTS>
     * <TEST_NUM>
     * <MAX_WORDS>
     * <PHRASE>
     * <NUMBER OF ANAGRAMS>
     * <ANAGRAMS>
     */
    private static void runAnagramTests(AnagramSolver solver, boolean displayAnagrams) {
        int solverTestCases = 0;
        int solverTestCasesPassed = 0;
        try {
            Scanner sc = new Scanner(new File(testCaseFileName));
            final int NUM_TEST_CASES = Integer.parseInt(sc.nextLine().trim());
            System.out.println(NUM_TEST_CASES);
            for(int i = 0; i < NUM_TEST_CASES; i++) {
                // expected results
                TestCase currentTest = new TestCase(sc);
                solverTestCases++;

                // actual results
                List<List<String>> actualAnagrams = solver.getAnagrams(currentTest.phrase, currentTest.maxWords);

                if(displayAnagrams) {
                    displayAnagrams("actual anagrams", actualAnagrams);
                    displayAnagrams("expected anagrams", currentTest.anagrams);
                }


                if(checkPassOrFailTest(currentTest, actualAnagrams))
                    solverTestCasesPassed++;
            }
        }
        catch(Exception e) {
            System.out.println("\nProblem while running test cases on AnagramSolver. Check" +
                    " that file testCaseAnagrams.txt is in the correct location.");
            System.out.println(e);
            System.out.println("AnagramSolver test cases run: " + solverTestCases);
            System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
        }
        System.out.println("\nAnagramSolver test cases run: " + solverTestCases);
        System.out.println("AnagramSolver test cases failed: " + (solverTestCases - solverTestCasesPassed));
    }


    // print out all of the anagrams in a list of anagram
    private static void displayAnagrams(String type,
            List<List<String>> anagrams) {

        System.out.println("Results for " + type);
        System.out.println("num anagrams: " + anagrams.size());
        System.out.println("anagrams: ");
        for(List<String> singleAnagram : anagrams)
            System.out.println(singleAnagram);
    }


    // determine if the test passed or failed
    private static boolean checkPassOrFailTest(TestCase currentTest,
            List<List<String>> actualAnagrams) {
        
        if(actualAnagrams.equals(currentTest.anagrams)) {
            System.out.println("Passed test " + currentTest.testCaseNumber);
            return true;
        }
        else{
            System.out.println("\nFailed test case " + currentTest.testCaseNumber);
            System.out.println("Phrase: " + currentTest.phrase + ", max words: " + currentTest.maxWords + ". Recall 0 means no limit.");
            System.out.println("Expected number of anagrams: " + currentTest.anagrams.size());
            System.out.println("Actual number of anagrams: " + actualAnagrams.size());
            if(currentTest.anagrams.size() == actualAnagrams.size()) {
                System.out.println("Sizes the same, but either a difference in anagrams or anagrams not in correct order.");
            }
            System.out.println();
            return false;
        }  
    }

    // class to handle the parameters for an anagram test 
    // and the expected reuslt
    private static class TestCase {

        private int testCaseNumber;
        private String phrase;
        private int maxWords;
        private List<List<String>> anagrams;

        // pre: sc is positioned at the start of a test case
        private TestCase(Scanner sc) {
            testCaseNumber = Integer.parseInt(sc.nextLine().trim());
            maxWords = Integer.parseInt(sc.nextLine().trim());
            phrase = sc.nextLine().trim();
            anagrams = new ArrayList<List<String>>();
            readAndStoreAnagrams(sc);
        }

        // pre: sc is positioned at the start of the resulting angrams
        // read in the number of angrams and then for each anagram:
        //  - read in the line
        //  - break the line up into words
        //  - create a new list of Strings for the anagram
        //  - add each word to the anagram
        //  - add the anagram to the list of anagrams
        private void readAndStoreAnagrams(Scanner sc) {
            int numAnagrams = Integer.parseInt(sc.nextLine().trim());
            for(int j = 0; j < numAnagrams; j++){
                String[] words = sc.nextLine().split("\\s+");
                ArrayList<String> anagram = new ArrayList<String>();
                for(String st : words)
                    anagram.add(st);
                anagrams.add(anagram);
            }
            assert anagrams.size() == numAnagrams : "Wrong number of angrams read or expected";
        }
    }
}