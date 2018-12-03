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

    public IExpression getExpression() {
        return expression;
    }

    public String getFact() {
        return fact;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule rule = (Rule) o;

        if (expression != null ? !expression.equals(rule.expression) : rule.expression != null) return false;
        return fact != null ? fact.equals(rule.fact) : rule.fact == null;
    }

    @Override
    public int hashCode() {
        int result = expression != null ? expression.hashCode() : 0;
        result = 31 * result + (fact != null ? fact.hashCode() : 0);
        return result;
    }
}
