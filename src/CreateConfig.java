import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import folderAndFile.Config;
import tools.ExternallyFile;

public class CreateConfig {

	public static void main(String[] args) throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		//open and read config file
				ExternallyFile.loadConfig();
				
		//add arrayList to config object 
				
//				//restaurer la config					
//				ArrayList<Integer> stockColumnPosition = new 	ArrayList<Integer>();
//				ArrayList<String> stockColumnLabel =  new 	ArrayList<String>();
//				ArrayList<Integer> croisColumnPosition =  new 	ArrayList<Integer>();
//				ArrayList<String> croisColumnLabel =   new 	ArrayList<String>();
//				
//				//remplir les array pour Stock
//				stockColumnPosition.add(0);
//				stockColumnLabel.add("Code");
//				
//				stockColumnPosition.add(1);
//				stockColumnLabel.add("Lign�e");
//				
//				stockColumnPosition.add(3);
//				stockColumnLabel.add("Marque");
//				
//				stockColumnPosition.add(4);
//				stockColumnLabel.add("Sexe");
//				
//				stockColumnPosition.add(5);
//				stockColumnLabel.add("D. naissance");
//				
//				stockColumnPosition.add(7);
//				stockColumnLabel.add("G�notype");
//				
//				stockColumnPosition.add(10);
//				stockColumnLabel.add("En croisement");
//				
//				stockColumnPosition.add(14);
//				stockColumnLabel.add("Souche");
//				
//				stockColumnPosition.add(15);
//				stockColumnLabel.add("Proc�dure");
//				
//				stockColumnPosition.add(16);
//				stockColumnLabel.add("Cage");
//				
//				stockColumnPosition.add(22);
//				stockColumnLabel.add("Croisement Origine");
//				
//				stockColumnPosition.add(36);
//				stockColumnLabel.add("Services en attente");
//				
//		
//				
//				//remplir les array pour croisement
//
//				croisColumnPosition.add(0);  // tmp code cage
//				croisColumnLabel.add("D�but"); // ne pas checker celui ci 
////				
////				croisColumnPosition.add(1);  // tmp2
////				croisColumnLabel.add("Code"); // ne pas checker celui ci 
////				
////				croisColumnPosition.add(2);  // date ouverture
////				croisColumnLabel.add("Date ouverture"); // ne pas checker celui ci 
//			
//				croisColumnPosition.add(1);   
//				croisColumnLabel.add("N� Croisement"); 
//				
//				croisColumnPosition.add(2);   
//				croisColumnLabel.add("Cage");
//				
//				croisColumnPosition.add(5);
//				croisColumnLabel.add("M�le");
//				
//				croisColumnPosition.add(6);
//				croisColumnLabel.add("Marque m�le");
//				
//				croisColumnPosition.add(7);
//				croisColumnLabel.add("Souche");
//				
//				croisColumnPosition.add(8);
//				croisColumnLabel.add("Lign�e m�le");
//				
//				croisColumnPosition.add(9);
//				croisColumnLabel.add("G�ne m�le");
//				
//				croisColumnPosition.add(10);
//				croisColumnLabel.add("G�notype m�le");
//				
//				croisColumnPosition.add(12);
//				croisColumnLabel.add("Naissance");
//				
//				croisColumnPosition.add(13);   
//				croisColumnLabel.add("Lign�e r�sultante");
//				
//				croisColumnPosition.add(17);   
//				croisColumnLabel.add("Proc�dure");
//				
//				croisColumnPosition.add(18); 
//				croisColumnLabel.add("Marque femelle");
//				
//				croisColumnPosition.add(19); 
//				croisColumnLabel.add("Souche");
//				
//				croisColumnPosition.add(20); 
//				croisColumnLabel.add("Lign�e femelle");
//				
//				croisColumnPosition.add(21); 
//				croisColumnLabel.add("G�ne femelle");
//				
//				croisColumnPosition.add(22); 
//				croisColumnLabel.add("G�notype femelle");
//				
//				croisColumnPosition.add(24); 
//				croisColumnLabel.add("Naissance");
//				
//				croisColumnPosition.add(34);   
//				croisColumnLabel.add("Code port�e");
//				
//				croisColumnPosition.add(40);   
//				croisColumnLabel.add("Naissance");
//				
//				croisColumnPosition.add(41);   
//				croisColumnLabel.add("Port�es");
//				
//				croisColumnPosition.add(42);   
//				croisColumnLabel.add("Anim. n�s");
//				
//				croisColumnPosition.add(48);   
//				croisColumnLabel.add("Animaux morts pre-s�vrage");
//				
//				Config conf = new Config (ExternallyFile.config.getMiceNacOptions(), ExternallyFile.config.getCageNacOptions(), ExternallyFile.config.getMice105Options(), ExternallyFile.config.getCage105Options(), ExternallyFile.config.getInstallPath(), stockColumnPosition, stockColumnLabel, croisColumnPosition, croisColumnLabel);
//				
//				ExternallyFile.saveObjectIntoTextFile(conf, "ressources/config.ini");
//				
//				System.out.println("update ok");
				
				//lire la config
				ArrayList<Integer> stockColumnPosition = ExternallyFile.config.getStockColumnPosition();
				ArrayList<String> stockColumnLabel = ExternallyFile.config.getStockColumnLabel();
				ArrayList<Integer> croisColumnPosition = ExternallyFile.config.getCroisColumnPosition();
				ArrayList<String> croisColumnLabel =  ExternallyFile.config.getCroisColumnLabel();
				
				System.out.println("Croisement ;");
				for (int i = 0; i<croisColumnPosition.size(); ++i){					
				System.out.println("indice tab = " + i + " || " + croisColumnLabel.get(i) + " : " + croisColumnPosition.get(i));					
				}
				System.out.println("===========================================");
				
				
				System.out.println("\nStock ");
				for (int i = 0; i<stockColumnPosition.size(); ++i){				
					System.out.println("indice tab = " + i + " || " + stockColumnLabel.get(i) + " : " + stockColumnPosition.get(i));
				}
				

	}

}
