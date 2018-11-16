package model;

import java.util.List;
import java.util.Set;

public class OrExpression implements IExpression {

    private List<IExpression> operands;

    public OrExpression(List<IExpression> operands) {
        this.operands = operands;
    }

    @Override
    public boolean evaluate(Set<String> facts) {

        for (IExpression operand : operands) {
            if (operand.evaluate(facts)) {
                return true;
            }
        }

        return false;
    }
}
