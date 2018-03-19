package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import algorithm.RunCroisement;
import algorithm.RunStock;
import enumeration.AnimalFacility;

public class WindowsSorting extends Windows {
	

	private JMenuBar menuBar = new JMenuBar();
	private JMenu option, update;
	private JButton nacStock, nacBreeding, a105Stock, a105Breeding;
	
	public WindowsSorting (){
		super ();
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(480, 275);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		initMenuBar ();
		initComponent ();

		
	    //button listener
	    nacStock.addActionListener(new NacStockButtomListener ());
	    nacBreeding.addActionListener(new NacBreedingButtomListener ());  
	    a105Stock.addActionListener(new A105StockButtomListener ());
	    a105Breeding.addActionListener(new A105BreedingButtomListener ());
	    
	}
	
	
	private void initMenuBar (){
		//menu option
		option = new JMenu ("Options");
		JMenuItem chooseOption = new JMenuItem ("Modifier le nombre de paramètres par souris");
		option.add(chooseOption);
		option.setMnemonic('O');
		
		
		//menu update
		update = new JMenu ("Mise à jours");
		JMenuItem chooseUpdate = new JMenuItem ("Mettre à jour les colonnes");
		update.add(chooseUpdate);
		update.setMnemonic('M');
		
		menuBar.add(option);
		menuBar.add(update);
		menuBar.setPreferredSize(new Dimension (1, 25));
		menuBar.setVisible(true);
		menuBar.validate();
		
		this.setJMenuBar(menuBar);
		this.validate();
		
		//menu listener
		chooseOption.addActionListener(new MenuOptionListener());
		chooseUpdate.addActionListener(new MenuUpdateListener());
	}
	
	
	
	private void initComponent (){
		//general panel
		JPanel panel = new JPanel ();
	    panel.setBounds(0, 26, 469, 250);
	    panel.setBackground(Color.white);
	    panel.validate();
	    
	    //Nac panel
	    JPanel panNac = new JPanel();
	    panNac.setBackground(Color.white);
	    panNac.setPreferredSize(new Dimension(220, 200));
	    panNac.setBorder(BorderFactory.createTitledBorder("Animalerie de la Nac"));   
   	        
	   		    
	    //105 panel
	    JPanel pan105 = new JPanel();
	    pan105.setBackground(Color.white);
	    pan105.setPreferredSize(new Dimension(220, 200));
	    pan105.setBorder(BorderFactory.createTitledBorder("Animalerie du 105"));    
	    
	    //Create and position button
	    buttonCreation (panNac, "Nac");
	    buttonCreation (pan105, "105");
	      
	    
	    panel.add(panNac);
	    panel.add(pan105);
	    panel.validate();
	    
		this.setContentPane(panel);		
	}
	
	
	private void  buttonCreation (JPanel panel, String str){
		 //On crée nos différents conteneurs de couleur différente
	    JPanel cell1 = new JPanel();
	    int x = 180;
	    int y = 50;
	    cell1.setBackground(Color.WHITE);
	    cell1.setPreferredSize(new Dimension(x, y));      
	    JPanel cell2 = new JPanel();
	    cell2.setPreferredSize(new Dimension(x, 25));
	    cell2.setBackground(Color.WHITE);
	    JPanel cell3 = new JPanel();
	    cell3.setBackground(Color.WHITE);
	    cell3.setPreferredSize(new Dimension(x, y));
		        

	    //Le conteneur principal + le layout manager
	    panel.setLayout(new GridBagLayout());       

	    //L'objet servant à positionner les composants
	    GridBagConstraints gbc = new GridBagConstraints();
	        

	    //On positionne la case de départ du composant
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 0;
	    gbc.gridy = 0;

	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    panel.add(cell1, gbc);
	   
	    cell1.setLayout(new BorderLayout());
	    

	    //---------------------------------------------
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 0;
	    gbc.gridy = 1;
	    
	    //La taille en hauteur et en largeur
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;
	    panel.add(cell2, gbc);
	    
	    //-------------------------------------------
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 0;
	    gbc.gridy = 2;
	    panel.add(cell3, gbc);
	    
	    cell3.setLayout(new BorderLayout());
	    
	    if (str.equals("Nac")){
	    	nacStock = new JButton ("Stock");
	    	cell1.add(nacStock, BorderLayout.CENTER);
	    	
	    	nacBreeding = new JButton ("Breeding");
		    cell3.add(nacBreeding, BorderLayout.CENTER);
		    
	    }else if (str.equals("105")) {
	    	a105Stock = new JButton ("Stock");
	    	cell1.add(a105Stock, BorderLayout.CENTER);
	    	
	    	a105Breeding = new JButton ("Breeding");
		    cell3.add(a105Breeding, BorderLayout.CENTER);		    
	    }
	}
	
	
	class MenuOptionListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new WindowsOption ();
			} catch (ClassNotFoundException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
		}
	}	
	
	class MenuUpdateListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			new WindowsUpdate ();
		}		
	}
	
	class NacStockButtomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new RunStock (AnimalFacility.Nac);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException | ClassNotFoundException e1) {
				JOptionPane .showMessageDialog(null, e1.getMessage()
						+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter", "Erreur", JOptionPane.ERROR_MESSAGE);
			}			
		}		
	}
	
	
	class NacBreedingButtomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new RunCroisement (AnimalFacility.Nac);
			} catch (EncryptedDocumentException | InvalidFormatException | ClassNotFoundException
				| IOException e1) {
				JOptionPane .showMessageDialog(null, e1.getMessage()
						+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter" , "Erreur", JOptionPane.ERROR_MESSAGE);

			}
			
		}
		
	}
	
	
	class A105StockButtomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new RunStock (AnimalFacility.a105);
			} catch (EncryptedDocumentException | InvalidFormatException | IOException | ClassNotFoundException e1) {
				JOptionPane .showMessageDialog(null, e1.getMessage()
						+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
	
		
	class A105BreedingButtomListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			try {
				new RunCroisement (AnimalFacility.a105);
			} catch (EncryptedDocumentException | InvalidFormatException | ClassNotFoundException | IOException e1) {
				JOptionPane .showMessageDialog(null, e1.getMessage()
						+ "\n le fichier Excel est vérouillé, il faut l'ouvrir, cliquer sur \"enable editing \", sauvegarder et quitter", "Erreur", JOptionPane.ERROR_MESSAGE);
			}
			
		}
		
	}
}
