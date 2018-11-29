package factory;

import factory.ModelParser;
import parser.IParser;
import parser.TextParser;

public class TextModelParser extends ModelParser {

    @Override
    public IParser createParser() {
        return new TextParser();
    }
}
