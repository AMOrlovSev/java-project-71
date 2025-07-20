package hexlet.code;

import hexlet.code.formatters.PlainFormater;
import hexlet.code.formatters.StylishFormater;

import java.io.IOException;
import java.util.List;

public class Formater {
    public static final String FORMAT_STYLISH = "stylish";
    public static final String FORMAT_PLAIN = "plain";

    public static String process(List<List<Object>> values, String form) throws IOException {
        String result = "";

        if (form.equals(FORMAT_STYLISH)) {
            result = StylishFormater.format(values);
        } else if (form.equals(FORMAT_PLAIN)) {
            result = PlainFormater.format(values);
        } else {
            throw new IOException("Not support format: " + form);
        }

        return result;
    }


}
