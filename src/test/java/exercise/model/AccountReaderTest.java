package exercise.model;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.Test;

import exercise.ApplicationException;

public class AccountReaderTest {

	final static Logger logger = Logger.getLogger(AccountReader.class);
	
	@Test
	public void testReadAccounts() {
		
		try {
			List<Account> testList = new ArrayList<>(); 
			Account testAccount  = new Account (12, "EMEA", "Italy", "EUR", new BigDecimal("24760534.78"), 5.0);
			testList.add(testAccount);
			testAccount  = new Account (23,"NA", "USA", "USD",  new BigDecimal("4132412.43"), 10.0);
			testList.add(testAccount);
			testAccount  = new Account (2,"NA", "Canada", "CAN",  new BigDecimal("453232.4576"), 10.0);
			testList.add(testAccount);
			testAccount  = new Account (3,"APAC", "Hong Kong", "HKD",  new BigDecimal("124342.1"), 1.0);
			testList.add(testAccount);


			List<Account> accountsList = AccountReader.readAccounts(".\\src\\main\\resources\\TestData.csv", true);
			for (Account account : accountsList) {
				logger.debug(account);
			}
			
			assertTrue(accountsList.containsAll(testList));
			
			testAccount  = new Account (3,"APAC", "Hong Kong", "HKD",  new BigDecimal("124342.1"), 1.0);
			testList.add(testAccount);
			
			assertTrue(accountsList.containsAll(testList));
		} catch (ApplicationException e) {
			fail(e.getMessage());
			logger.error(e);
		}
	}

	@Test
	public void testReadAccount() {
		
		Account testAccount  = new Account (12, "EMEA", "Italy", "EUR", new BigDecimal("24760534.78"), 5.0);
		logger.debug("testAccount = "+ testAccount);
		try {
			Account account = AccountReader.readAccount("EMEA,Italy,12,EUR,24760534.78,5");
			logger.debug("    account = " + account);
			assertTrue(testAccount.equalsAllFields(account));
		} catch (ApplicationException e) {
			fail(e.getMessage());
		}
	}
	
	@Test
	public void testReadAccountfailed() {
		
		Account testAccount  = new Account (12, "EMEA", "Italy", "EUR", new BigDecimal("24760534.78"), 5.0);
		logger.debug("testAccount = "+ testAccount);
		try {
			Account account = AccountReader.readAccount("EMEA,Italy,eee12,EUR,24760534.78,5");
			logger.debug("    account = " + account);
			assertTrue(testAccount.equalsAllFields(account));
		} catch (ApplicationException e) {
			fail(e.getMessage());
		}
	}


}
