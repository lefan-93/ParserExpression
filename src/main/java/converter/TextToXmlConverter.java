package converter;

import daoImplement.ParserDaoImpl;
import model.*;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;
import parser.XmlParser;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

public class TextToXmlConverter implements IConverter {

    @Override
    public boolean convert(String pathTextFile, String pathXmlFile) throws Exception {
        IParser parser = new TextParser();
        Model model = parser.parse(pathTextFile);
        File file = new File(pathXmlFile);
        JAXBContext context = JAXBContext.newInstance(Model.class,Rule.class, AndExpression.class, OrExpression.class, FactExpression.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        marshaller.setProperty(Marshaller.JAXB_SCHEMA_LOCATION, "http://www.pooh.com/model/XMLschema rulesSchema.xsd");
        marshaller.marshal(model, file);
        parser = new XmlParser();
        Model dbModel = parser.parse(pathXmlFile);
        return model.equals(dbModel);
    }
}
