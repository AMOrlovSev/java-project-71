package hexlet.code.formatters;

import java.util.List;

public class StylishFormater {
    public static String format(List<List<Object>> vals) {

        StringBuilder result = new StringBuilder("{\n");

        for (var list : vals) {
            result.append("  ")
                    .append(list.getLast())
                    .append(" ")
                    .append(list.getFirst())
                    .append(": ")
                    .append(list.get(1))
                    .append("\n");
        }

        return result.append("}").toString();
    }
}
