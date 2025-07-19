package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerateJsonStylish() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "stylish");
        String correct = Files.readString(Parser.resolvePath("result.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "stylish");
        String correct = Files.readString(Parser.resolvePath("result.yaml"));
        assertEquals(result, correct);
    }

}
