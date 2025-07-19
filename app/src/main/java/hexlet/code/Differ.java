package hexlet.code;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static final String ADDED_PREFIX = "+";
    private static final String REMOVED_PREFIX = "-";
    private static final String UNCHANGED_PREFIX = " ";


    public static String generate(String file1, String file2, String format) {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        try {
            map1 = Parser.readFileToMap(file1);
            map2 = Parser.readFileToMap(file2);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());

        List<List<Object>> keyValPrefix = new LinkedList<>();

        for (String key : uniqueKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (equals(value1, value2)) {
                keyValPrefix.add(Arrays.asList(key, value1, UNCHANGED_PREFIX));
                continue;
            }

            if (map1.containsKey(key)) {
                keyValPrefix.add(Arrays.asList(key, value1, REMOVED_PREFIX));
            }
            if (map2.containsKey(key)) {
                keyValPrefix.add(Arrays.asList(key, value2, ADDED_PREFIX));
            }
        }

        return Formater.process(keyValPrefix, format);
    }

    private static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }
}

