package hexlet.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static hexlet.code.Comparator.compareMaps;
import static hexlet.code.Formatter.FORMAT_STYLISH;

public class Differ {

    public static String generate(String file1, String file2, String format) throws Exception {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        map1 = Parser.readFileToMap(file1);
        map2 = Parser.readFileToMap(file2);

        List<Map<String, Object>> diffs = compareMaps(map1, map2);

        return Formatter.process(diffs, format);
    }

    public static String generate(String file1, String file2) throws Exception {
        return generate(file1, file2, FORMAT_STYLISH);
    }
}

