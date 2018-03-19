package IHM;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import options.Cage105Options;
import options.CageNacOptions;
import options.CageOptions;
import options.Mice105Options;
import options.MiceNacOptions;
import options.MiceOptions;
import tools.ExternallyFile;

public class WindowsOption extends Windows{
	

	
	private JComboBox<String> animalFacility;
	private JCheckBox boxNumM, boxNumF, boxLineM, boxLineF, boxDateM, boxDateF, boxGenoM, boxGenoF, boxGenM, boxGenF, boxCodeM, boxCodeF,
					  boxNumCage, boxNumBr, boxCodeBr;
	private JButton validButton, cancelButton;
	private MiceNacOptions miceNacOptions ;
	private CageNacOptions cageNacOptions ;
	private Mice105Options mice105Options ;
	private Cage105Options cage105Options ;



	public WindowsOption () throws FileNotFoundException, ClassNotFoundException, IOException{
		this.setSize(520, 430);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		this.setResizable(false);
		this.validate();
		initComponent ();
		
		miceNacOptions = ExternallyFile.config.getMiceNacOptions();
		cageNacOptions = ExternallyFile.config.getCageNacOptions();
		mice105Options = ExternallyFile.config.getMice105Options();
		cage105Options = ExternallyFile.config.getCage105Options();
		
	
	}
	
	public void initComponent (){
		
		JPanel panel = new JPanel ();
		panel.setPreferredSize(new Dimension (500, 400));
		panel.setBackground(Color.WHITE);
		
		 //Le conteneur principal + le layout manager
		panel.setLayout(new GridBagLayout ());
		
		completPanel (panel);
		
	}

	//complet panel with animalfacility panel, MiceOption panel, cageOption panel button valid and cancel panel	
	public void completPanel (JPanel panel){
		
		GridBagConstraints gbc = new GridBagConstraints();
	
		//panel with comboBox for choose animalFacility
			JPanel panChooseAnimalFacility = new JPanel();
			conpletAnimalFacilityPanel (panChooseAnimalFacility);
							
		// Panel for Male with all male mice option 
			JPanel panMiceOption = new JPanel ();
			completMiceOptionPanel (panMiceOption);
				
		// Panel for Cage with all cage option 			
			JPanel panCage = new JPanel ();
			completCagePanel (panCage);

		
		// Panel for button Valid	
			JPanel panValid = new JPanel ();
			completValidButton (panValid);

			
		// Panel for button cancel
			JPanel panCancel= new JPanel ();
			completCancelButton (panCancel);
			
			
			//position du panel choose première ligne
		    gbc.gridheight = 1;
		    gbc.gridwidth = 7;   
		    gbc.gridwidth = GridBagConstraints.REMAINDER;
		    
		    // 1er case de la 1er ligne
		    gbc.gridx = 0;
		    gbc.gridy = 0;	    
		    panel.add(panChooseAnimalFacility, gbc);
			   
		 //position du panel MiceOption sur la 2 eme ligne, première case
		    gbc.gridheight = GridBagConstraints.REMAINDER;
		    gbc.gridheight = 1;
		    gbc.gridwidth = 5; 
		    	    
		    gbc.gridx = 0;
		    gbc.gridy = 1;	       
		    panel.add(panMiceOption, gbc);
		    
		      
		  //position du panel CageOption sur la 2 eme ligne, 3eme case
		//    gbc.gridwidth = GridBagConstraints.REMAINDER;
		    gbc.gridheight = 1;
		    gbc.gridwidth = 2; 
		    gbc.gridx = 5;
		    gbc.gridy = 1;
		    panel.add(panCage, gbc);
		    
		  //position du panel panValid sur la 3 eme ligne, 2eme case
		//    gbc.gridwidth = GridBagConstraints.REMAINDER;
		    gbc.gridwidth = 1; 
		    gbc.gridx = 5;
		    gbc.gridy = 2;
		    panel.add(panValid, gbc);
		    
		    
		  //position du panel panCancel sur la 3 eme ligne, 2eme case
		//    gbc.gridwidth = GridBagConstraints.REMAINDER;
		    gbc.gridx = 6;
		    gbc.gridy = 2;
		    panel.add(panCancel, gbc);

			
			this.setContentPane(panel);	
	}
	
	//Complete AnimalChoose panel
	public void conpletAnimalFacilityPanel (JPanel panel){
		panel.setBackground(Color.WHITE);
		panel.setPreferredSize(new Dimension (500, 60));
		panel.setBorder(BorderFactory.createTitledBorder("Choix de l'animalerie"));		
		JLabel text = new JLabel ("Veuillez choisir votre animalerie : ");
		
		String [] listAnimalFacility = { "Nac", "105", ""};		
		animalFacility = new JComboBox <String> (listAnimalFacility);
		animalFacility.setBackground(Color.WHITE);
		animalFacility.setSelectedIndex(2);
		panel.add(text);
		panel.add(animalFacility);
		
		animalFacility.addActionListener(new ChooseAnimalFacilityListener());
	}
	
	
	
	//complete Mice option panel
	public void  completMiceOptionPanel (JPanel panMiceOption){
		panMiceOption.setBackground(Color.WHITE);
		panMiceOption.setPreferredSize(new Dimension (300, 300));
		panMiceOption.setLayout(new GridBagLayout ());
		panMiceOption.setBorder(BorderFactory.createTitledBorder("Options d'affichage des souris"));
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		int x1 = 135;
		int y1 = 40;
		int x2 = 80;
		int y2 = 40;
		
		JPanel cell1 = new JPanel ();
		cell1.setBackground(Color.WHITE);
		cell1.setPreferredSize(new Dimension (x1, y1 - 10));
		
		JPanel cell2 = new JPanel ();
		cell2.setBackground(Color.WHITE);
		cell2.setPreferredSize(new Dimension (x2, y2 - 10));
		JLabel textMale = new JLabel ("Mâles");
		cell2.add(textMale);
		
		JPanel cell3 = new JPanel ();
		cell3.setBackground(Color.WHITE);
		cell3.setPreferredSize(new Dimension (x2, y2 -10));
		JLabel textFemale = new JLabel ("Femelles");
		cell3.add(textFemale);
		
		JPanel cell4 = new JPanel ();
		cell4.setBackground(Color.WHITE);
		cell4.setPreferredSize(new Dimension (x1, y1));
		JLabel textNum = new JLabel ("Numéro :");
		cell4.add(textNum);
		
		JPanel cell5 = new JPanel ();
		cell5.setBackground(Color.WHITE);
		cell5.setPreferredSize(new Dimension (x2, y2));
		boxNumM = new JCheckBox ();
		boxNumM.setBackground(Color.WHITE);
		boxNumM.addActionListener(new CheckBoxNumM());
		cell5.add(boxNumM);
		
		JPanel cell6 = new JPanel ();
		cell6.setBackground(Color.WHITE);
		cell6.setPreferredSize(new Dimension (x2, y2));
		boxNumF = new JCheckBox ();
		boxNumF.setBackground(Color.WHITE);
		boxNumF.addActionListener(new CheckBoxNumF());
		cell6.add(boxNumF);
		
		JPanel cell7 = new JPanel ();
		cell7.setBackground(Color.WHITE);
		cell7.setPreferredSize(new Dimension (x1, y1));
		JLabel textLine = new JLabel ("Lignée :");
		cell7.add(textLine);
		
		JPanel cell8 = new JPanel ();
		cell8.setBackground(Color.WHITE);
		cell8.setPreferredSize(new Dimension (x2, y2));
		boxLineM = new JCheckBox ();
		boxLineM.setBackground(Color.WHITE);
		boxLineM.addActionListener(new CheckBoxLineM());
		cell8.add(boxLineM);
		
		JPanel cell9 = new JPanel ();
		cell9.setBackground(Color.WHITE);
		cell9.setPreferredSize(new Dimension (x2, y2));
		boxLineF = new JCheckBox ();
		boxLineF.setBackground(Color.WHITE);
		boxLineF.addActionListener(new CheckBoxLineF());
		cell9.add(boxLineF);
		
		JPanel cell10 = new JPanel ();
		cell10.setBackground(Color.WHITE);
		cell10.setPreferredSize(new Dimension (x1, y1));
		JLabel textDate = new JLabel ("Date :");
		cell10.add(textDate);
		
		JPanel cell11 = new JPanel ();
		cell11.setBackground(Color.WHITE);
		cell11.setPreferredSize(new Dimension (x2, y2));
		boxDateM = new JCheckBox ();
		boxDateM.setBackground(Color.WHITE);
		boxDateM.addActionListener(new CheckBoxDateM());
		cell11.add(boxDateM);
		
		JPanel cell12 = new JPanel ();
		cell12.setBackground(Color.WHITE);
		cell12.setPreferredSize(new Dimension (x2, y2));
		boxDateF = new JCheckBox ();
		boxDateF.setBackground(Color.WHITE);
		boxDateF.addActionListener(new CheckBoxDateF());
		cell12.add(boxDateF);
		
		JPanel cell13 = new JPanel ();
		cell13.setBackground(Color.WHITE);
		cell13.setPreferredSize(new Dimension (x1, y1));
		JLabel textGeno = new JLabel ("Génotype :");
		cell13.add(textGeno);
		
		JPanel cell14 = new JPanel ();
		cell14.setBackground(Color.WHITE);
		cell14.setPreferredSize(new Dimension (x2, y2));
		boxGenoM = new JCheckBox ();
		boxGenoM.setBackground(Color.WHITE);
		boxGenoM.addActionListener(new CheckBoxGenoM());
		cell14.add(boxGenoM);
		
		JPanel cell15 = new JPanel ();
		cell15.setBackground(Color.WHITE);
		cell15.setPreferredSize(new Dimension (x2, y2));
		boxGenoF = new JCheckBox ();
		boxGenoF.setBackground(Color.WHITE);
		boxGenoF.addActionListener(new CheckBoxGenoF());
		cell15.add(boxGenoF);
		
		JPanel cell16 = new JPanel ();
		cell16.setBackground(Color.WHITE);
		cell16.setPreferredSize(new Dimension (x1, y1));
		JLabel textGene = new JLabel ("Gene :");
		cell16.add(textGene);
		
		JPanel cell17 = new JPanel ();
		cell17.setBackground(Color.WHITE);
		cell17.setPreferredSize(new Dimension (x2, y2));
		boxGenM = new JCheckBox ();
		boxGenM.setBackground(Color.WHITE);
		boxGenM.addActionListener(new CheckBoxGenM());
		cell17.add(boxGenM);
		
		JPanel cell18 = new JPanel ();
		cell18.setBackground(Color.WHITE);
		cell18.setPreferredSize(new Dimension (x2, y2));
		boxGenF = new JCheckBox ();
		boxGenF.setBackground(Color.WHITE);
		boxGenF.addActionListener(new CheckBoxGenF());
		cell18.add(boxGenF);
		
		JPanel cell19 = new JPanel ();
		cell19.setBackground(Color.WHITE);
		cell19.setPreferredSize(new Dimension (x1, y1));
		JLabel textCode = new JLabel ("Code :");
		cell19.add(textCode);
		
		JPanel cell20 = new JPanel ();
		cell20.setBackground(Color.WHITE);
		cell20.setPreferredSize(new Dimension (x2, y2));
		boxCodeM = new JCheckBox ();
		boxCodeM.setBackground(Color.WHITE);
		boxCodeM.addActionListener(new CheckBoxCodeM());
		cell20.add(boxCodeM);
		
		JPanel cell21 = new JPanel ();
		cell21.setBackground(Color.WHITE);
		cell21.setPreferredSize(new Dimension (x2, y2));
		boxCodeF = new JCheckBox ();
		boxCodeF.setBackground(Color.WHITE);
		boxCodeF.addActionListener(new CheckBoxCodeF());
		cell21.add(boxCodeF);
		
		JPanel cell22 = new JPanel ();
		cell22.setBackground(Color.WHITE);
		cell22.setPreferredSize(new Dimension (x2, 1));
		
	
		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panMiceOption.add(cell1, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 0;
		panMiceOption.add(cell2, gbc);

		gbc.gridx = 4;
		gbc.gridy = 0;;
		panMiceOption.add(cell3, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 1;
		panMiceOption.add(cell4, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 1;
		panMiceOption.add(cell5, gbc);

		gbc.gridx = 4;
		gbc.gridy = 1;
		panMiceOption.add(cell6, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panMiceOption.add(cell7, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 2;
		panMiceOption.add(cell8, gbc);

		gbc.gridx = 4;
		gbc.gridy = 2;
		panMiceOption.add(cell9, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 3;
		panMiceOption.add(cell10, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 3;
		panMiceOption.add(cell11, gbc);

		gbc.gridx = 4;
		gbc.gridy = 3;
		panMiceOption.add(cell12, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 4;
		panMiceOption.add(cell13, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 4;
		panMiceOption.add(cell14, gbc);

		gbc.gridx = 4;
		gbc.gridy = 4;
		panMiceOption.add(cell15, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 5;
		panMiceOption.add(cell16, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 5;
		panMiceOption.add(cell17, gbc);

		gbc.gridx = 4;
		gbc.gridy = 5;
		panMiceOption.add(cell18, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 6;
		panMiceOption.add(cell19, gbc);
		
		gbc.gridx = 1;
		gbc.gridy = 6;
		panMiceOption.add(cell20, gbc);
		
		gbc.gridx = 4;
		gbc.gridy = 6;
		panMiceOption.add(cell21, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 7;
		panMiceOption.add(cell22, gbc);
		
	}
	
	public void completCagePanel (JPanel panCage){
		panCage.setBackground(Color.WHITE);
		panCage.setPreferredSize(new Dimension (200 , 300));
		panCage.setBorder(BorderFactory.createTitledBorder("Options d'affichage des cages"));
		panCage.setLayout(new GridBagLayout ());
		
		GridBagConstraints gbc = new GridBagConstraints();
		
		int x1 = 140;
		int y = 50;
		int x2 = 50;

		
				
		JPanel cell1 = new JPanel ();
		cell1.setBackground(Color.WHITE);
		cell1.setPreferredSize(new Dimension (x1, y));
		JLabel textCodeBr = new JLabel ("Code Breeding :");
		cell1.add(textCodeBr);
		
		JPanel cell2 = new JPanel ();
		cell2.setBackground(Color.WHITE);
		cell2.setPreferredSize(new Dimension (x2, y));
		boxCodeBr = new JCheckBox ();
		boxCodeBr.setBackground(Color.WHITE);
		boxCodeBr.addActionListener(new CheckBoxCodeBr());
		cell2.add(boxCodeBr);
		
		JPanel cell3 = new JPanel ();
		cell3.setBackground(Color.WHITE);
		cell3.setPreferredSize(new Dimension (x1, y));
		JLabel textNumBr = new JLabel ("Numéro Breeding :");
		cell3.add(textNumBr);
	
		JPanel cell4 = new JPanel ();
		cell4.setBackground(Color.WHITE);
		cell4.setPreferredSize(new Dimension (x2, y));
		boxNumBr = new JCheckBox () ;
		boxNumBr.setBackground(Color.WHITE);
		boxNumBr.addActionListener(new CheckBoxNumBr());
		cell4.add(boxNumBr);

		
		JPanel cell5 = new JPanel ();
		cell5.setBackground(Color.WHITE);
		cell5.setPreferredSize(new Dimension (x1, y));
		JLabel textNumCage = new JLabel ("Numéro Cage :");
		cell5.add(textNumCage);
	
		JPanel cell6 = new JPanel ();
		cell6.setBackground(Color.WHITE);
		cell6.setPreferredSize(new Dimension (x2, y));
		boxNumCage = new JCheckBox () ;
		boxNumCage.setBackground(Color.WHITE);
		boxNumCage.addActionListener(new CheckBoxNumCage());
		cell6.add(boxNumCage);

		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		panCage.add(cell1, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		panCage.add(cell2, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;;
		panCage.add(cell3, gbc);
		
		gbc.gridx = 2;
		gbc.gridy = 1;
		panCage.add(cell4, gbc);
		
		gbc.gridx = 0;
		gbc.gridy = 2;
		panCage.add(cell5, gbc);

		gbc.gridx = 2;
		gbc.gridy = 2;
		panCage.add(cell6, gbc);
	}
	
	public void completValidButton (JPanel panValid){
		panValid.setBackground(Color.WHITE);
		panValid.setPreferredSize(new Dimension (110 , 40));
		validButton = new JButton ("Sauvegarder");
		validButton.addActionListener(new SaveButtonListener(this));
		panValid.add(validButton);
		
	}
	
	
	public void completCancelButton (JPanel panCancel){
		panCancel.setBackground(Color.WHITE);
		panCancel.setPreferredSize(new Dimension (100 , 40));
		cancelButton = new JButton ("Annuler");
		cancelButton.addActionListener(new CancelButtonListener(this));
		panCancel.add(cancelButton);
	}	
	
	class ChooseAnimalFacilityListener implements ActionListener {
		@Override
		public void actionPerformed(ActionEvent e) {
			String animalFacilityName = (String) animalFacility.getSelectedItem();
			
			if (animalFacilityName.equals("Nac")){
				updateCheckBox(miceNacOptions, cageNacOptions);
			}else if (animalFacilityName.equals("105")){
				updateCheckBox(mice105Options, cage105Options);
			}else {
				rezetCheckBox();
			}
		}
		
		
		private void updateCheckBox (MiceOptions miceOpt, CageOptions cageOpt){
		
			//update miceOption
			boxNumM.setSelected(miceOpt.isMaleNum()); 
			boxNumF.setSelected(miceOpt.isFemaleNum());
			boxLineM.setSelected(miceOpt.isMaleLine());
			boxLineF.setSelected(miceOpt.isFemaleLine());
			boxDateM.setSelected(miceOpt.isMaleDate());
			boxDateF.setSelected(miceOpt.isFemaleDate());
			boxGenoM.setSelected(miceOpt.isMaleGeno());
			boxGenoF.setSelected(miceOpt.isFemaleGeno());
			boxGenM.setSelected(miceOpt.isMaleGene());
			boxGenF.setSelected(miceOpt.isFemaleGene());
			boxCodeM.setSelected(miceOpt.isMaleCode());
			boxCodeF.setSelected(miceOpt.isFemaleCode());
			
			//update cageOption
			boxNumCage.setSelected(cageOpt.isNumberCage());
			boxNumBr.setSelected(cageOpt.isNumberCage());
			boxCodeBr.setSelected(cageOpt.isCodeBreeding());
		}
		
		private void rezetCheckBox(){
			boxNumM.setSelected(false); 
			boxNumF.setSelected(false);
			boxLineM.setSelected(false);
			boxLineF.setSelected(false);
			boxDateM.setSelected(false);
			boxDateF.setSelected(false);
			boxGenoM.setSelected(false);
			boxGenoF.setSelected(false);
			boxGenM.setSelected(false);
			boxGenF.setSelected(false);
			boxCodeM.setSelected(false);
			boxCodeF.setSelected(false);
			
			//update cageOption
			boxNumCage.setSelected(false);
			boxNumBr.setSelected(false);
			boxCodeBr.setSelected(false);
		}
		
	}
	
	class CheckBoxListener implements ActionListener {
		private MiceOptions miceOpt;
		private CageOptions cageOpt;
		
		@Override
		public void actionPerformed(ActionEvent e) {
			animalFicilityIsChoose ();
			
		}
		
		private void animalFicilityIsChoose (){
			String animalFacilityName = (String) animalFacility.getSelectedItem();
				if (animalFacilityName.equals("")){
					//Boîte du message d'erreur
					JOptionPane .showMessageDialog(null, "Veuillez selection une animalerie \n avant de modifier des paramètres", "Erreur", JOptionPane.ERROR_MESSAGE);
				}else if (animalFacilityName.equals("Nac")){
					miceOpt =  (MiceNacOptions) miceNacOptions;
					cageOpt =  (CageNacOptions) cageNacOptions;
				}else if (animalFacilityName.equals("105")){
					miceOpt = (Mice105Options) mice105Options;
					cageOpt = (Cage105Options) cage105Options;
				}	
				
		}

		public MiceOptions getMiceOpt() {
			return miceOpt;
		}
		
		public CageOptions getCageOpt() {
			return cageOpt;
		}


		
	}
	
	class CheckBoxNumM extends CheckBoxListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleNum(boxNumM.isSelected());
			}	
		}
		
	}
	
	class CheckBoxNumF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setFemaleNum(boxNumF.isSelected());
			}	
		}
		
	}
	
	class CheckBoxLineM extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleLine(boxLineM.isSelected());
			}	
		}
		
	}
	
	class CheckBoxLineF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setFemaleLine(boxLineF.isSelected());
			}	
		}
		
	}
	
	class CheckBoxDateM extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleDate(boxDateM.isSelected());
			}	
		}
		
	}
	
	class CheckBoxDateF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){	
				getMiceOpt().setFemaleDate(boxDateF.isSelected());
			}	
		}
		
	}
	
	class CheckBoxGenoM extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleGeno(boxGenoM.isSelected());
			}	
		}
		
	}
	
	class CheckBoxGenoF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setFemaleGeno(boxGenoF.isSelected());
			}	
		}
		
	}
	
	class CheckBoxGenM extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleGene(boxGenM.isSelected());
			}	
		}
		
	}
	
	class CheckBoxGenF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setFemaleGene(boxGenF.isSelected());
			}
		}
		
	}
	
	class CheckBoxCodeM extends CheckBoxListener implements ActionListener {
		
		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setMaleCode(boxCodeM.isSelected());
			}
		}
		
	}
	
	class CheckBoxCodeF extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getMiceOpt() != null){
				getMiceOpt().setFemaleCode(boxCodeF.isSelected());
			}
		}
		
	}
	
	class CheckBoxNumCage extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getCageOpt() != null){
				getCageOpt().setNumberCage(boxNumCage.isSelected());
			}
		}
		
	}
	
	class CheckBoxNumBr extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getCageOpt() != null){
				getCageOpt().setNumBreeding(boxNumBr.isSelected());		
			}
		}
		
	}
	
	class CheckBoxCodeBr extends CheckBoxListener implements ActionListener {

		@Override
		public void actionPerformed(ActionEvent e) {
			super.actionPerformed(e);
			if (getCageOpt() != null){
				getCageOpt().setCodeBreeding(boxCodeBr.isSelected());	
			}
		}
		
	}
	
	class SaveButtonListener implements ActionListener {
		private WindowsOption wo;
		
		public SaveButtonListener (WindowsOption wo){
			super ();
			this.wo = wo;
		}
		
		@Override
		public void actionPerformed (ActionEvent e) {
			
			try {
				ExternallyFile.saveObjectIntoTextFile(ExternallyFile.config, "ressources/config.ini");
								
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}			
			wo.setVisible(false);			
		}
		
	}
	
	class CancelButtonListener implements ActionListener {
		
		private WindowsOption wo;
		
		public CancelButtonListener (WindowsOption wo){
			super ();
			this.wo = wo;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			wo.setVisible(false);
			
		}
		
	}
		
}
