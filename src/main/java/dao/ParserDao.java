package dao;

import dto.ExpressionDto;
import dto.RuleDto;

import java.awt.*;
import java.util.ArrayList;

public interface ParserDao {

    ArrayList<RuleDto> getRules();

    ArrayList<String> getKnownFacts();

    ExpressionDto getExpression(ExpressionDto expressionDto);

    ArrayList<ExpressionDto> getChildren(ExpressionDto expressionDto);

    void insertExpression(ExpressionDto expressionDto);

    void insertChildExpressions(Integer parent_id, Integer child_id);

    void insertKnownFact(String fact);

    void insertRule(Integer expression_id, String fact);


}
