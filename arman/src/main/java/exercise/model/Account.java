package exercise.model;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 
 * @author Arman Melkumyan
 *
 */
public class Account implements Serializable {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int accountId; 
	private String region;
	private String country;
	
	private String currency;
	private BigDecimal grossProfit;
	private double taxRate;
	
	public Account() {
		// TODO Auto-generated constructor stub
	}
	
	

	public Account(int accountId) {
		super();
		this.accountId = accountId;
	}



	public Account(int accountId, String region, String country, String currency, BigDecimal grossProfit,
			double taxRate) {
		super();
		this.accountId = accountId;
		this.region = region;
		this.country = country;
		this.currency = currency;
		this.grossProfit = grossProfit;
		this.taxRate = taxRate;
	}



	public String getRegion() {
		return region;
	}
	public void setRegion(String region) {
		this.region = region;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public int getAccountId() {
		return accountId;
	}
	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}
	public String getCurrency() {
		return currency;
	}
	public void setCurrency(String currency) {
		this.currency = currency;
	}
	public BigDecimal getGrossProfit() {
		return grossProfit;
	}
	public void setGrossProfit(BigDecimal grossProfit) {
		this.grossProfit = grossProfit;
	}
	public double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(double taxRate) {
		this.taxRate = taxRate;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + accountId;
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		return true;
	}

	


	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", region=" + region + ", country=" + country + ", currency="
				+ currency + ", grossProfit=" + grossProfit + ", taxRate=" + taxRate + "]";
	}

	public boolean equalsAllFields(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		if (accountId != other.accountId)
			return false;
		if (country == null) {
			if (other.country != null)
				return false;
		} else if (!country.equals(other.country))
			return false;
		if (currency == null) {
			if (other.currency != null)
				return false;
		} else if (!currency.equals(other.currency))
			return false;
		if (grossProfit == null) {
			if (other.grossProfit != null)
				return false;
		} else if (!grossProfit.equals(other.grossProfit))
			return false;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		if (Double.doubleToLongBits(taxRate) != Double.doubleToLongBits(other.taxRate))
			return false;
		return true;
	}
	
	
	
	
}
