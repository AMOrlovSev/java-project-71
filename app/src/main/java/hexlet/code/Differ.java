package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_UNCHANGED = "unchanged";
    public static final String STATUS_CHANGED = "changed";


    public static <T> String generate(String file1, String file2, String format) {
        Map<String, T> map1 = new HashMap<>();
        Map<String, T> map2 = new HashMap<>();

        try {
            map1 = Parser.readFileToMap(file1);
            map2 = Parser.readFileToMap(file2);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> diffs = new LinkedList<>();

        for (String key : allKeys) {
            T value1 = map1.get(key);
            T value2 = map2.get(key);
            Map<String, Object> diffEntry = new HashMap<>();

            diffEntry.put("key", key);
            diffEntry.put("oldValue", value1);
            diffEntry.put("newValue", value2);

            if (!map1.containsKey(key)) {
                diffEntry.put("status", STATUS_ADDED);
            } else if (!map2.containsKey(key)) {
                diffEntry.put("status", STATUS_REMOVED);
            } else if (equals(value1, value2)) {
                diffEntry.put("status", STATUS_UNCHANGED);
            } else {
                diffEntry.put("status", STATUS_CHANGED);
            }

            diffs.add(diffEntry);
        }

        String result = "";
        try {
            return Formatter.process(diffs, format);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return result;
    }

    private static <T> boolean equals(T o1, T o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }
}

