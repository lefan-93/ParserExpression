package parser;

import exception.ParserException;
import model.Model;

import java.io.IOException;

public interface IParser {

    Model parse(String path)  throws Exception;
}
