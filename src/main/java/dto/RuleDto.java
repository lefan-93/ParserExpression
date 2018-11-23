package dto;

public class RuleDto {

    private Integer rule_id;
    private Integer expression_id;
    private String fact;

    public Integer getExpression_id() {
        return expression_id;
    }

    public String getFact() {
        return fact;
    }
}
