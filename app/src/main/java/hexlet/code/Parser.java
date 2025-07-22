package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.util.Map;

public class Parser {

    public static <T> Map<String, T> readStringToMap(String content, String format) throws IOException {
        if (format.equals("json")) {
            return convertJsonStringToMap(content);
        } else if (format.equals("yaml") || format.equals("yml")) {
            return convertYamlStringToMap(content);
        } else {
            throw new IOException("Format not support: " + format);
        }
    }

    private static <T> Map<String, T> convertJsonStringToMap(String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<>() { });
    }

    private static <T> Map<String, T> convertYamlStringToMap(String yamlString) throws IOException {
        final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        return yamlMapper.readValue(yamlString, new TypeReference<>() { });
    }
}
