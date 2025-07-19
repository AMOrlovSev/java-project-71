package hexlet.code;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ParserTest {
    @Test
    public void testReadFileToMap() throws Exception {
        Map result = Parser.readFileToMap("file1.yaml");
        Map<String, Object> correct = Map.of(
                "host", "hexlet.io",
                "timeout", 50,
                "proxy", "123.234.53.22",
                "follow", false
        );
        assertEquals(result, correct);
    }
}
