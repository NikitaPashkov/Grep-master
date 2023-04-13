package org.example;

import org.kohsuke.args4j.Argument;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.Option;

import java.util.List;

public final class AppParser {
    @Option(name = "-v", metaVar = "Invert search")
    private boolean inv;
    @Option(name = "-i", metaVar = "Ignore case of found content")
    private boolean ic;
    @Option(name = "-r", metaVar = "Find by regex, not by word")
    private boolean rg;
    @Argument(required = true, metaVar = "String to find")
    private String str;
    @Argument(required = true, index = 1, metaVar = "Input file name")
    private String inputName;

    public void launch(String[] args) {
        CmdLineParser parser = new CmdLineParser(this);
        try {
            parser.parseArgument(args);
            App app = new App(inv, ic, rg, str, inputName);
            try {
                List<String> lst = app.search();
                for (String line: lst)
                    System.out.println(line);
            }
            catch (RuntimeException e) {
                System.err.println("Such file not found");
            }
        }
        catch (CmdLineException e) {
            System.err.println("Incorrect command line");
        }
    }
}
