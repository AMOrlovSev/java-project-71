import hexlet.code.Differ;
import hexlet.code.Parser;
import org.junit.jupiter.api.Test;
import java.nio.file.Files;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    @Test
    public void testGenerateJson() throws Exception {
        String result = Differ.generate("file1.json", "file2.json");
        String correct = Files.readString(Parser.resolvePath("result.json"));
        assertEquals(result, correct);
    }

    @Test
    public void testGenerateYaml() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml");
        String correct = Files.readString(Parser.resolvePath("result.yaml"));
        assertEquals(result, correct);
    }

}
