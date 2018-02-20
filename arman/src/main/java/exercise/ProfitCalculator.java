package exercise;

import java.math.BigDecimal;
import java.util.Currency;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

import exercise.calculable.TotalProfit;
import exercise.model.Account;

/**
 * 
 * @author Arman Melkumyan
 *
 */
public class ProfitCalculator {

	final static Logger logger = Logger.getLogger(ProfitCalculator.class);
	
	/**
	 * 
	 * @param account
	 * @return
	 */
	public static BigDecimal calculateNetProfitInUSD(Account account) {
		return calculateNetProfit(account, Currency.getInstance("USD"));
	}

	/**
	 * 
	 * @param account
	 * @param currency
	 * @return
	 */
	public static BigDecimal calculateNetProfit(Account account, Currency currency) {
		
		BigDecimal netProfitByCurrency = null;
		CurrencyCodeRate.fromString(account.getCurrency()).convertAmountToUSD(account.getGrossProfit());
		BigDecimal grossProfitByCurrency = calculateGrossProfit(account, currency == null ? Currency.getInstance("USD") : currency);
		if (account.getTaxRate() == 0)
			netProfitByCurrency = grossProfitByCurrency;
		else
			netProfitByCurrency = grossProfitByCurrency.subtract(grossProfitByCurrency.multiply(new BigDecimal(account.getTaxRate() / 100)));

		return netProfitByCurrency;
	}

	/**
	 * 
	 * @param account
	 * @return
	 */
	public static BigDecimal calculateGrossProfitInUSD(Account account) {
		return calculateGrossProfit(account, Currency.getInstance("USD"));
	}

	/**
	 * 
	 * @param account
	 * @param currency
	 * @return
	 */
	public static BigDecimal calculateGrossProfit(Account account, Currency currency) {

		CurrencyCodeRate targetCurrencyRate = CurrencyCodeRate.fromString(currency.toString());
//		logger.debug("targetCurrencyRate = " + targetCurrencyRate);
		BigDecimal grossProfitInUSD = CurrencyCodeRate.fromString(account.getCurrency())
				.convertAmountToUSD(account.getGrossProfit());
//		logger.debug("grossProfitInUSD = " + grossProfitInUSD);
		return grossProfitInUSD.multiply(new BigDecimal(targetCurrencyRate.rate()));
	}

	/**
	 * 
	 * @param accounts
	 * @param currency
	 * @param region
	 * @return
	 */
	public static BigDecimal calculateTotalNetProfitByRegion(List<Account> accounts, String currency, String region) {
		BigDecimal netProfitByCurrency = new BigDecimal(0);

		for (Account account : accounts) {
			// logger.debug(account);
			if (!account.getRegion().equalsIgnoreCase(region))
				continue;

			BigDecimal netProfit = null;
			if (currency == null)
				netProfit = ProfitCalculator.calculateNetProfitInUSD(account);
			else 
				netProfit = ProfitCalculator.calculateNetProfit(account,  Currency.getInstance(currency));

			netProfitByCurrency.add(netProfit);
		}
		return netProfitByCurrency;
	}
	
	/**
	 * 
	 * @param accounts
	 * @param currency
	 * @param country
	 * @return
	 */
	public static BigDecimal calculateTotalNetProfitByCountry(List<Account> accounts, String currency, String country) {
		BigDecimal netProfitByCurrency = new BigDecimal(0);

		for (Account account : accounts) {
			// logger.debug(account);
			if (!account.getCountry().equalsIgnoreCase(country))
				continue;
			
			BigDecimal netProfit = null;
			if (currency == null)
				netProfit = ProfitCalculator.calculateNetProfitInUSD(account);
			else 
				netProfit = ProfitCalculator.calculateNetProfit(account,  Currency.getInstance(currency));

			netProfitByCurrency.add(netProfit);
		}
		return netProfitByCurrency;
	}
	
	
	/**
	 * 
	 * @param accounts
	 * @param currency
	 * @param region
	 * @return
	 */
	public static BigDecimal calculateTotalGrossProfitByRegion(List<Account> accounts, String currency, String region) {
		BigDecimal grossProfitByCurrency = new BigDecimal(0);

		for (Account account : accounts) {
			// logger.debug(account);
			if (!account.getRegion().equalsIgnoreCase(region))
				continue;

			BigDecimal grossProfit = null;
			if (currency == null)
				grossProfit = ProfitCalculator.calculateGrossProfitInUSD(account);
			else 
				grossProfit = ProfitCalculator.calculateGrossProfit(account,  Currency.getInstance(currency));

			grossProfitByCurrency.add(grossProfit);
			
		}
		return grossProfitByCurrency;
	}
	
	/**
	 * 
	 * @param accountsList
	 * @return
	 */
	public TreeMap<String, TotalProfit> calculateTotalGrossProfitByRegion(final List<Account> accountsList) {
		TreeMap<String, TotalProfit> totalGProfitsByRegionMap = new TreeMap<String, TotalProfit>();
		for (Account account : accountsList) {
			// logger.debug(account);
			TotalProfit totalGrossProfitByRegion = null;

			BigDecimal grossProfit = ProfitCalculator.calculateGrossProfitInUSD(account);

			if (totalGProfitsByRegionMap.containsKey(account.getRegion())) {
				totalGrossProfitByRegion = totalGProfitsByRegionMap.get(account.getRegion());
			} else {
				totalGrossProfitByRegion = new TotalProfit(account.getRegion(), "Region");
			}
			totalGrossProfitByRegion.addProfit(grossProfit);
			totalGProfitsByRegionMap.put(account.getRegion(), totalGrossProfitByRegion);

		}
		return totalGProfitsByRegionMap;
	}

	/**
	 * 
	 * @param accountsList
	 * @return
	 */
	public TreeMap<String, TotalProfit> calculateTotalGrossProfitByCountry(final List<Account> accountsList) {
		TreeMap<String, TotalProfit> totalGProfitsByCountryMap = new TreeMap<String, TotalProfit>();

		for (Account account : accountsList) {
			// logger.debug(account);
			TotalProfit totalGrossProfitByCountry = null;
			BigDecimal grossProfit = ProfitCalculator.calculateGrossProfitInUSD(account);

			if (totalGProfitsByCountryMap.containsKey(account.getCountry())) {
				totalGrossProfitByCountry = totalGProfitsByCountryMap.get(account.getCountry());
			} else {
				totalGrossProfitByCountry = new TotalProfit(account.getCountry(), "Country");
			}
			totalGrossProfitByCountry.addProfit(grossProfit);
			totalGProfitsByCountryMap.put(account.getCountry(), totalGrossProfitByCountry);
		}

		return totalGProfitsByCountryMap;
	}

	/**
	 * 
	 * @param accountsList
	 * @return
	 */
	public static TreeMap<String, TotalProfit> calculateTotalNetProfitByRegion(final List<Account> accountsList) {
		TreeMap<String, TotalProfit> totalNProfitsByRegionMap = new TreeMap<String, TotalProfit>();
		for (Account account : accountsList) {
			// logger.debug(account);
			TotalProfit totalNetProfit = null;

			BigDecimal netProfit = ProfitCalculator.calculateNetProfitInUSD(account);

			if (totalNProfitsByRegionMap.containsKey(account.getRegion())) {
				totalNetProfit = totalNProfitsByRegionMap.get(account.getRegion());
			} else {
				totalNetProfit = new TotalProfit(account.getRegion(), "Region");
			}
			totalNetProfit.addProfit(netProfit);
			totalNProfitsByRegionMap.put(account.getRegion(), totalNetProfit);

		}
		return totalNProfitsByRegionMap;
	}
}
