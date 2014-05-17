interface Functor { Object fn(Object x); }

interface Predicate { boolean pred(Object x); }

public class Cons
{
	// instance variables
	private Object car;
	private Cons cdr;
	private Cons(Object first, Cons rest)
	{ car = first;
	cdr = rest; }
	public static Cons cons(Object first, Cons rest)
	{ return new Cons(first, rest); }
	public static boolean consp (Object x)
	{ return ( (x != null) && (x instanceof Cons) ); }
	// safe car, returns null if lst is null
	public static Object first(Cons lst) {
		return ( (lst == null) ? null : lst.car  ); }
	// safe cdr, returns null if lst is null
	public static Cons rest(Cons lst) {
		return ( (lst == null) ? null : lst.cdr  ); }
	public static Object second (Cons x) { return first(rest(x)); }
	public static Object third (Cons x) { return first(rest(rest(x))); }
	public static void setfirst (Cons x, Object i) { x.car = i; }
	public static void setrest  (Cons x, Cons y) { x.cdr = y; }
	public static Cons list(Object ... elements) {
		Cons list = null;
		for (int i = elements.length-1; i >= 0; i--) {
			list = cons(elements[i], list);
		}
		return list;
	}

	// convert a list to a string for printing
	public String toString() {
		return ( "(" + toStringb(this) ); }
	public static String toString(Cons lst) {
		return ( "(" + toStringb(lst) ); }
	private static String toStringb(Cons lst) {
		return ( (lst == null) ?  ")"
				: ( first(lst) == null ? "()" : first(lst).toString() )
				+ ((rest(lst) == null) ? ")" 
						: " " + toStringb(rest(lst)) ) ); }

	public static int square(int x) { return x*x; }

	// iterative destructive merge using compareTo
	public static Cons dmerj (Cons x, Cons y) {
		if ( x == null ) return y;
		else if ( y == null ) return x;
		else { Cons front = x;
		if ( ((Comparable) first(x)).compareTo(first(y)) < 0)
			x = rest(x);
		else { front = y;
		y = rest(y); };
		Cons end = front;
		while ( x != null )
		{ if ( y == null ||
		((Comparable) first(x)).compareTo(first(y)) < 0)
		{ setrest(end, x);
		x = rest(x); }
		else { setrest(end, y);
		y = rest(y); };
		end = rest(end); }
		setrest(end, y);
		return front; } }

	public static Cons midpoint (Cons lst) {
		Cons current = lst;
		Cons prev = current;
		while ( lst != null && rest(lst) != null) {
			lst = rest(rest(lst));
			prev = current;
			current = rest(current); };
			return prev; }

	// Destructive merge sort of a linked list, Ascending order.
	// Assumes that each list element implements the Comparable interface.
	// This function will rearrange the order (but not location)
	// of list elements.  Therefore, you must save the result of
	// this function as the pointer to the new head of the list, e.g.
	//    mylist = llmergesort(mylist);
	public static Cons llmergesort (Cons lst) {
		if ( lst == null || rest(lst) == null)
			return lst;
		else { Cons mid = midpoint(lst);
		Cons half = rest(mid);
		setrest(mid, null);
		return dmerj( llmergesort(lst),
				llmergesort(half)); } }


	// ****** your code starts here ******
	// add other functions as you wish.



	public static Cons union (Cons x, Cons y) {

		Cons xlist = llmergesort(x);
		Cons ylist = llmergesort(y);
		xlist = mergeunion(xlist, ylist);
		return dmerj(xlist, ylist);

	}

	// following is a helper function for union

	/*Code taken from class slides */
	public static Cons mergeunion (Cons x, Cons y) {
		if ( x == null )
			return null;
		else if ( y == null )
			return null;
		else if ( ((Comparable) first(x)).equals(first(y)))
			return mergeunion(rest(x),rest(y));
		else if ( ((Comparable) first(x)).compareTo(first(y)) < 0)
			return cons(first(x), mergeunion(rest(x), y));
		else 
			return cons(first(y),mergeunion(x, rest(y))); 
	}


	public static Cons setDifference (Cons x, Cons y) {
		Cons xlist = llmergesort(x);
		Cons ylist = llmergesort(y);
		return mergediff(xlist, ylist);
	}

	// following is a helper function for setDifference
	public static Cons mergediff (Cons x, Cons y) {
		if ( x == null )
			return null;
		else if ( y == null )
			return null;
		else if ( ((Comparable) first(x)).compareTo(first(y)) == 0)
			return mergeunion(rest(x), rest(y));
		else if ( ((Comparable) first(x)).compareTo(first(y)) < 0  || ((Comparable) first(x)).compareTo(first(y)) > 0)
			return cons(first(x),mergediff(rest(x), y));
		else 
			return cons(first(y),mergediff(x, rest(y))); 
	}


	public static Cons bank(Cons accounts, Cons updates) {

		updates = llmergesort(updates);  //Sort updates before doing anything else
		Cons returnAccounts = applyUpdates(accounts, updates);
		return llmergesort(returnAccounts);
	}

	public static Cons applyUpdates(Cons accounts, Cons updates){

		Cons updatedAccounts = accounts;
		Cons newUpdates = updates;
		Account currentAccount = (Account) first(updatedAccounts);
		Account currentUpdate = (Account) first(newUpdates);
		boolean status = false;

		while (updatedAccounts != null  && currentUpdate != null){

			/* Do a check to see if the update is a new account */
			if ((currentUpdate.name().compareTo(currentAccount.name())) < 0){
				status = true;
				newUpdates = rest(newUpdates);
				currentAccount = currentUpdate;
				currentUpdate = (Account) first(newUpdates);
			}

			/* If update name isn't the same as account name, move to next account */
			if (!currentAccount.name().equals(currentUpdate.name())){

				/*Make sure new account amounts are > 0 */
				if (status == true){
					if (currentAccount.amount() > 0){
						accounts = cons(currentAccount, accounts);
						System.out.println("New account created for " + currentAccount.name() + " : " + currentAccount.amount());
					}
					else
						System.out.println("No account created for " + currentAccount.name() + " : " + currentAccount.amount());
					status = false;
				}

				updatedAccounts = rest(updatedAccounts);
				currentAccount = (Account) first(updatedAccounts);
			}

			/* Apply the updated amount */
			else{
				currentAccount.setAmount(currentAccount.amount() + currentUpdate.amount());  //Set new amount

				/* Check for and apply an overdraft fee */
				if (currentAccount.amount() < 0 && status == false){
					currentAccount.setAmount(currentAccount.amount() - 30);
					System.out.println("Account with name: " + currentAccount.name() + " has occured an overdraft fee. Balance after overdraft fee: " + currentAccount.amount());
				}

				newUpdates = rest(newUpdates);
				currentUpdate = (Account) first(newUpdates);
			}		
		}

		/* Add any new accounts that were at the end of the updates cons */
		if (newUpdates != null){
			while (newUpdates != null){
				currentUpdate = (Account) first(newUpdates);
				if (currentUpdate.amount() > 0)
					accounts = cons(currentUpdate, accounts);
				newUpdates = rest(newUpdates);
			}
		}

		return accounts;
	}



	public static String [] mergearr(String [] x, String [] y) {

		String [] outputString = new String[((x.length + y.length))];

		int i = 0, k = 0, j = 0;

		while (i < x.length && k< y.length){
			if (x[i].compareTo(y[k]) <= 0){
				outputString[j] = x[i];
				i++;
				j++;}
			else{
				outputString[j] = y[k];
				k++;
				j++;}}

		while (i < x.length){
			outputString[j] = x[i];
			i++;
			j++;}

		while (k < y.length){
			outputString[j] = y[k];
			k++;
			j++;}

		return outputString;
	}


	public static boolean markup(Cons text) {

		int position = 0;
		String currentSub;
		String firstSub;
		String firstTag;
		Cons stack = null;

		while (text != null){
			String current = (String) first(text);

			/*We've reached a tag */
			if (current.length() > 0 && current.charAt(0) == '<'){

				/*If we reach a closing tag, pop from the stack */
				if (current.charAt(1) == '/'){

					firstTag = (String) first(stack);
					stack = rest(stack);

					if (firstTag == null)
						return false;

					/*Create substrings of the tags and compare them */
					currentSub = current.substring(2);
					firstSub = firstTag.substring(1);		
					if (!currentSub.equals(firstSub)){
						System.out.println("Offending tag: '" + current + "' at position " + position + ". Expected </" + firstSub);
						return false;
					}
				}
				/*If it's an opening tag, push it onto the stack */
				else
					stack = cons(current, stack);		
			}
			text = rest(text);
			position++;
		}

		/*If there are items left on the stack, return false */
		if (stack!= null){
			System.out.println("List is not well formed: Openening tags left without closing tags");
			return false;
		}
		return true;

	}

	// ****** your code ends here ******

	public static void main( String[] args )
	{ 
		
		Cons set1 = list("d", "b", "c", "a");
		Cons set2 = list("f", "d", "b", "g", "h");
		System.out.println("set1 = " + Cons.toString(set1));
		System.out.println("set2 = " + Cons.toString(set2));
		System.out.println("union = " + Cons.toString(union(set1, set2)));


		Cons set3 = list("d", "b", "c", "a");
		Cons set4 = list("f", "d", "b", "g", "h");
		System.out.println("set3 = " + Cons.toString(set3));
		System.out.println("set4 = " + Cons.toString(set4));
		System.out.println("difference = " +
				Cons.toString(setDifference(set3, set4)));
		
		Cons accounts = list(
				new Account("Arbiter", new Integer(498)),
				new Account("Flintstone", new Integer(102)),
				new Account("Foonly", new Integer(123)),
				new Account("Kenobi", new Integer(373)),
				new Account("Rubble", new Integer(514)),
				new Account("Tirebiter", new Integer(752)),
				new Account("Vader", new Integer(1024)) );

		Cons updates = list(
				new Account("Foonly", new Integer(100)),
				new Account("Flintstone", new Integer(-10)),
				new Account("Arbiter", new Integer(-600)),
				new Account("Garble", new Integer(-100)),
				new Account("Rabble", new Integer(100)),
				new Account("Flintstone", new Integer(-20)),
				new Account("Foonly", new Integer(10)),
				new Account("Tirebiter", new Integer(-200)),
				new Account("Flintstone", new Integer(10)),
				new Account("Flintstone", new Integer(-120))) ;
		System.out.println("accounts = " + accounts.toString());
		System.out.println("updates = " + updates.toString());
		Cons newaccounts = bank(accounts, updates);
		System.out.println("result = " + newaccounts.toString());

		String[] arra = {"a", "big", "dog", "hippo"};
		String[] arrb = {"canary", "cat", "fox", "turtle"};
		String[] resarr = mergearr(arra, arrb);
		for ( int i = 0; i < resarr.length; i++ )
			System.out.println(resarr[i]);



		Cons xmla = list( "<TT>", "foo", "</TT>");
		Cons xmlb = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
				"<TD>", "bar", "</TD>", "</TR>",
				"<TR>", "<TD>", "fum", "</TD>", "<TD>",
				"baz", "</TD>", "</TR>", "</TABLE>" );
		Cons xmlc = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
				"<TD>", "bar", "</TD>", "</TR>",
				"<TR>", "<TD>", "fum", "</TD>", "<TD>",
				"baz", "</TD>", "</WHAT>", "</TABLE>" );
		Cons xmld = list( "<TABLE>", "<TR>", "<TD>", "foo", "</TD>",
				"<TD>", "bar", "</TD>", "", "</TR>",
				"</TABLE>", "</NOW>" );
		Cons xmle = list( "<THIS>", "<CANT>", "<BE>", "foo", "<RIGHT>" );
		Cons xmlf = list( "<CATALOG>",
				"<CD>",
				"<TITLE>", "Empire", "Burlesque", "</TITLE>",
				"<ARTIST>", "Bob", "Dylan", "</ARTIST>",
				"<COUNTRY>", "USA", "</COUNTRY>",
				"<COMPANY>", "Columbia", "</COMPANY>",
				"<PRICE>", "10.90", "</PRICE>",
				"<YEAR>", "1985", "</YEAR>",
				"</CD>",
				"<CD>",
				"<TITLE>", "Hide", "your", "heart", "</TITLE>",
				"<ARTIST>", "Bonnie", "Tyler", "</ARTIST>",
				"<COUNTRY>", "UK", "</COUNTRY>",
				"<COMPANY>", "CBS", "Records", "</COMPANY>",
				"<PRICE>", "9.90", "</PRICE>",
				"<YEAR>", "1988", "</YEAR>",
				"</CD>", "</CATALOG>");
		System.out.println("xmla = " + xmla.toString());
		System.out.println("result = " + markup(xmla));
		System.out.println("xmlb = " + xmlb.toString());
		System.out.println("result = " + markup(xmlb));
		System.out.println("xmlc = " + xmlc.toString());
		System.out.println("result = " + markup(xmlc));
		System.out.println("xmld = " + xmld.toString());
		System.out.println("result = " + markup(xmld));
		System.out.println("xmle = " + xmle.toString());
		System.out.println("result = " + markup(xmle));
		System.out.println("xmlf = " + xmlf.toString());
		System.out.println("result = " + markup(xmlf));
	}

}