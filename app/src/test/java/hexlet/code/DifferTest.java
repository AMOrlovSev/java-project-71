package hexlet.code;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DifferTest {
    private static String resultStylishJson;
    private static String resultStylishYaml;
    private static String resultPlainJson;
    private static String resultPlainYaml;
    private static String resultJsonJson;
    private static String resultJsonYaml;

    @BeforeAll
    public static void beforeAll() throws Exception {
        resultStylishJson = Differ.readFileToString("resultStylish.json");
        resultStylishJson = Differ.readFileToString("resultStylish.json");
        resultStylishYaml = Differ.readFileToString("resultStylish.yaml");
        resultPlainJson = Differ.readFileToString("resultPlain.json");
        resultPlainYaml = Differ.readFileToString("resultPlain.yaml");
        resultJsonJson = Differ.readFileToString("resultJson.json");
        resultJsonYaml = Differ.readFileToString("resultJson.yaml");
    }

    @Test
    public void testGenerateJsonDefault() throws Exception {
        String result = Differ.generate("file1.json", "file2.json");
        assertEquals(resultStylishJson, result);
    }

    @Test
    public void testGenerateYamlDefault() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml");
        assertEquals(resultStylishYaml, result);
    }

    @Test
    public void testGenerateJsonStylish() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "stylish");
        assertEquals(resultStylishJson, result);
    }

    @Test
    public void testGenerateYamlStylish() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "stylish");
        assertEquals(resultStylishYaml, result);
    }

    @Test
    public void testGenerateJsonPlain() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "plain");
        assertEquals(resultPlainJson, result);
    }

    @Test
    public void testGenerateYamlPlain() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "plain");
        assertEquals(resultPlainYaml, result);
    }

    @Test
    public void testGenerateJsonJson() throws Exception {
        String result = Differ.generate("file1.json", "file2.json", "json");
        assertEquals(resultJsonJson, result);
    }

    @Test
    public void testGenerateYamlJson() throws Exception {
        String result = Differ.generate("file1.yaml", "file2.yaml", "json");
        assertEquals(resultJsonYaml, result);
    }

}
