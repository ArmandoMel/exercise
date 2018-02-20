package exercise.calculable;

import java.math.BigDecimal;

public class TotalProfit implements Comparable<TotalProfit> {

	private BigDecimal totalProfit = new BigDecimal(0);

	//
	private final String key;

	// the value could be "Country" or "Region"
	private final String keyGroup;

	public TotalProfit(String key, String keyGroup) {
		this.key = key;
		this.keyGroup = keyGroup;
	}

	@Override
	public int compareTo(TotalProfit o) {
		return this.totalProfit.compareTo(o.getTotalProfit());
	}

	public BigDecimal addProfit(BigDecimal augendProfit) {
		this.totalProfit = totalProfit.add(augendProfit);
		return totalProfit;
	}

	public BigDecimal getTotalProfit() {
		return totalProfit;
	}

	public void setTotalProfit(BigDecimal totalProfit) {
		this.totalProfit = totalProfit;
	}

	public String getKey() {
		return key;
	}

	public String getKeyGroup() {
		return keyGroup;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
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
		TotalProfit other = (TotalProfit) obj;
		if (key == null) {
			if (other.getKey() != null)
				return false;
		} else if (!key.equals(other.getKey()))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TotalNetProfit [" + keyGroup + " = " + key + ", Total Profit = " + getTotalProfit() + "]";
	}

}
