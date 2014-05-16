import java.util.Iterator;

/**
 * Students are to complete this class. 
 * Students should implement as many methods
 * as they can using the Iterator from the iterator 
 * method and the other methods. 
 *
 */
public abstract class AbstractSet<E> implements ISet<E> {

	public boolean addAll(ISet<E> otherSet)
	{
		Iterator<E> it = otherSet.iterator();

		while(it.hasNext())
			this.add(it.next());

		return true;
	}

	public void clear()
	{
		Iterator<E> it = this.iterator();

		while(it.hasNext())
			this.remove(it.next());
	}

	public boolean equals(Object other){

		if (!(other instanceof SortedSet) && !(other instanceof UnsortedSet) && !(other instanceof AbstractSet))
			return false;
		ISet<E> otherSet = (ISet<E>)other;
		
		Iterator<E> it = this.iterator();
		if(this.size() == otherSet.size()){
			while(it.hasNext()){
				Iterator<E> it2 = otherSet.iterator();
				Comparable<Object> currObject = (Comparable<Object>) it.next();
				boolean result = false;
				while(it2.hasNext()){
					Object it2Obj = it2.next();
					if (currObject.getClass() != it2Obj.getClass()){
						return false;
					}
					if(currObject.compareTo(it2Obj) == 0){
						result = true;
					}
				}
				if (result == false)
					return false;
			}
		}
		return true;
	}

	public int size()
	{
		int count = 0;
		Iterator<E> it;

		for(it = this.iterator(); it.hasNext(); it.next())
			count++;

		return count;
	}

	public boolean contains(E item)
	{
		Iterator<E> it = this.iterator();
		while(it.hasNext())
			if(it.next().equals(item))
				return true;
		return false;
	}

	public boolean containsAll(ISet<E> otherSet)
	{
		Iterator<E> it = otherSet.iterator();
		while(it.hasNext())
			if(this.contains(it.next()) == false)
				return false;
		return true;
	}

    
    public String toString() {
        StringBuilder result = new StringBuilder();
        String seperator = ", ";
        result.append("(");

        Iterator<E> it = this.iterator();
        while (it.hasNext()) {
            result.append(it.next());
            result.append(seperator);
        }
        // get rid of extra separator
        if (this.size() > 0)
            result.setLength(result.length() - seperator.length());

        result.append(")");
        return result.toString();
    }
}
