package tools;
import java.util.Date;

public  class DateComparator {

	
	//return difference between today and another date in weeks (input Date1 and date2) round to 0.0 !
	public static double differenceBetween2Dates (Date date1, Date date2){
		return Math.abs(Math.round((date1.getTime() - date2.getTime())/60480000.0)/10.0);	
	}
	
	//return difference between today and another date in weeks (input Date2, date1 is the today date) round to 0.0 !
	public static double differenceBetween2Dates (Date date2){
		Date date1 = new Date();
		return (Math.round((date1.getTime() - date2.getTime())/60480000.0))/10.0;
	}

}
