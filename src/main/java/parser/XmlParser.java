package parser;


import exception.ParserException;
import model.*;
import org.xml.sax.SAXException;

import javax.swing.text.html.parser.Parser;
import javax.xml.XMLConstants;
import javax.xml.bind.*;
import javax.xml.bind.util.JAXBSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.*;
import java.net.URL;


public class XmlParser implements IParser {

    @Override
    public Model parse(String path) throws SAXException, JAXBException {

        URL file = ClassLoader.getSystemResource("rulesSchema.xsd");
        JAXBContext context = JAXBContext.newInstance(Model.class, Rule.class, AndExpression.class, OrExpression.class, FactExpression.class);
        SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
        Schema schema = sf.newSchema(file);
        Unmarshaller unmarshaller = context.createUnmarshaller();
        unmarshaller.setSchema(schema);
        return (Model) unmarshaller.unmarshal(new File(path));
    }

}
