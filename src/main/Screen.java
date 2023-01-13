package main;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.util.*;

/**
 * The class that provides the front-end view of the program
 */
public class Screen {
	
	//For the entire application
	public JFrame frame;
	public JPanel mainPanel;
	public JButton back = new JButton("<< Zurück");
	private Dimension standardOpButtonSize = new Dimension(100, 30);
	//For the result pop-up-frame
	public JFrame defaultResultFrame;
	public JPanel defaultResultPanel;
	public JFrame fireworksResultFrame;
	public JPanel fireworksResultPanel;
	private String[] letters = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
	//For the main screen
	public JButton roads;
	public JButton water;
	public JButton electricity;
	public JButton fireworks;
	public JButton invitations;
	public JButton traffic;
	public JButton employees;
	//For road screen
	private JPanel roadPanel;
	private JPanel roadOpPanel;
	public DefaultTableModel roadTableModel = new DefaultTableModel(10, 10); //Declared separately to save the contents on page reload
	public JTable roadTable;
	public JButton roadReadFile;
	public JButton roadSaveChanges;
	public JButton roadSizePlus;
	public JButton roadSizeMinus;
	public JButton calculateRoads;
	public JButton deleteRoadTable;
	//For water screen
	private JPanel waterPanel;
	private JPanel waterOpPanel;
	public DefaultTableModel waterTableModel = new DefaultTableModel(10, 10);
	public JTable waterTable;
	public JLabel waterNeededDesc;
	public JTextField waterNeeded;
	public JButton waterReadFile;
	public JButton waterSaveChanges;
	public JButton waterSizePlus;
	public JButton waterSizeMinus;
	public JButton calculateWater;
	public JButton deleteWaterTable;
	//For electricity screen
	private JPanel elecPanel;
	private JPanel elecOpPanel;
	public DefaultTableModel elecTableModel = new DefaultTableModel(10, 10);
	public JTable elecTable;
	public JButton elecReadFile;
	public JButton elecSaveChanges;
	public JButton elecSizePlus;
	public JButton elecSizeMinus;
	public JButton calculateElec;
	public JButton deleteElecTable;
	//For fireworks screen
	private JPanel fireworksPanel;
	private JPanel fireworksOpPanel;
	public DefaultTableModel fireworksTableModel = new DefaultTableModel(10, 10);
	public JTable fireworksTable;
	public JButton fireworksReadFile;
	public JButton fireworksSaveChanges;
	public JButton fireworksSizePlus;
	public JButton fireworksSizeMinus;
	public JButton calculateFireworks;
	public JButton deleteFireworksTable;
	//For invitations screen
	private JPanel invitationsPanel;
	private JPanel invitationsOpPanel;
	public DefaultTableModel invitationsTableModel = new DefaultTableModel(10, 10);
	public JTable invitationsTable;
	public JButton invitationsReadFile;
	public JButton invitationsSaveChanges;
	public JButton invitationsSizePlus;
	public JButton invitationsSizeMinus;
	public JButton calculateInvitations;
	public JButton deleteInvitationsTable;
	//For traffic screen
	private JPanel trafficPanel;
	private JPanel trafficOpPanel;
	public DefaultTableModel trafficTableModel = new DefaultTableModel(10, 10);
	public JTable trafficTable;
	public JButton trafficReadFile;
	public JButton trafficSaveChanges;
	public JButton trafficSizePlus;
	public JButton trafficSizeMinus;
	public JButton calculateTraffic;
	public JButton deleteTrafficTable;
	//For employees screen
	private JPanel employeesPanel;
	private JPanel employeesOpPanel;
	public DefaultTableModel employeesTableModel = new DefaultTableModel(10, 10);
	public JTable employeesTable;
	public JButton employeesReadFile;
	public JButton employeesSaveChanges;
	public JButton employeesSizePlus;
	public JButton employeesSizeMinus;
	public JButton calculateEmployees;
	public JButton deleteEmployeesTable;
	
	
	
	public Screen() {
		initFrame();
		createMainScreen();
		frame.setVisible(true);
	}
	
	private void initFrame() {
		frame = new JFrame("CityManager");
		frame.setSize(1000, 562);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		mainPanel = new JPanel();
		frame.setContentPane(mainPanel);
	}

	public void createMainScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new GridBagLayout());
		
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(5, 0, 5, 0);
		
		//Aligns all buttons below each other
		roads = new JButton("Straßenanbindung");
		c.gridy = 0;
		mainPanel.add(roads, c);
		
		water = new JButton("Wasserversorgung");
		c.gridy = 1;
		mainPanel.add(water, c);
		
		electricity = new JButton("Stromversorgung");
		c.gridy = 2;
		mainPanel.add(electricity, c);
		
		fireworks = new JButton("Feuerwerk");
		c.gridy = 3;
		mainPanel.add(fireworks, c);
		
		invitations = new JButton("Verteilung der Einladungen");
		c.gridy = 4;
		mainPanel.add(invitations, c);
		
		traffic = new JButton("Verkehrsplanung");
		c.gridy = 5;
		mainPanel.add(traffic, c);
		
		employees = new JButton("Personalverwaltung");
		c.gridy = 6;
		mainPanel.add(employees, c);
		
		//The size of the buttons
		Dimension mainButtonSize = new Dimension(500, 50);
		roads.setPreferredSize(mainButtonSize);
		water.setPreferredSize(mainButtonSize);
		electricity.setPreferredSize(mainButtonSize);
		fireworks.setPreferredSize(mainButtonSize);
		invitations.setPreferredSize(mainButtonSize);
		traffic.setPreferredSize(mainButtonSize);
		employees.setPreferredSize(mainButtonSize);
		
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createRoadScreen() {
		mainPanel.removeAll();
		mainPanel.repaint();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		roadPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the road panel
		roadTable = new JTable(roadTableModel);
		c.gridx = 0;
		setColumnSize(roadTable, 30);
		roadPanel.add(roadTable, c);
		
		roadOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		roadPanel.add(roadOpPanel, c);
		
		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		roadReadFile = new JButton("Einlesen");
		d.gridy = 0;
		roadOpPanel.add(roadReadFile, d);
		
		roadSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		roadOpPanel.add(roadSaveChanges, d);
		
		roadSizePlus = new JButton("+");
		d.gridy = 2;
		roadOpPanel.add(roadSizePlus, d);
		
		roadSizeMinus = new JButton("-");
		d.gridy = 3;
		roadOpPanel.add(roadSizeMinus, d);
		
		calculateRoads = new JButton("Berechnen");
		d.gridy = 4;
		roadOpPanel.add(calculateRoads, d);
		
		deleteRoadTable = new JButton("Löschen");
		d.gridy = 5;
		roadOpPanel.add(deleteRoadTable, d);
		
		roadReadFile.setPreferredSize(standardOpButtonSize);
		roadSaveChanges.setPreferredSize(standardOpButtonSize);
		roadSizePlus.setPreferredSize(standardOpButtonSize);
		roadSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateRoads.setPreferredSize(standardOpButtonSize);
		deleteRoadTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(roadPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
	}
	
	public void createWaterScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		waterPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the water panel
		waterTable = new JTable(waterTableModel);
		c.gridx = 0;
		setColumnSize(waterTable, 30);
		waterPanel.add(waterTable, c);
		
		waterOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		waterPanel.add(waterOpPanel, c);
		
		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		waterNeededDesc = new JLabel("Benötigtes Wasser in m³/s:");
		d.gridy = 0;
		waterOpPanel.add(waterNeededDesc, d);
		
		waterNeeded = new JTextField();
		d.gridy = 1;
		waterOpPanel.add(waterNeeded, d);
		
		waterReadFile = new JButton("Einlesen");
		d.gridy = 2;
		waterOpPanel.add(waterReadFile, d);
		
		waterSaveChanges = new JButton("Speichern");
		d.gridy = 3;
		waterOpPanel.add(waterSaveChanges, d);
		
		waterSizePlus = new JButton("+");
		d.gridy = 4;
		waterOpPanel.add(waterSizePlus, d);
		
		waterSizeMinus = new JButton("-");
		d.gridy = 5;
		waterOpPanel.add(waterSizeMinus, d);
		
		calculateWater = new JButton("Berechnen");
		d.gridy = 6;
		waterOpPanel.add(calculateWater, d);
		
		deleteWaterTable = new JButton("Löschen");
		d.gridy = 7;
		waterOpPanel.add(deleteWaterTable, d);

		waterNeeded.setPreferredSize(standardOpButtonSize);
		waterReadFile.setPreferredSize(standardOpButtonSize);
		waterSaveChanges.setPreferredSize(standardOpButtonSize);
		waterSizePlus.setPreferredSize(standardOpButtonSize);
		waterSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateWater.setPreferredSize(standardOpButtonSize);
		deleteWaterTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(waterPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createElectricityScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		elecPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the electricity panel
		elecTable = new JTable(elecTableModel);
		c.gridx = 0;
		setColumnSize(elecTable, 30);
		elecPanel.add(elecTable, c);
		
		elecOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		elecPanel.add(elecOpPanel, c);

		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		elecReadFile = new JButton("Einlesen");
		d.gridy = 0;
		elecOpPanel.add(elecReadFile, d);
		
		elecSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		elecOpPanel.add(elecSaveChanges, d);
		
		elecSizePlus = new JButton("+");
		d.gridy = 2;
		elecOpPanel.add(elecSizePlus, d);
		
		elecSizeMinus = new JButton("-");
		d.gridy = 3;
		elecOpPanel.add(elecSizeMinus, d);
		
		calculateElec = new JButton("Berechnen");
		d.gridy = 4;
		elecOpPanel.add(calculateElec, d);
		
		deleteElecTable = new JButton("Löschen");
		d.gridy = 5;
		elecOpPanel.add(deleteElecTable, d);
		
		elecReadFile.setPreferredSize(standardOpButtonSize);
		elecSaveChanges.setPreferredSize(standardOpButtonSize);
		elecSizePlus.setPreferredSize(standardOpButtonSize);
		elecSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateElec.setPreferredSize(standardOpButtonSize);
		deleteElecTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(elecPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createFireworksScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		fireworksPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the fireworks panel
		fireworksTable = new JTable(fireworksTableModel);
		c.gridx = 0;
		setColumnSize(fireworksTable, 30);
		fireworksPanel.add(fireworksTable, c);
		
		fireworksOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		fireworksPanel.add(fireworksOpPanel, c);

		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		fireworksReadFile = new JButton("Einlesen");
		d.gridy = 0;
		fireworksOpPanel.add(fireworksReadFile, d);
		
		fireworksSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		fireworksOpPanel.add(fireworksSaveChanges, d);
		
		fireworksSizePlus = new JButton("+");
		d.gridy = 2;
		fireworksOpPanel.add(fireworksSizePlus, d);
		
		fireworksSizeMinus = new JButton("-");
		d.gridy = 3;
		fireworksOpPanel.add(fireworksSizeMinus, d);
		
		calculateFireworks = new JButton("Berechnen");
		d.gridy = 4;
		fireworksOpPanel.add(calculateFireworks, d);
		
		deleteFireworksTable = new JButton("Löschen");
		d.gridy = 5;
		fireworksOpPanel.add(deleteFireworksTable, d);
		
		fireworksReadFile.setPreferredSize(standardOpButtonSize);
		fireworksSaveChanges.setPreferredSize(standardOpButtonSize);
		fireworksSizePlus.setPreferredSize(standardOpButtonSize);
		fireworksSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateFireworks.setPreferredSize(standardOpButtonSize);
		deleteFireworksTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(fireworksPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createInvitationsScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		invitationsPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the invitations panel
		invitationsTable = new JTable(invitationsTableModel);
		c.gridx = 0;
		setColumnSize(invitationsTable, 30);
		invitationsPanel.add(invitationsTable, c);
		
		invitationsOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		invitationsPanel.add(invitationsOpPanel, c);

		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		invitationsReadFile = new JButton("Einlesen");
		d.gridy = 0;
		invitationsOpPanel.add(invitationsReadFile, d);
		
		invitationsSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		invitationsOpPanel.add(invitationsSaveChanges, d);
		
		invitationsSizePlus = new JButton("+");
		d.gridy = 2;
		invitationsOpPanel.add(invitationsSizePlus, d);
		
		invitationsSizeMinus = new JButton("-");
		d.gridy = 3;
		invitationsOpPanel.add(invitationsSizeMinus, d);
		
		calculateInvitations = new JButton("Berechnen");
		d.gridy = 4;
		invitationsOpPanel.add(calculateInvitations, d);
		
		deleteInvitationsTable = new JButton("Löschen");
		d.gridy = 5;
		invitationsOpPanel.add(deleteInvitationsTable, d);
		
		invitationsReadFile.setPreferredSize(standardOpButtonSize);
		invitationsSaveChanges.setPreferredSize(standardOpButtonSize);
		invitationsSizePlus.setPreferredSize(standardOpButtonSize);
		invitationsSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateInvitations.setPreferredSize(standardOpButtonSize);
		deleteInvitationsTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(invitationsPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createTrafficScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		trafficPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the traffic panel
		trafficTable = new JTable(trafficTableModel);
		c.gridx = 0;
		setColumnSize(trafficTable, 30);
		trafficPanel.add(trafficTable, c);
		
		trafficOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		trafficPanel.add(trafficOpPanel, c);

		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		trafficReadFile = new JButton("Einlesen");
		d.gridy = 0;
		trafficOpPanel.add(trafficReadFile, d);
		
		trafficSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		trafficOpPanel.add(trafficSaveChanges, d);
		
		trafficSizePlus = new JButton("+");
		d.gridy = 2;
		trafficOpPanel.add(trafficSizePlus, d);
		
		trafficSizeMinus = new JButton("-");
		d.gridy = 3;
		trafficOpPanel.add(trafficSizeMinus, d);
		
		calculateTraffic = new JButton("Berechnen");
		d.gridy = 4;
		trafficOpPanel.add(calculateTraffic, d);
		
		deleteTrafficTable = new JButton("Löschen");
		d.gridy = 5;
		trafficOpPanel.add(deleteTrafficTable, d);
		
		trafficReadFile.setPreferredSize(standardOpButtonSize);
		trafficSaveChanges.setPreferredSize(standardOpButtonSize);
		trafficSizePlus.setPreferredSize(standardOpButtonSize);
		trafficSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateTraffic.setPreferredSize(standardOpButtonSize);
		deleteTrafficTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(trafficPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	public void createEmployeesScreen() {
		mainPanel.removeAll();
		mainPanel.setLayout(new BorderLayout());
		placeBackButton(mainPanel);
		
		employeesPanel = new JPanel(new GridBagLayout());
		GridBagConstraints c = new GridBagConstraints();
		c.insets = new Insets(10, 10, 10, 10);
		
		//For the traffic panel
		employeesTable = new JTable(employeesTableModel);
		c.gridx = 0;
		setColumnSize(employeesTable, 30);
		employeesPanel.add(employeesTable, c);
		
		employeesOpPanel = new JPanel(new GridBagLayout());
		c.gridx = 1;
		employeesPanel.add(employeesOpPanel, c);

		//For the operations panel
		GridBagConstraints d = new GridBagConstraints();
		d.insets = new Insets(5, 0, 5, 0);
		d.anchor = GridBagConstraints.WEST;
		
		employeesReadFile = new JButton("Einlesen");
		d.gridy = 0;
		employeesOpPanel.add(employeesReadFile, d);
		
		employeesSaveChanges = new JButton("Speichern");
		d.gridy = 1;
		employeesOpPanel.add(employeesSaveChanges, d);
		
		employeesSizePlus = new JButton("+");
		d.gridy = 2;
		employeesOpPanel.add(employeesSizePlus, d);
		
		employeesSizeMinus = new JButton("-");
		d.gridy = 3;
		employeesOpPanel.add(employeesSizeMinus, d);
		
		calculateEmployees = new JButton("Berechnen");
		d.gridy = 4;
		employeesOpPanel.add(calculateEmployees, d);
		
		deleteEmployeesTable = new JButton("Löschen");
		d.gridy = 5;
		employeesOpPanel.add(deleteEmployeesTable, d);
		
		employeesReadFile.setPreferredSize(standardOpButtonSize);
		employeesSaveChanges.setPreferredSize(standardOpButtonSize);
		employeesSizePlus.setPreferredSize(standardOpButtonSize);
		employeesSizeMinus.setPreferredSize(standardOpButtonSize);
		calculateEmployees.setPreferredSize(standardOpButtonSize);
		deleteEmployeesTable.setPreferredSize(standardOpButtonSize);
		
		mainPanel.add(employeesPanel, BorderLayout.CENTER);
		frame.setContentPane(mainPanel);
		mainPanel.repaint();
	}
	
	/**
	 * Creates a new JFrame within the main frame and displays a matrix
	 * @param title The title of the new JFrame
	 * @param matrix The matrix to be displayed
	 * @param upperTriangularMatrix Whether or not the matrix is an upper triangular matrix; shows only lower triangle if true
	 */
	public void createStandardResultFrame(String title, Object[][] matrix, boolean upperTriangularMatrix) {
		defaultResultFrame = new JFrame(title);
		defaultResultPanel = new JPanel(new GridBagLayout());
		defaultResultFrame.setSize(562, 562);
		defaultResultFrame.setLocationRelativeTo(frame);
		defaultResultFrame.setVisible(true);
		defaultResultFrame.setContentPane(defaultResultPanel);
		
		String matrixAsString = "";
		if(upperTriangularMatrix) {
			matrix = FileManager.toLowerTriangle(matrix);
		}
		
		for(int x = 0; x < matrix.length; x++) { //Adds the top headline of letters
			matrixAsString = matrixAsString + letters[x] + " ";
		}
		
		for(int i = 0; i < matrix.length; i++) {
			String line = letters[i]; //Sets the letter for the row
			for(int j = 0; j < matrix[i].length; j++) {
				if(!(matrix[i][j] == null)) { //Skips over null values
					line = line + " " + matrix[i][j]; //Constructs each line with the contents of the matrix
				}
			}
			matrixAsString = matrixAsString + "<br>" + line;
		}
		matrixAsString = "<html><pre>  " + matrixAsString + "</pre></html>"; //Surrounds the String with html tags to enable line breaks in the JLabel; pre-tag to show spaces
		
		GridBagConstraints c = new GridBagConstraints();
		JLabel matrixLabel = new JLabel(matrixAsString);
		matrixLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		defaultResultPanel.add(matrixLabel, c);
	}
	
	public void createFireworksResultFrame(String title, int[] distanceArray) {
		fireworksResultFrame = new JFrame(title);
		fireworksResultPanel = new JPanel(new GridBagLayout()); //GridBag for centered Components
		fireworksResultFrame.setSize(562, 562);
		fireworksResultFrame.setLocationRelativeTo(frame);
		fireworksResultFrame.setVisible(true);
		fireworksResultFrame.setContentPane(fireworksResultPanel);
		
		//Creates a String with the names and value of the vertices ordered by the value
		int infinity = (int)(0.9*Integer.MAX_VALUE);
		String labelText = "";

		HashMap<String, Integer> map = new HashMap<String, Integer>();

		for (int i = 0; i < distanceArray.length; i++) {
			map.put(letters[i], distanceArray[i]);
		}
		while (!map.isEmpty()) {
			String minKey = "A";
			int minValue = Integer.MAX_VALUE;
			for (Map.Entry<String, Integer> pair : map.entrySet()) { // Iterates through the map and looks for the smallest value in each iteration
				if (pair.getValue() < minValue) {
					minKey = pair.getKey();
					minValue = pair.getValue();
				}
			}
			map.remove(minKey);
			if (minValue != infinity) {
				labelText += minKey + " " + minValue + "<br>";
			}
		}
		labelText = "<html>" + labelText + "</html>"; //Wraps the text with html tags to allow linebreaks
		
		//Adds a JLabel with the labelText to the panel
		GridBagConstraints c = new GridBagConstraints();
		JLabel fireworksLabel = new JLabel(labelText);
		fireworksLabel.setFont(new Font(Font.MONOSPACED, Font.BOLD, 12));
		fireworksResultPanel.add(fireworksLabel, c);
	}
	
	//Places a Button to the main Menu on the specified JPanel that has a BorderLayout
	private void placeBackButton(JPanel parent) {
		JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		northPanel.add(back);
		back.setPreferredSize(new Dimension(100, 30));
		parent.add(northPanel, BorderLayout.NORTH);
	}
	
	//Sets the width for all columns in a JTable
	public void setColumnSize(JTable table, int size) {
		for(int i = 0; i < table.getColumnCount(); i++) {
			table.getColumnModel().getColumn(i).setPreferredWidth(size);
		}
	}
	
}
