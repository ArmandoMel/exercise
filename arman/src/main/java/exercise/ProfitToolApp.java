package exercise;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

import org.apache.log4j.Logger;

//import exercise.calculable.TotalGrossProfit;
//import exercise.calculable.TotalNetProfit;
import exercise.calculable.TotalProfit;
import exercise.model.Account;
import exercise.model.AccountReader;

public class ProfitToolApp {

	private final static Logger logger = Logger.getLogger(ProfitToolApp.class);
	private static String dataFileName;
	private static boolean hasHeader = true;
	
	
	public static void main(String[] args) {
		
		dataFileName = args[0];
		hasHeader =  Boolean.parseBoolean(args[1]);
		
		try {
			new ProfitToolApp().process();
		} catch (ApplicationException e) {
			logger.error("Error: ", e);
			System.exit(1);
		}
	}
	
	
	public void process() throws ApplicationException {
		
			TreeMap<String, TotalProfit> totalNProfitsByRegionMap = new TreeMap<String, TotalProfit>();
			
			TreeMap<String, TotalProfit> totalGProfitsByCountryMap = new TreeMap<String, TotalProfit>();
			TreeMap<String, TotalProfit> totalGProfitsByRegionMap = new TreeMap<String, TotalProfit>();

			List<Account> accountsList = AccountReader.readAccounts(dataFileName, hasHeader);
			for (Account account : accountsList) {
//				logger.debug(account);
				TotalProfit totalNetProfit = null;
				TotalProfit totalGrossProfitByRegion = null;
				TotalProfit totalGrossProfitByCountry = null;
				
				BigDecimal netProfit = ProfitCalculator.calculateNetProfitInUSD(account);
				BigDecimal grossProfit = ProfitCalculator.calculateGrossProfitInUSD(account);
				
				if (totalNProfitsByRegionMap.containsKey(account.getRegion())) {
					totalNetProfit = totalNProfitsByRegionMap.get(account.getRegion());					
				} else {					
					totalNetProfit = new TotalProfit(account.getRegion(), "Region");
				}
				totalNetProfit.addProfit(netProfit);
				totalNProfitsByRegionMap.put(account.getRegion(), totalNetProfit);
				
				if (totalGProfitsByCountryMap.containsKey(account.getCountry())) {
					totalGrossProfitByCountry = totalGProfitsByCountryMap.get(account.getCountry());
				} else {
					totalGrossProfitByCountry = new TotalProfit(account.getCountry(), "Country");					
				}
				totalGrossProfitByCountry.addProfit(grossProfit);
				totalGProfitsByCountryMap.put(account.getCountry(), totalGrossProfitByCountry);
								
				if (totalGProfitsByRegionMap.containsKey(account.getRegion())) {
					totalGrossProfitByRegion = totalGProfitsByRegionMap.get(account.getRegion());
				} else {
					totalGrossProfitByRegion = new TotalProfit(account.getRegion(), "Region");					
				}
				totalGrossProfitByRegion.addProfit(grossProfit);
				totalGProfitsByRegionMap.put(account.getRegion(), totalGrossProfitByRegion);
				
				
				
			}
			
			System.out.println();
			printTotalProfitBy(totalNProfitsByRegionMap, new String[]{"[Region]", "[Total Net Profit]"});
			
			System.out.println();
			printTotalProfitBy(totalGProfitsByCountryMap, new String[]{"[Country]", "[Total Gross Profit]"});
			
			System.out.println("Most Profitable Region before taxes");
			printMostProfitableRegion(totalGProfitsByRegionMap, new String[]{"[Region]", "[Profit]"});
			System.out.println("\nMost Profitable Region after taxes");
			printMostProfitableRegion(totalNProfitsByRegionMap, new String[]{"[Region]", "[Profit]"});
	}
	
	
	
	private void printTotalProfitBy(TreeMap<String, TotalProfit> totalNProfitsByRegionMap, String[] header) {
		
		for (int i = 0; i < header.length; i++) {
			System.out.print(header[i] + "\t");
		}
		System.out.println();
		System.out.println("--------------------------");
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		for (String key : totalNProfitsByRegionMap.keySet()) {
			System.out.println(key + "\t" + currency.format(totalNProfitsByRegionMap.get(key).getTotalProfit()));
		}
		System.out.println();
	}
	
	private void printMostProfitableRegion(TreeMap<String, TotalProfit> totalNProfitsMap, String[] header) {
		
		for (int i = 0; i < header.length; i++) {
			System.out.print(header[i] + "\t");
		}
		System.out.println();
		System.out.println("--------------------------");
		NumberFormat currency = NumberFormat.getCurrencyInstance();
		
		List<TotalProfit> sorderMap = new ArrayList<TotalProfit>(totalNProfitsMap.values());
		sorderMap.sort((TotalProfit o1, TotalProfit o2)-> (-1 * o1.getTotalProfit().compareTo(o2.getTotalProfit())));
		TotalProfit mostProfitable = sorderMap.get(0);
		System.out.println(mostProfitable.getKey() + "\t" + currency.format(mostProfitable.getTotalProfit()));
		
	}
	
}
