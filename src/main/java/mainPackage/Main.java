package mainPackage;

import model.Model;
import parser.TextParser;

import java.util.Iterator;
import java.util.Set;

public class Main {

    public static void main(String[] args) {
        if (args.length == 0 || args[0].equals("")) {
            System.out.print("File name must not be empty");
            return;
        }

        try {
            Set<String> facts;
            String path = args[0];
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
        } catch (Exception e) {
           System.out.print(e.getMessage());
        }
    }

}






