package dto;

public class ExpressionDto {

    private Integer expression_id;
    private ExpressionType expression_type;
    private String fact;

    public Integer getExpression_id() {
        return expression_id;
    }

    public ExpressionType getExpressionType() {
        return expression_type;
    }

    public String getFact() {
        return fact;
    }
}
