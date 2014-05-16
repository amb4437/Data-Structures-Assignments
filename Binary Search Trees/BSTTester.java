/*  Student information for assignment:

/*
 * Place results of experiments here:
Average time to insert 1000 elements: elapsed time: 0.002744628 seconds.
Average height of tree with 1000 elements: 19
Average size of tree with 1000 elements: 1000
Minimum possible tree height with 1000 elements: 10

Average time to insert 2000 elements: elapsed time: 0.004710944 seconds.
Average height of tree with 2000 elements: 22
Average size of tree with 2000 elements: 2000
Minimum possible tree height with 2000 elements: 11

Average time to insert 4000 elements: elapsed time: 0.005811222 seconds.
Average height of tree with 4000 elements: 26
Average size of tree with 4000 elements: 4000
Minimum possible tree height with 4000 elements: 12

Average time to insert 8000 elements: elapsed time: 0.009629998 seconds.
Average height of tree with 8000 elements: 27
Average size of tree with 8000 elements: 8000
Minimum possible tree height with 8000 elements: 13

Average time to insert 16000 elements: elapsed time: 0.024025999 seconds.
Average height of tree with 16000 elements: 32
Average size of tree with 16000 elements: 16000
Minimum possible tree height with 16000 elements: 14

Average time to insert 32000 elements: elapsed time: 0.027905436 seconds.
Average height of tree with 32000 elements: 36
Average size of tree with 32000 elements: 32000
Minimum possible tree height with 32000 elements: 15

Average time to insert 64000 elements: elapsed time: 0.074164701 seconds.
Average height of tree with 64000 elements: 38
Average size of tree with 64000 elements: 63999
Minimum possible tree height with 64000 elements: 16

Average time to insert 128000 elements: elapsed time: 0.231936055 seconds.
Average height of tree with 128000 elements: 42
Average size of tree with 128000 elements: 128000
Minimum possible tree height with 128000 elements: 17

Average time to insert 256000 elements: elapsed time: 0.446660087 seconds.
Average height of tree with 256000 elements: 45
Average size of tree with 256000 elements: 255987
Minimum possible tree height with 256000 elements: 18

Average time to insert 512000 elements: elapsed time: 1.029581049 seconds.
Average height of tree with 512000 elements: 49
Average size of tree with 512000 elements: 511971
Minimum possible tree height with 512000 elements: 19

Average time to insert 1024000 elements: elapsed time: 2.521224678 seconds.
Average height of tree with 1024000 elements: 54
Average size of tree with 1024000 elements: 1023878
Minimum possible tree height with 1024000 elements: 20



TreeSet tests:


Average time to insert 1000 elements: elapsed time: 0.007305789 seconds.
Average time to insert 2000 elements: elapsed time: 0.00266997 seconds.
Average time to insert 4000 elements: elapsed time: 0.002743695 seconds.
Average time to insert 8000 elements: elapsed time: 0.00586255 seconds.
Average time to insert 16000 elements: elapsed time: 0.006147651 seconds.
Average time to insert 32000 elements: elapsed time: 0.017516255 seconds.
Average time to insert 64000 elements: elapsed time: 0.05155441 seconds.
Average time to insert 128000 elements: elapsed time: 0.157405995 seconds.
Average time to insert 256000 elements: elapsed time: 0.421847375 seconds.
Average time to insert 512000 elements: elapsed time: 0.907701245 seconds.
Average time to insert 1024000 elements: elapsed time: 2.214462262 seconds.



How do these average times compare to your BinarySearchTree?
- The TreeSet is slightly faster

For each value of N what is the minimum possible tree height, assuming N unique values are inserted into the tree?
-Log(base2)(N) (Minimum for each value of N written above)



Adding numbers in sorted order tests:

Average time to insert 1000 elements: elapsed time: 0.017030043 seconds.
Average height of tree with 1000 elements: 999
Average size of tree with 1000 elements: 1000

Average time to insert 2000 elements: elapsed time: 0.044103971 seconds.
Average height of tree with 2000 elements: 1999
Average size of tree with 2000 elements: 2000

Average time to insert 4000 elements: elapsed time: 0.171454368 seconds.
Average height of tree with 4000 elements: 3999
Average size of tree with 4000 elements: 4000

Average time to insert 8000 elements: elapsed time: 0.65838477312 seconds.
Average height of tree with 8000 elements: 7999
Average size of tree with 8000 elements: 8000

Average time to insert 16000 elements: elapsed time: 2.13975051264
Average height of tree with 16000 elements: 15999
Average size of tree with 16000 elements: 16000

Average time to insert 16000 elements: elapsed time: 7.48912679424
Average height of tree with 16000 elements: 31999
Average size of tree with 16000 elements: 32000

Average time to insert 16000 elements: elapsed time: 28.2340080143
Average height of tree with 16000 elements: 63999
Average size of tree with 16000 elements: 64000

Predicted times for 128,000: 98.81902805 seconds

Predicted time for 256,000: 361.677642663 seconds
 
Predicted time for 512,000: 1363.52471284 seconds

Predicted time for 1,024,000: 4772.33649494 seconds


Adding numbers in sorted order using a TreeSet:
Average time to insert 1000 elements: elapsed time: 0.006057595 seconds.
Average time to insert 2000 elements: elapsed time: 0.001881858 seconds.
Average time to insert 4000 elements: elapsed time: 0.001500166 seconds.
Average time to insert 8000 elements: elapsed time: 0.003411421 seconds.
Average time to insert 16000 elements: elapsed time: 0.007584825 seconds.
Average time to insert 32000 elements: elapsed time: 0.013486569 seconds.
Average time to insert 64000 elements: elapsed time: 0.033729256 seconds.
Average time to insert 128000 elements: elapsed time: 0.088912064 seconds.
Average time to insert 256000 elements: elapsed time: 0.209104123 seconds.
Average time to insert 512000 elements: elapsed time: 0.391020464 seconds.
Average time to insert 1024000 elements: elapsed time: 0.867572833 seconds.

How do these times compare to the times it took for you BinarySearchTree class when inserting integers in sorted order? 
-The TreeSet is much faster

What do you think is the cause for these differences?
-Rather than having a binary tree, we instead have a one sided tree, which will be much faster when adding to the end.
 */

/**
 * Test class for binary search tree
 */

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class BSTTester {

    public static void main(String[] args) {
        BinarySearchTree<String> t = new BinarySearchTree<String>();

        //test 1
        t.add("apple");
        System.out.println("Test 1: Add and isPresent methods");
        showTestResults(t.isPresent("apple") == true, 1 );
        
        //test 2
        t.add("bee");
        System.out.println("Test 2: Add and isPresent methods");
        showTestResults(t.isPresent("bee") == true, 2 );

        
        //test 3
        t.remove("bee");
        System.out.println("Test 3: Remove and isPresent methods");
        showTestResults(t.isPresent("bee") == false, 3 );
        
        //test 4
        t.remove("apple");
        System.out.println("Test 4: Remove and isPresent methods");
        showTestResults(t.isPresent("apple") == false, 4 );
        
        //test 5
        System.out.println("Test 5: Size method");
        showTestResults(t.size() == 0, 5);
        
        //test 6
        t.add("apple");
        System.out.println("Test 6: Size method");
        showTestResults(t.size() == 1, 6);
        
        //test 7
        System.out.println("Test 7: Height method");
        showTestResults( t.height() == 0, 7 );
        
        //test 8
        t.remove("apple");
        System.out.println("Test 8: Height method");
        showTestResults( t.height() == -1, 8 );
        
        //test 9
        ArrayList<String> expected = new ArrayList<String>();
        System.out.println("Test 9: getAll method.");
        showTestResults( expected.equals( t.getAll() ) == true, 9 );
        
        
        //test 10
        t.add("apple");
        t.add("bee");
        t.add("cell");
        t.add("call");
        expected.add("apple");
        expected.add("bee");
        expected.add("call");
        expected.add("cell");

        System.out.println("Test 10: getAll method.");
        showTestResults( expected.equals( t.getAll() ) == true, 10 );
        
        //test 11  
        System.out.println("Test 11: Max method");    
        showTestResults( t.max().equals("cell"), 11);
        
        //test 12
        t.remove("cell");
        System.out.println("Test 12: Max method");    
        showTestResults( t.max().equals("call"), 12);
        
        //test 13
        System.out.println("Test 13: Min method");    
        showTestResults( t.min().equals("apple"), 13);
        
        //test 14
        t.remove("apple");
        System.out.println("Test 14: Min method");    
        showTestResults( t.min().equals("bee"), 14);
        
        //test 15
        t.iterativeAdd("apple");
        System.out.println("Test 15: IterativeAdd method");  
        showTestResults(t.isPresent("apple") == true, 15 );
        

        //test 16
        System.out.println("Test 16: IterativeAdd method");  
        showTestResults(t.iterativeAdd("apple") == false, 16 );
        
        //test 17:
        System.out.println("Test 17: Get method");  
        showTestResults(t.get(0) == ("apple"), 17);
        
        //test 18:
        System.out.println("Test 18: Get method");  
        showTestResults(t.get(1) == ("bee"), 18);
        
        
        BinarySearchTree<Integer> t2 = new BinarySearchTree();
        t2.add(100);
        t2.add(500);
        t2.add(50);
        t2.add(150);
        
        //test 19
        ArrayList<Integer> expectedList = new ArrayList();
        expectedList.add(50);
        expectedList.add(100);
        System.out.println("Test 19: Get All Less Than method");  
        showTestResults(t2.getAllLessThan(150).equals(expectedList), 19);
        
        //test 20
        t2.remove(50);
        t2.remove(100);
        expectedList.clear();
        System.out.println("Test 20: Get All Less Than method");  
        showTestResults(t2.getAllLessThan(150).equals(expectedList), 20);
        
        //test 21
        expectedList.add(500);
        System.out.println("Test 21: Get All Greater Than method");  
        showTestResults(t2.getAllGreaterThan(150).equals(expectedList), 21);
        
        //test 22
        t2.add(1000);
        expectedList.add(1000);
        System.out.println("Test 21: Get All Greater Than method");  
        showTestResults(t2.getAllGreaterThan(150).equals(expectedList), 22);
        
        //test 23
        System.out.println("Test 21: Num Nodes at Depth method");  
        showTestResults(t2.numNodesAtDepth(0) == 1, 23);
        
        //test 24
        t2.add(90);
        t2.add(400);
        t2.add(45);
        System.out.println("Test 21: Num Nodes at Depth method");  
        showTestResults(t2.numNodesAtDepth(2) == 2, 24);
    }

    private static void showTestResults(boolean passed, int testNum) {
        if( passed )
            System.out.println( "test " + testNum + " passed.");
        else
            System.out.println( "test " + testNum + " failed.");
    }
}