package mapper;

import dto.ExpressionDto;
import model.IExpression;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface ExpressionMapper {

    ExpressionDto getExpression(ExpressionDto expressionDto);

    ArrayList<ExpressionDto> getChildren( ExpressionDto expressionDto);

    void insertExpression(ExpressionDto expressionDto);

    void insertChildExpressions(@Param("parent_id") Integer parent_id, @Param("child_id") Integer child_id);
}
