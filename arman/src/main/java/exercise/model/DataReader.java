package exercise.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

import org.apache.log4j.Logger;

import exercise.ApplicationException;

/**
 * DataReader - is a class for reading test data.
 * This class is designed to make the code more generic and allow to read a list  columns from the CSV file 
 * 
 * @author Arman Melkumyan
 *
 */
public class DataReader {

	final static Logger logger = Logger.getLogger(DataReader.class);
	
	public static void main(String[] args) throws FileNotFoundException {
		try (Scanner scanner = new Scanner(
				new File("C:\\Workspaces\\AQR-Exercise\\arman\\src\\main\\resources\\TestData.csv"))) {
			while (scanner.hasNextLine()) {
				String line = scanner.nextLine();
				StringReader lineInputStream = new StringReader(line);
				try (Scanner lineScanner = new Scanner(lineInputStream)) {
					lineScanner.useDelimiter(",");
					while (lineScanner.hasNext()) {
						System.out.print(lineScanner.next() + "|");
					}
					System.out.println("\n");
				}
			}
		}
	}
	
	/**
	 * 
	 * @param sourceFile - Source CSV file's name or full path
	 * @param columnNames - List of required columns names. Data file can contain more columns than needed.
	 * @return - List<Map<String, String>> 
	 * @throws ApplicationException
	 */
	public List<Map<String, String>> readAccounts(String sourceFile, String[] columnNames) throws ApplicationException {
		List<Map<String, String>> accountsList = new ArrayList<Map<String, String>>();
		Map<Integer, String> columnIndexes = new HashMap<Integer, String>();
		
		List<String> columnNameList = Arrays.asList(columnNames);
		
		try (Scanner scanner = new Scanner(
				new File(sourceFile))) {
			if (scanner.hasNextLine()) {
				//Read header and create a column filter by indexes in the data file 
				String header = scanner.nextLine();
				String[] columns = header.split(",");
				for (int i = 0; i < columns.length; i++) {
					if (columnNameList.contains(columns[i]))
						columnIndexes.put(i, columns[i]);
				}
			}
			// read data
			while (scanner.hasNextLine()) {
				String record = scanner.nextLine();
				accountsList.add(readAccount(record, columnIndexes));
			}
		} catch (FileNotFoundException e) {
			
		}
		return accountsList;		
	}
	
	public Map<String, String> readAccount(String record, final Map<Integer, String> columnIndexes) throws ApplicationException {
		Map<String, String> data = new HashMap<String, String>();
		StringReader lineInputStream = new StringReader(record);
		try (Scanner lineScanner = new Scanner(lineInputStream)) {
			lineScanner.useDelimiter(",");
			for (int i = 0; lineScanner.hasNext(); i++) {
				String value = lineScanner.next();
				if (columnIndexes.containsKey(i)) {
					data.put(columnIndexes.get(i), value);
				}
			}
			
		} catch(Exception e) {
			throw new ApplicationException("Exception on reading an account record: [" + record + "]", e);
		}
		return data;		
	}

}
