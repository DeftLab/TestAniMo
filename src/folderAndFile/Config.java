package folderAndFile;

import java.io.Serializable;
import java.util.ArrayList;

import folderAndFile.PathSave;
import options.Cage105Options;
import options.CageNacOptions;
import options.Mice105Options;
import options.MiceNacOptions;

public class Config implements Serializable{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private  MiceNacOptions miceNacOptions ;
	private  CageNacOptions cageNacOptions ;
	private  Mice105Options mice105Options ;
	private  Cage105Options cage105Options ;
	private  PathSave installPath;
	private ArrayList<Integer> stockColumnPosition;
	private ArrayList<String> stockColumnLabel;
	private ArrayList<Integer> croisColumnPosition;
	private ArrayList<String> croisColumnLabel;
	


	public Config (MiceNacOptions miceNacOptions, CageNacOptions cageNacOptions, Mice105Options mice105Options,	Cage105Options cage105Options, PathSave installPath, ArrayList <Integer> StockColumnPosition, ArrayList<String> StockColumnLabel, ArrayList <Integer> CroisColumnPosition, ArrayList<String> CroisColumnLabel) {
		this.miceNacOptions = miceNacOptions;
		this.cageNacOptions = cageNacOptions;
		this.mice105Options = mice105Options;
		this.cage105Options = cage105Options;
		this.installPath = installPath;
		this.stockColumnPosition = StockColumnPosition;
		this.stockColumnLabel = StockColumnLabel;
		this.croisColumnPosition = CroisColumnPosition;
		this.croisColumnLabel = CroisColumnLabel;
	}



	public void setStockColumnPosition(ArrayList<Integer> stockColumnPosition) {
		this.stockColumnPosition = stockColumnPosition;
	}



	public void setStockColumnLabel(ArrayList<String> stockColumnLabel) {
		this.stockColumnLabel = stockColumnLabel;
	}



	public void setCroisColumnPosition(ArrayList<Integer> croisColumnPosition) {
		this.croisColumnPosition = croisColumnPosition;
	}



	public void setCroisColumnLabel(ArrayList<String> croisColumnLabel) {
		this.croisColumnLabel = croisColumnLabel;
	}



	public ArrayList<Integer> getCroisColumnPosition() {
		return croisColumnPosition;
	}



	public ArrayList<String> getCroisColumnLabel() {
		return croisColumnLabel;
	}



	public ArrayList<Integer> getStockColumnPosition() {
		return stockColumnPosition;
	}


	public ArrayList<String> getStockColumnLabel() {
		return stockColumnLabel;
	}



	public  MiceNacOptions getMiceNacOptions() {
		return miceNacOptions;
	}



	public  CageNacOptions getCageNacOptions() {
		return cageNacOptions;
	}



	public Mice105Options getMice105Options() {
		return mice105Options;
	}



	public  Cage105Options getCage105Options() {
		return cage105Options;
	}



	public  PathSave getInstallPath() {
		return installPath;
	}


	public  void setSavePath (String nacBreedingFolder, String nacStockFolder, String nacSourceFolder, String a105BreedingFolder, String a105StockFolder, String a105SourceFolder){
		this.installPath = new PathSave (nacBreedingFolder, nacStockFolder, nacSourceFolder, a105BreedingFolder, a105StockFolder, a105SourceFolder);
	}
	
	public void rezetPath (){
		this.installPath = null;
	}

	
}
