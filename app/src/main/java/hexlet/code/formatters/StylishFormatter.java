package hexlet.code.formatters;

import java.util.List;
import java.util.Map;

import static hexlet.code.Differ.STATUS_ADDED;
import static hexlet.code.Differ.STATUS_CHANGED;
import static hexlet.code.Differ.STATUS_REMOVED;
import static hexlet.code.Differ.STATUS_UNCHANGED;

public class StylishFormatter {
    public static String format(List<Map<String, Object>>  diffs) {

        StringBuilder result = new StringBuilder("{\n");

        for (Map<String, Object> diff : diffs) {
            String status = (String) diff.get("status");
            String key = (String) diff.get("key");
            Object oldValue = diff.get("oldValue");
            Object newValue = diff.get("newValue");

            switch (status) {
                case STATUS_UNCHANGED ->
                        result.append(String.format("    %s: %s%n", key, newValue));
                case STATUS_REMOVED ->
                        result.append(String.format("  - %s: %s%n", key, oldValue));
                case STATUS_ADDED ->
                        result.append(String.format("  + %s: %s%n", key, newValue));
                case STATUS_CHANGED -> {
                    result.append(String.format("  - %s: %s%n", key, oldValue));
                    result.append(String.format("  + %s: %s%n", key, newValue));
                }
                default ->
                        throw new IllegalArgumentException("Unknown status: " + status);
            }
        }
        return result.append("}").toString();
    }
}
