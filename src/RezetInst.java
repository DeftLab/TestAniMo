import java.io.IOException;

import folderAndFile.Config;
import tools.ExternallyFile;

public class RezetInst {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
			
		try {
			ExternallyFile.config = (Config) ExternallyFile.readObjectIntoTextFile("ressources/config.ini");
			ExternallyFile.config.rezetPath();
			ExternallyFile.saveObjectIntoTextFile( ExternallyFile.config, "ressources/config.ini");
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("le path est vide, ready pour une nouvelle install !");
		
	}

}
