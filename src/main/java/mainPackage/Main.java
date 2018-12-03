package mainPackage;

import engine.Engine;
import interconnection.UserConsoleInterconnection;
import org.apache.commons.cli.*;

public class Main {

    public static void main(String[] args) {

        if (args.length == 0 || args[0].equals("")) {
            System.out.print("Command line should not be empty, enter -help for reference");
            return;
        }

        Options options = new Options();
        OptionGroup optionGroup = new OptionGroup();
        Option fileOption = Option.builder("f")
                .hasArg()
                .argName("file_path")
                .numberOfArgs(1)
                .valueSeparator(' ')
                .longOpt("file")
                .desc("select this option for reading rules from txt file")
                .build();
        Option dbOption = Option.builder("b")
                .hasArg()
                .argName("file_properties")
                .numberOfArgs(1)
                .valueSeparator(' ')
                .longOpt("database")
                .desc("select this option for reading rules from database")
                .build();
        Option inserOption = Option.builder("i")
                .hasArg()
                .argName("file_path, file_properties ")
                .numberOfArgs(2)
                .valueSeparator(' ')
                .longOpt("insert")
                .desc("select this option for inserting txt to database")
                .build();
        Option helpOption = new Option("h", "help", false, "help with options.");
        optionGroup.addOption(fileOption)
                .addOption(dbOption)
                .addOption(helpOption)
                .addOption(inserOption);
        options.addOptionGroup(optionGroup);
        HelpFormatter help = new HelpFormatter();
        CommandLineParser lineParser = new DefaultParser();
        try {
            CommandLine cmd = lineParser.parse(options, args);
            String path;
            if (cmd.hasOption("h")) {
                help.setSyntaxPrefix("Reference:");
                help.printHelp(" to launch the application you can use the following commands: ", options);
            } else if (cmd.hasOption("f")) {
                path = cmd.getOptionValue("f");
                Engine engine = new Engine(new UserConsoleInterconnection());
                engine.evaluate('f', path);
            } else if (cmd.hasOption("b")) {
                path = cmd.getOptionValue("b");
                Engine engine = new Engine(new UserConsoleInterconnection());
                engine.evaluate('b', path);
            } else if (cmd.hasOption("i")) {
                String[] arguments = cmd.getOptionValues("i");
                Engine engine = new Engine(new UserConsoleInterconnection());
                engine.insertDataBase(arguments[0], arguments[1]);
            } else {
                help.setSyntaxPrefix("Error:");
                help.printHelp(" To correctly launch the application, you must enter one of the following options.", options);
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}








