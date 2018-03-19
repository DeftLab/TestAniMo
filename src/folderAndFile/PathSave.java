package folderAndFile;

import java.io.Serializable;

public class PathSave implements Serializable {
	

	
	private String nacBreedingFolder, nacStockFolder, nacSourceFolder, a105BreedingFolder, a105StockFolder, a105SourceFolder;
	

	public PathSave(String nacBreedingFolder, String nacStockFolder, String nacSourceFolder, String a105BreedingFolder, String a105StockFolder, String a105SourceFolder) {
		this.nacBreedingFolder = nacBreedingFolder;
		this.nacStockFolder = nacStockFolder;
		this.nacSourceFolder = nacSourceFolder;
		this.a105BreedingFolder = a105BreedingFolder;
		this.a105StockFolder = a105StockFolder;
		this.a105SourceFolder = a105SourceFolder;
	}


	public String getNacBreedingFolder() {
		return nacBreedingFolder;
	}


	public String getNacStockFolder() {
		return nacStockFolder;
	}


	public String getNacSourceFolder() {
		return nacSourceFolder;
	}


	public String getA105BreedingFolder() {
		return a105BreedingFolder;
	}


	public String getA105StockFolder() {
		return a105StockFolder;
	}


	public String getA105SourceFolder() {
		return a105SourceFolder;
	}
	
		
	
}
