package main;

import java.util.Arrays;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * The main class which provides most of the back-end functionality to the program
 */
public class CityManager {
	
	private Screen screen;
	public Object[][] roadData;
	public Object[][] waterData;
	public Object[][] elecData;
	public Object[][] fireworksData;
	public Object[][] invitationsData;
	public Object[][] trafficData;
	
	public void run() {
		screen = new Screen();
		addMainMenuListeners();
	}
	
	private void addMainMenuListeners() {
		screen.roads.addActionListener(new RoadsListener());
		screen.water.addActionListener(new WaterListener());
		screen.electricity.addActionListener(new ElectricityListener());
		screen.fireworks.addActionListener(new FireworksListener());
		screen.invitations.addActionListener(new InvitationsListener());
		screen.traffic.addActionListener(new TrafficListener());
		screen.employees.addActionListener(new EmployeesListener());
	}
	
	private void addRoadsListeners() {
		screen.roadReadFile.addActionListener(new RoadReadFileListener());
		screen.roadSaveChanges.addActionListener(new RoadSaveListener());
		screen.roadSizePlus.addActionListener(new RoadSizePlusListener());
		screen.roadSizeMinus.addActionListener(new RoadSizeMinusListener());
		screen.calculateRoads.addActionListener(new CalculateRoadsListener());
		screen.deleteRoadTable.addActionListener(new DeleteRoadTableListener());
	}
	
	private void addWaterListeners() {
		screen.waterReadFile.addActionListener(new WaterReadFileListener());
		screen.waterSaveChanges.addActionListener(new WaterSaveListener());
		screen.waterSizePlus.addActionListener(new WaterSizePlusListener());
		screen.waterSizeMinus.addActionListener(new WaterSizeMinusListener());
		screen.calculateWater.addActionListener(new CalculateWaterListener());
		screen.deleteWaterTable.addActionListener(new DeleteWaterTableListener());
	}
	
	private void addElecListeners() {
		screen.elecReadFile.addActionListener(new ElecReadFileListener());
		screen.elecSaveChanges.addActionListener(new ElecSaveListener());
		screen.elecSizePlus.addActionListener(new ElecSizePlusListener());
		screen.elecSizeMinus.addActionListener(new ElecSizeMinusListener());
		screen.calculateElec.addActionListener(new CalculateElecListener());
		screen.deleteElecTable.addActionListener(new DeleteElecTableListener());
	}
	
	private void addFireworksListeners() {
		screen.fireworksReadFile.addActionListener(new FireworksReadFileListener());
		screen.fireworksSaveChanges.addActionListener(new FireworksSaveListener());
		screen.fireworksSizePlus.addActionListener(new FireworksSizePlusListener());
		screen.fireworksSizeMinus.addActionListener(new FireworksSizeMinusListener());
		screen.calculateFireworks.addActionListener(new CalculateFireworksListener());
		screen.deleteFireworksTable.addActionListener(new DeleteFireworksTableListener());
	}
	
	private void addInvitationsListeners() {
		screen.invitationsReadFile.addActionListener(new InvitationsReadFileListener());
		screen.invitationsSaveChanges.addActionListener(new InvitationsSaveListener());
		screen.invitationsSizePlus.addActionListener(new InvitationsSizePlusListener());
		screen.invitationsSizeMinus.addActionListener(new InvitationsSizeMinusListener());
		screen.calculateInvitations.addActionListener(new CalculateInvitationsListener());
		screen.deleteInvitationsTable.addActionListener(new DeleteInvitationsTableListener());
	}
	
	private void addTrafficListeners() {
		screen.trafficReadFile.addActionListener(new TrafficReadFileListener());
		screen.trafficSaveChanges.addActionListener(new TrafficSaveListener());
		screen.trafficSizePlus.addActionListener(new TrafficSizePlusListener());
		screen.trafficSizeMinus.addActionListener(new TrafficSizeMinusListener());
		screen.calculateTraffic.addActionListener(new CalculateTrafficListener());
		screen.deleteTrafficTable.addActionListener(new DeleteTrafficTableListener());
	}
	
	/**
	 * Returns a matrix with the content of the table
	 * @param table The JTable to draw content from
	 * @return A two-dimensional array with the content of the table
	 */
	private Object[][] readTable(JTable table) {
		TableModel tm = table.getModel();
		int numRows = tm.getRowCount();
		int numCols = tm.getColumnCount();
		Object[][] tableData = new Object[numRows][numCols];
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				if(tm.getValueAt(i, j) == null) {
					tableData[i][j] = 0;
				} else {
					tableData[i][j] = Integer.parseInt(tm.getValueAt(i, j).toString()); //Cast to String and back to int so that java does not treat the object as a String
				}
			}
		}
		return tableData;
	}
	
	/**
	 * Prints the JTable to the console
	 * @param table The JTable to be printed
	 */
	private void printTable(JTable table) {
		Object[][] matrix = readTable(table);
		for(int i = 0; i < table.getModel().getRowCount(); i++) {
			System.out.println(Arrays.toString(matrix[i]));
		}
	}
	
	/**
	 * Changes the table size by altering its TableModel
	 * @param table The JTable that has to be changed
	 * @param op Whether to increase or to decrease the size of the table; must be '+' or '-'
	 */
	private void changeTableSize(JTable table, char op) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if((op == '+') && (model.getRowCount() < 26)) {
			model.setRowCount(model.getRowCount() + 1);
			model.setColumnCount(model.getColumnCount() + 1);
		} else if((op == '-') && (model.getRowCount() > 2)) {
			model.setRowCount(model.getRowCount() - 1);
			model.setColumnCount(model.getColumnCount() - 1);
		}
		screen.setColumnSize(table, 30);
	}
	
	/**
	 * Sets the size of the JTable to the specified size
	 * @param table The JTable to be changed
	 * @param size The new size of the table
	 */
	private void setTableSize(JTable table, int size) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(size);
		model.setColumnCount(size);
		screen.setColumnSize(table, 30);
	}
	
	//May throw ArrayIndexOutOfBoundsException if data-matrix is too small
	/**
	 * Inserts data from a matrix into the table
	 * @param table The table to be filled
	 * @param data A two-dimensional array with the data
	 */
	private void fillTable(JTable table, Object[][] data) {
		TableModel tm = table.getModel();
		int numRows = data.length;
		int numCols = data[0].length;
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				tm.setValueAt(data[i][j], i, j);
			}
		}
	}
	
	/**
	 * Replaces all fields of the table with <code>null</code> values
	 * @param table The table to be deleted
	 */
	private void deleteTableData(JTable table) {
		TableModel tm = table.getModel();
		int numRows = tm.getRowCount();
		int numCols = tm.getColumnCount();
		for(int i = 0; i < numRows; i++) {
			for(int j = 0; j < numCols; j++) {
				tm.setValueAt(null, i, j);
			}
		}
	}
	
	
	
	//MainMenu listeners
	private class RoadsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createRoadScreen();
			addRoadsListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class WaterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createWaterScreen();
			addWaterListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class ElectricityListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createElectricityScreen();
			addElecListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class FireworksListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createFireworksScreen();
			addFireworksListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class InvitationsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createInvitationsScreen();
			addInvitationsListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class TrafficListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createTrafficScreen();
			addTrafficListeners();
			screen.back.addActionListener(new BackListener());
		}
	}
	private class EmployeesListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createEmployeesScreen();
			screen.back.addActionListener(new BackListener());
		}
	}
	
	//The handler for the back button used on the function screens
	private class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createMainScreen();
			addMainMenuListeners();
		}
	}
	
	//Handlers for the Roads screen
	private class RoadReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.roadTable, matrix.length);
				fillTable(screen.roadTable, matrix);
			}
		}
	}
	private class RoadSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			roadData = readTable(screen.roadTable);
			printTable(screen.roadTable);
		}
	}
	private class RoadSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.roadTable, '+');
		}
	}
	private class RoadSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.roadTable, '-');
		}
	}
	private class CalculateRoadsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			roadData = readTable(screen.roadTable);
			screen.createStandardResultFrame("Straßennetz als Adjazenzmatrix", Algorithms.prim(roadData), true);
		}
	}
	private class DeleteRoadTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.roadTable);
		}
	}
	
	// Handlers for the Water screen
	private class WaterReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.waterTable, matrix.length);
				fillTable(screen.waterTable, matrix);
			}
		}
	}
	private class WaterSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			roadData = readTable(screen.waterTable);
			printTable(screen.waterTable);
		}
	}
	private class WaterSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.waterTable, '+');
		}
	}
	private class WaterSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.waterTable, '-');
		}
	}
	private class CalculateWaterListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class DeleteWaterTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.waterTable);
		}
	}
	
	//Handlers for the electricity screen
	private class ElecReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.elecTable, matrix.length);
				fillTable(screen.elecTable, matrix);
			}
		}
	}
	private class ElecSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			elecData = readTable(screen.elecTable);
			printTable(screen.elecTable);
		}
	}
	private class ElecSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.elecTable, '+');
		}
	}
	private class ElecSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.elecTable, '-');
		}
	}
	private class CalculateElecListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			elecData = readTable(screen.elecTable);
			screen.createStandardResultFrame("Stromnetz als Adjazenzmatrix", Algorithms.prim(elecData, 5), true);
		}
	}
	private class DeleteElecTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.elecTable);
		}
	}
	
	//Handlers for the fireworks screen
	private class FireworksReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.fireworksTable, matrix.length);
				fillTable(screen.fireworksTable, matrix);
			}
		}
	}
	private class FireworksSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			elecData = readTable(screen.fireworksTable);
			printTable(screen.fireworksTable);
		}
	}
	private class FireworksSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.fireworksTable, '+');
		}
	}
	private class FireworksSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.fireworksTable, '-');
		}
	}
	private class CalculateFireworksListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			fireworksData = readTable(screen.fireworksTable);
			screen.createFireworksResultFrame("Abfolge der Explosionen", Algorithms.dijkstra(fireworksData));
		}
	}
	private class DeleteFireworksTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.fireworksTable);
		}
	}
	
	//Handlers for the invitations screen
	private class InvitationsReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.invitationsTable, matrix.length);
				fillTable(screen.invitationsTable, matrix);
			}
		}
	}
	private class InvitationsSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			invitationsData = readTable(screen.invitationsTable);
			printTable(screen.invitationsTable);
		}
	}
	private class InvitationsSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.invitationsTable, '+');
		}
	}
	private class InvitationsSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.invitationsTable, '-');
		}
	}
	private class CalculateInvitationsListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class DeleteInvitationsTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.invitationsTable);
		}
	}
	
	//Handlers for the traffic screen
	private class TrafficReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if(!(fc.getSelectedFile() == null)) {
				Object[][] matrix = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.trafficTable, matrix.length);
				fillTable(screen.trafficTable, matrix);
			}
		}
	}
	private class TrafficSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			trafficData = readTable(screen.trafficTable);
			printTable(screen.trafficTable);
		}
	}
	private class TrafficSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.trafficTable, '+');
		}
	}
	private class TrafficSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.trafficTable, '-');
		}
	}
	private class CalculateTrafficListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			
		}
	}
	private class DeleteTrafficTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.trafficTable);
		}
	}
}
