package reader;


import model.*;
import org.junit.Test;
import parser.TextParser;

import java.lang.reflect.Field;
import java.util.*;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;

public class TextParserTest {

    private TextParser textParser = new TextParser();

    @Test
    public void fileNotExistTest() {
        String test = "target\\test_resources\\null (Не удается найти указанный файл)";
        try {
            textParser.parseFile("target/test_resources/null");
            fail();
        } catch (Exception ex) {
            assertEquals(test, ex.getMessage());
        }

    }

    @Test
    public void emptyFileParsingTest() {

        try {
            textParser.parseFile("target/test_resources/empty_files_and_lines_Tests/emptyFileParsingTest");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of file", ex.getMessage());

        }
    }

    @Test
    public void wrongSeparatedRulesAndFactsTest() {
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrongSeparatedRulesAndFactsTest1");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of rule in line 2", ex.getMessage());

        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrongSeparatedRulesAndFactsTest2");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of fact in line 3", ex.getMessage());
        }
    }

    @Test
    public void wrongRecordOfRulesTest() {
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Rule_Format");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of rule in line 1", ex.getMessage());

        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Rule_Format_Test1");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of rule in line 1", ex.getMessage());

        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test7");
            fail();

        } catch (Exception ex) {
            assertEquals("The closing and opening brackets do not match in line 1", ex.getMessage());

        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Rule_Format_Test3");
            fail();

        } catch (Exception ex) {
            assertEquals("The closing and opening brackets do not match in line 1", ex.getMessage());

        }
    }

    @Test
    public void wrongRecordOfFactsTest() {
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Rule_Format_Test2");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of fact in line 1", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test1");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of fact in line 1 (pos: 2)", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test2");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of fact in line 1 (pos: 2)", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test3");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of fact in line 1 (pos: 2)", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test4");
            fail();

        } catch (Exception ex) {
            assertEquals("Wrong format of operator in line 1 (pos: 8)", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test5");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of file", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test6");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of the beginning of the fact in line 1 (pos: 11)", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test8");
            fail();
        } catch (Exception ex) {
            assertEquals("Unexpected ending of expression in line 1", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Fact_Format_Test9");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format record of facts", ex.getMessage());
        }
        try {
            textParser.parseFile("target/test_resources/wrong_Format_Tests/wrong_Rule_Format_Test4");
            fail();
        } catch (Exception ex) {
            assertEquals("Wrong format of operator in line 1 (pos: 8)", ex.getMessage());
        }
    }

    @Test
    public void trueRecordFileTest() {
        Set<String> testAnswer = new HashSet<>();
        String answer = "TestFact1";
        testAnswer.add(answer);
        try {
            TextParser textParser = new TextParser();
            Model model1 = textParser.parseFile("target/test_resources/true_Format_File_Tests/trueRecordFileTest");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String a = rules.toString();
            String b = testAnswer.toString();
            assertEquals(a, b);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void trueRecordFileTest2() {
        Set<String> testAnswer = new HashSet<>();
        String answer = "TestFact1";
        testAnswer.add(answer);
        try {
            TextParser textParser = new TextParser();
            Model model1 = textParser.parseFile("target/test_resources/true_Format_File_Tests/trueRecordFileTest2");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String a = rules.toString();
            String b = testAnswer.toString();
            assertEquals(a, b);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void trueRecordFileTest3() {
        Set<String> testAnswer = new HashSet<>();
        String answer = "TestFact1";
        testAnswer.add(answer);
        try {
            TextParser textParser = new TextParser();
            Model model1 = textParser.parseFile("target/test_resources/empty_files_and_lines_Tests/emptyMiddleLineTest");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String a = rules.toString();
            String b = testAnswer.toString();
            assertEquals(b, a);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void trueRecordFileTest4() {
        Set<String> testAnswer = new HashSet<>();
        String answer = "TestFact1";
        testAnswer.add(answer);
        try {
            TextParser textParser = new TextParser();
            Model model1 = textParser.parseFile("target/test_resources/empty_files_and_lines_Tests/emptyFirstLineTest");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String a = rules.toString();
            String b = testAnswer.toString();
            assertEquals(b, a);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }

    @Test
    public void trueRecordFileTest5() {
        Set<String> testAnswer = new HashSet<>();
        String answer = "TestFact1";
        testAnswer.add(answer);
        try {
            TextParser textParser = new TextParser();
            Model model1 = textParser.parseFile("target/test_resources/true_Format_File_Tests/trueRecordFileTest3");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String a = rules.toString();
            String b = testAnswer.toString();
            assertEquals(b, a);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }


}