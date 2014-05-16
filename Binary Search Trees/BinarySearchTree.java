import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

public class BinarySearchTree<E extends Comparable<? super E>> {
	
	private BSTNode<E> root;
	private int size;
	private int count;
	private BSTNode<E> getNode;

    // CS314 students. Add any other instance variables you want here

	/*Constructor Method (from class)*/
	public BinarySearchTree(){
		size = 0;
		root = null;
	}

    /**
     *  Add the specified item to this Binary Search Tree if it is not already present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Add value to this tree if not already present. Return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to add to the tree
     *  @return false if an item equivalent to value is already present
     *  in the tree, return true if value is added to the tree and size() = old size() + 1
     */
	/*Add method (from class) */
    public boolean add(E value) {
        int oldSize = size;
        root= addHelper(root, value);
        return oldSize != size;
    }

    /**
     *  Remove a specified item from this Binaray Search Tree if it is present.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: Remove value from the tree if present, return true if this tree
     *  changed as a result of this method call, false otherwise.
     *  @param value the value to remove from the tree if present
     *  @return false if value was not present
     *  returns true if value was present and size() = old size() - 1
     */
    
    /*Add Helper method (from class) */
    private BSTNode<E> addHelper (BSTNode<E> currentNode, E newValue){
    	if (currentNode == null){
    		size++;
    		return new BSTNode<E>(null, newValue, null);
    	}
    	int direction = newValue.compareTo(currentNode.data);
    	if (direction < 0)
    		currentNode.left = addHelper(currentNode.left, newValue);
    	else if (direction > 0)
    		currentNode.right = addHelper(currentNode.right, newValue);
    	return currentNode;
    }
    
    
    /* Code from class */
	public boolean remove (E val)
	{
		int oldsize = size;
		root = removeHelper(root, val);
		return oldsize != size;
	}
	
	private BSTNode<E> removeHelper (BSTNode<E> currentNode, E val)
	{
		int direction;
		if (currentNode == null)
			return null;
		
		direction = val.compareTo(currentNode.data);
		
		if (direction < 0){
			currentNode.left = removeHelper(currentNode.left, val);
		}
		else if (direction > 0){
			currentNode.right = removeHelper(currentNode.right, val);
		}
		else
		{
			if(currentNode.left == null && currentNode.right == null){
				size--;
				return null;
			}
			else if(currentNode.right == null){
				size--;
				return currentNode.left;
			}
			else if (currentNode.left == null){
				size--;
				return currentNode.right;
			}
			else{
				currentNode.data = maxHelper(currentNode);
				currentNode.left = removeHelper(currentNode.left, currentNode.data);
			}
		}
		return currentNode;


	}



    /**
     *  Check to see if the specified element is in this Binary Search Tree.
     *  <br>
     *  pre: <tt>value</tt> != null<br>
     *  post: return true if value is present in tree, false otherwise
     *  @param value the value to look for in the tree
     *  @returns true if value is present in this tree, false otherwise
     */
    public boolean isPresent(E value) {
        return isPresentHelper(root, value);
    }
    
    /* Code from class */
	private boolean isPresentHelper (BSTNode <E> currentNode, E val)
	{
		if (currentNode == null)
			return false;
		else
		{
			int direction = val.compareTo(currentNode.data);
			if (direction == 0)
				return true;
			else if (direction < 0)
				return true;
			else
				return isPresentHelper(currentNode.right, val);
		}
	}



    /**
     *  Return how many elements are in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the number of items in this tree
     *  @returns the number of items in this Binary Search Tree
     */
    public int size() {
        return size;
    }

    /**
     *  return the height of this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return the height of this tree.
     *  If the tree is empty return -1, otherwise return the
     *  height of the tree
     *  @returns the height of this tree or -1 if the tree is empty
     */
    public int height() {
        return heightHelper(root);
    }
    
    private int heightHelper(BSTNode<E> n){
    	if (n == null)
    		return -1;
    	return (1 + Math.max(heightHelper(n.left), heightHelper(n.right)));
    }

    /**
     *  Return a list of all the elements in this Binary Search Tree.
     *  <br>
     *  pre: none<br>
     *  post: return a List object with all data from the tree in ascending order. 
     *  If the tree is empty return an empty List
     *  @returns a List object with all data from the tree in sorted order
     *  if the tree is empty return an empty List
     */
    public List<E> getAll() {
        List<E> allList = new ArrayList<E>();
        getAllHelper(root, allList);
        return allList;
    }
    
    private void getAllHelper(BSTNode<E> currNode, List<E> allList){
    	if (currNode == null){
    		return;
    	}
    	else{
    		getAllHelper(currNode.getLeft(), allList);
    		allList.add((E)currNode.getData());
    		getAllHelper(currNode.getRight(), allList);
    	}
    }



    /**
     * return the maximum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the largest value in this Binary Search Tree
     * @return the maximum value in this tree
     */
    public E max(){
    	return maxHelper(root);
    }
    
    public E maxHelper(BSTNode currNode){
    	while (currNode.getRight()!= null){
    		currNode = currNode.getRight();
    	}
    	return (E) currNode.getData();
    }

    /**
     * return the minimum value in this binary search tree.
     * <br>
     * pre: <tt>size()</tt> > 0<br>
     * post: return the smallest value in this Binary Search Tree
     * @return the minimum value in this tree
     */
    public E min() {
    	BSTNode<E> currNode = root;
    	while (currNode.getLeft()!= null){
    		currNode = currNode.getLeft();
    	}
    	return currNode.getData();
    }

    /**
     * An add method that implements the add algorithm iteratively instead of recursively.
     * <br>pre: data != null
     * <br>post: if data is not present add it to the tree, otherwise do nothing.
     * @param data the item to be added to this tree
     * @return true if data was not present before this call to add, false otherwise.
     */
    /* Code from class */
    public boolean iterativeAdd(E data) {
    	if (size == 0){
    		size++;
    		root = new BSTNode<E>(null, data, null);
    		return true;
    	}
    	BSTNode<E> temp = root;
    	boolean done = false;
    	while (!done){
    		int direction = data.compareTo(temp.data);
    		if (direction == 0){
    			return false;
    		}
    		if (direction < 0 && temp.left == null){
    			temp.setLeft(new BSTNode<E>(data));
    			size++;
    			return true; 			
    		}
    		else if (direction > 0 && temp.right == null){
    			temp.setRight(new BSTNode<E>(data));
    			size++;
    			return true;
    		}
    		else if (direction < 0)
    			temp = temp.getLeft();
    		else if (direction > 0)
    			temp = temp.getRight();
    	}
    	return false;
    }


    /**
     * Return the "kth" largest element in this Binary Search Tree. If kth = 0 the 
     * smalles value (minimum) is returned. If kth = 1 the second smallest value is
     * returned, and so forth.
     * <br>pre: 0 <= kth < size()
     * @param kth indicates the rank of the element to get
     * @return the kth largest value in this Bianry Search Tree
     */
    public E get(int kth) {
  	
    	count = -1;
        getHelper(root, kth);
        return getNode.getData();
    }
    
    private BSTNode<E> getHelper(BSTNode<E> currNode, int kth){
    	if (currNode == null){
    		return currNode;
    	}
    	else{
    		getHelper(currNode.getLeft(), kth);
    		count++;
    		if (count == kth){
    			getNode = currNode;
    			return null;
    		}
    		getHelper(currNode.getRight(), kth);
    	}
    	return null;
    }


    /**
     * Return a List with all values in this Binary Search Tree that are less than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are less than the parameter value. If there are
     * no values in this tree less than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllLessThan(E value) {
            List<E> lessThanList = new ArrayList<E>();
        	getAllLessThanHelper(root, lessThanList, value);
        	return lessThanList;
        }
        
        private void getAllLessThanHelper(BSTNode<E> currNode, List<E> lessThanList, E value){
        	if (currNode == null){
        		return;
        	}
        	else{
        		getAllLessThanHelper(currNode.getLeft(), lessThanList, value);
        		if (currNode.getData().compareTo(value) < 0){
        			lessThanList.add((E)currNode.getData());
        		}
        		getAllLessThanHelper(currNode.getRight(), lessThanList, value);
        	}
        }


    /**
     * Return a List with all values in this Binary Search Tree that are greater than
     * the parameter <tt>value</tt>.
     * <tt>value</tt> != null<br>
     * @param value the cutoff value
     * @return a List with all values in this tree that are greater than the parameter value. If there are
     * no values in this tree greater than value return an empty list. The elements of the list are in ascending order.
     */
    public List<E> getAllGreaterThan(E value) {
        List<E> greaterThanList = new ArrayList<E>();
    	getAllGreaterThanHelper(root, greaterThanList, value);
    	return greaterThanList;
    }
    
    private void getAllGreaterThanHelper(BSTNode<E> currNode, List<E> allList, E value){
    	if (currNode == null){
    		return;
    	}
    	else{
    		getAllGreaterThanHelper(currNode.getLeft(), allList, value);
    		if (currNode.getData().compareTo(value) > 0){
    			allList.add((E)currNode.getData());
    		}
    		getAllGreaterThanHelper(currNode.getRight(), allList, value);
    	}
    }



    /**
     * Find the number of nodes in this tree at the specified depth.
     * <br>pre: none
     * @param d The target depth.
     * @return The number of nodes in this tree at a depth equal to
     * the parameter d.
     */
    public int numNodesAtDepth(int d) {
        return numNodesAtDepthHelper(root, d);
    }
    
    private int numNodesAtDepthHelper(BSTNode<E> currNode, int d){
		int count = 0;

		if(currNode != null){
			if(d == 0)
				count = 1;
			else{
				d--;
				count = count + numNodesAtDepthHelper(currNode.getLeft(), d);
				count = count + numNodesAtDepthHelper(currNode.getRight(), d);
			}
		}
		return count;
	}

    /**
     * Prints a vertical representation of this tree.
     * The tree has been rotated counter clockwise 90
     * degrees. The root is on the left. Each node is printed
     * out on its own row. A node's children wil not necessarily
     * be at the rows directly above and below a row. They will
     * be indented three spaces from the parent. Nodes indented the
     * same amount are at the same depth.
     * pre: none
     */
    public void printTree() {
        printTree(root, "");
    }

    private void printTree(BSTNode<E> n, String spaces) {
        if(n != null){
            printTree(n.getRight(), spaces + "  ");
            System.out.println(spaces + n.getData());
            printTree(n.getLeft(), spaces + "  ");
        }
    }

    private static class BSTNode<E extends Comparable<? super E>> {
        private E data;
        private BSTNode<E> left;
        private BSTNode<E> right;

        public BSTNode() {
            this(null);
        }

        public BSTNode(E initValue) {
            this(null, initValue, null); 
        }

        public BSTNode(BSTNode<E> initLeft,
                E initValue,        
                BSTNode<E> initRight) {
            data = initValue; 
            left = initLeft; 
            right = initRight; 
        }

        public E getData() { 
            return data; 
        }

        public BSTNode<E> getLeft() { 
            return left;
        }

        public BSTNode<E> getRight() { 
            return right; 
        }

        public void setData(E theNewValue) { 
            data = theNewValue; 
        }

        public void setLeft(BSTNode<E> theNewLeft) { 
            left = theNewLeft; 
        }

        public void setRight(BSTNode<E> theNewRight) { 
            right = theNewRight; 
        }    
    }
  
    
    
}