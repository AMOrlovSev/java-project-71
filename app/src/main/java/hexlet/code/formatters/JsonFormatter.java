package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    public static String format(List<Map<String, Object>> diffs) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(diffs);
    }
}
