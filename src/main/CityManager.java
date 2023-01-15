package main;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

import javax.swing.JFileChooser;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import Postman.Postman;
import Traffic.Traffic;
import WaterSupply.WaterSupply;
import WorkDistribution.WorkDistribution;

/**
 * The main class which provides most of the back-end functionality to the
 * program
 * 
 * @author Simon
 *
 */
public class CityManager {

	private Screen screen;
	public Object[][] roadData;
	public Object[][] waterData;
	public Object[][] inputMatrixWater;
	public Object[][] elecData;
	public Object[][] fireworksData;
	public Object[][] invitationsData;
	public Object[][] inputMatrixInvitation;
	public Object[][] trafficData;
	public Object[][] inputMatrixTraffic;
	public Object[][] employeesData;
	public Object[][] inputMatrixEmployees;

	public void run() {
		screen = new Screen();
		addMainMenuListeners();
	}

	private void addMainMenuListeners() {
		// screen.selectFileButton.addActionListener(new FileButtonListener());
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
		screen.deleteRoadTable.addActionListener(new DeleteRoadTableListener());
	}

	private void addWaterListeners() {
		screen.waterReadFile.addActionListener(new WaterReadFileListener());
		screen.waterSaveChanges.addActionListener(new WaterSaveListener());
		screen.waterSizePlus.addActionListener(new WaterSizePlusListener());
		screen.waterSizeMinus.addActionListener(new WaterSizeMinusListener());
		screen.calculateWater.addActionListener(new CalculateWater());
		screen.deleteWaterTable.addActionListener(new DeleteWaterTableListener());
	}

	private void addElecListeners() {
		screen.elecReadFile.addActionListener(new ElecReadFileListener());
		screen.elecSaveChanges.addActionListener(new ElecSaveListener());
		screen.elecSizePlus.addActionListener(new ElecSizePlusListener());
		screen.elecSizeMinus.addActionListener(new ElecSizeMinusListener());
		screen.deleteElecTable.addActionListener(new DeleteElecTableListener());
	}

	private void addFireworksListeners() {
		screen.fireworksReadFile.addActionListener(new FireworksReadFileListener());
		screen.fireworksSaveChanges.addActionListener(new FireworksSaveListener());
		screen.fireworksSizePlus.addActionListener(new FireworksSizePlusListener());
		screen.fireworksSizeMinus.addActionListener(new FireworksSizeMinusListener());
		screen.deleteFireworksTable.addActionListener(new DeleteFireworksTableListener());
	}

	private void addInvitationsListeners() {
		screen.invitationsReadFile.addActionListener(new InvitationsReadFileListener());
		screen.invitationsSaveChanges.addActionListener(new InvitationsSaveListener());
		screen.invitationsSizePlus.addActionListener(new InvitationsSizePlusListener());
		screen.invitationsSizeMinus.addActionListener(new InvitationsSizeMinusListener());
		screen.calculateInvitations.addActionListener(new CalculateInvitations());
		screen.deleteInvitationsTable.addActionListener(new DeleteInvitationsTableListener());

	}

	private void addTrafficListeners() {
		screen.trafficReadFile.addActionListener(new TrafficReadFileListener());
		screen.trafficSaveChanges.addActionListener(new TrafficSaveListener());
		screen.trafficSizePlus.addActionListener(new TrafficSizePlusListener());
		screen.trafficSizeMinus.addActionListener(new TrafficSizeMinusListener());
		screen.calculateTraffic.addActionListener(new CalculateTraffic());
		screen.deleteTrafficTable.addActionListener(new DeleteTrafficTableListener());
	}

	private void addEmployeesListeners() {
		screen.employeesReadFile.addActionListener(new EmployeesReadFileListener());
		screen.employeesSaveChanges.addActionListener(new EmployeesSaveListener());
		screen.employeesSizePlus.addActionListener(new EmployeesSizePlusListener());
		screen.employeesSizeMinus.addActionListener(new EmployeesSizeMinusListener());
		screen.calculateEmployees.addActionListener(new CalculateEmployees());
		screen.deleteEmployeesTable.addActionListener(new DeleteEmployeesTableListener());
	}

	/**
	 * Returns a matrix with the content of the table
	 * 
	 * @param table The JTable to draw content from
	 * @return A two-dimensional array with the content of the table
	 */
	private Object[][] readTable(JTable table) {
		TableModel tm = table.getModel();
		int numRows = tm.getRowCount();
		int numCols = tm.getColumnCount();
		Object[][] tableData = new Object[numRows][numCols];
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tableData[i][j] = tm.getValueAt(i, j);
			}
		}
		return tableData;
	}

	/**
	 * Prints the JTable to the console
	 * 
	 * @param table The JTable to be printed
	 */
	private void printTable(JTable table) {
		Object[][] matrix = readTable(table);
		for (int i = 0; i < table.getModel().getRowCount(); i++) {

		}
	}

	/**
	 * Changes the table size by altering it's TableModel
	 * 
	 * @param table The JTable that has to be changed
	 * @param op    Whether to increase or to decrease the size of the table; must
	 *              be '+' or '-'
	 */
	private void changeTableSize(JTable table, char op) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		if (op == '+') {
			model.setRowCount(model.getRowCount() + 1);
			model.setColumnCount(model.getColumnCount() + 1);
		} else if (op == '-') {
			model.setRowCount(model.getRowCount() - 1);
			model.setColumnCount(model.getColumnCount() - 1);
		}
		screen.setColumnSize(table, 30);
	}

	private void setTableSize(JTable table, int size) {
		DefaultTableModel model = (DefaultTableModel) table.getModel();
		model.setRowCount(size);
		model.setColumnCount(size);
		screen.setColumnSize(table, 30);
	}

	// May throw ArrayIndexOutOfBoundsException if data-matrix is too small
	/**
	 * Inserts data from a matrix into the table
	 * 
	 * @param table The table to be filled
	 * @param data  A two-dimensional array with the data
	 */
	private void fillTable(JTable table, Object[][] data) {
		TableModel tm = table.getModel();
		int numRows = data.length;
		int numCols = data[0].length;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tm.setValueAt(data[i][j], i, j);
			}
		}
	}

	private void fillTableInvitation(JTable table, Object[][] data) {
		TableModel tm = table.getModel();
		int numRows = data.length;
		int numCols = data.length;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tm.setValueAt(data[i][j], i, j);
			}
		}
	}

	private void fillTableEmployees(JTable table, Object[][] data) {
		TableModel tm = table.getModel();
		int numRows = data.length;
		int numCols = data.length;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tm.setValueAt(data[i][j], i, j);
			}
		}
	}

	private void fillTableWater(JTable table, Object[][] data) {
		TableModel tm = table.getModel();
		int numRows = data.length;
		int numCols = data.length;
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tm.setValueAt(data[i][j], i, j);
			}
		}
	}

	/**
	 * Replaces all fields of the table with <code>null</code> values
	 * 
	 * @param table The table to be deleted
	 */
	private void deleteTableData(JTable table) {
		TableModel tm = table.getModel();
		int numRows = tm.getRowCount();
		int numCols = tm.getColumnCount();
		for (int i = 0; i < numRows; i++) {
			for (int j = 0; j < numCols; j++) {
				tm.setValueAt(null, i, j);
			}
		}
	}

	// MainMenu listeners
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
			addEmployeesListeners();
			screen.back.addActionListener(new BackListener());
		}
	}

	// The handler for the back button used on the function screens
	private class BackListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			screen.createMainScreen();
			addMainMenuListeners();
		}
	}

	// Handlers for the Roads screen
	private class RoadReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
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
			if (!(fc.getSelectedFile() == null)) {
				inputMatrixWater = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.waterTable, inputMatrixWater.length);
				fillTable(screen.waterTable, inputMatrixWater);
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

	private class CalculateWater implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			WaterSupply waterSupply = new WaterSupply();
			waterSupply.getWaterSupply(inputMatrixWater);
			setTableSize(screen.waterTable, waterSupply.outputWaterMatrix.length);
			fillTableWater(screen.waterTable, waterSupply.outputWaterMatrix);
		}
	}

	private class DeleteWaterTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.waterTable);
		}
	}

	// Handlers for the electricity screen
	private class ElecReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
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

	private class DeleteElecTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.elecTable);
		}
	}

	// Handlers for the fireworks screen
	private class FireworksReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
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

	private class DeleteFireworksTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.fireworksTable);
		}
	}

	// Handlers for the invitations screen
	private class InvitationsReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
				inputMatrixInvitation = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.invitationsTable, inputMatrixInvitation.length);
				fillTable(screen.invitationsTable, inputMatrixInvitation);
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

	private class CalculateInvitations implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			Postman postman = new Postman();
			postman.getPostman(inputMatrixInvitation);
			setTableSize(screen.invitationsTable, postman.eulerianMatrix.length);
			fillTableInvitation(screen.invitationsTable, postman.eulerianMatrix);
		}
	}

	private class DeleteInvitationsTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.invitationsTable);
		}
	}

	private class EmployeesReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
				inputMatrixEmployees = FileManager.readFile(fc.getSelectedFile());
				setTableSize(screen.employeesTable, inputMatrixEmployees.length);
				fillTable(screen.employeesTable, inputMatrixEmployees);
			}
		}
	}

	private class EmployeesSaveListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			employeesData = readTable(screen.employeesTable);
			printTable(screen.employeesTable);
		}
	}

	private class EmployeesSizePlusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.employeesTable, '+');
		}
	}

	private class EmployeesSizeMinusListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			changeTableSize(screen.employeesTable, '-');
		}
	}

	private class CalculateEmployees implements ActionListener {
		public void actionPerformed(ActionEvent e) {

			WorkDistribution workDistribution = new WorkDistribution();
			workDistribution.getWorkDistribution(inputMatrixEmployees);
			setTableSize(screen.employeesTable,
					workDistribution.outputWorkAssignmentMatrix.length);
			fillTableEmployees(screen.employeesTable,
					workDistribution.outputWorkAssignmentMatrix);
		}
	}

	private class DeleteEmployeesTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.employeesTable);
		}
	}

	// Handlers for the traffic screen
	private class TrafficReadFileListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			JFileChooser fc = new JFileChooser();
			fc.showOpenDialog(screen.frame);
			if (!(fc.getSelectedFile() == null)) {
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

	private class CalculateTraffic implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			Traffic traffic = new Traffic();
			traffic.getTraffic(inputMatrixEmployees);
			setTableSize(screen.employeesTable,
					traffic.outputTrafficMatrix.length);
			fillTableEmployees(screen.employeesTable,
					traffic.outputTrafficMatrix);
		}
	}

	private class DeleteTrafficTableListener implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			deleteTableData(screen.trafficTable);
		}
	}

}
