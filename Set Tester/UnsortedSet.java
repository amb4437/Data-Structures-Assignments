import java.util.Iterator;
import java.util.ArrayList;

/**
 * A simple implementation of an ISet. 
 * Elements are not in any particular order.
 * Students are to implement methods that 
 * were not implemented in AbstractSet and override
 * methods that can be done more efficiently.
 * An ArrayList must be used as the internal storage container.
 *
 */
public class UnsortedSet<E> extends AbstractSet<E> {
	private ArrayList<E> myCon;
	
	public UnsortedSet(){
		myCon = new ArrayList<E>();
	}

	@Override
	//O(N)
	public boolean add(E item) {
		if(!myCon.contains(item)){
			myCon.add(item);
			return true;
		}
		else
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

	
	@Override
	//O(N^2)
	public ISet<E> difference(ISet<E> otherSet) {
		ISet<E> returnSet = new UnsortedSet<E>();
		E currObject;
		Iterator<E> it = myCon.iterator();
		while (it.hasNext()){
			currObject = (E) it.next();
			if (!otherSet.contains(currObject)){
				returnSet.add(currObject);
			}
		}
		
		it = otherSet.iterator();
		while (it.hasNext()){
			currObject = (E) it.next();
			if (!this.contains(currObject)){
				returnSet.add(currObject);
			}
		}
		return returnSet;
	}

	@Override
	//O(N^2)
	public ISet<E> intersection(ISet<E> otherSet) {
		ISet<E> returnSet = new UnsortedSet<E>();
		E currObject;
		Iterator<E> it = myCon.iterator();
		while (it.hasNext()){
			currObject = (E) it.next();
			if (otherSet.contains(currObject)){
				returnSet.add(currObject);
			}
		}
		return returnSet;
	}
	//O(N^2)
	public boolean containsAll(ISet otherSet) {
		Object currObject;
		Iterator it = otherSet.iterator();
		boolean doesContain = true;
		
		while(it.hasNext()){
			currObject = it.next();
			if(!this.contains((E) currObject)){
				return false;
			}
		}
		return true;
	}
	
	public int size(){
		return myCon.size();
	}

	@Override
	//O(1)
	public Iterator<E> iterator() {
		Iterator<E> it = myCon.iterator();
		return it;
	}

	@Override
	//O(N)
	public boolean remove(E item) {
		return (myCon.remove(item));
	}

	@Override
	//O(N^2)
	public ISet<E> union(ISet<E> otherSet) {
		ISet<E> currObject = this;
		currObject.addAll(otherSet);
		return currObject;
	}
	
	@Override
	//O(1)
	public void clear(){
		myCon.clear();
	}
	//O(N^2) worst case
	

}
