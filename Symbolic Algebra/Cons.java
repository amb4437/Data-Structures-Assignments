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
	// access functions for expression representation
	public static Object op  (Cons x) { return first(x); }
	public static Object lhs (Cons x) { return first(rest(x)); }
	public static Object rhs (Cons x) { return first(rest(rest(x))); }
	public static boolean numberp (Object x)
	{ return ( (x != null) &&
			(x instanceof Integer || x instanceof Double) ); }
	public static boolean integerp (Object x)
	{ return ( (x != null) && (x instanceof Integer ) ); }
	public static boolean floatp (Object x)
	{ return ( (x != null) && (x instanceof Double ) ); }
	public static boolean stringp (Object x)
	{ return ( (x != null) && (x instanceof String ) ); }

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

	public boolean equals(Object other) { return equal(this,other); }

	// tree equality
	public static boolean equal(Object tree, Object other) {
		if ( tree == other ) return true;
		if ( consp(tree) )
			return ( consp(other) &&
					equal(first((Cons) tree), first((Cons) other)) &&
					equal(rest((Cons) tree), rest((Cons) other)) );
		return eql(tree, other); }

	// simple equality test
	public static boolean eql(Object tree, Object other) {
		return ( (tree == other) ||
				( (tree != null) && (other != null) &&
						tree.equals(other) ) ); }

	// member returns null if requested item not found
	public static Cons member (Object item, Cons lst) {
		if ( lst == null )
			return null;
		else if ( item.equals(first(lst)) )
			return lst;
		else return member(item, rest(lst)); }

	public static Cons union (Cons x, Cons y) {
		if ( x == null ) return y;
		if ( member(first(x), y) != null )
			return union(rest(x), y);
		else return cons(first(x), union(rest(x), y)); }

	public static boolean subsetp (Cons x, Cons y) {
		return ( (x == null) ? true
				: ( ( member(first(x), y) != null ) &&
						subsetp(rest(x), y) ) ); }

	public static boolean setEqual (Cons x, Cons y) {
		return ( subsetp(x, y) && subsetp(y, x) ); }

	// combine two lists: (append '(a b) '(c d e))  =  (a b c d e)
	public static Cons append (Cons x, Cons y) {
		if (x == null)
			return y;
		else return cons(first(x),
				append(rest(x), y)); }

	// look up key in an association list
	// (assoc 'two '((one 1) (two 2) (three 3)))  =  (two 2)
	public static Cons assoc(Object key, Cons lst) {
		if ( lst == null )
			return null;
		else if ( key.equals(first((Cons) first(lst))) )
			return ((Cons) first(lst));
		else return assoc(key, rest(lst)); }

	public static int square(int x) { return x*x; }
	public static int pow (int x, int n) {
		if ( n <= 0 ) return 1;
		if ( (n & 1) == 0 )
			return square( pow(x, n / 2) );
		else return x * pow(x, n - 1); }

	public static Cons formulas = 
			list( list( "=", "s", list("*", new Double(0.5),
					list("*", "a",
							list("expt", "t", new Integer(2))))),
							list( "=", "s", list("+", "s0", list( "*", "v", "t"))),
							list( "=", "a", list("/", "f", "m")),
							list( "=", "v", list("*", "a", "t")),
							list( "=", "f", list("/", list("*", "m", "v"), "t")),
							list( "=", "f", list("/", list("*", "m",
									list("expt", "v", new Integer(2))),
									"r")),
									list( "=", "h", list("-", "h0", list("*", new Double(4.94),
											list("expt", "t",
													new Integer(2))))),
													list( "=", "c", list("sqrt", list("+",
															list("expt", "a",
																	new Integer(2)),
																	list("expt", "b",
																			new Integer(2))))),
																			list( "=", "v", list("*", "v0",
																					list("-", new Double(1.0),
																							list("exp", list("/", list("-", "t"),
																									list("*", "r", "c"))))))
					);

	// Note: this list will handle most, but not all, cases.
	// The binary operators - and / have special cases.
	public static Cons opposites = 
			list( list( "+", "-"), list( "-", "+"), list( "*", "/"),
					list( "/", "*"), list( "sqrt", "expt"), list( "expt", "sqrt"),
					list( "log", "exp"), list( "exp", "log") );

	public static void printanswer(String str, Object answer) {
		System.out.println(str +
				((answer == null) ? "null" : answer.toString())); }

	// ****** your code starts here ******

	/*Reverse function from class notes*/
	public static Cons trrev (Cons lst) {
		return trrevb(lst, null); }

	public static Cons trrevb (Cons in, Cons out) {
		if ( in == null )
			return out;
		else return trrevb( rest(in),
				cons(first(in), out) ); }



	public static Cons findpath(Object item, Object cave) {
		return trrev((findpathRecurr(item, cave, list())));
	}

	public static Cons findpathRecurr(Object item, Object cave, Cons list){

		/*Base case.  Found our item */
		if(item.equals(cave)) 
			return cons("done", list);
		/*Combine a call on the first with a call on the rest*/
		if(consp(cave))
			return (append(findpathRecurr(item, first((Cons)cave), cons("first", list)), findpathRecurr(item, rest((Cons)cave), cons("rest", list))));
		/*Return null if we haven't found a match*/
		else
			return null;
	}

	public static Object follow(Cons path, Object cave) {

		String current = (String)first(path);
		if (current.equals("done"))
			return (cave);
		else if (current.equals("first")){
			return follow(rest(path), first((Cons)cave));
		}
		else
			return follow(rest(path), rest((Cons)cave));

	}

	public static Object corresp(Object item, Object tree1, Object tree2) {

		Cons path = findpath(item, tree1);
		return follow(path, tree2);
	}




	public static Cons solve(Cons e, String v) {
		return solveRecurr(e, v, null);
	}

	public static Cons solveRecurr(Cons e, String v, Cons stack){
		Cons answer = null;
		
		/*1st condition : lhs is e*/
		if(stack == null && lhs(e).equals(v)) 
			return e;
		
		/*2nd condition : rhs is v*/
		if(rhs(e) == null && lhs(e).equals(v))
			answer = list("=", v, solveRecurrB(cons(list(op(e), lhs(e), "lhs"), stack),v));
		else if(lhs(e).equals(v))
			answer = list("=", v, solveRecurrB(cons(list(op(e), rhs(e), "right"), stack), v));
		else if(rhs(e) != null && rhs(e).equals(v)) 
			answer = list("=", v, solveRecurrB(cons(list(op(e), lhs(e), "left"), stack), v));
		if(answer != null)
			return answer;

		if(rhs(e) == null && consp(lhs(e))){
			answer = solveRecurr((Cons)lhs(e), v, cons(list(op(e), lhs(e), "lhs"), stack));
			if(answer != null)
				return answer;
		}
		if(consp(lhs(e))){
			answer = solveRecurr((Cons)lhs(e), v, cons(list(op(e), rhs(e), "right"), stack));
			if(answer != null)
				return answer;
		}
		if(consp(rhs(e))) {
			answer = solveRecurr((Cons)rhs(e), v, cons(list(op(e), lhs(e), "left"), stack));
			if(answer != null)
				return answer;
		}
		return null;
	}      

	public static Cons solveRecurrB(Cons e, String v){
		if(rest(rest(e)) == null){

			if(third((Cons)first(e)).equals("lhs")){

				if(first((Cons)first(e)).equals("sqrt"))
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(rest(e))), (Double) 2.0);
				if(first((Cons)first(e)).equals("-"))
					return list("-", second((Cons)first(rest(e))));
				else
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(rest(e))));
			}
			
			else if(third((Cons)first(e)).equals("right")){
				if(first((Cons)first(e)).equals("expt"))
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(rest(e))));
				else
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(rest(e))), second((Cons)first(e)));
			}

			else if(third((Cons)first(e)).equals("left")){
				if(first((Cons)first(e)).equals("expt"))
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(rest(e))));
				else
					return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
							second((Cons)first(e)), second((Cons)first(rest(e))));
			}
		}
		if(third((Cons)first(e)).equals("lhs")){
			if(first((Cons)first(e)).equals("sqrt"))
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						solveRecurrB(rest(e),v), (Double) 2.0);
			else if(first((Cons)first(e)).equals("-"))
				return list("-", solveRecurrB(rest(e), v));
			else
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						solveRecurrB(rest(e),v));
		}
		
		else if(third((Cons)first(e)).equals("right")) {
			if(first((Cons)first(e)).equals("expt"))
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						solveRecurrB(rest(e), v));
			else
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						solveRecurrB(rest(e), v), second((Cons)first(e)));
		}

		else if(third((Cons)first(e)).equals("left")){
			if(first((Cons)first(e)).equals("expt"))
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						solveRecurrB(rest(e),v));
			else
				return list(second((Cons)assoc(first((Cons)first(e)), opposites)), 
						second((Cons)first(e)), solveRecurrB(rest(e), v));
		}

		return null;
	}


	public static Double solveit (Cons equations, String var, Cons values) {


		Cons eqs = equations;
		Cons valueList = values;
		Cons current = null;
		Cons currentVal;
		Double answer = null;

		while (answer == null){

			while (valueList!= null){

				/*Find the equation with only one unknown*/
				if (solve((Cons)first(eqs), (String) op((Cons) first(valueList))) != null){
					valueList = rest(valueList);}

				else{
					eqs = rest(eqs);
					valueList = values;}
			}	
			current = solve((Cons)first(eqs), var);
			answer = (eval(second((Cons)(rest(current))), values));
			eqs = rest(eqs);
			valueList = values;
		}

		return answer;



	}


	// Include your functions vars and eval from the previous assignment.
	// Modify eval as described in the assignment.


	public static Cons vars (Object expr) {
		return varsRecurs(expr);

	}
	public static Cons varsRecurs (Object expr){
		/*If we reach a number, return null */

		if (expr == null)
			return null;
		if(numberp(expr))
			return null;

		/*If we reach a variable, add it to cons*/
		if(stringp(expr)) 
			return cons(expr, null);	

		return union(varsRecurs(lhs((Cons)expr)), varsRecurs(rhs((Cons)expr)));
	}  


	public static Double eval (Object tree, Cons bindings) {

		Double left;
		Double right;

		/*Turn tree into a cons, if need be*/
		if(!consp(tree)) 
			tree = cons(" ", cons(tree, null));

		if(consp(lhs((Cons)tree))) 
			left = eval(lhs((Cons)tree), bindings);
		else if(stringp(lhs((Cons)tree))) 
			left = (Double)first(rest(assoc(lhs((Cons)tree), bindings)));
		else 
			left = (Double)lhs((Cons)tree);

		if(consp(rhs((Cons)tree)))
			right = eval(rhs((Cons)tree), bindings);
		else if(stringp(rhs((Cons)tree)))
			right = (Double)first(rest(assoc(rhs((Cons)tree), bindings)));
		else if(floatp(rhs((Cons)tree))) 
			right = (Double)rhs((Cons)tree);
		else if(rhs((Cons)tree) == null) 
			right = null;
		else if(rhs((Cons)tree) == null && op((Cons)tree).equals(" ")) 
			return left;
		else 
			right = ((Integer)rhs((Cons)tree)).doubleValue();

		/*Unary Minus*/
		if(op((Cons)tree).equals("-")){
			if (rhs((Cons)tree) == null) 
				return -1*left;
			else 
				return left - right;}

		if(op((Cons)tree).equals("+")) 
			return left + right;
		if(op((Cons)tree).equals("*")) 
			return (left * right);
		if(op((Cons)tree).equals("/")) 
			return left / right;
		if(op((Cons)tree).equals("sqrt")) 
			return Math.sqrt(left);
		if(op((Cons)tree).equals("expt")) 
			return Math.pow(left, right);
		if(op((Cons)tree).equals("exp")) 
			return Math.exp(left);
		if(op((Cons)tree).equals("log")) 
			return Math.log(left);
		return null;
	}



	// ****** your code ends here ******

	public static void main( String[] args ) {

		Cons cave = list("rocks", "gold", list("monster"));
		Cons path = findpath("gold", cave);
		printanswer("cave = " , cave);
		printanswer("path = " , path);
		printanswer("follow = " , follow(path, cave));
		Cons treea = list(list("my", "eyes"),
				list("have", "seen", list("the", "light")));
		Cons treeb = list(list("my", "ears"),
				list("have", "heard", list("the", "music")));
		printanswer("treea = " , treea);
		printanswer("treeb = " , treeb);
		printanswer("corresp = " , corresp("light", treea, treeb));
		System.out.println("formulas = ");
		Cons frm = formulas;
		Cons vset = null;
		while ( frm != null ) {
			printanswer("   "  , ((Cons)first(frm)));
			vset = vars((Cons)first(frm));
			while ( vset != null ) {
				printanswer("       "  ,
						solve((Cons)first(frm), (String)first(vset)) );
				vset = rest(vset); 
			}
			frm = rest(frm); 
		}

		Cons bindings = list( list("a", (Double) 32.0),
				list("t", (Double) 4.0));
		printanswer("Eval:      " , rhs((Cons)first(formulas)));
		printanswer("  bindings " , bindings);
		printanswer("  answer = " , eval(rhs((Cons)first(formulas)), bindings));

		        printanswer("Tower: " , solveit(formulas, "h0",
		                                            list(list("h", new Double(0.0)),
		                                                 list("t", new Double(4.0)))));
		
		        printanswer("Car: " , solveit(formulas, "a",
		                                            list(list("v", new Double(88.0)),
		                                                 list("t", new Double(8.0)))));
		        
		        printanswer("Capacitor: " , solveit(formulas, "c",
		                                            list(list("v", new Double(3.0)),
		                                                 list("v0", new Double(6.0)),
		                                                 list("r", new Double(10000.0)),
		                                                 list("t", new Double(5.0)))));

		printanswer("Ladder: " , solveit(formulas, "b",
				list(list("a", new Double(6.0)),
						list("c", new Double(10.0)))));


	}

}