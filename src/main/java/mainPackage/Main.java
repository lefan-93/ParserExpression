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
        Option xmlOption = Option.builder("x")
                .hasArg()
                .argName("file_path")
                .numberOfArgs(1)
                .valueSeparator(' ')
                .longOpt("xml")
                .desc("select this option for reading rules from xml")
                .build();
        Option convertTextToDBOption = Option.builder("d")
                .hasArg()
                .argName("file_path, file_properties ")
                .numberOfArgs(2)
                .valueSeparator(' ')
                .longOpt("converttb")
                .desc("select this option for inserting txt to database")
                .build();
        Option convertTextToXmlOption = Option.builder("l")
                .hasArg()
                .argName("text_file_path, xml_file_path ")
                .numberOfArgs(2)
                .valueSeparator(' ')
                .longOpt("converttx")
                .desc("select this option for convert txt to xml")
                .build();
        Option convertXmlToDBOption = Option.builder("m")
                .hasArg()
                .argName("text_file_path, file_properties ")
                .numberOfArgs(2)
                .valueSeparator(' ')
                .longOpt("convertxb")
                .desc("select this option for convert xml to database")
                .build();
        Option helpOption = new Option("h", "help", false, "help with options.");
        optionGroup.addOption(fileOption)
                .addOption(dbOption)
                .addOption(helpOption)
                .addOption(convertTextToDBOption)
                .addOption(convertTextToXmlOption)
                .addOption(xmlOption)
                .addOption(convertXmlToDBOption);
        options.addOptionGroup(optionGroup);
        HelpFormatter help = new HelpFormatter();
        help.setWidth(120);

        CommandLineParser lineParser = new DefaultParser();
        try {
            CommandLine cmd = lineParser.parse(options, args);
            String path;
            Engine engine = null;
            if (cmd.hasOption("h")) {
                help.setSyntaxPrefix("Reference:");
                help.printHelp("This application evaluate a set of rules presented in the form of a logical expression consisting of a set of facts " +
                                            "connected by the logical operators \"&\" and \"||\" and the fact of following from this expression.\n" +
                                            "For example: \"Fact1 && Fact2-> Fact3\"\n" +
                                            "Evaluate are made on the basis of the presented set of facts.\n" +
                                            "A set of rules and facts can be presented in the form of texts or xml format or database.\n " +
                                            "to launch the application you can use the following commands: ", options);
            } else if (cmd.hasOption("f")) {
                path = cmd.getOptionValue("f");
                 engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.TEXT, path );
            } else if (cmd.hasOption("b")) {
                path = cmd.getOptionValue("b");
                 engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.DATABASE, path );
            } else if (cmd.hasOption("x")) {
                path = cmd.getOptionValue("x");
                 engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.XML, path );
            } else if (cmd.hasOption("d")) {
                String[] arguments = cmd.getOptionValues("d");
                 engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.TEXT_TO_DATABASE,arguments[0],arguments[0]);
            }else if (cmd.hasOption("tx")) {
                String[] arguments = cmd.getOptionValues("l");
              engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.TEXT_TO_XML, arguments[0], arguments[1]);
            } else if (cmd.hasOption("m")) {
                String[] arguments = cmd.getOptionValues("m");
                 engine = new Engine(new UserConsoleInterconnection(), Engine.Mode.TEXT_TO_XML, arguments[0], arguments[1]);
            } else {
                help.setSyntaxPrefix("Error:");
                help.printHelp(" To correctly launch the application, you must enter one of the following options.", options);
            }
            if (engine != null) {
                engine.run();
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}








