package hexlet.code;

import java.util.List;

public class Formater {
    public static final String FORMAT_STYLISH = "stylish";

    public static String process(List<List<Object>> values, String form) {
        String result = "";

        if (form.equals(FORMAT_STYLISH)) {
            result = stylishFormat(values);
        }

        return result;
    }


    public static String stylishFormat(List<List<Object>> vals) {

        StringBuilder result = new StringBuilder("{\n");

        for (var key : vals) {
            result.append("  ")
                    .append(key.getLast())
                    .append(" ")
                    .append(key.getFirst())
                    .append(": ")
                    .append(key.get(1))
                    .append("\n");

        }

        return result.append("}").toString();
    }

}
