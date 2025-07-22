package hexlet.code;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeSet;

public class Comparator {
    public static final String STATUS_ADDED = "added";
    public static final String STATUS_REMOVED = "removed";
    public static final String STATUS_UNCHANGED = "unchanged";
    public static final String STATUS_CHANGED = "changed";

    public static List<Map<String, Object>> compareMaps(Map<String, Object> map1, Map<String, Object> map2) {
        Set<String> allKeys = new TreeSet<>();
        allKeys.addAll(map1.keySet());
        allKeys.addAll(map2.keySet());

        List<Map<String, Object>> diffs = new LinkedList<>();

        for (String key : allKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);
            Map<String, Object> diffEntry = new HashMap<>();

            diffEntry.put("key", key);
            diffEntry.put("oldValue", value1);
            diffEntry.put("newValue", value2);

            if (!map1.containsKey(key)) {
                diffEntry.put("status", STATUS_ADDED);
            } else if (!map2.containsKey(key)) {
                diffEntry.put("status", STATUS_REMOVED);
            } else if (Objects.equals(value1, value2)) {
                diffEntry.put("status", STATUS_UNCHANGED);
            } else {
                diffEntry.put("status", STATUS_CHANGED);
            }

            diffs.add(diffEntry);
        }
        return diffs;
    }
}
