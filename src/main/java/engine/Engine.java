package engine;

import converter.IConverter;
import converter.TextToDBConverter;
import converter.TextToXmlConverter;
import converter.XmlToDBConverter;
import exception.EngineException;
import interconnection.Interconnection;
import model.Model;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;
import parser.XmlParser;

import javax.swing.*;
import javax.xml.bind.JAXBException;

public class Engine {

    private Interconnection interconnection;
    private String path;
    private String targetPath;
    private Mode mode = Mode.NULL;

    public enum Mode {
        NULL,
        TEXT,
        XML,
        DATABASE,
        TEXT_TO_DATABASE,
        XML_TO_DATABASE,
        TEXT_TO_XML
    }

    public Engine(Interconnection interconnection, Mode mode, String path) throws EngineException {
        switch (mode) {
            case TEXT_TO_DATABASE:
                throw new EngineException("wrong mode");
            case TEXT_TO_XML:
                throw new EngineException("wrong mode");
            case XML_TO_DATABASE:
                throw new EngineException("wrong mode");
        }
        this.interconnection = interconnection;
        this.mode = mode;
        this.path = path;
    }

    public Engine(Interconnection interconnection, Mode mode, String path, String targetPath) throws EngineException {
        switch (mode) {
            case TEXT:
                throw new EngineException("wrong mode");
            case XML:
                throw new EngineException("wrong mode");
            case DATABASE:
                throw new EngineException("wrong mode");
        }
        this.interconnection = interconnection;
        this.mode = mode;
        this.path = path;
        this.targetPath = targetPath;
    }

    public void run() {
        try {
            switch (mode) {
                case NULL:
                    interconnection.showErrorMessage("Unknown error");
                    break;
                case TEXT:
                    parseAndEvaluate(new TextParser(), path);
                    break;
                case XML:
                    parseAndEvaluate(new XmlParser(), path);
                    break;
                case DATABASE:
                    parseAndEvaluate(new DBParser(), path);
                    break;
                case TEXT_TO_XML:
                    convert(new TextToXmlConverter(), path, targetPath);
                    break;
                case XML_TO_DATABASE:
                    convert(new XmlToDBConverter(), path, targetPath);
                    break;
                case TEXT_TO_DATABASE:
                    convert(new TextToDBConverter(), path, targetPath);
                    break;
            }

        } catch (JAXBException e) {
            interconnection.showErrorMessage(e.getLinkedException().getMessage());
        } catch (Exception e) {
            interconnection.showErrorMessage(e.getMessage());
        }

    }

    private void parseAndEvaluate(IParser parser, String path) throws Exception {
        Model model = parser.parse(path);
        model.evaluate();
        interconnection.showFacts(model.getFacts());

    }

    private void convert(IConverter converter, String path, String targetPath) throws Exception {
        converter.convert(path, targetPath);
        interconnection.showMessage("Conversion successful");

    }
}
