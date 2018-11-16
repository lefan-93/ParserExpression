package model;

import java.util.Set;

public class FactExpression implements IExpression {
    private String fact;

    public FactExpression(String fact) {

        this.fact = fact;
    }


    @Override
    public boolean evaluate(Set<String> facts) {
        return facts.contains(fact);

    }
}
