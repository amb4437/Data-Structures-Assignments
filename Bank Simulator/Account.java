    public class Account implements Comparable<Account> {
        private String name;
        private Integer amount;
        public Account(String nm, Integer amt) {
            name = nm;
            amount = amt; }
        public static Account account(String nm, Integer amt) {
            return new Account(nm, amt); }
        public String name() { return name; }
        public Integer amount() { return amount; }
        public boolean equals(Object x) {
            if ( x == null ) return false;
            else if ( getClass() != x.getClass() ) return false;
            else return name.equals( ((Account)x).name); }

        // return -1 to sort this account before x, else 1
        public int compareTo(Account x) {
        	if ( ((Comparable)this.name).compareTo((Comparable)x.name()) > 0)
        		return 1;
        	if ( ((Comparable)this.name).compareTo((Comparable)x.name()) < 0)
        		return -1;
        	
        	/* Same name, sort by update amount */
        	else{
        		if (this.amount() < 0 && x.amount() > 0)  //Positive amounts sorted before negative amounts
        			return 1;
        		if (this.amount() > 0 && x.amount() < 0)  //Positive amounts sorted before negative amounts
        			return -1;
        		if (this.amount() < 0 && x.amount() < 0){ //Most negative amounts processed first
        			if (this.amount < x.amount())
        				return -1;
        			else
        				return 1;
        			}
        	}
        	return 1;
        	
        }
        
        public void setAmount(Integer x) { this.amount = x; }
        
        // ***** your code here *****	

        public String toString() {
            return ( "(" + this.name + " " + this.amount + ")"); }
    }