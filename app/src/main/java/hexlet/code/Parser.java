package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Map;

public class Parser {

    public static Map<String, Object> readFileToMap(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + filePath);
        }
        if (!Files.isRegularFile(filePath)) {
            throw new IOException("Path is not a file: " + filePath);
        }

        String content = Files.readString(filePath);
        String extension = fileExtension(filePath);

        if (extension.equals("json")) {
            return convertJsonStringToMap(content);
        } else if (extension.equals("yaml") || extension.equals("yml")) {
            return convertYamlStringToMap(content);
        } else {
            throw new IOException("File extension not support: " + filePath);
        }
    }

    private static Map<String, Object> convertJsonStringToMap(String jsonString) throws IOException {
        final ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(jsonString, new TypeReference<>() { });
    }

    private static Map<String, Object> convertYamlStringToMap(String yamlString) throws IOException {
        final ObjectMapper yamlMapper = new ObjectMapper(new YAMLFactory());
        return yamlMapper.readValue(yamlString, Map.class);
    }

    private static String fileExtension(Path filePath) {
        String fileName = filePath.getFileName().toString();
        String fileExt = "";

        int dotIndex = fileName.lastIndexOf('.');
        if (dotIndex > 0 && dotIndex < fileName.length() - 1) {
            fileExt = fileName.substring(dotIndex + 1);
        }
        return fileExt;
    }
}
