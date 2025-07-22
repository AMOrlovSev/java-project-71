package hexlet.code;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import java.util.concurrent.Callable;

import static hexlet.code.Formatter.FORMAT_STYLISH;

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
            defaultValue = FORMAT_STYLISH,
            paramLabel = "format"
    )
    private String format;

    @Override
    public final Integer call() {
        System.out.println(Differ.generate(file1, file2, format));
        return 0;
    }

    public static void main(String[] args) {
        int helpLongOptionsMaxWidth = 30;
        int exitCode = new CommandLine(new App())
                .setUsageHelpLongOptionsMaxWidth(helpLongOptionsMaxWidth)
                .execute(args);
        System.exit(exitCode);
    }
}

