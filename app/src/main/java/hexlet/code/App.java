package hexlet.code;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.concurrent.Callable;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        version = "1.0",
        mixinStandardHelpOptions = true,
        usageHelpAutoWidth = true
)
public class App implements Callable<Integer> {

    @Parameters(
            index = "0",
            description = "path to first file",
            paramLabel = "filepath1"
    )
    private String file1;

    @Parameters(
            index = "1",
            description = "path to second file",
            paramLabel = "filepath2"
    )
    private String file2;

    @Option(
            names = {"-f", "--format"},
            description = "output format [default: ${DEFAULT-VALUE}]",
            defaultValue = "stylish",
            paramLabel = "format"
    )
    private String format;

    @Override
    public Integer call() {
        try {
            Map<String, Object> map1 = readJsonFile(resolvePath(file1));
            Map<String, Object> map2 = readJsonFile(resolvePath(file2));

            System.out.println(Differ.generate(map1, map2));

            return CommandLine.ExitCode.OK;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return CommandLine.ExitCode.SOFTWARE;
        }
    }

//    public static Path resolvePath(String pathString) throws IOException {
//        if (pathString == null || pathString.trim().isEmpty()) {
//            throw new IllegalArgumentException("Path cannot be null or empty");
//        }
//
//        Path path = Paths.get(pathString).normalize();
//
//        if (!path.isAbsolute()) {
//            path = Paths.get("").toAbsolutePath()
//                    .resolve("src/test/resources")
//                    .resolve(path)
//                    .normalize();
//        }
//
//        if (!Files.exists(path)) {
//            throw new FileNotFoundException("Path does not exist: " + path);
//        }
//
//        return path;
//    }

    public static Path resolvePath(String pathString) throws IOException {
        if (pathString == null || pathString.trim().isEmpty()) {
            throw new IllegalArgumentException("Path cannot be null or empty");
        }

        Path path = Paths.get(pathString).normalize();

        if (!path.isAbsolute()) {
            path = Paths.get("").toAbsolutePath()
                    .resolve("src/test/resources")
                    .resolve(path)
                    .normalize();
        }

        if (!Files.exists(path)) {
            throw new FileNotFoundException("Path does not exist: " + path);
        }

        return path;
    }

    public static Map<String, Object> readJsonFile(Path filePath) throws IOException {
        if (!Files.exists(filePath)) {
            throw new IOException("File not found: " + filePath);
        }
        if (!Files.isRegularFile(filePath)) {
            throw new IOException("Path is not a file: " + filePath);
        }

        String content = Files.readString(filePath);
        return new ObjectMapper().readValue(content, new TypeReference<>() {
        });
    }


    public static void main(String[] args) {
        int exitCode = new CommandLine(new App())
                .setUsageHelpLongOptionsMaxWidth(30)
                .execute(args);
        System.exit(exitCode);
    }
}

