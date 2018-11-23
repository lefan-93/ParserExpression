package parser;

import dao.ParserDao;
import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.ExpressionType;
import dto.RuleDto;
import exception.ParserException;
import model.*;

import java.util.*;

public class DBParser {

    private ParserDao dao;

    private IExpression AssembleExpression(ExpressionDto expressionDto) throws ParserException {
        if (expressionDto.getExpressionType() == ExpressionType.FACT) {
            return new FactExpression(expressionDto.getFact());
        }
        if (expressionDto.getExpressionType() != ExpressionType.AND && expressionDto.getExpressionType() != ExpressionType.OR)
            throw new ParserException("Wrong format of rule", expressionDto.getExpression_id());
        List<IExpression> expressions = new ArrayList<>();
        for (ExpressionDto child : dao.getChildren(expressionDto.getExpression_id())) {
            expressions.add(AssembleExpression(child));
        }
        return expressionDto.getExpressionType() == ExpressionType.AND ? new AndExpression(expressions) : new OrExpression(expressions);
    }

    public Model parseDataBase(String propertiesFile) throws Exception {

        dao = new ParserDaoImpl(propertiesFile);
        ArrayList<RuleDto> rulesDto = dao.getRules();
        LinkedList<Rule> rules = new LinkedList<>();
        HashSet<String> facts = new HashSet<>(dao.getKnownFacts());
        for (RuleDto ruleDto : rulesDto) {
            ExpressionDto expressionDto = dao.getExpression(ruleDto.getExpression_id());
            rules.add(new Rule(AssembleExpression(expressionDto), ruleDto.getFact()));
        }
        return new Model(rules, facts);
    }
}
