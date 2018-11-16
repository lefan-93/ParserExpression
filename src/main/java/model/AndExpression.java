package model;

import java.util.List;
import java.util.Set;

public class AndExpression implements IExpression {
    private List<IExpression> operands;

    public AndExpression( List<IExpression> operands) {
        this.operands = operands;
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
}

