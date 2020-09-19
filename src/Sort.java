import java.util.stream.*;
import java.util.*;
import java.nio.file.*;
import java.io.*;

public class Sort {
	public static HashMap<String, Integer> sortByValue(HashMap<String, Integer> po) throws IOException {

		// Create a list from elements of HashMap
		List<Map.Entry<String, Integer>> list = new LinkedList<Map.Entry<String, Integer>>(po.entrySet());
		// Sort the list
		Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
			public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
				return (o1.getValue()).compareTo(o2.getValue());
			}
		});

		// put data from sorted list to hashmap
		HashMap<String, Integer> temp = new LinkedHashMap<String, Integer>();
		for (Map.Entry<String, Integer> aa : list) {
			temp.put(aa.getKey(), aa.getValue());
		}
		return temp;
	}
//	    private Map<String, Integer> sortByValue(final Map<String, Integer> wordCounts) {
//
//	        return wordCounts.entrySet()
//
//	                .stream()
//
//	                .sorted((Map.Entry.<String, Integer>comparingByValue()))
//
//	                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e1, LinkedHashMap::new));
//	}

}
