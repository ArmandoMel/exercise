package exercise.util;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Map;

public class ValueComparator implements Comparator<String> {
    
	Map<String, BigDecimal> map;

    public ValueComparator(Map<String, BigDecimal> map) {
        this.map = map;
    }

	@Override
	public int compare(String o1, String o2) {		
        return map.get(o1).compareTo(map.get(o2));
    }
}