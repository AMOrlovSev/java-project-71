package hexlet.code;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Differ {
    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        String result = "{\n";

        Set<String> uniqueKeys = new HashSet<String>();
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());
        var sortedKeys = uniqueKeys.stream().sorted().toList();

        for (var key : sortedKeys) {
            if (map1.getOrDefault(key, String.valueOf("")).equals(map2.getOrDefault(key, String.valueOf("")))) {
                result += "    " + key + ": " + map1.get(key) + "\n";
                continue;
            }
            if (map1.containsKey(key)) {
                result += "  - " + key + ": " + map1.get(key) + "\n";
            }
            if (map2.containsKey(key)) {
                result += "  + " + key + ": " + map2.get(key) + "\n";
            }
        }

        return result + "}";
    }
}

