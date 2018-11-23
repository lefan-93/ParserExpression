package reader;

import dao.ParserDao;
import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.RuleDto;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.TestCase.assertEquals;


public class DBParserTest {
    @Test
    public void ruleMapperTest() throws Exception {
        ArrayList<RuleDto> rulesDto = new ArrayList<>();
        ParserDao dao = new ParserDaoImpl("postgresql.jdbc.properties");
        ArrayList<RuleDto> rules = dao.getRules();
        assertEquals(1, (int) rules.get(0).getExpression_id());
        assertEquals(2, (int) rules.get(1).getExpression_id());
        assertEquals("C", rules.get(0).getFact());
        assertEquals("B", rules.get(1).getFact());
    }

    @Test
    public void factMapperTest() throws Exception {
        ParserDao dao = new ParserDaoImpl("postgresql.jdbc.properties");
        ArrayList<String> facts = dao.getKnownFacts();
        assertEquals("A",facts.get(0));
        assertEquals("B", facts.get(1));
    }

    @Test
    public void ExpressionMapperTest() throws Exception {
        ParserDao dao = new ParserDaoImpl("postgresql.jdbc.properties");
        ExpressionDto expr = dao.getExpression(2);
        assertEquals("A",expr.getFact());
    }


}
