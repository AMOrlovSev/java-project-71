package hexlet.code;

import hexlet.code.formatters.PlainFormatter;
import hexlet.code.formatters.StylishFormatter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Formatter {
    public static final String FORMAT_STYLISH = "stylish";
    public static final String FORMAT_PLAIN = "plain";
    public static final String FORMAT_JSON = "json";

    public static String process(List<Map<String, Object>> diffs, String format) throws IOException {
        return switch (format) {
            case FORMAT_STYLISH -> StylishFormatter.format(diffs);
            case FORMAT_PLAIN -> PlainFormatter.format(diffs);
//            case FORMAT_JSON -> JsonFormatter.format(diffs);
            default -> throw new IOException("Unsupported format: " + format);
        };
    }
}
