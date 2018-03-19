package mice;

import java.util.Date;

public class Litter {
	private int newBornsNumber, weanedMiceNumber, newBornDeathNUmber;
	private Date newBornsDate;
	private String litterCode;
	
	
	public Litter(String litterCode, int newBornsNumber, int newBornDeatNumber, Date newBornsDate) {		
		this.litterCode = litterCode;
		this.newBornsNumber = newBornsNumber;
		this.newBornDeathNUmber = newBornDeatNumber;
		this.newBornsDate = newBornsDate;		
	}


	public String getlitterCode() {
		return litterCode;
	}


	public int getNewBornsNumber() {
		return newBornsNumber;
	}


	public int getWeanedMiceNumber() {
		return weanedMiceNumber;
	}


	public int getNewBornDeathNUmber() {
		return newBornDeathNUmber;
	}


	public Date getNewBornsDate() {
		return newBornsDate;
	}

	
}


