package model;

import java.util.List;
import java.util.Set;

public class AndExpression implements IExpression {
    private List<IExpression> operands;

    public AndExpression(List<IExpression> operands) {
        this.operands = operands;
    }

    @Override
    public List<IExpression> getOperands() {
        return operands;
    }

    @Override
    public boolean evaluate(Set<String> facts) {
        for (IExpression operand : operands) {
            if (!operand.evaluate(facts)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AndExpression that = (AndExpression) o;

        return operands != null ? operands.equals(that.operands) : that.operands == null;
    }

    @Override
    public int hashCode() {
        return operands != null ? operands.hashCode() : 0;
    }
}

