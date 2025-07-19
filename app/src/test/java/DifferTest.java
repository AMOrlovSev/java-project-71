import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerateJson() throws Exception {
        Map<String, Object> map1 = Parser.readFileToMap(Parser.resolvePath("file1.json"));
        Map<String, Object> map2 = Parser.readFileToMap(Parser.resolvePath("file2.json"));

        String result = Differ.generate(map1, map2);

        String correct = Files.readString(Parser.resolvePath("result.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateYaml() throws Exception {
        Map<String, Object> map1 = Parser.readFileToMap(Parser.resolvePath("file1.yaml"));
        Map<String, Object> map2 = Parser.readFileToMap(Parser.resolvePath("file2.yaml"));

        String result = Differ.generate(map1, map2);

        String correct = Files.readString(Parser.resolvePath("result.yaml"));
        assertEquals(result, correct);
    }

}
