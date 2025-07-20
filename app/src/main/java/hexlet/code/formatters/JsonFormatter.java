package hexlet.code.formatters;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class JsonFormatter {
    private static ObjectMapper objectMapper = new ObjectMapper()
            .enable(SerializationFeature.INDENT_OUTPUT);

    public static String format(List<Map<String, Object>> diffs) throws IOException {
        Map<String, Map<String, Object>> result = new LinkedHashMap<>();

        for (Map<String, Object> diff : diffs) {
            String key = (String) diff.get("key");
            String status = (String) diff.get("status");

            Map<String, Object> entry = new LinkedHashMap<>();
            entry.put("status", status);

            switch (status) {
                case "removed":
                    entry.put("oldValue", diff.get("oldValue"));
                    break;
                case "added":
                    entry.put("newValue", diff.get("newValue"));
                    break;
                case "changed":
                    entry.put("oldValue", diff.get("oldValue"));
                    entry.put("newValue", diff.get("newValue"));
                    break;
                case "unchanged":
                    // No additional fields needed
                    break;
                default:
                    throw new IllegalArgumentException("Unknown status: " + status);
            }

            result.put(key, entry);
        }

        return objectMapper.writeValueAsString(result);
    }
}
