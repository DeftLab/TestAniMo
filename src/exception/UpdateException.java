package exception;

import javax.swing.JOptionPane;

public class UpdateException extends Exception{
	
	
	public UpdateException (String columnLetter, String columnLabel){
		super ("");
		JOptionPane.showMessageDialog(null, "AnimotoMagic nécessite une mise à jour:"
                + "\nla colonne " + columnLabel + " n'est pas dans la colonne " + columnLetter  
                + "\n mettre à jour Animotomagic via le menu: Mise à jour .", "Information", JOptionPane.INFORMATION_MESSAGE);
	}

}
