package ee.sda.maven.hackerrank;

import java.util.HashMap;
import java.util.Map;

public class SparseArrays {

    //Our default what we started out with
//    //array of integers with one element, zero
//    int[] matchingStrings(String[] strings, String[] queries){
//        return new int []{0};
//    }


    public int[] matchingStrings(String[] strings, String[] queries) {
        Map<String, Integer> stringCounts = new HashMap<>();
        for (String string : strings) {

//      stringCounts.compute(string, (k, v) -> (v == null ? 1 : v + 1));
            if (!stringCounts.containsKey(string)) {
                stringCounts.put(string, 1);
            } else {
                stringCounts.put(string, stringCounts.get(string) + 1);
            }
        }
        int[] counts = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            String query = queries[i];
            counts[i] = stringCounts.getOrDefault(query, 0);
        }
        return counts;
    }
}

