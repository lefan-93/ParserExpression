package mainPackage;

import model.Model;
import org.apache.commons.cli.*;
import parser.DBParser;
import parser.TextParser;

import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        Option fileOption = new Option("f", "file", true, "file_path");
        Option dbOption = new Option("b", "database", true, "fileProperties");
        Options options = new Options();
        options.addOption(fileOption);
        options.addOption(dbOption);
        CommandLineParser lineParser = new DefaultParser();
        try {
            CommandLine cmd = lineParser.parse(options, args);
            Set<String> facts;
            if (cmd.hasOption("f")) {
                String[] arguments = cmd.getOptionValues("f");
                String path = arguments[0];
                TextParser parser = new TextParser();
                Model model = parser.parseFile(path);
                model.evaluate();
                facts = model.getFacts();
                Iterator itr = facts.iterator();
                // The  set of facts has been verified previously, so this method should not throw an exception.
                System.out.print(itr.next());
                while (itr.hasNext()) {
                    System.out.print("," + itr.next());
                }
            }
            if (cmd.hasOption("b")) {
                String[] arguments = cmd.getOptionValues("b");
                String property = arguments[0];
                DBParser dbparser = new DBParser();
                Model model = dbparser.parseDataBase(property);
                model.evaluate();
                facts = model.getFacts();
                Iterator itr = facts.iterator();
                // The  set of facts has been verified previously, so this method should not throw an exception.
                System.out.print(itr.next());
                while (itr.hasNext()) {
                    System.out.print("," + itr.next());

                }
            }
            else {
                System.out.print("File name must not be empty");
            }
        } catch (Exception e) {
            System.out.print(e.getMessage());
        }
    }
}






