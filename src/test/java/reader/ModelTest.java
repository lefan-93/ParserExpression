package reader;

import model.Model;
import org.junit.Test;
import parser.TextParser;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.*;

public class ModelTest {

    @Test
    public void calculateTest1() {
        Set<String> answer = new HashSet<>();
        answer.add("TestFact1");
        answer.add("A");
        answer.add("B");
        answer.add("C");
        answer.add("D");
        try {
            TextParser textParser = new TextParser();
            Model model = textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest1");
            model.evaluate();
            Set<String> answerCalculate = new HashSet<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();

        }
    }
    @Test
    public void calculateTest2() {
        List<String> answer = new ArrayList<>();
        answer.add("TestFact1");
        answer.add("TestFact2");
        try {
            TextParser textParser = new TextParser();
            Model model =  textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest2");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void proccesingTest3() {
        List<String> answer = new ArrayList<>();
        answer.add("TestFact1");
        answer.add("TestFact3");
        try {
            TextParser textParser = new TextParser();
            Model model =  textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest3");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void proccesingTest4() {
        List<String> answer = new ArrayList<>();
        answer.add("A");
        answer.add("B");
        answer.add("F");
        try {
            TextParser textParser = new TextParser();
            Model model =  textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest4");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void proccesingTest5() {
        List<String> answer = new ArrayList<>();
        answer.add("A");
        answer.add("B");
        try {
            TextParser textParser = new TextParser();
            Model model =  textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest5");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
    @Test
    public void proccesingTest6() {
        List<String> answer = new ArrayList<>();
        answer.add("A");
        answer.add("B");
        answer.add("F");
        try {
            TextParser textParser = new TextParser();
            Model model =  textParser.parseFile("target/test_resources/model_Processing_Tests/modelProcessingTest6");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
}