package parser;

import dao.ParserDao;
import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.ExpressionType;
import dto.RuleDto;
import exception.ParserException;
import model.*;

import java.util.*;

public class DBParser implements IParser {

    private ParserDao dao;

    private IExpression AssembleExpression(ExpressionDto expressionDto) throws ParserException {
        if (expressionDto.expression_type == ExpressionType.FACT) {
            return new FactExpression(expressionDto.fact);
        }
        List<IExpression> expressions = new ArrayList<>();
        for (ExpressionDto child : dao.getChildren(expressionDto)) {
            expressions.add(AssembleExpression(child));
        }
        return expressionDto.expression_type == ExpressionType.AND ? new AndExpression(expressions) : new OrExpression(expressions);
    }

    @Override
    public Model parse(String propertiesFile) throws Exception {

        dao = new ParserDaoImpl(propertiesFile);
        ArrayList<RuleDto> rulesDto = dao.getRules();
        LinkedList<Rule> rules = new LinkedList<>();
        HashSet<String> facts = new HashSet<>(dao.getKnownFacts());
        for (RuleDto ruleDto : rulesDto) {
            ExpressionDto expressionDto = dao.getExpression(ruleDto.expression);
            rules.add(new Rule(AssembleExpression(expressionDto), ruleDto.fact));
        }
        return new Model(rules, facts);
    }
}
