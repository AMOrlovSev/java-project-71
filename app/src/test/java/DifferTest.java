import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void generateTest() throws Exception {
        Map<String, Object> map1 = App.readJsonFile(App.resolvePath("file1.json"));
        Map<String, Object> map2 = App.readJsonFile(App.resolvePath("file2.json"));

        String result = Differ.generate(map1, map2);

        String correct = Files.readString(App.resolvePath("result.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateWithEmptyMaps() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = new HashMap<>();

        String result = Differ.generate(map1, map2);
        assertEquals("{\n}", result);
    }

    @Test
    public void testGenerateWithFirstMapEmpty() {
        Map<String, Object> map1 = new HashMap<>();
        Map<String, Object> map2 = Map.of("key", "value");

        String expected = "{\n"
                + "  + key: value\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithSecondMapEmpty() {
        Map<String, Object> map1 = Map.of("key", "value");
        Map<String, Object> map2 = new HashMap<>();

        String expected = "{\n"
                + "  - key: value\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithAllDifferentKeys() {
        Map<String, Object> map1 = Map.of("key1", "value1");
        Map<String, Object> map2 = Map.of("key2", "value2");

        String expected = "{\n"
                + "  - key1: value1\n"
                + "  + key2: value2\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithSameKeysDifferentValues() {
        Map<String, Object> map1 = Map.of("key", "value1");
        Map<String, Object> map2 = Map.of("key", "value2");

        String expected = "{\n"
                + "  - key: value1\n"
                + "  + key: value2\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithSameKeysSameValues() {
        Map<String, Object> map1 = Map.of("key", "value");
        Map<String, Object> map2 = Map.of("key", "value");

        String expected = "{\n"
                + "    key: value\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithNullValues() {
        Map<String, Object> map1 = new HashMap<>();
        map1.put("key", null);
        Map<String, Object> map2 = Map.of("key", "value");

        String expected = "{\n"
                + "  - key: null\n"
                + "  + key: value\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithMultipleKeys() {
        Map<String, Object> map1 = Map.of(
                "a", 1,
                "b", 2,
                "c", 3
        );
        Map<String, Object> map2 = Map.of(
                "a", 1,
                "b", 20,
                "d", 4
        );

        String expected = "{\n"
                + "    a: 1\n"
                + "  - b: 2\n"
                + "  + b: 20\n"
                + "  - c: 3\n"
                + "  + d: 4\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }

    @Test
    public void testGenerateWithNumericAndBooleanValues() {
        Map<String, Object> map1 = Map.of(
                "timeout", 50,
                "enabled", false
        );
        Map<String, Object> map2 = Map.of(
                "timeout", 100,
                "enabled", true,
                "verbose", true
        );

        String expected = "{\n"
                + "  - enabled: false\n"
                + "  + enabled: true\n"
                + "  - timeout: 50\n"
                + "  + timeout: 100\n"
                + "  + verbose: true\n"
                + "}";
        String result = Differ.generate(map1, map2);
        assertEquals(expected, result);
    }
}
