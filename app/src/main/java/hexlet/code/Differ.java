package hexlet.code;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.Comparator.compareMaps;

public class Differ {

    public static String generate(String file1, String file2, String format) {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        try {
            map1 = Parser.readFileToMap(file1);
            map2 = Parser.readFileToMap(file2);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        List<Map<String, Object>> diffs = compareMaps(map1, map2);

        String result = "";
        try {
            return Formatter.process(diffs, format);
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
        }

        return result;
    }
}

