import java.util.HashMap;
import java.util.TreeMap;

import fc.util.ValueComparator;


public class MainClass {
	public static void main(String args[]){
		HashMap map = new HashMap();
        ValueComparator bvc = new ValueComparator(map);
        TreeMap sorted_map = new TreeMap(bvc);

        map.put("A", 99);
        map.put("B", 67);
        map.put("C", 68);
        map.put("D", 67);
        map.put("D", 50);

        System.out.println("unsorted map: " + map);
        sorted_map.putAll(map);
        System.out.println("results: " + sorted_map);
	}
}
