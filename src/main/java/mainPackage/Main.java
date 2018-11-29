package mainPackage;

import factory.DBModelParser;
import factory.ModelParser;
import factory.TextModelParser;
import org.apache.commons.cli.*;

import java.util.Iterator;
import java.util.Set;

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
        Option helpOption = new Option("h", "help", false, "help with options.");
        optionGroup.addOption(fileOption)
                .addOption(dbOption)
                .addOption(helpOption);
        options.addOptionGroup(optionGroup);
        HelpFormatter help = new HelpFormatter();
        CommandLineParser lineParser = new DefaultParser();
        try {
            CommandLine cmd = lineParser.parse(options, args);
            String path;
            ModelParser modelParser;
            if (cmd.hasOption("h")) {
                help.setSyntaxPrefix("Reference:");
                help.printHelp(" to launch the application you can use the following commands: ", options);
                return;
            } else if (cmd.hasOption("f")) {
                path = cmd.getOptionValue("f");
                modelParser = new TextModelParser();
            } else if (cmd.hasOption("b")) {
                path = cmd.getOptionValue("b");
                modelParser = new DBModelParser();
            } else {
                help.setSyntaxPrefix("Error:");
                help.printHelp(" To correctly launch the application, you must enter one of the following options.", options);
                return;
            }
            Set<String> facts = modelParser.evaluate(path);
            Iterator itr = facts.iterator();
            // The  set of facts has been verified previously, so this method should not throw an exception.
            System.out.print(itr.next());
            while (itr.hasNext()) {
                System.out.print("," + itr.next());
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}








