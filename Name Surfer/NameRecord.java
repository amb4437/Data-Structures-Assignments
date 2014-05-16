import java.util.ArrayList;


public class NameRecord {
	
    private int[] namelist;
    private String[] stringline;
    int decade = 0;
	
	public NameRecord(String fileScanner){
      stringline = fileScanner.split("\\s+");
      namelist = new int[stringline.length-1];
      for (int i = 1; i < stringline.length; i++){
    	 Integer rank = new Integer (stringline[i]);
    	 namelist[i-1] = rank;
      }
		
	}
	
	public String getName(){
		return stringline[0];
	}
	
	public int getRank(int decade){
		//Need to add an illegal arguement statement
		return namelist[decade];
	}
	
	public String allRanks(){
		String returnstring = "";
		for (int i = 0; i < namelist.length; i ++)
			returnstring += namelist[i] + " ";
		return returnstring;
	}
	
	public int nameValue(){
		return (namelist[0]);
	}

	public int bestDecade(){
		int highest = 1001;
		int position = 0;
		for (int i = 0; i < namelist.length;){
			int currentrank = this.getRank(i);
			if (currentrank < highest && currentrank != 0){
				highest = currentrank;
			    position = i;
			}
			i++;
		}
		return position;
		}
	
	public int numtimesRanked(){
		int count = 0;
		for (int i = 0; i < namelist.length; i++){
			if (namelist[i] > 0)
				count++;
		}
		return count;
	}
	
	public boolean everyDecade(){
		boolean decade = true;
		for (int i = 0; i < namelist.length; i++){
			if (namelist[i] == 0)
				decade = false;
		}
		return decade;
	}
	
	public boolean oneDecade(){
		int count = 0;
		for (int i = 0; i < namelist.length; i++){
			if (namelist[i] > 0)
				count++;
		}
		return (count == 1);
	}
	
	public boolean morePopular(){
		
		boolean status = true;
		int previousrank = this.getRank(0);
		
		int j = 0;
		if (this.getRank(j) == 0){
			j++;
			previousrank = this.getRank(1);
		}
		
		
		for (int i = j; i < namelist.length; i++){
			int currentrank = this.getRank(i);
						
			if (currentrank == 0){
				if (i < namelist.length -1 && this.getRank(i +1) == 0)
					status = false;
			}
			
			if (currentrank > previousrank){
				status = false;
			}
			previousrank = currentrank;
		}
		return status;
	}
	
	public int biggestJump(){

		int previousrank = this.getRank(0);
		int largestJump = 0;
		
				
		for (int i = 0; i < namelist.length; i++){
			int currentrank = this.getRank(i);
			
			if (currentrank < previousrank && currentrank!= 0){
				int sum = (previousrank - currentrank);
				if (sum > largestJump)
					largestJump = sum;
			}
			previousrank = currentrank;
		}
		return largestJump;
	}
	
	
	
	public boolean lessPopular(){
		
		boolean status = true;
		int previousrank = this.getRank(0);
		
		int j = 0;
		if (this.getRank(j) == 0){
			if (j < namelist.length -1 && (this.getRank(j+1) == 0))
				status = false;
			j++;
			previousrank = this.getRank(1);
		}
		
		
		for (int i = j; i < namelist.length; i++){
			int currentrank = this.getRank(i);
						
			if (currentrank == 0){
				if (i < namelist.length -1 && (this.getRank(i+1) == 0))
					status = false;
			}
			
			if (currentrank < previousrank){
				status = false;
			}
			previousrank = currentrank;
		}
		return status;
	}
	
	//tests
	
//	public static void main (String[] args){
//		NameRecord test3 = new NameRecord ("Sean 0 0 160 0 0 0 0 0 0 0 0");
//		NameRecord test4 = new NameRecord ("Andrew 300 200 160 150 50 40 30 29 20 10 5");
//		NameRecord test1 = new NameRecord ("Aaron 0 192 191 190 180 170 169 36 32 31 30");
//	    NameRecord test2 = new NameRecord ("Abbey 190 191 192 193 200 201 210 300 301 302 400");
//	    
//	    System.out.println(test1.getName());
//	    System.out.println(test2.getName());
//	    
//	    System.out.println(test2.getRank(1));
//	    System.out.println(test1.getRank(4));
//	    
//	    System.out.println(test1.allRanks());
//	    System.out.println(test2.allRanks());
//	    
//	    System.out.println(test1.bestDecade());
//	    System.out.println(test2.bestDecade());
//	    
//	    System.out.println(test1.oneDecade());
//	    System.out.println(test3.oneDecade());
//	    
//	    System.out.println(test1.morePopular());
//	    System.out.println(test2.morePopular());
//	    
//	    System.out.println(test4.biggestJump());	    
//	    System.out.println(test1.biggestJump());
//	    
//	    System.out.println(test2.lessPopular());
//	    System.out.println(test1.lessPopular());
//	    
//	}
	
	
	
	}
