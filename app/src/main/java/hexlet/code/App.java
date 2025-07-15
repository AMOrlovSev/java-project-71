package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;

@Command(
        name = "gendiff",
        description = "Compares two configuration files and shows a difference.",
        version = "1.0",
        mixinStandardHelpOptions = true,
        usageHelpAutoWidth = true
)
public class App implements Runnable {

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
    public void run() {
        // Логика сравнения файлов
        System.out.println("Hello World!");
        System.out.printf("Comparing %s and %s (format: %s)%n", file1, file2, format);
    }

    public static void main(String[] args) {
        new CommandLine(new App())
                .setUsageHelpLongOptionsMaxWidth(30)
                .execute(args);
    }
}

