package exercise;

import static org.junit.Assert.*;

import java.math.BigDecimal;
import java.util.Currency;

import org.apache.log4j.Logger;
import org.junit.Test;

import exercise.model.Account;
import exercise.model.AccountReader;

public class ProfitCalculatorTest {

	final static Logger logger = Logger.getLogger(ProfitCalculatorTest.class);
	
	@Test
	public void testCalculateNetProfitInUSD() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateNetProfit() {

		double EUR_currencyRate= 1.11598;
				
		BigDecimal grossProfit = new BigDecimal("24760534.78");
		BigDecimal grossPrInUSD = grossProfit.multiply(new BigDecimal(EUR_currencyRate));
				
		BigDecimal netProfitByCurrency = grossPrInUSD.subtract(grossPrInUSD.multiply(new BigDecimal(5.0 / 100)));
		
		logger.debug("netProfitByCurrency = " + netProfitByCurrency);
		
		Account testAccount  = new Account (12, "EMEA", "Italy", "EUR", new BigDecimal("24760534.78"), 5.0);
		
		BigDecimal netProfitCalculated = ProfitCalculator.calculateNetProfit(testAccount, Currency.getInstance("USD") );
		logger.debug("netProfitCalculated = " + netProfitCalculated);
		
		assertEquals(netProfitByCurrency, netProfitCalculated);
	}

	@Test
	public void testCalculateGrossProfitInUSD() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateGrossProfit() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalNetProfitByRegionListOfAccountStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalNetProfitByCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalGrossProfitByRegionListOfAccountStringString() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalGrossProfitByRegionListOfAccount() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalGrossProfitByCountry() {
		fail("Not yet implemented");
	}

	@Test
	public void testCalculateTotalNetProfitByRegionListOfAccount() {
		fail("Not yet implemented");
	}

}
