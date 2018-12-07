package model;

import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;
import java.util.Set;

@XmlRootElement(name = "Or")
public class OrExpression implements IExpression {
    @XmlAnyElement(lax = true)
    private List<IExpression> operands;

    public OrExpression(List<IExpression> operands) {
        this.operands = operands;
    }

    //This constructor is required for JAXB to work correctly.
    public OrExpression() {
    }

    @Override
    public List<IExpression> getOperands() {
        return operands;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrExpression that = (OrExpression) o;

        return operands != null ? operands.equals(that.operands) : that.operands == null;
    }

    @Override
    public int hashCode() {
        return operands != null ? operands.hashCode() : 0;
    }
}
