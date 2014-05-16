package CodeCamp;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

//  CodeCamp.java - CS314 Assignment 1 - Tester class

/*  Student information for assignment:
 *
 *  Name: Andrew Baldwin
 *  email address: amb4437@gmail.com
 *  UTEID: AMB4437
 *  Section 5 digit ID: 90804
 *  Grader name:
 *  Number of slip days used on this assignment: 2
 */


/* CS314 Students: place results of shared birthdays experiments in this comment.
 * 
 * 1,000,000 experiments with 182 people: 
 * Average number of pairs of people with shared birthdays: 47.04
 * 
 * 50,000 experiments with number of people varying 2 to 100.  4,950,000 experiments total
 * 20 people required to have a 50% chance of there being at least 1 shared birthday, given 365 day per year
Num people:2, Number of experiments with one or more shared birthdays: 149, percentage:  0.298
Num people:3, Number of experiments with one or more shared birthdays: 144, percentage:  0.288
Num people:4, Number of experiments with one or more shared birthdays: 282, percentage:  0.564
Num people:5, Number of experiments with one or more shared birthdays: 309, percentage:  0.618
Num people:6, Number of experiments with one or more shared birthdays: 432, percentage:  0.864
Num people:7, Number of experiments with one or more shared birthdays: 565, percentage:  1.13
Num people:8, Number of experiments with one or more shared birthdays: 732, percentage:  1.464
Num people:9, Number of experiments with one or more shared birthdays: 857, percentage:  1.714
Num people:10, Number of experiments with one or more shared birthdays: 4130, percentage:  8.26
Num people:11, Number of experiments with one or more shared birthdays: 4334, percentage:  8.668
Num people:12, Number of experiments with one or more shared birthdays: 7813, percentage:  15.626
Num people:13, Number of experiments with one or more shared birthdays: 8191, percentage:  16.382
Num people:14, Number of experiments with one or more shared birthdays: 11229, percentage:  22.458
Num people:15, Number of experiments with one or more shared birthdays: 13791, percentage:  27.582
Num people:16, Number of experiments with one or more shared birthdays: 15689, percentage:  31.378
Num people:17, Number of experiments with one or more shared birthdays: 19591, percentage:  39.182
Num people:18, Number of experiments with one or more shared birthdays: 20733, percentage:  41.466
Num people:19, Number of experiments with one or more shared birthdays: 23582, percentage:  47.164
Num people:20, Number of experiments with one or more shared birthdays: 26089, percentage:  52.178
Num people:21, Number of experiments with one or more shared birthdays: 27939, percentage:  55.878
Num people:22, Number of experiments with one or more shared birthdays: 30328, percentage:  60.656
Num people:23, Number of experiments with one or more shared birthdays: 31041, percentage:  62.082
Num people:24, Number of experiments with one or more shared birthdays: 32959, percentage:  65.918
Num people:25, Number of experiments with one or more shared birthdays: 34825, percentage:  69.65
Num people:26, Number of experiments with one or more shared birthdays: 36617, percentage:  73.234
Num people:27, Number of experiments with one or more shared birthdays: 36626, percentage:  73.252
Num people:28, Number of experiments with one or more shared birthdays: 39036, percentage:  78.072
Num people:29, Number of experiments with one or more shared birthdays: 40029, percentage:  80.058
Num people:30, Number of experiments with one or more shared birthdays: 41286, percentage:  82.572
Num people:31, Number of experiments with one or more shared birthdays: 42160, percentage:  84.32
Num people:32, Number of experiments with one or more shared birthdays: 42942, percentage:  85.884
Num people:33, Number of experiments with one or more shared birthdays: 43635, percentage:  87.27
Num people:34, Number of experiments with one or more shared birthdays: 44462, percentage:  88.924
Num people:35, Number of experiments with one or more shared birthdays: 45053, percentage:  90.106
Num people:36, Number of experiments with one or more shared birthdays: 45041, percentage:  90.082
Num people:37, Number of experiments with one or more shared birthdays: 46139, percentage:  92.278
Num people:38, Number of experiments with one or more shared birthdays: 46905, percentage:  93.81
Num people:39, Number of experiments with one or more shared birthdays: 46759, percentage:  93.518
Num people:40, Number of experiments with one or more shared birthdays: 47432, percentage:  94.864
Num people:41, Number of experiments with one or more shared birthdays: 47587, percentage:  95.174
Num people:42, Number of experiments with one or more shared birthdays: 47951, percentage:  95.902
Num people:43, Number of experiments with one or more shared birthdays: 48450, percentage:  96.9
Num people:44, Number of experiments with one or more shared birthdays: 48670, percentage:  97.34
Num people:45, Number of experiments with one or more shared birthdays: 48707, percentage:  97.414
Num people:46, Number of experiments with one or more shared birthdays: 49104, percentage:  98.208
Num people:47, Number of experiments with one or more shared birthdays: 49153, percentage:  98.306
Num people:48, Number of experiments with one or more shared birthdays: 49238, percentage:  98.476
Num people:49, Number of experiments with one or more shared birthdays: 49452, percentage:  98.904
Num people:50, Number of experiments with one or more shared birthdays: 49421, percentage:  98.842
Num people:51, Number of experiments with one or more shared birthdays: 49481, percentage:  98.962
Num people:52, Number of experiments with one or more shared birthdays: 49714, percentage:  99.428
Num people:53, Number of experiments with one or more shared birthdays: 49719, percentage:  99.438
Num people:54, Number of experiments with one or more shared birthdays: 49674, percentage:  99.348
Num people:55, Number of experiments with one or more shared birthdays: 49855, percentage:  99.71
Num people:56, Number of experiments with one or more shared birthdays: 49735, percentage:  99.47
Num people:57, Number of experiments with one or more shared birthdays: 49838, percentage:  99.676
Num people:58, Number of experiments with one or more shared birthdays: 49855, percentage:  99.71
Num people:59, Number of experiments with one or more shared birthdays: 49917, percentage:  99.834
Num people:60, Number of experiments with one or more shared birthdays: 49949, percentage:  99.898
Num people:61, Number of experiments with one or more shared birthdays: 49950, percentage:  99.9
Num people:62, Number of experiments with one or more shared birthdays: 49946, percentage:  99.892
Num people:63, Number of experiments with one or more shared birthdays: 49922, percentage:  99.844
Num people:64, Number of experiments with one or more shared birthdays: 49952, percentage:  99.904
Num people:65, Number of experiments with one or more shared birthdays: 49973, percentage:  99.946
Num people:66, Number of experiments with one or more shared birthdays: 49969, percentage:  99.938
Num people:67, Number of experiments with one or more shared birthdays: 49976, percentage:  99.952
Num people:68, Number of experiments with one or more shared birthdays: 49980, percentage:  99.96
Num people:69, Number of experiments with one or more shared birthdays: 49987, percentage:  99.974
Num people:70, Number of experiments with one or more shared birthdays: 49981, percentage:  99.962
Num people:71, Number of experiments with one or more shared birthdays: 49979, percentage:  99.958
Num people:72, Number of experiments with one or more shared birthdays: 49991, percentage:  99.982
Num people:73, Number of experiments with one or more shared birthdays: 49982, percentage:  99.964
Num people:74, Number of experiments with one or more shared birthdays: 49987, percentage:  99.974
Num people:75, Number of experiments with one or more shared birthdays: 49995, percentage:  99.99
Num people:76, Number of experiments with one or more shared birthdays: 49993, percentage:  99.986
Num people:77, Number of experiments with one or more shared birthdays: 49996, percentage:  99.992
Num people:78, Number of experiments with one or more shared birthdays: 49993, percentage:  99.986
Num people:79, Number of experiments with one or more shared birthdays: 49994, percentage:  99.988
Num people:80, Number of experiments with one or more shared birthdays: 49995, percentage:  99.99
Num people:81, Number of experiments with one or more shared birthdays: 49998, percentage:  99.996
Num people:82, Number of experiments with one or more shared birthdays: 49998, percentage:  99.996
Num people:83, Number of experiments with one or more shared birthdays: 49998, percentage:  99.996
Num people:84, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:85, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:86, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:87, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:88, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:89, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:90, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:91, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:92, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:93, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:94, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:95, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:96, Number of experiments with one or more shared birthdays: 49999, percentage:  99.998
Num people:97, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:98, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:99, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0
Num people:100, Number of experiments with one or more shared birthdays: 50000, percentage:  100.0

*/

 
public class CodeCampTester {

    public static void main(String[] args){
        final String newline = System.getProperty("line.separator");
        
        
        // CS314 Students: add tests here.        
        
        
        //test 1, hamming distance
        int[] h1 = {9, 1, 14, 3, 2, 11, 11, 7, 6};
        int[] h2 = {10, 4, 16, 4, 2, 1, 0, -8, 1};
        int expected = 8;
        int actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println("Test 1 hamming distance: expected value: " + 
                expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 1, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 1, hamming distance");
        
        // test 2, hamming distance
        h1 = new int[] {1, 1, 1, 99, 98, 24, 43, -13, -15, 2};
        h2 = new int[] {0, 15, -3, -2, 33, 21, 14, -6, 4, -77};
        expected = 10;
        actual = CodeCamp.hammingDistance(h1, h2);
        System.out.println(newline + "Test 2 hamming distance: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 2, hamming distance");
        else
            System.out.println(" ***** FAILED ***** test 2, hamming distance");
        
        //test 3, isPermutation
        int[] a = {10, 44, 90};
        int[] b = {44, 10, 90};
        boolean expectedBool = true;
        boolean actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 3 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 3, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 4, isPermutation");

        //test 4, is Permutation
        b = new int[]{9, 9, 9, 10, 11};
        expectedBool = false;
        actualBool = CodeCamp.isPermutation(a, b);
        System.out.println(newline + "Test 4 isPermutation: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 4, isPermutation");
        else
            System.out.println(" ***** FAILED ***** test 5, isPermutation");
        
        
        // test 5, mostVowels
        String[] sList = {"ooooooooooo", "UUuuyst"};
        int expectedResult = 0;
        int actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 15 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 15, mostVowels");
        else
            System.out.println("***** FAILED ***** test 15, mostVowels");

        
        // test 6, mostVowels
        sList = new String[] {"magic", null, "", "test", "Hello There", "!*&^%$$@&&^^#@#$%^&*()#$%^&"};
        expectedResult = 4;
        actualResult = CodeCamp.mostVowels(sList);
        System.out.println(newline + "Test 6 mostVowels: expected result: " 
                + expectedResult + ", actual result: " + actualResult);
        if( actualResult == expectedResult )
            System.out.println("passed test 6, mostVowels");
        else
            System.out.println("***** FAILED ***** test 16, mostVowels");
// 
//        
        //test 7, sharedBirthdays, simple test
        int pairs = CodeCamp.sharedBirthdays(1, 365);
        System.out.println(newline + "Test 7 shared birthdays: expected: 0, actual: " + pairs);
        int expectedShared = 0;
        if( pairs == expectedShared )
            System.out.println("passed test 7, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 21, shared birthdays");
        
        //test 8, sharedBirthdays, simple test
        pairs = CodeCamp.sharedBirthdays(365, 365);
        System.out.println(newline + "Test 8 shared birthdays: expected: " +
                "a value of 1 or more, actual: " + pairs);
        if( pairs > 100 )
            System.out.println("passed test 8, shared birthdays");
        else
            System.out.println("***** FAILED ***** test 22, shared birthdays");        
 
        
        //test 9, queensAreASafe
        char[][] board = { {'.', 'q', '.'},
                          {'q', '.', 'q'},
                          {'.', '.', 'q'}};
        
        expectedBool = false;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 9 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 9, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 30, queensAreSafe");
//
        //test 10, queensAreASafe
        board = new char[][]{{'.', '.', '.', 'q'},
                             {'.', 'q', '.', '.'},
                             {'.', '.', '.', '.'},
                             {'.', '.', 'q', '.'}};
        expectedBool = true;
        actualBool = CodeCamp.queensAreSafe(board);
        System.out.println(newline + "Test 10 queensAreSafe: expected value: " 
                + expectedBool + ", actual value: " + actualBool);
        if ( expectedBool == actualBool )
            System.out.println(" passed test 10, queensAreSafe");
        else
            System.out.println(" ***** FAILED ***** test 31, queensAreSafe");
        
        
        // test 11, getValueOfMostValuablePlot
        int[][] city = {{0, -9, -3, 9, -1},
                          {9, 20, -2, -1, 0},
                          {-2, 8, -1, 1, 1},
                          {-4, 2, 2, 2, 1},
                          {10, 3, 6, -3, -6},
                          {-12, -4, 1, 5, 4}};
        
        expected = 53;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 12 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 12, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 34, getValueOfMostValuablePlot");
        
        // test 11, getValueOfMostValuablePlot
        city = new int [][]{{0, 11, 4, -9, -2},
                          {1, -2, 4, 90, 0},
                          {4, -8, -5, 5, 5},
                          {-1, -9, 9, -9, 1},
                          {14, 3, -6, -3, 6},
                          {-1, 14, 2, 4, -10}};
        
        expected = 112;
        actual = CodeCamp.getValueOfMostValuablePlot(city);
        System.out.println(newline + "Test 12 getValueOfMostValuablePlot: expected value: " 
                + expected + ", actual value: " + actual);
        if( expected == actual )          
            System.out.println(" passed test 12, getValueOfMostValuablePlot");
        else
            System.out.println(" ***** FAILED ***** test 34, getValueOfMostValuablePlot");
        
        
        
        
        // DELETE THE ABOVE TESTS IN THE VERSION OF THE FILE YOU TURN IN
        
    } // end of main method
    
    // pre: list != null
    private static int[] intListToArray(List<Integer> list) {
        if(list == null)
            throw new IllegalArgumentException("list parameter may not be null.");
        int[] result = new int[list.size()];
        int arrayIndex = 0;
        for(int x : list) {
            result[arrayIndex++] = x;
        }
        return result;
    }
}
