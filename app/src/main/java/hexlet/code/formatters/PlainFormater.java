package hexlet.code.formatters;

import java.util.List;

import static hexlet.code.Differ.ADDED_PREFIX;
import static hexlet.code.Differ.REMOVED_PREFIX;
import static hexlet.code.Differ.UNCHANGED_PREFIX;

public class PlainFormater {
    public static String format(List<List<Object>> keyValPrefix) {

        StringBuilder result = new StringBuilder("");

        String keyCurrent = "";
        String keyNext = "";
        Object valCurrent;
        Object valNext;
        String prefixCurrent = "";

        for (int i = 0; i < keyValPrefix.size() - 1; i++) {
            keyCurrent = keyValPrefix.get(i).getFirst().toString();
            keyNext = keyValPrefix.get(i + 1).getFirst().toString();
            valCurrent = keyValPrefix.get(i).get(1);
            valNext = keyValPrefix.get(i + 1).get(1);
            prefixCurrent = keyValPrefix.get(i).getLast().toString();

            if (prefixCurrent.equals(UNCHANGED_PREFIX)) {
                continue;
            } else if (keyCurrent.equals(keyNext)) {
                result.append("\nProperty '")
                        .append(keyCurrent)
                        .append("' was updated. From ")
                        .append(objectValueToString(valCurrent))
                        .append(" to ")
                        .append(objectValueToString(valNext));
                i++;
            } else if (prefixCurrent.equals(ADDED_PREFIX)) {
                result.append("\nProperty '")
                        .append(keyCurrent)
                        .append("' was added with value ")
                        .append(objectValueToString(valCurrent));
            } else if (prefixCurrent.equals(REMOVED_PREFIX)) {
                result.append("\nProperty '")
                        .append(keyCurrent)
                        .append("' was removed");
            }
        }

        return result.toString();
    }

    private static String objectValueToString(Object obj) {
        String result = "";
        if (obj instanceof String) {
            result = "'" + obj.toString() + "'";
        } else if (obj instanceof Integer || obj instanceof Boolean) {
            result = obj.toString();
        } else if (obj == null) {
            result = "null";
        } else {
            result = "[complex value]";
        }
        return result;
    }
}
