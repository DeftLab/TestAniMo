

import java.io.FileNotFoundException;

import IHM.WindowsInstall;
import IHM.WindowsSorting;
import tools.ExternallyFile;

public class AnimitoMagicMultiThread {


	public static void main(String[] args) throws FileNotFoundException {
		
		//open and read config file
		ExternallyFile.loadConfig();
		
		
		if (ExternallyFile.config.getInstallPath() == null){
			new WindowsInstall ();
			
		}else {
			new WindowsSorting ();
			
		}
		
	}

}


//