import java.util.Scanner;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.UIManager;

import java.util.ArrayList;
import java.util.TreeSet;
import java.util.HashSet;
import java.util.Set;
import java.util.Iterator;


/*
CS 314 Students, put your results to the experiments and answers to
questions here:

/*

Title: Child Verses
*Total number of words in text including duplicates: 6553
*Number of distinct words in this text: 2697
*File size: 39kb

*Set Type and Times             
SortedSet: 0.01805528 seconds.
UnsortedSet: 0.112119677 seconds. (620% SortedSet)
Java HashSet: 0.019004379 seconds. (17% UnsortedSet)
JavaTreeSet: 0.02043876 seconds. (100% HashSet)



Title: Project Trinity 1945-1946
*Total number of words in text including duplicates: 13900 
*Number of distinct words in this text: 3544
*File size: 86kb

*Set Type and Times             
SortedSet: 0.052200916 seconds.
UnsortedSet: 0.230173791 seconds. (440% SortedSet)
Java HashSet: 0.033624424 seconds. (14% UnsortedSet)
JavaTreeSet: 0.043458379 seconds. (129% HashSet)



Title: Book of Monsters
Total number of words in text including duplicates: 37950
Number of distinct words in this text 8025
*File size: 213kb

*Set Type and Times             
SortedSet: 0.132834209 seconds.
UnsortedSet:  0.958646064 seconds. (721% SortedSet)
Java HashSet: 0.095090022 seconds. (10% UnsortedSet)
JavaTreeSet: 0.103421003 seconds. (108% HashSet)



Title: The Glow
*Total number of words in text including duplicates: 92375
*Number of distinct words in this text 15492
*File size: 521kb

*Set Type and Times             
SortedSet: 0.324494829 seconds.
UnsortedSet: 3.547216746 seconds. (1009% SortedSet)
Java HashSet: 0.275337656 seconds. (7.7% UnsortedSet)
JavaTreeSet: 0.243808809 seconds. (88% HashSet)


*  What do you think the Big O of the two processText methods are for each kind of Set?
   
SortedSet: O(N)
UnsortedSet: O(N^3)
HashSet: O(log(N))
TreeSet: O(log(N))

*What are the orders (Big O) of your add methods? What do you think the Big O of the HashSet and TreeSet add methods are?
SortedSet: O(N)
UnsortedSet: O(N^3)
HashSet: O(N)
TreeSet: O(N^2)

*What are the differences between HashSet and TreeSet when printing out the contents of the Set?
* TreeSet is sorted, while HashSet is unsorted


CS314 Students, why is it unwise to implement all three of the
intersection, union, and difference methods in the AbstractSet class:
*Because we can implement the necessary helper methods in order to complete those three methods in AbstractSet

 */



public class SetTester {

    public static void main(String[] args){

    	
        System.out.println("UnsortedSet Tests:");
        System.out.println("");
        ISet<String> s1 = new UnsortedSet<String>();
        s1.add("X");
        s1.add("Y");
        s1.add("Z");

        /* Contains test */
        if(!s1.contains("A") )
            System.out.println("Passed test 1: add and contains methods SortedSet");
        else
            System.out.println("Failed test 1: add and contains methods SortedSet");
        
        if(s1.contains("Y") )
            System.out.println("Passed test 2: add and contains methods SortedSet");
        else
            System.out.println("Failed test 2: add and contains methods SortedSet");

        
        /* Remove test */
        s1.remove("Z");
        if(!s1.contains("Z") )
            System.out.println("Passed test 3: remove method UnsortedSet");
        else
            System.out.println("Failed test 3: remove method UnsortedSet");
        
        s1.remove("X");
        if(!s1.contains("X") )
            System.out.println("Passed test 4: remove method UnsortedSet");
        else
            System.out.println("Failed test 4: remove method UnsortedSet");

        /* Size test */
        if( s1.size() == 1 )
            System.out.println("Passed test 5: size method UnsortedSet");
        else
            System.out.println("Failed test 5: size method UnsortedSet");

        ISet<String> s2 = new UnsortedSet<String>();
        s2.add("J");
        s2.add("X");
        s2.add("Y");
        s1.add("X");
        s1.add("Z");
        
        /* Size test */
        if( s1.size() == 3 )
            System.out.println("Passed test 6: size method UnsortedSet");
        else
            System.out.println("Failed test 6: size method UnsortedSet");

        
        /* Contains all tests */
        //test 4
        s1.add("J");
        if( s1.containsAll(s2) )
            System.out.println("Passed test 7: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 7: containsAll method UnsortedSet");

        //test 5
        s1.remove("J");
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 8: containsAll method UnsortedSet");
        else
            System.out.println("Failed test 8: containsAll method UnsortedSet");

        /* Difference test */
        ISet<String> s3 = s2.difference(s1);
        ISet<String> expected = new UnsortedSet<String>();
        expected.add("J");
        expected.add("Z");
        if( s3.equals(expected))
            System.out.println("Passed test 9: difference and equals methods UnsortedSet");
        else
            System.out.println("Failed test 9: difference and equals methods UnsortedSet");
        
        s2.add("I");
        expected.add("I");
        s3 = s2.difference(s1);
        if( s3.equals(expected))
            System.out.println("Passed test 10: difference and equals methods UnsortedSet");
        else
            System.out.println("Failed test 10: difference and equals methods UnsortedSet");

        
        /* Union test */
        s3 = s2.union(s1);
        expected.clear(); //ADD THIS METHOD
        expected.add("J");
        expected.add("X");
        expected.add("Y");
        expected.add("Z");
        if( s3.equals(expected))
            System.out.println("Passed test 11: union and equals methods UnsortedSet");
        else
            System.out.println("Failed test 11: union and equals methods UnsortedSet");

        /* Intersection test */
        s3 = s2.intersection(s1);
        expected.remove("J");
        if( s3.equals(expected))
            System.out.println("Passed test 12: intersection and equals methods UnsortedSet");
        else
            System.out.println("Failed test 12: intersection and equals methods UnsortedSet");

        
        /* addAll test */
        expected.add("I");
        expected.add("J");
        s1.addAll(s2);
        if( s1.equals(expected))
            System.out.println("Passed test 13: addAll and equals methods UnsortedSet");
        else
            System.out.println("Failed test 13: addAll and equals methods UnsortedSet");
        
        
        /* Clear test */
        s1.clear();
        s3.remove("X");
        s3.remove("Y");
        s3.remove("Z");
        if(s1.equals(s3))
            System.out.println("Passed test 14: clear and equals methods UnsortedSet");
        else
            System.out.println("Failed test 14: clear and equals methods UnsortedSet");
        
        /* Iterator test */
        s1.add("X");
        Iterator<String> sit1 = s1.iterator();
        if(sit1.hasNext())
            System.out.println("Passed test 15: iterator method UnsortedSet");
        else
            System.out.println("Failed test 15: iterator method UnsortedSet");
        
        
        
        System.out.println("");
        System.out.println("Sorted Set Tests:");
        System.out.println("");
        
        
        //sorted sets
        s1 = new SortedSet<String>();
        s1.add("X");
        s1.add("Y");
        s1.add("Z");
        s1.add("K");

        //test 16
        if( s1.contains("K") )
            System.out.println("Passed test 16: add and contains methods SortedSet");
        else
            System.out.println("Failed test 16: add and contains methods SortedSet");

        //test 17
        s1.remove("K");
        if( !s1.contains("K") )
            System.out.println("Passed test 17: remove and contains methods SortedSet");
        else
            System.out.println("Failed test 17: remove and contains methods SortedSet");

        //test 18
        if( s1.size() == 3 )
            System.out.println("Passed test 18: size method SortedSet");
        else
            System.out.println("Failed test 18: size method SortedSet");

        s2 = new SortedSet<String>();
        s2.add("X");
        s2.add("Y");
        s2.add("Q");

        //test 19
        if( !s1.containsAll(s2) )
            System.out.println("Passed test 19: containsAll and hasItem method SortedSet");
        else
            System.out.println("Failed test 19: containsAll and hasItem method SortedSet");

        //test 20
        s2.remove("Q");
        if( s1.containsAll(s2) )
            System.out.println("Passed test 20: containsAll and hasItem method SortedSet");
        else
            System.out.println("Failed test 20: containsAll and hasItem method SortedSet");

        //test 21
        
        s3 = s2.difference(s1);
        expected = new SortedSet<String>();
        expected.add("Z");
        if( s3.equals(expected))
            System.out.println("Passed test 21: difference and equals methods SortedSet");
        else
            System.out.println("Failed test 21: difference and equals methods SortedSet");

        
        //test 22
        s3 = s1.union(s2);
        expected = new SortedSet<String>();
        expected.add("X");
        expected.add("Y");
        expected.add("Z");
        if( s3.equals(expected))
            System.out.println("Passed test 22: union and equals methods SortedSet");
        else
            System.out.println("Failed test 22: union and equals methods SortedSet");

        //test 23
        s3 = s1.intersection(s2);
        expected.remove("Z");
        if( s3.equals(expected))
            System.out.println("Passed test 23: intersection and equals methods SortedSet");
        else
            System.out.println("Failed test 23: intersection and equals methods SortedSet");

        // test 24
        
        s1.remove("Z");
        Iterator<String> it1 = s1.iterator();
        Iterator<String> it2 = s2.iterator();
        boolean good = true;
        while( good && it1.hasNext() )
            good = it1.next().equals(it2.next());
        if( good )
            System.out.println("Passed test 24: iterator and add methods SortedSet");
        else
            System.out.println("Failed test 24: iterator and add methods SortedSet");

        // test 25
        s1 = new SortedSet<String>();
        s1.add("X");
        s1.add("Y");
        s1.add("Z");
        ISet<String> ss2 = new SortedSet<String>();
        ss2.add("Z");
        ss2.add("Z");
        ss2.add("Y");
        ss2.add("X");
        ss2.add("X");
        if(s1.equals(ss2)) 
            System.out.println("Passed test 25: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 25: equals methods SortedSet - different types");

        
        // test 26
        ISet<Integer> ss3 = new SortedSet<Integer>();
        ss3.add(19);
        ss3.add(22);
        if(!ss3.equals(s1)) 
            System.out.println("Passed test 26: equals methods SortedSet - different types");
        else
            System.out.println("Failed test 26: equals methods SortedSet - different types");       
        
        
        /* Max test */
        
        //test 27
        SortedSet<String> set1 = new SortedSet();
        String compareSet;
        set1.add("X");
        set1.add("Y");
        set1.add("Z");
        compareSet = set1.max();
        if(compareSet == "Z") 
            System.out.println("Passed test 27: max method SortedSet");
        else
            System.out.println("Failed test 27: max method SortedSet");  
        
        //test 28
        set1.add("A");
        compareSet = set1.min();
        if(compareSet == "A") 
            System.out.println("Passed test 28: min method SortedSet");
        else
            System.out.println("Failed test 28: min method SortedSet");  
        
        //test 29
        s1.add("A");
        s2.remove("X");
        s2.remove("Y");
        s2.addAll(s1);
        expected.add("A");
        expected.add("Z");
        if(s2.equals(expected)) 
            System.out.println("Passed test 29: add all method SortedSet");
        else
            System.out.println("Failed test 29: add all method SortedSet");  
       
        //test 30
        if(s1.contains("A")) 
            System.out.println("Passed test 30: contains method SortedSet");
        else
            System.out.println("Failed test 30: contains method SortedSet");  
       
        
        //test 31
        s1.clear();
        s2.remove("X");
        s2.remove("Y");
        s2.remove("A");
        s2.remove("Z");
        if(s1.equals(s2)) 
            System.out.println("Passed test 31: clear method SortedSet");
        else
            System.out.println("Failed test 31: clear method SortedSet");  
        
        

        // CS314 Students. Uncomment this section when ready to 
        // run your experiments
//                try {
//                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
//                }
//                catch(Exception e) {
//                    System.out.println("Unable to change look and feel");
//                }
//        		Scanner sc = new Scanner(System.in);
//        		String response = "";
//        		do {
//        			largeTest();
//        			System.out.print("Another file? Enter y to do another file: ");
//        			response = sc.next();
//        		} while( response != null && response.length() > 0 
//                      && response.substring(0,1).equalsIgnoreCase("y") );
//
    }

    /*
     * Method asks user for file and compares run times to add words from file to
     * various sets, including CS314 UnsortedSet and SortedSet and Java's
     * TreeSet and HashSet.
     */
    private static void largeTest(){
        System.out.println();
        System.out.println("Opening Window to select file. You may have to minimize other windows.");
        String text = convertFileToString();
        System.out.println();
        System.out.println("***** CS314 SortedSet: *****");
        processTextCS314Sets(new SortedSet<String>(), text);
        System.out.println("****** CS314 UnsortedSet: *****");
        processTextCS314Sets(new UnsortedSet<String>(), text);
        System.out.println("***** Java HashSet ******");
        processTextJavaSets( new HashSet<String>(), text);
        System.out.println("***** Java TreeSet ******");
        processTextJavaSets( new TreeSet<String>(), text);
    }

    
    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for CS314 sets.
     */
    private static void processTextCS314Sets(ISet<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }


    /*
     * pre: set != null, text != null
     * Method to add all words in text to the given set. Words are delimited by
     * white space.
     * This version for Java Sets.
     */
    private static void processTextJavaSets(Set<String> set, String text){
        Stopwatch s = new Stopwatch();
        Scanner sc = new Scanner(text);
        int totalWords = 0;
        s.start();
        while( sc.hasNext() ){
            totalWords++;
            set.add(sc.next());
        }
        s.stop();
        sc.close();

        showResultsAndWords(set, s, totalWords, set.size());
    }

    
    /*
     * Show results of add words to given set.
     */
    private static <E> void showResultsAndWords(Iterable<E> set, Stopwatch s, 
            int totalWords, int setSize) {

        System.out.println("Time to add the elements in the text to this set: " + s.toString() );
        System.out.println("Total number of words in text including duplicates: " + totalWords);
        System.out.println("Number of distinct words in this text " + setSize);


        System.out.print("Enter y to see the contents of this set: ");
        Scanner sc = new Scanner(System.in);
        String response = sc.next();

        if( response != null && response.length() > 0 && response.substring(0,1).equalsIgnoreCase("y") ){
            for(Object o : set)
                System.out.println(o);
        }	
        System.out.println();
    }


    /*
     * Ask user to pick a file via a file choosing window and
     * convert that file to a String. Since we are evalutatin the file
     * with many sets convert to string once instead of reading through
     * file multiple times.
     */
    private static String convertFileToString() {
        //create a GUI window to pick the text to evaluate
        JFileChooser chooser = new JFileChooser(".");
        StringBuilder text = new StringBuilder();
        int retval = chooser.showOpenDialog(null);

        chooser.grabFocus();

        //read in the file
        if (retval == JFileChooser.APPROVE_OPTION) {
            File source = chooser.getSelectedFile();
            try {
                Scanner s = new Scanner( new FileReader( source ) );

                while( s.hasNextLine() ) {
                    text.append( s.nextLine() );
                    text.append(" ");
                }

                s.close();
            }
            catch(IOException e) {
                System.out.println("An error occured while trying to read from the file: " + e);
            }
        }

        return text.toString();
    }
}