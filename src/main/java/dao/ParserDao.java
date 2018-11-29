package dao;

import dto.ExpressionDto;
import dto.RuleDto;

import java.util.ArrayList;

public interface ParserDao {

    ArrayList<RuleDto> getRules();

    ArrayList<String> getKnownFacts();

    ExpressionDto getExpression(Integer id);

    ArrayList<ExpressionDto> getChildren(Integer id);


}
