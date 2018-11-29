package factory;

import parser.DBParser;
import parser.IParser;

public class DBModelParser extends ModelParser {

    @Override
    public IParser createParser() {
        return new DBParser();
    }
}
