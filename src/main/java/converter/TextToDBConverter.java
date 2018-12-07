package converter;

import daoImplement.ParserDaoImpl;
import dto.ExpressionDto;
import dto.ExpressionType;
import model.*;
import parser.DBParser;
import parser.IParser;
import parser.TextParser;

import java.util.*;

public class TextToDBConverter implements IConverter {

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
    public boolean convert(String pathFile, String propertiesFile) throws Exception {
        IParser parser = new TextParser();
        Model model = parser.parse(pathFile);
        dao = new ParserDaoImpl(propertiesFile);
        for (Rule rule : model.getRules()) {
            dao.insertRule(insertExpression(rule.getExpression()), rule.getFact());
        }
        for (String fact : model.getFacts()) {
            dao.insertKnownFact(fact);
        }
        parser = new DBParser();
        Model dbModel = parser.parse(propertiesFile);
        return model.equals(dbModel);
    }


}
