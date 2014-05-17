/**
 * this class Cons implements a Lisp-like Cons cell
 * 
 * @author  Gordon S. Novak Jr.
 * @version 29 Nov 01; 25 Aug 08; 05 Sep 08; 08 Sep 08; 12 Sep 08; 24 Sep 08
 *          06 Oct 08; 07 Oct 08; 09 Oct 08; 23 Oct 08; 30 Oct 08; 07 Apr 09
 *          10 Apr 09; 02 Aug 10; 06 Aug 10
 */

/*
 * Name: Andrew Baldwin
 * EID: AMB4437
 * TA: Yunsik
 * Assignment #: 8
 */

import java.util.StringTokenizer;

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

	// iterative version of length
	public static int length (Cons lst) {
		int n = 0;
		while ( lst != null ) {
			n++;
			lst = rest(lst); }
		return n; }

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

	public static Object copy_tree(Object tree) {
		if ( consp(tree) )
			return cons(copy_tree(first((Cons) tree)),
					(Cons) copy_tree(rest((Cons) tree)));
		return tree; }

	public static Object subst(Object gnew, String old, Object tree) {
		if ( consp(tree) )
			return cons(subst(gnew, old, first((Cons) tree)),
					(Cons) subst(gnew, old, rest((Cons) tree)));
		return (old.equals(tree)) ? gnew : tree; }

	public static Object sublis(Cons alist, Object tree) {
		if ( consp(tree) )
			return cons(sublis(alist, first((Cons) tree)),
					(Cons) sublis(alist, rest((Cons) tree)));
		if ( tree == null ) return null;
		Cons pair = assoc(tree, alist);
		return ( pair == null ) ? tree : second(pair); }

	public static Cons setDifference (Cons x, Cons y) {
		Cons xlist = llmergesort(x);
		Cons ylist = llmergesort(y);
		Cons returnList = null;	

		int num;

		String currentX = (String)op(xlist);
		String currentY = (String)op(ylist);


		while (xlist!= null && ylist != null){
			num = currentX.compareTo(currentY);
			if (num == 0){ //same characters
				xlist = rest(xlist);
				ylist = rest(ylist);
				currentX = (String) op(xlist);
				currentY = (String) op(ylist);}
			if (num > 0){ //x is greater than y in the alphabet
				ylist = rest(ylist);
				currentY = (String) op(ylist);}
			if (num < 0 ){ //x is less than y in the alphabet
				returnList = cons(currentX, returnList);
				xlist = rest(xlist);
				currentX = (String) op(xlist);}
		}

		while (xlist!= null){
			returnList = cons(currentX, returnList);
			xlist = rest(xlist);
			currentX = (String) op(xlist);
		}


		return returnList;



	}



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


	public static Cons llmergesort (Cons lst) {
		if ( lst == null || rest(lst) == null)
			return lst;
		else { Cons mid = midpoint(lst);
		Cons half = rest(mid);
		setrest(mid, null);
		return dmerj( llmergesort(lst),
				llmergesort(half)); } }

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

	public static Cons dummysub = list(list("t", "t"));

	public static Cons match(Object pattern, Object input) {
		return matchb(pattern, input, dummysub); }

	public static Cons matchb(Object pattern, Object input, Cons bindings) {
		if ( bindings == null ) return null;
		if ( consp(pattern) )
			if ( consp(input) )
				return matchb( rest( (Cons) pattern),
						rest( (Cons) input),
						matchb( first( (Cons) pattern),
								first( (Cons) input),
								bindings) );
			else return null;
		if ( varp(pattern) ) {
			Cons binding = assoc(pattern, bindings);
			if ( binding != null )
				if ( equal(input, second(binding)) )
					return bindings;
				else return null;
			else return cons(list(pattern, input), bindings); }
		if ( eql(pattern, input) )
			return bindings;
		return null; }

	public static Object reader(String str) {
		return readerb(new StringTokenizer(str, " \t\n\r\f()'", true)); }

	public static Object readerb( StringTokenizer st ) {
		if ( st.hasMoreTokens() ) {
			String nexttok = st.nextToken();
			if ( nexttok.charAt(0) == ' ' ||
					nexttok.charAt(0) == '\t' ||
					nexttok.charAt(0) == '\n' ||
					nexttok.charAt(0) == '\r' ||
					nexttok.charAt(0) == '\f' )
				return readerb(st);
			if ( nexttok.charAt(0) == '(' )
				return readerlist(st);
			if ( nexttok.charAt(0) == '\'' )
				return list("QUOTE", readerb(st));
			return readtoken(nexttok); }
		return null; }

	public static Object readtoken( String tok ) {
		if ( (tok.charAt(0) >= '0' && tok.charAt(0) <= '9') ||
				((tok.length() > 1) &&
						(tok.charAt(0) == '+' || tok.charAt(0) == '-' ||
						tok.charAt(0) == '.') &&
						(tok.charAt(1) >= '0' && tok.charAt(1) <= '9') ) ||
						((tok.length() > 2) &&
								(tok.charAt(0) == '+' || tok.charAt(0) == '-') &&
								(tok.charAt(1) == '.') &&
								(tok.charAt(2) >= '0' && tok.charAt(2) <= '9') )  ) {
			boolean dot = false;
			for ( int i = 0; i < tok.length(); i++ )
				if ( tok.charAt(i) == '.' ) dot = true;
			if ( dot )
				return Double.parseDouble(tok);
			else return Integer.parseInt(tok); }
		return tok; }

	public static Cons readerlist( StringTokenizer st ) {
		if ( st.hasMoreTokens() ) {
			String nexttok = st.nextToken();
			if ( nexttok.charAt(0) == ' ' ||
					nexttok.charAt(0) == '\t' ||
					nexttok.charAt(0) == '\n' ||
					nexttok.charAt(0) == '\r' ||
					nexttok.charAt(0) == '\f' )
				return readerlist(st);
			if ( nexttok.charAt(0) == ')' )
				return null;
			if ( nexttok.charAt(0) == '(' ) {
				Cons temp = readerlist(st);
				return cons(temp, readerlist(st)); }
			if ( nexttok.charAt(0) == '\'' ) {
				Cons temp = list("QUOTE", readerb(st));
				return cons(temp, readerlist(st)); }
			return cons( readtoken(nexttok),
					readerlist(st) ); }
		return null; }

	// read a list of strings, producing a list of results.
	public static Cons readlist( Cons lst ) {
		if ( lst == null )
			return null;
		return cons( reader( (String) first(lst) ),
				readlist( rest(lst) ) ); }

	public static Object transform(Cons patpair, Cons input) {
		Cons bindings = match(first(patpair), input);
		if ( bindings == null ) return null;
		return sublis(bindings, second(patpair)); }

	// Transform a list of arguments.  If no change, returns original.
	public static Cons transformlst(Cons allpats, Cons input) {
		if ( input == null ) return null;
		Cons restt = transformlst(allpats, rest(input));
		Object thist = transformr(allpats, first(input));
		if ( thist == first(input) && restt == rest(input) )
			return input;
		return cons(thist, restt); }

	// Transform a single item.  If no change, returns original.
	public static Object transformr(Cons allpats, Object input) {
		//    System.out.println("transformr:  " + input.toString());
		if ( consp(input) ) {
			Cons listt = transformlst(allpats, (Cons) input);
			//       System.out.println("   lst =  " + listt.toString());
			return transformrb(allpats, allpats,
					transformlst(allpats, listt)); }
		Object res = transformrb(allpats, allpats, input);
		//    System.out.println("   result =  " + res.toString());
		return res; }

	// Transform a single item.  If no change, returns original.
	public static Object transformrb(Cons pats, Cons allpats, Object input) {
		if ( pats == null ) return input;
		if ( input == null ) return null;
		Cons bindings = match(first((Cons)first(pats)), input);
		if ( bindings == null ) return transformrb(rest(pats), allpats, input);
		return sublis(bindings, second((Cons)first(pats))); }

	// Transform a single item repeatedly, until fixpoint (no change).
	public static Object transformfp(Cons allpats, Object input) {
		//    System.out.println("transformfp: " + input.toString());
		Object trans = transformr(allpats, input);
		if ( trans == input ) return input;
		//    System.out.println("    result = " + trans.toString());
		return transformfp(allpats, trans); }          // potential loop!

	public static boolean varp(Object x) {
		return ( stringp(x) &&
				( ((String) x).charAt(0) == '?' ) ); }

	// Note: this list will handle most, but not all, cases.
	// The binary operators - and / have special cases.
	public static Cons opposites = 
			list( list( "+", "-"), list( "-", "+"), list( "*", "/"),
					list( "/", "*"), list( "sqrt", "expt"), list( "expt", "sqrt"),
					list( "log", "exp"), list( "exp", "log") );

	public static String opposite(String op) {
		Cons pair = assoc(op, opposites);
		if ( pair != null ) return (String) second(pair);
		return "error"; }

	public static void javaprint(Object item, int tabs) {
		if ( item == null ) System.out.print("null");
		else if ( consp(item) ) javaprintlist((Cons) item, tabs);
		else if ( stringp(item) )
			if ( item.equals("zlparen") ) System.out.print("(");
			else if ( item.equals("zrparen") ) System.out.print(")");
			else if ( item.equals("zspace") ) System.out.print(" ");
			else if ( item.equals("znothing") ) ;
			else if ( item.equals("ztab") ) System.out.print("\t");
			else if ( item.equals("zreturn") ) System.out.println();
			else System.out.print((String)item);
		else System.out.print(item.toString()); }

	public static void javaprintlist(Cons lst, int tabs) {
		if ( lst != null ) {
			if ( stringp(first(lst)) )
				if ( ((String)first(lst)).equals("ztab" ) ) tabs++;
				else if ( ((String)first(lst)).equals("zuntab" ) ) tabs--;
				else if ( ((String)first(lst)).equals("zreturn" ) ) {
					System.out.println();
					for (int i = 0; i < tabs; i++) System.out.print("\t"); }
				else javaprint(first(lst), tabs);
			else javaprint(first(lst), tabs);
			javaprintlist(rest(lst), tabs); } }

	// ****** your code starts here ******

	// your program from the previous assignments
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


	public static Double eval (Object expr, Cons bindings) {
		if (consp(expr)) {
			Double left = eval(lhs((Cons) expr), bindings);
			Double right = eval(rhs((Cons) expr), bindings);
			String operator = (String) op((Cons) expr);

			if ("+".equals(operator)) 
				return left + right;	
			/*Unary minus */
			else if ("-".equals(operator)) {
				if (rhs((Cons) expr) == null) 
					return left * -1;
				else 
					return left - right;}
			else if ("/".equals(operator)) 
				return left / right;
			else if ("*".equals(operator)) 
				return left * right;
			else if ("expt".equals(operator)) 
				return Math.pow(left, right);
			else if ("sqrt".equals(operator)) 
				return Math.sqrt(left);
			else if ("log".equals(operator)) 
				return Math.log(left);
			else if ("exp".equals(operator)) 
				return Math.exp(left);
			else 
				return 0.0;
		}
		else if (expr != null) {
			Cons assoc_value = assoc(expr, bindings);

			if (assoc_value == null) 
				return Double.parseDouble(expr.toString());
			else 
				return (Double) first(rest(assoc_value));
		}

		return 0.0;
	}



	// new for this assignment


	public static Double solveqns(Cons eqns, Cons vals, String v) {

		Cons equationVars;
		Cons tempEqns = eqns;
		Cons allVariables = allVariables(vals);
		Cons solveEquation;
		Double answer;


		Cons current = (Cons) first(tempEqns);

		while (current!= null){
			equationVars = vars(current);
			equationVars = setDifference(equationVars, allVariables(vals));
			if (length(equationVars) == 1){	
				solveEquation = solve(current, (String) first(equationVars));
				answer = eval(second((Cons)(rest(solveEquation))), vals);
				vals = cons(cons(first(equationVars), cons(answer, null)), vals);
				solveqns(tempEqns, vals, v);}
			else{
				tempEqns = rest(tempEqns);
				current = (Cons) first(tempEqns);}

		}


		Cons value = assoc(v, vals);
		if (value!= null)
			return (Double) (lhs(value));

		else
			return null;
	}


	public static Cons allVariables(Cons vals){

		Cons allVariables = null;
		Cons tempVals = vals;
		while (tempVals!= null){
			allVariables = cons((first((Cons)first(tempVals))), allVariables);
			tempVals = rest(tempVals);
		}
		return allVariables;

	}


	// Question 2 of Assignment 8
	// Code substitutions to be done in 'binaryfn' below
	// ((?function ...) (?combine ...) (?baseanswer ...))
	//    (defun ?function (tree)
	//       (if (consp tree)
	//           (?combine (?function (first tree))
	//                     (?function (rest tree)))
	//           ?baseanswer))
	public static Cons substitutions = readlist( list(
			"( (?function addnums) (?combine +) (?baseanswer (if (numberp tree) tree 0)))",

			// add to the following
			"( (?function countstrings) (?combine +) (?baseanswer (if (stringp tree) tree 0)) )",
			"( (?function copytree) (?combine cons)(?baseanswer tree)",
			"( (?function mintree)(?combine min) (?baseanswer  (if (numberp tree) tree null)) )",
			"((?function conses) (?combine add1) (?baseanswer (if (consp tree) 1 0)))"
			));


	public static Cons optpats = readlist( list(
		       "( (+ ?x 0)   ?x)",
		       "( (+ 0 ?x)   ?x)",
		       "( (expt ?x (- 2 1)) (expt ?x 1)",
		       "( (expt ?x 1) ?x)",
		       "( (* ?x 1) ?x)",
		       "( (- (- ?y))   ?y)",
		       "( (- (- ?x ?y))   (- ?y ?x))",
		       "( (* ?x 0) 0)"

			
			// add more

			));


	public static Cons derivpats = readlist( list(
			"( (deriv ?x ?x)   1)",
			"( (deriv (+ ?u ?v) ?x)  (+ (deriv ?u ?x) (deriv ?v ?x)))",
			"( (deriv (* ?y ?x) ?x) ?y)",
			"( (deriv (* ?y ?x) ?y) ?x)",
			"( (deriv (expt ?x ?y) ?x)  (* ?y (expt ?x (- ?y 1)    )  )",
			"( (deriv (* ?y ?x) ?y) ?x)",
			"( (deriv (sin ?x) ?x) (cos ?x)",
			"( (deriv (sin (* ?y ?x)) ?x)  ( * (* ?y (deriv ?x ?x)) (cos (* ?y ?x))) )",
			"( (deriv (sqrt ?x) ?x) (*  (/ 1 2) (/ (deriv ?x ?x) (sqrt ?x) ) )     )",
			" ( (deriv (sqrt (?+ (?a ?x ?y) ?z)) x)    (/ ?x (sqrt (exp ?x ?y) ?z))",
			"( (deriv (?log (?expt (+ ?y ?x) ?z)) x) (/ ?z ( + ?y ?x)))",
			// add more

			"( (deriv ?y ?x)   0)"   // this must be last!
			));

	public static Cons javarestructpats = readlist( list(
			"( (return (if ?test ?then)) (if ?test (return ?then)) )",
			"( (return (if ?test ?then ?else)) (if ?test (return ?then) (return  ?else)) )",
			"( (defun ?fun ?args ?code) (zdefun ?fun (arglist ?args) (progn (return ?code))) )",
			"( (setq ?x (+ ?x 1)) (incf ?x) )"
			));
	
	 public static Cons javapats = readlist( list(
		       "( (if ?test ?then) (if zspace zlparen zspace ?test zspace zrparen ztab zreturn ?then))",
		       "((if ?test ?then ?else) (znothing if zspace zlparen ?test zrparen zspace { ztab zreturn ?then zreturn } zreturn else zspace { zreturn ?else zreturn }))",
		       "( (< ?x ?y)  (zlparen ?x zspace < zspace ?y zrparen))",
		       "( (min ?x ?y) (Math.min zlparen ?x , zspace ?y zrparen))",
		       "( (cons ?x ?y) (znothing cons zlparen ?x , zspace ?y zrparen))",
		       "( (zdefun ?fun ?args ?code) (public zspace static zspace Object zspace ?fun zspace ?args zreturn ?code zreturn) )",
		       "( (arglist (?x))   (zlparen Object zspace ?x zrparen))",
		       "( (progn ?x) ({ ztab zreturn ?x zreturn }) )",
		       "( (setq ?x ?y) (?x zspace = zspace ?y ; zreturn) )",
		       "( (first ?x) (znothing first zlparen zlparen Cons zrparen ?x zrparen) )",
		       // add more
		       
		       "((incf ?x) (znothing ?x + + ;))",
		   
		       /*Mathematical functions*/
		       "((+ ?x ?y) (znothing zlparen ?x zspace + zspace ?y zrparen))",
		       "((* ?x ?y) (zlparen ?x zspace * zspace ?y zrparen))",
		       "((> ?x ?y) (zlparen ?x zspace > zspace ?y zrparen))",
		       "((<= ?x ?y) (znothing zlparen ?x zspace <= zspace ?y zrparen))",
		       "((- ?x ?y) (znothing zlparen ?x zspace - zspace ?y zrparen))",
		       
		       "((or ?x ?y) (znothing zlparen ?x zspace || zspace ?y zrparen))",
		       "((and ?x ?y) (znothing zlparen ?x zspace && zspace ?y zrparen))",
		       "((not ?x) (znothing ! ?x))",

		       "( (?fun ?x)   (znothing ?fun zlparen ?x zrparen))"  // must be last
		       ));


	// ****** your code ends here ******

	public static void main( String[] args ) {

		Cons eqnsbat =
				readlist(
						list("(= loss_voltage (* internal_resistance current))",
								"(= loss_power (* internal_resistance (expt current 2)))",
								"(= terminal_voltage (- voltage loss_voltage))",
								"(= power (* terminal_voltage current))",
								"(= work (* charge terminal_voltage))" ) );



		System.out.println("battery = " +
				solveqns(eqnsbat, (Cons)
						reader("((current 0.3)(internal_resistance 4.0)(voltage 12.0))"),
						"terminal_voltage"));


		Cons eqnscirc =
				readlist(
						list("(= acceleration (/ (expt velocity 2) radius))",
								"(= force        (* mass acceleration))",
								"(= kinetic_energy   (* (/ mass 2) (expt velocity 2)))",
								"(= moment_of_inertia (* mass (expt radius 2)))",
								"(= omega (/ velocity radius))",
								"(= angular_momentum (* omega moment_of_inertia))" ));



		System.out.println("circular = " +
				solveqns(eqnscirc, (Cons)
						reader("((radius 4.0)(mass 2.0)(velocity 3.0))"),
						"angular_momentum"));


		Cons eqnslens =
				readlist(
						list("(= focal_length (/ radius 2))",
								"(= (/ 1 focal_length) (+ (/ 1 image_distance) (/ 1 subject_distance)))",
								"(= magnification (- (/ image_distance subject_distance)))",
								"(= image_height (* magnification subject_height))" ));


		System.out.println("magnification = " +
				solveqns(eqnslens, (Cons)
						reader("((subject_distance 6.0)(focal_length 9.0))"),
						"magnification"));

		Cons eqnslift =
				readlist(
						list("(= gravity     9.80665 )",
								"(= weight      (* gravity mass))",
								"(= force       weight)",
								"(= work        (* force height))",
								"(= speed       (/ height time))",
								"(= power       (* force speed))",
								"(= power       (/ work time)) ))" ));

		System.out.println("power = " +
				solveqns(eqnslift, (Cons)
						reader("((weight 700.0)(height 8.0)(time 10.0)))"),
						"power"));
						
		Cons binaryfn = (Cons) reader(
				"(defun ?function (tree) (if (consp tree) (?combine (?function (first tree)) (?function (rest tree))) ?baseanswer))");

		for ( Cons ptr = substitutions; ptr != null; ptr = rest(ptr) ) {
			Object trans = sublis((Cons) first(ptr), binaryfn);
			System.out.println("sublis:  " + trans.toString()); }

		Cons opttests = readlist( list(
				"(+ 0 foo)",
				"(* fum 1)",
				"(- (- y))",
				"(- (- x y))",
				"(+ foo (* fum 0))"
				));




		for ( Cons ptr = opttests; ptr != null; ptr = rest(ptr) ) {
			System.out.println("opt:  " + ((Cons)first(ptr)).toString());
			Object trans = transformfp(optpats, first(ptr));
			System.out.println("      " + trans.toString()); }

		
		Cons derivtests = readlist( list(
				"(deriv x x)",
				"(deriv 3 x)",
				"(deriv z x)",
				"(deriv (+ x 3) x)",
				"(deriv (* x 3) x)",
				"(deriv (* 5 x) x)",
				"(deriv (sin x) x)",
				"(deriv (sin (* 2 x)) x)",
				"(deriv (+ (expt x 2) (+ (* 3 x) 6)) x)",
				"(deriv (sqrt (+ (expt x 2) 2)) x)",
				"(deriv (log (expt (+ 1 x) 3)) x)"
				

				));

		for ( Cons ptr = derivtests; ptr != null; ptr = rest(ptr) ) {
			System.out.println("deriv:  " + ((Cons)first(ptr)).toString());
			Object trans = transformfp(derivpats, first(ptr));
			System.out.println("  der:  " + trans.toString());
			Object transopt = transformfp(optpats, trans);
			System.out.println("  opt:  " + transopt.toString()); }
		


		Cons javatests = readlist( list(
				"(* fum 7)",
				"(setq x y)",
				"(setq x (+ x 1))",
				"(setq area (* pi (expt r 2)))",
				"(if (> x 7) (setq y x))",
				"(if (or (> x 7) (< y 3)) (setq y x))",
				"(if (and (> x 7) (not (< y 3))) (setq y x))",
				"(defun factorial (n) (if (<= n 1) 1 (* n (factorial (- n 1)))))"
				));
		


		for ( Cons ptr = javatests; ptr != null; ptr = rest(ptr) ) {
			System.out.println("java:  " + ((Cons)first(ptr)).toString());
			Cons restr = (Cons) transformfp(javarestructpats, first(ptr));
			System.out.println("       " + restr.toString());
			Cons trans = (Cons) transformfp(javapats, restr);
			System.out.println("       " + trans.toString());
			javaprintlist(trans, 0);
			System.out.println(); }


		for ( Cons ptr = substitutions; ptr != null; ptr = rest(ptr) ) {
			Object ltrans = sublis((Cons) first(ptr), binaryfn);
			System.out.println("sublis:  " + ltrans.toString());
			Cons restr = (Cons) transformfp(javarestructpats, ltrans);
			System.out.println("       " + restr.toString());
			Cons trans = (Cons) transformfp(javapats, restr);
			System.out.println("       " + trans.toString());
			javaprintlist(trans, 0);
			System.out.println(); }


	}

}