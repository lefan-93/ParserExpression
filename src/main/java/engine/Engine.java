package engine;

import inserter.DBInserter;
import interconnection.Interconnection;
import model.Model;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;

public class Engine {

    private Interconnection interconnection;
    private IParser parser;

    public Engine(Interconnection interconnection) {
        this.interconnection = interconnection;
    }

    public void evaluate(char mode, String path) {
        try {
            if (mode == 'f') {
                parser = new TextParser();

            } else if (mode == 'b') {
                parser = new DBParser();
            }
            Model model = parser.parse(path);
            model.evaluate();
            interconnection.showFacts(model.getFacts());
        } catch (Exception e) {
            interconnection.showMessage(e.getMessage());
        }
    }

    public void insertDataBase(String txtFilePath, String dbProperties) {
        DBInserter dbInserter = new DBInserter();
        try {
            if (dbInserter.insert(txtFilePath, dbProperties)) {
                interconnection.showMessage("Database write success");
            }
        } catch (Exception e) {
            interconnection.showMessage(e.getMessage());
        }
    }
}
