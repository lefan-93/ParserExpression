package factory;

import model.Model;
import parser.IParser;

import java.util.Set;

public abstract class ModelParser {

    public Set<String> evaluate(String parameter) throws Exception {
        IParser parser = createParser();
        Model model = parser.parse(parameter);
        model.evaluate();
        return model.getFacts();
    }

    public abstract IParser createParser();
}
