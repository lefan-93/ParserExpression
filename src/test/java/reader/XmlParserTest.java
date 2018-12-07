package reader;

import model.Model;
import org.junit.Test;
import parser.TextParser;
import parser.XmlParser;

import javax.xml.bind.UnmarshalException;
import javax.xml.bind.annotation.XmlElement;

import java.lang.reflect.Field;
import java.util.HashSet;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;
import static junit.framework.TestCase.fail;

public class XmlParserTest {
    private XmlParser parser = new XmlParser();

    @Test
    public void wrongXmlFileTest() throws Exception {
        try {
            parser.parse("target/test_resources/xml_parser_tests/test_two_expressions_in_rule.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("Invalid content"));
        }
        try {
            parser.parse("target/test_resources/xml_parser_tests/wrong_format_of_fact_in_expression.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("is not facet-valid with respect to pattern"));
        }
        try {
            parser.parse("target/test_resources/xml_parser_tests/wrong_format_of_fact_in_rule.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("is not facet-valid with respect to pattern"));
        }
        try {
            parser.parse("target/test_resources/xml_parser_tests/wrong_format_of_fact_in_facts.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("is not facet-valid with respect to pattern"));
        }
        try {
            parser.parse("target/test_resources/xml_parser_tests/missing_rules_tag_test.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("One of '{Rules}' is expected."));
        }
        try {
            parser.parse("target/test_resources/xml_parser_tests/missing_rule_tag_test.xml");
            fail();
        } catch (UnmarshalException e) {
            assertTrue(e.getLinkedException().getMessage().contains("One of '{Rule}' is expected."));
        }
    }

    @Test
    public void trueFormatFileTest1() {
        Set<String> testAnswer = new HashSet<>();
        String answer1 = "A";
        String answer2 = "S";
        testAnswer.add(answer1);
        testAnswer.add(answer2);
        try {
            XmlParser xmlParser = new XmlParser();
            Model model1 = xmlParser.parse("target/test_resources/xml_parser_tests/true_format_xml_test.xml");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> facts = (Set<String>) fieldModel1Rules.get(model1);
            String a = facts.toString();
            String b = testAnswer.toString();
            assertEquals(a, b);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void trueFormatFileTest2() {
        Set<String> testAnswer = new HashSet<>();
        String answer1 = "AbcD12";
        String answer2 = "S_o_S";
        testAnswer.add(answer1);
        testAnswer.add(answer2);
        try {
            XmlParser xmlParser = new XmlParser();
            Model model1 = xmlParser.parse("target/test_resources/xml_parser_tests/true_format_xml_test2.xml");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> facts = (Set<String>) fieldModel1Rules.get(model1);
            String a = facts.toString();
            String b = testAnswer.toString();
            assertEquals(a, b);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }

}
