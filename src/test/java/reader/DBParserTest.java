package reader;

import dao.ParserDao;
import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.ExpressionType;
import dto.RuleDto;
import model.FactExpression;
import model.IExpression;
import model.Model;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;

import javax.swing.text.html.parser.Parser;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.fail;


public class DBParserTest {

    @Test
    public void ruleMapperTest() throws Exception {
        ArrayList<RuleDto> rulesDto = new ArrayList<>();
        ParserDao dao = new ParserDaoImpl("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
        ArrayList<RuleDto> rules = dao.getRules();
        assertEquals(1, (int) rules.get(0).expression.expression_id);
        assertEquals(6, (int) rules.get(1).expression.expression_id);
        assertEquals("E", rules.get(0).fact);
        assertEquals("C", rules.get(1).fact);
    }

    @Test
    public void factMapperTest() throws Exception {
        ParserDao dao = new ParserDaoImpl("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
        ArrayList<String> facts = dao.getKnownFacts();
        assertEquals("A", facts.get(0));
        assertEquals("D", facts.get(1));
    }

    @Test
    public void ExpressionMapperTest() throws Exception {
        ParserDao dao = new ParserDaoImpl("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
        ExpressionDto expressionDto = new ExpressionDto();
        expressionDto.expression_id = 3;
        expressionDto.expression_type= ExpressionType.FACT;
        expressionDto.fact="A";
        assertEquals("A", dao.getExpression(expressionDto).fact);
    }

    @Test
    public void ExpressionMapperTest1() throws Exception {
        ParserDao dao = new ParserDaoImpl("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
        ExpressionDto expressionDto = new ExpressionDto();
        expressionDto.expression_id = 2;
        expressionDto.expression_type= ExpressionType.AND;
        ArrayList<ExpressionDto> children = dao.getChildren(expressionDto);
        assertEquals("A,B", children.get(0).fact + "," + children.get(1).fact);
    }
    @Test
    public void trueRecordFileTest4() {
        Set<String> testAnswer = new HashSet<>();
        testAnswer.add("A");
        testAnswer.add("D");
        try {
            IParser parser = new DBParser();
            Model model1 = parser.parse("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
            Field fieldModel1Rules = model1.getClass().getDeclaredField("facts");
            fieldModel1Rules.setAccessible(true);
            Set<String> rules = (Set<String>) fieldModel1Rules.get(model1);
            String s = rules.toString();
            String b = testAnswer.toString();
            assertEquals(s, b);
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
        answer.add("C");
        answer.add("D");
        answer.add("E");
        try {
            IParser parser = new DBParser();
            Model model =  parser.parse("target/test_resources/db_parser_rule_mapper_tests/postgresql_mapper_tests.jdbc.properties");
            model.evaluate();
            List<String> answerCalculate = new ArrayList<>(model.getFacts());
            Assert.assertEquals(answer, answerCalculate);
        } catch (Exception ex) {
            ex.getMessage();
            ex.printStackTrace();
            fail();
        }
    }
}
