package hexlet.code;

import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

public class Differ {
    private static final String INDENT = "  ";
    private static final String ADDED_PREFIX = "+ ";
    private static final String REMOVED_PREFIX = "- ";
    private static final String UNCHANGED_PREFIX = "  ";


    public static String generate(Map<String, Object> map1, Map<String, Object> map2) {
        StringBuilder result = new StringBuilder("{\n");

        Set<String> uniqueKeys = new TreeSet<>();
        uniqueKeys.addAll(map1.keySet());
        uniqueKeys.addAll(map2.keySet());

        for (String key : uniqueKeys) {
            Object value1 = map1.get(key);
            Object value2 = map2.get(key);

            if (equals(value1, value2)) {
                appendLine(result, UNCHANGED_PREFIX, key, value1);
                continue;
            }

            if (map1.containsKey(key)) {
                appendLine(result, REMOVED_PREFIX, key, value1);
            }
            if (map2.containsKey(key)) {
                appendLine(result, ADDED_PREFIX, key, value2);
            }
        }

        return result.append("}").toString();
    }

    private static void appendLine(StringBuilder builder, String prefix, String key, Object value) {
        builder.append(INDENT)
                .append(prefix)
                .append(key)
                .append(": ")
                .append(value)
                .append("\n");
    }

    private static boolean equals(Object o1, Object o2) {
        if (o1 == null) {
            return o2 == null;
        }
        return o1.equals(o2);
    }
}

