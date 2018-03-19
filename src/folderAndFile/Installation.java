package folderAndFile;
import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import tools.ExternallyFile;


public class Installation {

	
	public Installation () {

		
	//Create a file chooser
	final JFileChooser fc = new JFileChooser();
	fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
	
	//In response to a button click:
	int folderTarget = fc.showDialog(fc, "Install");
	
	if (folderTarget == JFileChooser.APPROVE_OPTION){
		String pathInstall = fc.getSelectedFile().getAbsolutePath();
		
		try{
			//Creation of parent folder 
			File parentFolder = new File (pathInstall + "/ExportAnimotoMagic");
			parentFolder.mkdir();
			
			//Creation of child folder
			String animotoMagicFolder = parentFolder.getAbsolutePath();
			File nacFolder = new File (animotoMagicFolder + "/Nac");
			File a105Folder = new File (animotoMagicFolder + "/105");
			
			
			nacFolder.mkdir();
			a105Folder.mkdir();
							
			//creation of grandChild folder
			String nacFolderComplet = nacFolder.getAbsolutePath();
			String a105FolderComplet = a105Folder.getAbsolutePath();
			
			String nacBreedingFolderPath = nacFolderComplet + "/Breeding";
			String nacBStockFolderPath = nacFolderComplet + "/Stock";
			String nacSourceFolderPath = nacFolderComplet + "/Source";
			String a105BreedingFolderPath = a105FolderComplet + "/Breeding";
			String a105BStockFolderPath = a105FolderComplet + "/Stock";
			String a105SourceFolderPath = a105FolderComplet + "/Source";
			
			
			File nacBreedingFolder = new File (nacBreedingFolderPath);
			File nacStockFolder = new File (nacBStockFolderPath );
			File nacSourceFolder = new File (nacSourceFolderPath);
			
			
			File a105BreedingFolder = new File (a105BreedingFolderPath);
			File a105StockFolder = new File (a105BStockFolderPath);
			File a105SourceFolder = new File (a105SourceFolderPath);
			
			nacBreedingFolder.mkdir();
			nacStockFolder.mkdir();
			nacSourceFolder.mkdir();
			a105BreedingFolder.mkdir();
			a105StockFolder.mkdir();
			a105SourceFolder.mkdir();
					
		
			//creation of text file for save path Install
			ExternallyFile.config.setSavePath(nacBreedingFolderPath, nacBStockFolderPath, nacSourceFolderPath, a105BreedingFolderPath, a105BStockFolderPath, a105SourceFolderPath);					
			ExternallyFile.saveObjectIntoTextFile(ExternallyFile.config, "ressources/config.ini");
			

		}catch (Exception e){
			JOptionPane .showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
		}
	} else if (folderTarget == JFileChooser.CANCEL_OPTION){
		System.exit (0);
	}
  }		
}
	

