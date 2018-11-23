package mapper;

import dto.ExpressionDto;
import model.IExpression;

import java.util.ArrayList;

public interface ExpressionMapper {

    ExpressionDto getExpression(Integer id);

    ArrayList<ExpressionDto> getChildren(Integer id);
}
