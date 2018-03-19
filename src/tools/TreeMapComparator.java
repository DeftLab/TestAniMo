package tools;


import java.util.Comparator;



public class TreeMapComparator implements Comparator <String>{
	private int year1;
	private int year2;
	private int number1;
	private int number2;
		
	    @Override
	    public int compare( String str1, String str2){
	    	//set first attribute : year1 and number1
	    	setAttribute(str1, true);
	    	setAttribute(str2, false);
	    	
	        if(year1 > year2){
	            return 1;
	        } else if (year1 == year2){
	        	if (number1 > number2){
	        		return 1;
	        	}else {
	        		return -1;
	        	}
	        }else {	
	         return -1;
	        }
	    }	    
	    
	    private void setAttribute (String str, boolean firstString){
	    	if ((str.length() < 8) || !(str.substring(0,8).equals("Unk cage"))){
	    		int split = str.indexOf('-');
		    	String strYear = str.substring(split + 1);
		    	String strNumber = str.substring(0, split);
		    	
		    	if (firstString){
		    		year1 = Integer.parseInt(strYear);
		    		number1 = Integer.parseInt(strNumber);
		    	} else {
		    		year2 = Integer.parseInt(strYear);
		    		number2 = Integer.parseInt(strNumber);
		    	}
	    	}else {
		    	if (firstString){
		    		year1 = 99;
		    		number1 = 9999;
		    	} else {
		    		year2 = 99;
		    		number2 = 9999;
		    	}
	    	}
	    }
	}


