package hexlet.code;

import org.junit.jupiter.api.Test;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerateJsonStylish() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "stylish");
        String correct = Files.readString(Parser.resolvePath("resultStylish.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "stylish");
        String correct = Files.readString(Parser.resolvePath("resultStylish.yaml"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "plain");
        String correct = Files.readString(Parser.resolvePath("resultPlain.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateYamlPlain() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "plain");
        String correct = Files.readString(Parser.resolvePath("resultPlain.yaml"));
        assertEquals(result, correct);
    }

}
