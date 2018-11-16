package model;


import java.util.Set;

public class Rule {

    private IExpression expression;
    private String fact;

    public Rule(IExpression expression, String fact) {
        this.expression = expression;
        this.fact = fact;
    }

    public String evaluate(Set<String> facts) {
        if (expression.evaluate(facts))
            return fact;
        return null;

    }
}
