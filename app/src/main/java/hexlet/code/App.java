package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

import java.io.IOException;
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
            Map<String, Object> map1 = Parser.readFileToMap(Parser.resolvePath(file1));
            Map<String, Object> map2 = Parser.readFileToMap(Parser.resolvePath(file2));

            System.out.println(Differ.generate(map1, map2));

            return CommandLine.ExitCode.OK;
        } catch (IOException e) {
            System.err.println("Error: " + e.getMessage());
            return CommandLine.ExitCode.SOFTWARE;
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new App())
                .setUsageHelpLongOptionsMaxWidth(30)
                .execute(args);
        System.exit(exitCode);
    }
}

