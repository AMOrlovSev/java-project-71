package hexlet.code.formatters;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static hexlet.code.Comparator.STATUS_ADDED;
import static hexlet.code.Comparator.STATUS_CHANGED;
import static hexlet.code.Comparator.STATUS_REMOVED;
import static hexlet.code.Comparator.STATUS_UNCHANGED;

public class PlainFormatter {
    public static String format(List<Map<String, Object>> diffs) {

        StringBuilder result = new StringBuilder("");

        for (Map<String, Object> diff : diffs) {
            String status = (String) diff.get("status");
            String key = (String) diff.get("key");
            Object oldValue = diff.get("oldValue");
            Object newValue = diff.get("newValue");

            switch (status) {
                case STATUS_UNCHANGED -> { }
                case STATUS_REMOVED ->
                        result.append(String.format("\nProperty '%s' was removed",
                                key));
                case STATUS_ADDED ->
                        result.append(String.format("\nProperty '%s' was added with value %s",
                                key, formatValue(newValue)));
                case STATUS_CHANGED ->
                    result.append(String.format("\nProperty '%s' was updated. From %s to %s",
                            key, formatValue(oldValue), formatValue(newValue)));
                default ->
                        throw new IllegalArgumentException("Unknown status: " + status);
            }
        }

        return result.toString().trim();
    }

    private static String formatValue(Object obj) {
        String result = "";
        if (obj instanceof String) {
            result = "'" + obj + "'";
        } else if (obj instanceof Integer || obj instanceof Boolean) {
            result = obj.toString();
        } else if (obj instanceof Collection || obj instanceof Map) {
            result = "[complex value]";
        } else if (obj == null) {
            result = "null";
        } else {
            result = obj.toString();
        }
        return result;
    }
}
