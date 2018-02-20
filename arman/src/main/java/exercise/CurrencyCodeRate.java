package exercise;

import java.math.BigDecimal;

public enum CurrencyCodeRate {
	
	USD("USD", 1),
	CAN("CAN", 0.8108),
	HKD("HKD", 0.128983),
	SAR("SAR", 0.266645),
	GBP("GBP", 1.57217),
	EUR("EUR", 1.11598),
	INR("INR", 0.0157311),
	AUD("AUD", 0.772744),
	NZD("NZD", 0.684621),
	MXN("MXN", 0.064829);


	private final String code;
	private final double rate;
	private CurrencyCodeRate(String code, double rate) {
		this.code = code;
		this.rate = rate;
	}

	public double rate() {
		return rate;
	}

	public static CurrencyCodeRate fromString(String code) {
		for (CurrencyCodeRate currencyCodeRate : CurrencyCodeRate.values()) {
			if (currencyCodeRate.code.equalsIgnoreCase(code)) {
				return currencyCodeRate;
			}
		}
		return null;
	}
	
	public BigDecimal convertAmountToUSD(BigDecimal amountInCurrency) {
		return amountInCurrency.multiply(new BigDecimal(rate));
		
	}
	
	public static BigDecimal convertAmountToUSD(final BigDecimal amountInCurrency, final CurrencyCodeRate currencyCodeRate) {
		return amountInCurrency.multiply(new BigDecimal(currencyCodeRate.rate));
		
	}
}
