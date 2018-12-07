package converter;

import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.ExpressionType;
import model.*;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;
import parser.XmlParser;

import java.util.List;

public class XmlToDBConverter implements IConverter {

    private ParserDaoImpl dao;

    private Integer insertExpression(IExpression expression) {
        ExpressionDto expressionDto = new ExpressionDto();
        List<IExpression> operands = expression.getOperands();
        if (operands.size() == 1) {
            expressionDto.fact = ((FactExpression) operands.get(0)).getFact();
            expressionDto.expression_type = ExpressionType.FACT;
            dao.insertExpression(expressionDto);
            return expressionDto.expression_id;
        }
        if (operands.size() > 1) {
            if (expression instanceof AndExpression)
                expressionDto.expression_type = ExpressionType.AND;
            else {
                expressionDto.expression_type = ExpressionType.OR;
            }
            expressionDto.fact = null;
            dao.insertExpression(expressionDto);
            for (IExpression operand : expression.getOperands()) {
                dao.insertChildExpressions(expressionDto.expression_id, insertExpression(operand));
            }
        }
        return expressionDto.expression_id;
    }

    @Override
    public boolean convert(String pathFromFile, String pathToFile) throws Exception {
        IParser parser = new XmlParser();
        Model model = parser.parse(pathFromFile);
        dao = new ParserDaoImpl(pathToFile);
        for (Rule rule : model.getRules()) {
            dao.insertRule(insertExpression(rule.getExpression()), rule.getFact());
        }
        for (String fact : model.getFacts()) {
            dao.insertKnownFact(fact);
        }
        parser = new DBParser();
        Model dbModel = parser.parse(pathToFile);
        return model.equals(dbModel);
    }
}
