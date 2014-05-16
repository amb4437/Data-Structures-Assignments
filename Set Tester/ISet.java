import java.util.Iterator;
/**
 * An interface that models a set. Sets have no implied order and duplicate items are not allowed.
 */
public interface ISet extends Iterable{
    
    
	/**
     * Add an item to this set.
     * @param item the item to be added to this set.
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean add(Object item);
    
    
 	/**
     * A union operation. Add all items of otherSet that are not already present in this set
     * to this set.
     * @param otherSet != null
     * @return true if this set changed as a result of this operation, false otherwise.
     */
    public boolean addAll(ISet otherSet);
    
    
	/**
     * Make this set empty.
     * <br>pre: none
     * <br>post: size() = 0
     */
    public void clear();
    
    
	/**
     * Determine if item is in this set.
     * <br>pre: none
     * @param item determine if item is in this set.
     * @return true if this set contains the specified item, false otherwise.
     */
    public boolean contains(Object item);
    
    
	/**
     * Determine if all of the elements of otherSet are in this set.
     * @param otherSet != null
     * @return true if this set contains all of the elements in otherSet,
     * false otherwise.
     */
    public boolean containsAll(ISet otherSet);
    
    
	/**
     * Return an Iterator object for the elements of this set.
     * pre: none
     * @return an Iterator object for the elements of this set
     */
    public Iterator iterator();
    
    
	/**
     * Remove the specified item from this set if it is present.
     * pre: none
     * @param item the item to remove from the set
     * @return true if this set changed as a result of this operation, false otherwise
     */
    public boolean remove(Object item);
    
    
	/**
     * Return the number of elements of this set.
     * pre: none
     * @return the number of items in this set
     */
    public int size();
    
    
	/**
     * create a new set that is the intersection of this set and otherSet.
     * <br>pre: otherSet != null<br>
     * <br>post: returns a set that is the intersection of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the intersection of this set and otherSet
     */
    public ISet intersection(ISet otherSet);
    
    
	/**
     * Create a new set that is the union of this set and otherSet.
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the union of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the union of this set and otherSet
     */
    public ISet union(ISet otherSet);
    
    
	/**
     * Create a new set that is the difference of this set and otherSet. Return an ISet of
     * elements that are in this Set but not in otherSet. Also called
     * the relative complement.
     * <br>Example: If ISet A contains [X, Y, Z] and ISet B contains [W, Z] then
     * A.difference(B) would return an ISet with elements [X, Y] while
     * B.difference(A) would return an ISet with elements [W].
     * <br>pre: otherSet != null
     * <br>post: returns a set that is the difference of this set and otherSet.
     * Neither this set or otherSet are altered as a result of this operation.
     * @param otherSet != null
     * @return a set that is the difference of this set and otherSet
     */
    public ISet difference(ISet otherSet);
    
    
	/**
     * determine if two sets are equals. (They have exactly the same elements)
     * <br>pre: none
     * @param other the object to compare to this set
     * @return true if other is a Set and is equal to this set
     */
    public boolean equals(Object other);
}