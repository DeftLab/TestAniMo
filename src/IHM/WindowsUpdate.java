package IHM;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Toolkit;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import listener.UpdateCancelButtonListener;
import listener.UpdateCroisListener;
import listener.UpdateQuitButtonListener;
import listener.UpdateSaveListener;
import listener.UpdateStockListener;
import tools.ExternallyFile;
import tools.Update;
import workbook.HeaderLabel;

public class WindowsUpdate extends Windows{
	private ArrayList <Integer> columnPosition;
	private JPanel content, updateInfo, panelText, panelButtonStock , panelButtonCrois, panelSeparator1, panelSeparator2, panelSeparator3  ;
	private JLabel text;
	private JButton updateStockButton, updateCroisButton;
	private JTable stockTable, croisTable;
	private HeaderLabel stockHeader, croisHeader;

	
	
	public WindowsUpdate (){
		this.setSize(800, 583);
		this.setDefaultCloseOperation(HIDE_ON_CLOSE);
		this.setVisible(true);
		Dimension screen = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screen.width - this.getSize().width)/2,(screen.height - this.getSize().height)/2);
		initContent ();
	}
	
	
	public JPanel getUpdateInfoPanel (){
		return updateInfo;
	}
	
	public JPanel getContentPanel (){
		return content;
	}
	
	public void setColumnPosition (ArrayList <Integer> columnLabel){
		this.columnPosition = columnLabel;
	}
	
	public JTable getCroisTable (){
		return croisTable;
	}
	
	public JTable getStockTable (){
		return stockTable;
	}
	
		
	public HeaderLabel getStockHeader() {
		return stockHeader;
	}

	public void setStockHeader(HeaderLabel stockHeader) {
		this.stockHeader = stockHeader;
	}

	public HeaderLabel getCroisHeader() {
		return croisHeader;
	}

	public void setCroisHeader(HeaderLabel croisHeader) {
		this.croisHeader = croisHeader;
	}

	public void initContent (){
		content = new JPanel ();	
		content.setBackground(Color.WHITE);
		content.add(initOnGoingInformationPane ());
		content.add(initUpdateInformationPane ());
		content.add(initButtonPanel());	
		this.setContentPane(content);
	}
	
	public JPanel initOnGoingInformationPane (){
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel onGoingInfo = new JPanel (new GridBagLayout ());
		onGoingInfo = panelFactory (onGoingInfo, "Données utilisées actuellement pour les tris");
		
		JPanel panel1 = new JPanel (new BorderLayout ());
		panel1.setBackground(Color.WHITE);
		panel1.setPreferredSize(new Dimension (750,15));
		JLabel label1 = new JLabel ("Colonnes utiles des stock :");
		panel1.add(label1, BorderLayout.CENTER);
		
		JPanel panel2 = new JPanel (new BorderLayout ());
		panel2.setBackground(Color.RED);
		panel2.setPreferredSize(new Dimension (750,60));
		panel2.add(new JScrollPane(createTable (ExternallyFile.config.getStockColumnLabel(), ExternallyFile.config.getStockColumnPosition()), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
		JPanel panel3 = new JPanel (new BorderLayout ());
		panel3.setBackground(Color.WHITE);
		panel3.setPreferredSize(new Dimension (750,30));
		
		JPanel panel4 = new JPanel (new BorderLayout ());
		panel4.setBackground(Color.WHITE);
		panel4.setPreferredSize(new Dimension (750,15));
		JLabel label2 = new JLabel ("Colonnes utiles des croisements :");
		panel4.add(label2, BorderLayout.CENTER);
		
		JPanel panel5 = new JPanel (new BorderLayout ());
		panel5.setBackground(Color.WHITE);
		panel5.setPreferredSize(new Dimension (750,60));
		panel5.add(new JScrollPane(createTable (ExternallyFile.config.getCroisColumnLabel(), ExternallyFile.config.getCroisColumnPosition()), JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		
			
		//position du panel 
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;   

	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridx = 0;
	    gbc.gridy = 0;	    
		onGoingInfo.add(panel1, gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 2;
		gbc.gridy = 1;
		onGoingInfo.add(panel2, gbc);
		
		gbc.gridwidth = 1;
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridy = 2;
		onGoingInfo.add(panel3, gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 2;
		gbc.gridy = 3;
		onGoingInfo.add(panel4, gbc);
		
		gbc.gridwidth = GridBagConstraints.REMAINDER;
		gbc.gridwidth = 2;
		gbc.gridy = 4;
		onGoingInfo.add(panel5, gbc);
		
		return onGoingInfo;
	}
	
	public JPanel initUpdateInformationPane (){
		updateInfo = new JPanel (new GridBagLayout ());
		updateInfo = panelFactory (updateInfo, "Données pour l'update");
		
		initUpdateButtonPanel ();

		return updateInfo;
	}
	
	
	public void initUpdateButtonPanel (){
		GridBagConstraints gbc = new GridBagConstraints();
		
		panelText = new JPanel ();
		panelText.setBackground(Color.WHITE);
		panelText.setPreferredSize(new Dimension (750, 25));
		text = new JLabel ("Cliquez \"Stock\" ou \"Croisement\" pour mettre à jour AnimotoMagic et complétez manuellement les champs \"Acompléter\" :");
		panelText.add (text);
				
		panelSeparator1 = new JPanel ();
		panelSeparator1.setBackground(Color.WHITE);
		panelSeparator1.setPreferredSize(new Dimension (750, 15));
		
		panelSeparator2 = new JPanel ();
		panelSeparator2.setBackground(Color.WHITE);
		panelSeparator2.setPreferredSize(new Dimension (750, 15));
		
		panelSeparator3 = new JPanel ();
		panelSeparator3.setBackground(Color.WHITE);
		panelSeparator3.setPreferredSize(new Dimension (750, 15));
		
		panelButtonStock = new JPanel ();
		panelButtonStock.setBackground(Color.WHITE);
		panelButtonStock.setPreferredSize(new Dimension (750, 60));
		updateStockButton = new JButton ("Stock");
		updateStockButton.addActionListener (new UpdateStockListener(this));
		panelButtonStock.add (updateStockButton);
		
		panelButtonCrois = new JPanel ();
		panelButtonCrois.setBackground(Color.WHITE);
		panelButtonCrois.setPreferredSize(new Dimension (750, 60));
		updateCroisButton = new JButton ("Croisement");
		updateCroisButton.addActionListener(new UpdateCroisListener(this));
		panelButtonCrois.add (updateCroisButton);
		
		//position du panel 
	    gbc.gridheight = 1;
	    gbc.gridwidth = 1;   
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    
	    gbc.gridx = 0;
	    gbc.gridy = 0;
	    updateInfo.add(panelText, gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridy = 1;
	    updateInfo.add(panelSeparator1, gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridy = 2;
	    updateInfo.add(panelButtonStock, gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridy = 3;
	    updateInfo.add(panelSeparator2, gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridy = 4;
	    updateInfo.add(panelSeparator3, gbc);
	    
	    gbc.gridwidth = GridBagConstraints.REMAINDER;
	    gbc.gridy = 5;
	    updateInfo.add(panelButtonCrois, gbc);
	    
	}
	
	public JPanel panelFactory (JPanel pan, String titleBorder){
		pan.setPreferredSize(new Dimension(770, 250));
		pan.setBackground(Color.WHITE);
		pan.setVisible(true);
		pan.setBorder(BorderFactory.createTitledBorder(titleBorder));		
		return pan;
	}
		
	private JTable createTable (ArrayList<String> labelList, ArrayList<Integer> positionList){
		
		String [] labelTab = new String [labelList.size()];
		labelList.toArray(labelTab);
		
		Object[][] positionTab = new String [1][positionList.size()];
		
			
		for (int i = 0; i<positionList.size(); ++i){
			if (!positionList.get(i).equals(99)){
				positionTab [0][i] = Update.whatColumnLetter(positionList, i, false);
			}else {
				positionTab [0][i] = "A compléter";
			}
		}
		
		DefaultTableModel tableModel = new DefaultTableModel(positionTab, labelTab);       
		JTable table = new JTable ();		
		table.setModel(tableModel);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);			
		table.setRowHeight(22);
				
		return table;
	}
	
	public void setUpdatePanel (boolean isStock){
			
		//create title tab
		ArrayList<String> labelList;
		if (isStock){
			labelList = ExternallyFile.config.getStockColumnLabel();
		}else {
			labelList = ExternallyFile.config.getCroisColumnLabel();
		}
				
		//clean panel and create a new one
		if (isStock){
			//Delete text and add a new one with a BorderLayout
			panelText.removeAll();
			panelText.updateUI();
			panelText.setLayout(new BorderLayout ());
			
			JLabel text = new JLabel("Nouvelles colonnes utiles pour les stock : ");
			panelText.add(text, BorderLayout.CENTER);
			
			//delete button and replace it by a Table
			panelButtonStock.removeAll();		
			panelButtonStock.updateUI();
			
			updateInfo.remove(panelSeparator1);
			updateInfo.updateUI();
			panelButtonStock.setLayout(new BorderLayout ());
			stockTable = createTable (labelList, columnPosition);
			panelButtonStock.add(new JScrollPane (stockTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));		
		} else {
			//delete button and replace it by a Table
			panelButtonCrois.removeAll();		
			panelButtonCrois.updateUI();
			
			JLabel text = new JLabel ("Nouvelles colonnes utiles pour les croisements");
			panelSeparator3.setLayout(new BorderLayout ());
			panelSeparator3.add(text, BorderLayout.CENTER);
			panelSeparator3.setPreferredSize(new Dimension (750, 25));
			
			panelButtonCrois.setLayout(new BorderLayout ());
			croisTable = createTable (labelList, columnPosition);
			panelButtonCrois.add(new JScrollPane (croisTable, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED));
		}			
	}
	
	//last part with save, cancel and quit button
	public JPanel initButtonPanel (){
		GridBagConstraints gbc = new GridBagConstraints();
		
		JPanel buttonPanel = new JPanel (new GridBagLayout ());
		buttonPanel.setPreferredSize(new Dimension(800, 40));
		
		//cancel button
		JPanel cancelPanel = new JPanel (new BorderLayout ());
		cancelPanel.setBackground(Color.WHITE);
		cancelPanel.setPreferredSize(new Dimension (80,30));
		JButton cancelButton = new JButton ("Annuler");
		cancelButton.addActionListener(new UpdateCancelButtonListener (this));
		cancelPanel.add(cancelButton, BorderLayout.CENTER);
		
		//save button
		JPanel savePanel = new JPanel (new BorderLayout ());
		savePanel.setBackground(Color.WHITE);
		savePanel.setPreferredSize(new Dimension (110,30));
		JButton saveButton = new JButton ("Sauvegarder");
		saveButton.addActionListener (new UpdateSaveListener (this));
		savePanel.add(saveButton, BorderLayout.CENTER);
		
		//quitbutton
		JPanel quitPanel = new JPanel (new BorderLayout ());
		quitPanel.setBackground(Color.WHITE);
		quitPanel.setPreferredSize(new Dimension (80,30));
		JButton quitButton = new JButton ("Quitter");
		quitButton.addActionListener(new UpdateQuitButtonListener (this));
		quitPanel.add(quitButton, BorderLayout.CENTER);

		JPanel separator = new JPanel ();
		separator.setPreferredSize(new Dimension (470, 30));

		
		JPanel separator1 = new JPanel ();
		separator1.setPreferredSize(new Dimension (15, 20));

		
		JPanel separator2 = new JPanel ();
		separator1.setPreferredSize(new Dimension (15, 20));

		
		gbc.gridheight = 1;
		gbc.gridwidth = 1;
		
		gbc.gridx = 0;
		gbc.gridy = 0;
		buttonPanel.add(separator, gbc);
		
		gbc.gridx = 1;
		buttonPanel.add(cancelPanel, gbc);
		
		gbc.gridx = 2;
		buttonPanel.add(separator1, gbc);
		
		gbc.gridx = 3;
		buttonPanel.add(savePanel, gbc);
		
		gbc.gridx = 4;
		buttonPanel.add(separator2, gbc);
		
		gbc.gridx = 5;
		buttonPanel.add(quitPanel, gbc);
		
		
		return buttonPanel;
	}

}
