package exercise.model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.StringReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

import exercise.ApplicationException;

/**
 * DataReader - is a class for reading test data
 * 
 * @author Arman Melkumyan
 *
 */
public class AccountReader {
	
	final static Logger logger = Logger.getLogger(AccountReader.class);
	
	public static void main(String[] args) {
		try {
			List<Account> accountsList = readAccounts(args[0], Boolean.parseBoolean(args[1]));
			for (Account account : accountsList) {
				logger.debug(account);
			}
		} catch (ApplicationException e) {
			logger.error(e);
			//e.printStackTrace();
		}
	}


	public static List<Account> readAccounts(String sourceFile, boolean hasHeader) throws ApplicationException {
		List<Account> accountsList = new ArrayList<Account>();
		try (Scanner scanner = new Scanner(
				new File(sourceFile))) {
			if (hasHeader && scanner.hasNextLine()) {
				String header = scanner.nextLine();
				logger.info("Header: " + header);
			}
			while (scanner.hasNextLine()) {
				String record = scanner.nextLine();
				accountsList.add(readAccount(record));
			}
		} catch (FileNotFoundException e) {
			
		}
		return accountsList;		
	}
	
	public static Account readAccount(String record) throws ApplicationException {
		Account account = new Account();
		
		try {
			String[] dataArray = record.split(",");
			
			
			account.setRegion(dataArray[0].trim());
			account.setCountry(dataArray[1].trim());
			
			try {
				account.setAccountId(Integer.parseInt(dataArray[2].trim(), 10));
			} catch (Exception e) {
				String message = "Cannot convert '" + dataArray[2].trim() + "' to integer. Incorrect value of ACCOUNT_ID.";
				logger.error(message, e);
				throw new ApplicationException(message, e);
			}
			
			account.setCurrency(dataArray[3].trim());
			try {
				BigDecimal grossProfit = new BigDecimal(dataArray[4].trim());
				account.setGrossProfit(grossProfit);
			} catch (Exception e) {
				throw new ApplicationException("Cannot convert '" + dataArray[4].trim() + "' to BigDecimal. Incorrect value of GROSS_PROFIT.", e);
			}
			
			try {
				account.setTaxRate(Double.parseDouble(dataArray[5].trim()));
			} catch (Exception e) {
				throw new ApplicationException("Cannot convert '" + dataArray[5].trim() + "' to BigDecimal. Incorrect value of GROSS_PROFIT.", e);
			}
			
			//System.out.println("\n");
		} catch(ApplicationException e) {
			throw e;
		} catch(Exception e) {
			throw new ApplicationException("Exception on reading an account record: [" + record + "]", e);
		}
		return account;		
	}

}
