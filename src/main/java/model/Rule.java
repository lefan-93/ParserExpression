package model;


import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Set;

@XmlRootElement(name = "Rule")
public class Rule {
    @XmlAnyElement(lax = true)
    private IExpression expression;
    @XmlAttribute(name = "resultFact")
    private String fact;

    public Rule(IExpression expression, String fact) {
        this.expression = expression;
        this.fact = fact;
    }

    //This constructor is required for JAXB to work correctly.
    public Rule(){}

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
