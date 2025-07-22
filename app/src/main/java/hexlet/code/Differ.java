package hexlet.code;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static hexlet.code.Comparator.compareMaps;
import static hexlet.code.Formatter.FORMAT_STYLISH;
import static hexlet.code.Parser.readStringToMap;

public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {

        String string1 = readFileToString(file1);
        String extension1 = fileExtension(resolvePath(file1));

        String string2 = readFileToString(file2);
        String extension2 = fileExtension(resolvePath(file2));

        Map<String, Object> map1 = readStringToMap(string1, extension1);
        Map<String, Object> map2 = readStringToMap(string2, extension2);

        List<Map<String, Object>> diffs = compareMaps(map1, map2);

        return Formatter.process(diffs, format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, FORMAT_STYLISH);
    }


    public static String readFileToString(String file) throws IOException {
        Path filePath = resolvePath(file);

        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + filePath);
        } else if (!Files.isRegularFile(filePath)) {
            throw new IOException("Path is not a file: " + filePath);
        }

        return Files.readString(filePath);
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

    public static Path resolvePath(String pathString) throws IOException {
        if (pathString == null || pathString.trim().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        Path path = Paths.get(pathString).normalize();

        if (!path.isAbsolute()) {
            path = Paths.get("").toAbsolutePath()
                    .resolve("src/test/resources")
                    .resolve(path)
                    .normalize();
        }

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Path does not exist: " + path);
        }

        return path;
    }
}

