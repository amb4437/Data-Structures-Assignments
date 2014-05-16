import java.util.Iterator;
import java.util.ArrayList;

/**
 * In this implementation of the ISet interface the elements in the Set are 
 * maintained in ascending order.
 * 
 * The data type for E must be a type that implements Comparable.
 * 
 * Students are to implement methods that were not implemented in AbstractSet 
 * and override methods that can be done more efficiently. An ArrayList must 
 * be used as the internal storage container. For methods involving two set, 
 * if that method can be done more efficiently if the other set is also a 
 * SortedSet do so.
 */
public class SortedSet<E extends Comparable<? super E>> extends AbstractSet<E> {

	private ArrayList<E> myCon;

	/**
	 * create an empty SortedSet
	 */
	public SortedSet() {
		myCon = new ArrayList<E>();

	}

	/* O(NlogN) */
	public SortedSet(ISet<E> other) {
		myCon = new ArrayList<E>();

		Iterator it = other.iterator();

		while(it.hasNext());
		myCon.add((E) it.next());
	}

	/* O(logN) */    
	@Override
	public boolean add(E item){    	

		int contains = this.hasItem((Comparable)item);   	
		if (contains == -1)
			return false;
		else
			myCon.add(contains, item);
		return false;


	}
	
	/* O(N^2) */
	public boolean addAll(ISet<E> otherSet){
		
		boolean status = false;		
		Iterator it = otherSet.iterator();
		while (it.hasNext()){
			Object currObj = it.next();			
			if (!this.contains((E)currObj)){
				this.add((E)currObj);
				status = true;
			}
				
		}
		
		
		return status;
	}

	/* O(N) OR O(N^2) */
	public ISet<E> intersection(ISet<E> otherSet)
	{

		// This method is the mirror of difference() with the
		// exception being that we do not need to determine the
		// the largest set and contains() uses the opposite
		// result.

		Iterator<E> it;
		ISet<E> ret = new SortedSet<E>();
		E examine;

		/* PRE */
		if(otherSet == null)
			return null;

		it = otherSet.iterator();

		while(it.hasNext())
		{
			examine = it.next();

			if(this.contains(examine) == true)
				ret.add(examine);
		}

		return ret;
	}
	/* O(N^2) Worst case, assuming the sets are equal. */
	@Override
	public boolean equals(Object other){
		Iterator it1 = this.iterator();
		Iterator it2 = ((ISet<E>) other).iterator();
		if(this.size() == ((ISet<E>)other).size()){
			while(it1.hasNext()){
				
				if(((Comparable<E>) it2.next()).compareTo((E) it1.next()) !=0)
					return false;
			}

		}
		else{
			return false;
		}
		return true;
	}


	/* O(N) if other set is Sorted, otherwise O(N^2) */
	public ISet<E> difference(ISet<E> otherSet)
	{
		Iterator<E> it;
		ISet<E> ret = new SortedSet<E>();
		E examine;

		/* Pre */
		if(otherSet == null)
			return null;


		if(this.size() > otherSet.size())
		{
			it = this.iterator();

			{
				examine = it.next();

				if(otherSet.contains(examine) == false)
					ret.add(examine);
			}

		}

		else if(this.size() < otherSet.size())
		{
			it = otherSet.iterator();

			while(it.hasNext())
			{
				examine = it.next();

				if(this.contains(examine) == false)
					ret.add(examine);
			}
		}

		return ret;
	}

	/* O(1) */
	public Iterator<E> iterator()
	{
		return myCon.iterator();
	}

	/* O(N) */
	public boolean remove(E item)
	{
		/* PRE */
		if(item == null)
			return false;

		for(int count = 0; count < myCon.size(); count++)
		{
			if(myCon.get(count).equals(item))
			{
				myCon.remove(count);
				return true;
			}
		}

		return false;	/* Item not found */
	}

	/* O(1) */
	public int size()
	{
		return myCon.size();
	}

	/* O(N) or O(N^2) */
	public ISet<E> union(ISet<E> otherSet)
	{
		ISet<E> ret = new SortedSet<E>();

		/* PRE */
		if(otherSet == null)
			return null;

		ret.addAll(this);
		ret.addAll(otherSet);

		return ret;
	}

	/**
	 * Return the smallest element in this SortedSet.
	 * <br> pre: size() != 0
	 * @return the smallest element in this SortedSet.
	 */
	    public E min() {
	    	return myCon.get(0);
	    }

	/**
	 * Return the largest element in this SortedSet.
	 * <br> pre: size() != 0
	 * @return the largest element in this SortedSet.
	 */
	    public E max() {	
	    	return myCon.get(myCon.size()-1);
	    }

	
	public boolean containsAll(ISet<E> otherSet){
		
		Iterator it = otherSet.iterator();
		while (it.hasNext()){
			if (this.hasItem((Comparable)it.next()) != -1){
				return false;
		}		
	}
		return true;
	}
	
	public boolean contains(E item){
		
		int result = hasItem((Comparable) item);
		
		if (result == -1)
			return true;
		else
			return false;
	}
	
	
	public void clear(){
		myCon.clear();
	}
	
	/* hasItem -- This subroutine performs a binary search for an item
	 * and returns either the position the item is located at or the
	 * position the item would be located at if we added it.
	 */
	    
	/* A modified version of binary search from class slides */
	
	public int hasItem(Comparable<E> target)
	{   
		boolean result = false;
		int low = 0;
		int high = this.size() - 1;
		int mid;
		while( !result && low <= high ){      
			mid = low + ((high - low) / 2);
			if( target.equals(myCon.get(mid)))
				result = true;
			else if(target.compareTo(myCon.get(mid)) > 0)
				low = mid + 1;
			else
				high = mid - 1;
		}
		if (result== false)
			return low;
		else
			return -1;
	}
}


