package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class FactExpression implements IExpression {
    private String fact;

    public FactExpression(String fact) {
        this.fact = fact;
    }

    public String getFact() {
        return fact;
    }

    @Override
    public boolean evaluate(Set<String> facts) {
        return facts.contains(fact);

    }

    @Override
    public List<IExpression> getOperands() {
        List<IExpression> operands = new ArrayList<>();
        operands.add(new FactExpression(fact));
        return operands;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FactExpression that = (FactExpression) o;

        return fact != null ? fact.equals(that.fact) : that.fact == null;
    }

    @Override
    public int hashCode() {
        return fact != null ? fact.hashCode() : 0;
    }
}
