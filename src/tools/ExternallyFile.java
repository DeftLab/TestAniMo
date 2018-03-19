package tools;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import folderAndFile.Config;




public class ExternallyFile {
	public static Config config;


	public static boolean fileExists (String pathAndName){
		File file = new File (pathAndName);
		return file.exists();
	}
	
	public static void saveObjectIntoTextFile (Object ob, String pathFile) throws FileNotFoundException, IOException{
		ObjectOutputStream oos = new ObjectOutputStream (
				new BufferedOutputStream (
						new FileOutputStream (
								new File (pathFile))));
		
		oos.writeObject(ob);
		oos.close();
	}
	
	public static Object readObjectIntoTextFile (String pathFile) throws FileNotFoundException, IOException, ClassNotFoundException{
		ObjectInputStream ois = new ObjectInputStream (
									new BufferedInputStream (
										 new FileInputStream (
												new File (pathFile))));
		Object ob =  ois.readObject();
		ois.close();
		return ob;
	}
	
	public static void loadConfig (){
		try {
			config = (Config) readObjectIntoTextFile ("ressources/config.ini");
		} catch (ClassNotFoundException | IOException e) {
			e.printStackTrace();
		}
	}

}
