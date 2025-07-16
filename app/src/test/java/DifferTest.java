import hexlet.code.App;
import hexlet.code.Differ;
import org.junit.jupiter.api.Test;

import java.nio.file.Files;
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
}
