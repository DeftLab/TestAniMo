package exception;

import javax.swing.JOptionPane;

public class UpdateException extends Exception{
	
	
	public UpdateException (String columnLetter, String columnLabel){
		super ("");
		JOptionPane.showMessageDialog(null, "AnimotoMagic n�cessite une mise � jour:"
                + "\nla colonne " + columnLabel + " n'est pas dans la colonne " + columnLetter  
                + "\n mettre � jour Animotomagic via le menu: Mise � jour .", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

}
